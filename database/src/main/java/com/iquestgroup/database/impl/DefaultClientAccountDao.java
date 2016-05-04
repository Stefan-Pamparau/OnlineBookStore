package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Client;
import com.iquestgroup.model.ClientAccount;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of the ClientAccountDao interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultClientAccountDao implements ClientAccountDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ClientAccount> getAllClientAccounts() throws DaoException {
        List<ClientAccount> result = null;

        try (Session session = sessionFactory.openSession()) {
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.ClientAccount account").list();

            if (clientAccounts != null && clientAccounts.size() > 0) {
                result = new ArrayList<>();

                for (Object clientAccount : clientAccounts) {
                    result.add((ClientAccount) clientAccount);
                }
            }
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all client accounts", e);
        }

        return result;
    }

    @Override
    public Set<ClientAccount> getClientAccounts(Integer clientId) throws DaoException {
        Transaction transaction = null;
        Set<ClientAccount> result = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId);

            if (client != null) {
                Hibernate.initialize(client.getClientAccounts());
                result = client.getClientAccounts();
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot retrieve all client accounts", e);
        }

        return result;
    }

    @Override
    public ClientAccount getClientAccountById(Integer clientAccountId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(ClientAccount.class, clientAccountId);
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve client account by id", e);
        }
    }

    @Override
    public String insertClientAccount(ClientAccount account) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.ClientAccount clientAccount " +
                    "WHERE clientAccount.email = :email")
                    .setParameter("email", account.getEmail())
                    .list();

            if (clientAccounts == null || clientAccounts.size() == 0) {
                session.save(account);
            } else {
                return "Client account with email: " + account.getEmail() + " already exists in the database.";
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Error while trying to insert new client", e);
        }

        return "Client account successfully inserted!";
    }

    @Override
    public String updateClientAccount(ClientAccount account) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ClientAccount persistenClientAccount = session.get(ClientAccount.class, account.getId());

            if (persistenClientAccount != null) {
                persistenClientAccount.setEmail(account.getEmail());
                persistenClientAccount.setPassword(account.getPassword());
                persistenClientAccount.setBalance(account.getBalance());
                session.update(persistenClientAccount);
                transaction.commit();
            } else {
                return "Client account not existent in database.";
            }

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot updated client account.", e);
        }

        return "Client account successfully updated.";
    }

    @Override
    public String deleteClientAccount(Integer clientAccountId) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            ClientAccount persistentClientAccount = session.get(ClientAccount.class, clientAccountId);

            if (persistentClientAccount != null) {
                session.delete(persistentClientAccount);
                transaction.commit();
            } else {
                return "Client account does not exist in the database.";
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot delete client account", e);
        }

        return "Client account successfully deleted.";
    }
}
