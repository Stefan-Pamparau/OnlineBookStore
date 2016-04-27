package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientDAO;
import com.iquestgroup.database.exceptionHandling.DAOException;
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
    public List<Client> listAllClients() throws DAOException {
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
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("An error occurred while retrieving all the clients from the database!", e);
        }

        return result;
    }

    @Override
    public String insertClient(Client client) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("An error occured while trying to insert client: " + client, e);
        }

        return "Successfully inserted client: " + client;
    }

    @Override
    public String deleteClient(Integer clientID) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientID);

            if (client == null) {
                return "Client with id: " + clientID + " does not exists in the database!";
            } else {
                session.delete(client);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("An error occurred while trying to delete client with id: " + clientID, e);
        }

        return "Successfully deleted client with id: " + clientID;
    }

    @Override
    public String purchaseBook(Integer clientID, Integer bookID) throws DAOException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);
            Client client = session.get(Client.class, clientID);
            if ((book == null) || (client == null)) {
                return "Book or client with specified id does not exist in the database!";
            } else {
                if (book.getInStock() > 0) {
                    book.setInStock(book.getInStock() - 1);
                    client.getBooks().add(book);
                } else {
                    return "Not enough books with id: " + bookID + " in stock";
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DAOException("An error occurred while trying to purchase the book with id: " + bookID
                + " for the client with the id: " + clientID, e);
        }

        return "Successfully purchased the book with id: " + bookID + " for the client with the id: " + clientID;
    }
}
