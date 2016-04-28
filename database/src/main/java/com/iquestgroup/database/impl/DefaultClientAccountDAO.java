package com.iquestgroup.database.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Book;
import com.iquestgroup.model.Client;
import com.iquestgroup.model.ClientAccount;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.ClientAccount").list();

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
    public List<ClientAccount> getClientAccounts(Integer clientId) throws DaoException {
        List<ClientAccount> result = null;

        try (Session session = sessionFactory.openSession()) {
            Client client = session.get(Client.class, clientId);

            if (client != null) {
                result = client.getClientAccounts();
            }
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all client accounts", e);
        }

        return result;
    }

    @Override
    public String insertClientAccount(ClientAccount account) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List clientAccounts = session.createQuery("FROM com.iquestgroup.model.ClientAccount clientAccount " +
                    "WHERE clientAccount.email = " + account.getEmail())
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

    @Override
    public String purchaseBook(Integer clientAccountID, Integer bookID) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, bookID);
            ClientAccount clientAccount = session.get(ClientAccount.class, clientAccountID);
            if ((book == null) || (clientAccount == null)) {
                return "Book or client with specified id does not exist in the database!";
            } else {
                if (book.getInStock() > 0) {
                    book.setInStock(book.getInStock() - 1);
                    clientAccount.getBooks().add(book);
                } else {
                    return "Not enough books with id: " + bookID + " in stock";
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occurred while trying to purchase the book with id: " + bookID
                    + " for the client with the id: " + clientAccountID, e);
        }

        return "Successfully purchased the book with id: " + bookID + " for the client with the id: " + clientAccountID;
    }
}
