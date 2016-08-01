package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.User;
import com.iquestgroup.model.UserAccount;

import org.apache.log4j.Logger;
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
public class DefaultClientAccountDao extends AbstractDao implements ClientAccountDao {

    private static Logger logger = Logger.getLogger(DefaultClientAccountDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ClientAccount> getAllClientAccounts() throws DaoException {
        List<ClientAccount> result = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying the database for ClientAccount instances");
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.ClientAccount account").list();

            if (clientAccounts != null && !clientAccounts.isEmpty()) {
                logger.debug(getLogPrefix() + "Client accounts found in the database. Inserting them " +
                        "in the result list");
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
    public Set<UserAccount> getClientAccounts(Integer clientId) throws DaoException {
        Set<UserAccount> result = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying the database for user with id: " + clientId);
            User user = session.get(User.class, clientId);

            if (user != null) {
                logger.debug(getLogPrefix() + "User with id:" + clientId + " exists in the database." +
                        "Initializing his accounts");
                Hibernate.initialize(user.getUserAccounts());
                result = user.getUserAccounts();
            }
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all client accounts", e);
        }

        return result;
    }

    @Override
    public ClientAccount getClientAccountById(Integer clientAccountId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying the database for accounts with: " + clientAccountId);
            return session.get(ClientAccount.class, clientAccountId);
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve client account by id", e);
        }
    }

    @Override
    public ClientAccount getInitializedClientAccountById(Integer clientAccountId) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            logger.debug(getLogPrefix() + "Querying the database for accounts with: " + clientAccountId);
            ClientAccount userAccount = session.get(ClientAccount.class, clientAccountId);

            if (userAccount != null) {
                logger.debug(getLogPrefix() + "Account with id: " + clientAccountId + " exists in the database." +
                        "Initializing it");
                Hibernate.initialize(userAccount.getUser());
                Hibernate.initialize(userAccount.getBooks());
                Hibernate.initialize(userAccount.getPurchases());
            }

            return userAccount;
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve client account by id", e);
        }
    }

    @Override
    public String insertClientAccount(ClientAccount account) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            logger.debug(getLogPrefix() + "Querying the database for accounts with email:" + account.getEmail());
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.UserAccount clientAccount " +
                    "WHERE clientAccount.email = :email")
                    .setParameter("email", account.getEmail())
                    .list();

            if (clientAccounts == null || clientAccounts.isEmpty()) {
                logger.debug("No accunts found. Inserting new account: " + account);
                session.save(account);
            } else {
                logger.debug(getLogPrefix() + "Client account with email: " + account.getEmail() + " already exists in the database.");
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

            logger.debug(getLogPrefix() + "Querying the database for account with id: " + account.getId());
            ClientAccount persistentUserAccount = session.get(ClientAccount.class, account.getId());

            if (persistentUserAccount != null) {
                logger.debug(getLogPrefix() + "Account with id: " + account.getId() + " exists in the database." +
                        "Updating it");
                persistentUserAccount.setEmail(account.getEmail());
                persistentUserAccount.setPassword(account.getPassword());
                persistentUserAccount.setBalance(account.getBalance());
                session.update(persistentUserAccount);
                transaction.commit();
            } else {
                logger.debug(getLogPrefix() + "Client account not existent in database.");
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

            logger.debug(getLogPrefix() + "Querying the database for account with id: " + clientAccountId);
            UserAccount persistentUserAccount = session.get(ClientAccount.class, clientAccountId);

            if (persistentUserAccount != null) {
                logger.debug(getLogPrefix() + "Account with id: " + clientAccountId + " exist in the database." +
                        "Deleting it");
                session.delete(persistentUserAccount);
                transaction.commit();
            } else {
                logger.debug(getLogPrefix() + "Client account does not exist in the database.");
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
