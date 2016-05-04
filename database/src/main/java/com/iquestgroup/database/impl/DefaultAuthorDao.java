package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;

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
public class DefaultAuthorDao implements AuthorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> getAllAuthors() throws DaoException {
        List<Author> resultList = null;

        try (Session session = sessionFactory.openSession()) {
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author").list();

            if (authorList != null && authorList.size() > 0) {
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
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + pattern + "%'")
                    .list();

            if (authorList != null && authorList.size() > 0) {
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
            Author persistentAuthor = session.get(Author.class, author.getId());

            if (persistentAuthor != null) {
                persistentAuthor.setName(author.getName());
                persistentAuthor.setAge(author.getAge());

                session.save(persistentAuthor);
                transaction.commit();
            } else {
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
            Author author = session.get(Author.class, authorID);

            if (author == null) {
                return "Author with id: " + authorID + " does not exist in the database!";
            } else {
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
