package com.iquestgroup.service;

import com.iquestgroup.model.User;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

/**
 * Service interface which declares all methods available to manipulate user objects.
 *
 * @author Stefan Pamparau
 */
public interface UserService {
    /**
     * Returns all the clients from the database.
     *
     * @return - a list containing all the clients from the database, null if no clients are stored
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Returns the user with the specified id from the database.
     *
     * @param id - id of the user to be returned
     * @return - a list containing all the clients from the database, null if user is found
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    User getUserById(Integer id) throws ServiceException;

    /**
     * Inserts a user into the database.
     *
     * @param user - user to be inserted into the database
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String insertUser(User user) throws ServiceException;

    /**
     * Deletes a user from the database.
     *
     * @param clientID - id of user to be deleted
     * @return - a message about the operation result
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String deleteUser(Integer clientID) throws ServiceException;
}
