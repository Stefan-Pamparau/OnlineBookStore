package com.iquestgroup.database.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.model.Author;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void insertAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {

    }
}
