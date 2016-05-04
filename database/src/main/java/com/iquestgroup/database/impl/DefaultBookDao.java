package com.iquestgroup.database.impl;

import com.iquestgroup.database.BookDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the BookDao interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultBookDao implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() throws DaoException {
        List<Book> resultList = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            List books =
                    session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author").list();
            for (Object book : books) {
                resultList.add((Book) book);
            }
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve all the books from the database!", e);
        }

        return resultList;
    }

    @Override
    public List<Book> getAllBooksByTitlePattern(String pattern) throws DaoException {
        List<Book> result = null;

        try (Session session = sessionFactory.openSession()) {
            List books = session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + pattern + "'%")
                    .list();

            if (books != null && books.size() > 0) {
                result = new ArrayList<>();
                for (Object book : books) {
                    result.add((Book) book);
                }
            }

        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all books by pattern", e);
        }

        return result;
    }

    @Override
    public Book getBookById(Integer bookId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, bookId);
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve book by id.", e);
        }
    }

    @Override
    public List<Book> getBooksOfAuthor(Author author) throws DaoException {
        List<Book> result = null;

        try (Session session = sessionFactory.openSession()) {
            Author persistentAuthor = session.get(Author.class, author.getId());

            if (persistentAuthor != null) {
                result = new ArrayList<>(persistentAuthor.getBooks());
            }
        } catch (HibernateException e) {
            throw new DaoException("Cannot get books of author", e);
        }

        return result;
    }

    @Override
    public String insertBook(Book book) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("An error occurred while trying to insert " + book, e);
        }

        return "Book " + book + " successfully inserted into the database";
    }

    @Override
    public String deleteBook(Integer bookID) throws DaoException {
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
            throw new DaoException("An error occurred while trying to delete book with id " + bookID, e);
        }

        return "Book with id " + bookID + " successfully deleted from the database!";
    }

    @Override
    public String updateBook(Book book) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book persistentBook = session.get(Book.class, book.getId());

            if (persistentBook != null) {
                persistentBook.setGenre(book.getGenre());
                persistentBook.setTitle(book.getTitle());
                persistentBook.setInStock(book.getInStock());
                persistentBook.setPrice(book.getPrice());
                session.update(persistentBook);
                transaction.commit();
            } else {
                return "Book does not exist in the database.";
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("An error occurred while trying to update book " + book, e);
        }

        return "Successfully updated book " + book;
    }

    @Override
    public Author getBookAuthor(Integer bookID) throws DaoException {
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
            throw new DaoException(
                    "An error occurred while trying to retrieve the book author from the book with the id " + bookID, e);
        }

        return result;
    }
}
