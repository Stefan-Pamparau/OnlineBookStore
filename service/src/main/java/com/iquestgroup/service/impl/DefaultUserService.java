package com.iquestgroup.service.impl;

import com.iquestgroup.database.UserDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.User;
import com.iquestgroup.service.UserService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultUserService implements UserService {
    @Autowired
    private UserDao clientDAO;

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return clientDAO.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(Integer id) throws ServiceException {
        try {
            return clientDAO.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertUser(User user) throws ServiceException {
        try {
            return clientDAO.insertUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteUser(Integer clientID) throws ServiceException {
        try {
            return clientDAO.deleteUser(clientID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
