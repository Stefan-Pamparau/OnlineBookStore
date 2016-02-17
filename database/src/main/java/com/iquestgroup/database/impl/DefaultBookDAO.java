package com.iquestgroup.database.impl;

import com.iquestgroup.database.BookDAO;
import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultBookDAO implements BookDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> resultList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List books =
                session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author").list();
            for (Object book : books) {
                resultList.add((Book) book);
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to retrieve all the books from the database!", e);
        }

        return resultList;
    }

    @Override
    public String insertBook(Book book) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to insert " + book, e);
        }

        return "Book " + book + " successfully inserted into the database";
    }

    @Override
    public String deleteBook(Integer bookID) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);

            if (book == null) {
                return "Book with id " + bookID + " does not exist in the database!";
            } else {
                session.delete(book);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to delete book with id " + bookID, e);
        }

        return "Book with id " + bookID + " successfully deleted from the database!";
    }

    @Override
    public String updateBook(Book book) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to update book " + book, e);
        }

        return "Successfully updated book " + book;
    }

    @Override
    public Author getBookAuthor(Integer bookID) throws DAOException {
        Author result = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);
            result = book.getAuthor();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(
                "An error occurred while trying to retrieve the book author from the book with the id " + bookID, e);
        }

        return result;
    }
}
