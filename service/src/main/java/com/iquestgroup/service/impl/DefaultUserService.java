package com.iquestgroup.service.impl;

import com.iquestgroup.database.UserDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.User;
import com.iquestgroup.service.UserService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultUserService implements UserService {

    private static final Logger logger = Logger.getLogger(DefaultUserService.class);

    @Autowired
    private UserDao clientDAO;

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            logger.debug("Delegating to client dao");
            return clientDAO.getAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User getUserById(Integer id) throws ServiceException {
        try {
            logger.debug("Delegating to client dao");
            return clientDAO.getUserById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertUser(User user) throws ServiceException {
        try {
            logger.debug("Delegating to client dao");
            return clientDAO.insertUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteUser(Integer clientID) throws ServiceException {
        try {
            logger.debug("Delegating to client dao");
            return clientDAO.deleteUser(clientID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
