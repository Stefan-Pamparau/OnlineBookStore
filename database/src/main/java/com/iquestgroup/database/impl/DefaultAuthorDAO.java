package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.model.Author;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorDAO implements AuthorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> resultList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List authorList = session.createQuery("FROM com.iquestgroup.model.Author").list();
            for (Object author : authorList) {
                resultList.add((Author) author);
            }
        } catch (HibernateError e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public Author getAuthorByID(Integer authorID) {
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
            e.printStackTrace();
        }

        return author;
    }

    @Override
    public void insertAuthor(Author author) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAuthor(Integer authorID) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Author author = session.get(Author.class, authorID);
            session.delete(author);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
