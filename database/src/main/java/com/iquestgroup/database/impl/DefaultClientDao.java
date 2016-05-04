package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultClientDao implements ClientDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Client> getAllClients() throws DaoException {
        List<Client> result = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List clientList = session.createQuery("FROM com.iquestgroup.model.Client").list();
            for (Object client : clientList) {
                Client resultClient = (Client) client;
                result.add(resultClient);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occurred while retrieving all the clients from the database!", e);
        }

        return result;
    }

    @Override
    public Client getClientById(Integer id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve client by id", e);
        }
    }

    @Override
    public String insertClient(Client client) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occured while trying to insert client: " + client, e);
        }

        return "Successfully inserted client: " + client;
    }

    @Override
    public String deleteClient(Integer clientID) throws DaoException {
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

            throw new DaoException("An error occurred while trying to delete client with id: " + clientID, e);
        }

        return "Successfully deleted client with id: " + clientID;
    }
}
