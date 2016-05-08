package com.iquestgroup.database.impl;

import com.iquestgroup.database.PurchaseDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.model.Purchase;

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
 * Default implementation of the PurchaseDao interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultPurchaseDao implements PurchaseDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Purchase> getAllPurchases() throws DaoException {
        List<Purchase> result = null;

        try (Session session = sessionFactory.openSession()) {
            List purchases = session.createQuery("FROM com.iquestgroup.model.Purchase purchase inner join fetch purchase.book " +
                    "inner join fetch purchase.clientAccount").list();

            if (purchases != null && purchases.size() > 0) {
                result = new ArrayList<>();
                result.addAll(purchases);
            }
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve all purchases", e);
        }

        return result;
    }

    @Override
    public Purchase getPurchaseById(Integer id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Purchase.class, id);
        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve purchase with id: " + id, e);
        }
    }

    @Override
    public Set<Purchase> getPurchasesByClientAccountId(Integer clientAccountId) throws DaoException {
        Set<Purchase> result = null;

        try (Session session = sessionFactory.openSession()) {
            ClientAccount userAccount = session.get(ClientAccount.class, clientAccountId);

            if (userAccount != null) {
                Hibernate.initialize(userAccount.getPurchases());
                result = userAccount.getPurchases();
            }

        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve purchases for the client account id: " + clientAccountId, e);
        }

        return result;
    }

    @Override
    public String insertPurchase(Purchase purchase) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(purchase);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DaoException("Cannot insert purchase: " + purchase, e);
        }

        return "Purchase: " + purchase + " successfully inserted";
    }
}
