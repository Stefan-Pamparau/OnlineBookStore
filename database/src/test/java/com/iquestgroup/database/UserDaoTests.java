package com.iquestgroup.database;

import com.iquestgroup.database.builder.UserBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultUserDao;
import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.User;
import com.iquestgroup.model.UserAccount;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserDao}.
 *
 * @author Stefan Pamparau
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTests {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private Transaction transaction;

    private UserDao userDao;
    private UserBuilder userBuilder;

    @Before
    public void setUp() {
        userDao = new DefaultUserDao();
        userBuilder = new UserBuilder();
        ReflectionTestUtils.setField(userDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetAllUsers() throws DaoException {
        List<User> expected = new ArrayList<>();
        expected.add(userBuilder.build(1, "name 1", "address 1", "serial 1"));
        expected.add(userBuilder.build(2, "name 2", "address 2", "serial 2"));
        expected.add(userBuilder.build(3, "name 3", "address 3", "serial 3"));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.User")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<User> actual = userDao.getAllUsers();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.User");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.User")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<User> actual = userDao.getAllUsers();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.User");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllUsersThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        List<User> actual = userDao.getAllUsers();

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetUserById() throws DaoException {
        User expected = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(expected);

        User actual = userDao.getUserById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserByIdReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(null);

        User actual = userDao.getUserById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetUserByIdThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        userDao.getUserById(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testInsertUser() throws DaoException {
        User expected = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(expected);

        userDao.insertUser(expected);
        User actual = userDao.getUserById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).save(expected);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testInsertUserThrowsDaoException() throws DaoException {
        User expected = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        userDao.insertUser(expected);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testDeleteUser() throws DaoException {
        User expected = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, 1)).thenReturn(expected);
        doNothing().when(transaction).commit();

        userDao.deleteUser(1);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(null);

        User actual = userDao.getUserById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).delete(expected);
        verify(transaction, times(1)).commit();
        verify(session, times(2)).get(User.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testDeleteUserDoesNotExist() throws DaoException {
        User user = userBuilder.build(1, "name 1", "address 1", "serial 1");
        String expected = "User with id: " + user.getId() + " does not exists in the database!";

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(User.class, 1)).thenReturn(null);

        String actual = userDao.deleteUser(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testDeleteUserThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        userDao.deleteUser(1);

        verify(sessionFactory, times(1)).openSession();
    }

}
