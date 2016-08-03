package com.iquestgroup.database.impl;

import com.iquestgroup.database.BookDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(DefaultBookDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Book> getAllBooks() throws DaoException {
        List<Book> resultList = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for book instances");
            List books =
                    session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author").list();

            if (books != null && !books.isEmpty()) {
                logger.debug("Database contains book instances. Inserting them in result list");
                resultList = new ArrayList<>(books);
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
            logger.debug("Querying the database for book instances whose title matches the pattern: " + pattern);
            List books = session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + pattern + "'%")
                    .list();

            if (books != null && !books.isEmpty()) {
                logger.debug("Database contains book instances whose name match the pattern: "
                        + pattern + ".Inserting them in result list");
                result = new ArrayList<>(books);
            }

        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all books by pattern", e);
        }

        return result;
    }

    @Override
    public Book getBookById(Integer bookId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for book with id: " + bookId);
            return session.get(Book.class, bookId);
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve book by id.", e);
        }
    }

    @Override
    public List<Book> getBooksOfAuthor(Author author) throws DaoException {
        List<Book> result = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for author with id: " + author.getId());
            Author persistentAuthor = session.get(Author.class, author.getId());

            if (persistentAuthor != null) {
                logger.debug("Author with id: " + author.getId() + " exists in the database." +
                        "Retrieving his books");
                if (persistentAuthor.getBooks() != null) {
                    result = new ArrayList<>(persistentAuthor.getBooks());
                }
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
            logger.debug("Inserting book: " + book);
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

            logger.debug("Querying the database for book with id: " + bookID);
            Book book = session.get(Book.class, bookID);

            if (book == null) {
                logger.debug("Book with id: " + bookID + " does not exists in the database");
                return "Book with id " + bookID + " does not exist in the database!";
            } else {
                logger.debug("Book with id: " + bookID + ". Deleting it");
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

            logger.debug("Querying the database for book with id: " + book.getId());
            Book persistentBook = session.get(Book.class, book.getId());

            if (persistentBook != null) {
                logger.debug("Book with id: " + book.getId() + " exists in the database. Updating them");
                persistentBook.setGenre(book.getGenre());
                persistentBook.setTitle(book.getTitle());
                persistentBook.setInStock(book.getInStock());
                persistentBook.setPrice(book.getPrice());
                session.update(persistentBook);
                transaction.commit();
            } else {
                logger.debug("Book with id: " + book.getId() + " does not exist in the database");
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

        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for book with id: " + bookID);
            Book book = session.get(Book.class, bookID);

            if (book != null) {
                logger.debug("Book with id:" + bookID + " exists in the database." +
                        "Retrieving it's author");
                result = book.getAuthor();
            }
        } catch (HibernateException e) {
            throw new DaoException(
                    "An error occurred while trying to retrieve the book author from the book with the id " + bookID, e);
        }

        return result;
    }
}
