package com.iquestgroup.database.impl;

import com.iquestgroup.database.UserDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> result = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List clientList = session.createQuery("FROM com.iquestgroup.model.User").list();
            for (Object client : clientList) {
                User resultUser = (User) client;
                result.add(resultUser);
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
    public User getUserById(Integer id) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
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
    public String deleteUser(Integer clientID) throws DaoException {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, clientID);

            if (user == null) {
                return "User with id: " + clientID + " does not exists in the database!";
            } else {
                session.delete(user);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("An error occurred while trying to delete user with id: " + clientID, e);
        }

        return "Successfully deleted user with id: " + clientID;
    }
}
