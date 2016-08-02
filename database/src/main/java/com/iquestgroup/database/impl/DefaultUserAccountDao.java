package com.iquestgroup.database.impl;

import com.iquestgroup.database.UserAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.UserAccount;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Default implementation of the UserAccountDao interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultUserAccountDao implements UserAccountDao {

    private static Logger logger = Logger.getLogger(DefaultUserAccountDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAccount getUserAccountByEmailAndPassword(String email, String password) throws DaoException {
        UserAccount result = null;
        try (Session session = sessionFactory.openSession()) {
            logger.debug("Querying the database for user account with email: " + email);
            List account = session.createQuery("FROM com.iquestgroup.model.UserAccount account WHERE account.email = :email AND account.password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .list();

            if (account != null && account.size() == 1) {
                logger.debug("Found user account with email: " + email);
                result = (UserAccount) account.get(0);
            }

        } catch (HibernateException e) {
            throw new DaoException("Cannot retrieve user account by email and password", e);
        }

        return result;
    }
}
