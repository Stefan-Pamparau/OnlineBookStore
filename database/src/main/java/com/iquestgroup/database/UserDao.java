package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.User;

import java.util.List;

/**
 * Interface which declares available methods to manipulate User object instances
 *
 * @author Stefan Pamparau
 */
public interface UserDao {
    /**
     * Returns all the clients from the database.
     *
     * @return - a list containing all the clients from the database, null if no clients are stored
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    List<User> getAllUsers() throws DaoException;

    /**
     * Returns the user with the specified id from the database.
     *
     * @param id - id of the user to be returned
     * @return - a list containing all the clients from the database, null if user is found
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    User getUserById(Integer id) throws DaoException;

    /**
     * Inserts a user into the database.
     *
     * @param user - user to be inserted into the database
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String insertUser(User user) throws DaoException;

    /**
     * Deletes a user from the database.
     *
     * @param userID - id of user to be deleted
     * @return - a message about the operation result
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String deleteUser(Integer userID) throws DaoException;
}
