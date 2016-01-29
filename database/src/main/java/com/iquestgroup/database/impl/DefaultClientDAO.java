package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientDAO;
import com.iquestgroup.model.Book;
import com.iquestgroup.model.Client;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultClientDAO implements ClientDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Client> listAllClients() {
        List<Client> result = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List clientList = session.createQuery("FROM com.iquestgroup.model.Client").list();
            for (Object client : clientList) {
                Client resultClient = (Client) client;
                Hibernate.initialize(resultClient.getPurchases());
                for (Book book : resultClient.getBooks()) {
                    Hibernate.initialize(book.getAuthor());
                }
                result.add(resultClient);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return result;
    }

    @Override
    public void insertClient(Client client) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteClient(Integer clientID) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientID);
            session.delete(client);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void purchaseBook(Integer clientID, Integer bookID) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);
            Client client = session.get(Client.class, clientID);

            if (book.getInStock() > 0) {
                book.setInStock(book.getInStock() - 1);
                client.getBooks().add(book);
            }

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
