package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the AuthorDao interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultAuthorDao extends AbstractDao implements AuthorDao {

    private static final Logger logger = Logger.getLogger(DefaultAuthorDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> getAllAuthors() throws DaoException {
        List<Author> resultList = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying database for authors");
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author").list();

            if (authorList != null && !authorList.isEmpty()) {
                logger.debug(getLogPrefix() + "Database contains authors. Inserting them in result list");

                resultList = new ArrayList<>();
                for (Object author : authorList) {
                    resultList.add((Author) author);
                }
            }
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve all authors from the database", e);
        }

        return resultList;
    }

    @Override
    public Author getAuthorByID(Integer authorID) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Trying to query the database for the author with id: " + authorID);
            return session.get(Author.class, authorID);
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve the author with the id "
                    + authorID + "from the database", e);
        }
    }

    @Override
    public List<Author> getAuthorByNamePattern(String pattern) throws DaoException {
        List<Author> result = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying database for authors whose names match the pattern: " + pattern);
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + pattern + "%'")
                    .list();

            if (authorList != null && authorList.size() > 0) {
                logger.debug(getLogPrefix() + "Found authors whose names match the pattern: " + pattern);
                result = new ArrayList<>();
                for (Object object : authorList) {
                    result.add((Author) object);
                }
            }
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve author by pattern.", e);
        }

        return result;
    }

    @Override
    public String insertAuthor(Author author) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Inserting author: " + author);
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("An error occurred while trying to insert " + author, e);
        }

        return "Author with name " + author.getName() + " successfully inserted!";
    }

    @Override
    public String updateAuthor(Author author) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            logger.debug(getLogPrefix() + "Querying the database for the author with id: " + author.getId());
            Author persistentAuthor = session.get(Author.class, author.getId());

            if (persistentAuthor != null) {
                logger.debug(getLogPrefix() + "Author with id: " + author.getId() + " exists in the database. Updating him");
                persistentAuthor.setName(author.getName());
                persistentAuthor.setAge(author.getAge());

                session.save(persistentAuthor);
                transaction.commit();
            } else {
                logger.debug(getLogPrefix() + "Author with id: " + author.getId() + " does not exist in the database");
                return "Cannot updated author. Author does not exist in the database.";
            }

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("An error occurred while trying to update author.", e);
        }

        return "Author with name " + author.getName() + " successfully updated!";
    }

    @Override
    public String deleteAuthor(Integer authorID) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            logger.debug(getLogPrefix() + "Querying the database for the author with id: " + authorID);
            Author author = session.get(Author.class, authorID);

            if (author == null) {
                logger.debug(getLogPrefix() + "Author with id: " + authorID + " does not exist in the database");
                return "Author with id: " + authorID + " does not exist in the database!";
            } else {
                logger.debug(getLogPrefix() + "Author with id: " + author.getId() + " exists in the database. Deleting him");
                session.delete(author);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException(
                    "An error occurred while trying to delete author with id: " + authorID + " from the database", e);
        }

        return "Author with id: " + authorID + " successfully deleted from the database!";
    }
}
