package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorDAO implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> resultList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author").list();
            for (Object author : authorList) {
                resultList.add((Author) author);
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to retrieve all authors from the database", e);
        }

        return resultList;
    }

    @Override
    public Author getAuthorByID(Integer authorID) throws DAOException {
        Author author = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            author = session.get(Author.class, authorID);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException(
                "An error occurred while trying to retrieve the author with the id " + authorID + "from the database",
                e);
        }

        return author;
    }

    @Override
    public String insertAuthor(Author author) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("An error occurred while trying to insert " + author, e);
        }

        return "Author with name " + author.getName() + " successfully inserted!";
    }

    @Override
    public String deleteAuthor(Integer authorID) throws DAOException {
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
            throw new DAOException(
                "An error occurred while trying to delete author with id: " + authorID + " from the database", e);
        }

        return "Author with id: " + authorID + " successfully deleted from the database!";
    }
}
