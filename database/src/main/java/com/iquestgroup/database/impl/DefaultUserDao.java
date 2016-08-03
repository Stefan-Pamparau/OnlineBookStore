package com.iquestgroup.database.impl;

import com.iquestgroup.database.UserDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.User;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {

    private static Logger logger = Logger.getLogger(DefaultUserDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> result = null;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for user instances");
            List clientList = session.createQuery("FROM com.iquestgroup.model.User").list();

            if (clientList != null && !clientList.isEmpty()) {
                logger.debug("Found user instances. Inserting them in result list");
                result = new ArrayList<>(clientList);
            }
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while retrieving all the clients from the database!", e);
        }

        return result;
    }

    @Override
    public User getUserById(Integer id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for user with id: " + id);
            return session.get(User.class, id);
        } catch (HibernateException e) {
            throw new DaoException("An error occurred while trying to retrieve user by id", e);
        }
    }

    @Override
    public String insertUser(User user) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            logger.debug("Inserting user: " + user + " in the database");
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occured while trying to insert user: " + user, e);
        }

        return "Successfully inserted user: " + user;
    }

    @Override
    public String deleteUser(Integer userID) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            logger.debug("Querying the database for user with id: " + userID);
            User user = session.get(User.class, userID);

            if (user == null) {
                logger.debug("User with id: " + userID + " does not exists in the database!");
                return "User with id: " + userID + " does not exists in the database!";
            } else {
                logger.debug("User with id: " + userID + " found. Deleting him!");
                session.delete(user);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occurred while trying to delete user with id: " + userID, e);
        }

        return "Successfully deleted user with id: " + userID;
    }
}
