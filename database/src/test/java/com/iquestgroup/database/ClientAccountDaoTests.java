package com.iquestgroup.database;

import com.iquestgroup.database.builder.ClientAccountBuilder;
import com.iquestgroup.database.builder.UserAccountBuilder;
import com.iquestgroup.database.builder.UserBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultClientAccountDao;
import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.ClientAccount;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ClientAccountDao}.
 *
 * @author Stefan Pamparau
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientAccountDaoTests {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private Transaction transaction;

    private ClientAccountDao clientAccountDao;
    private ClientAccountBuilder clientAccountBuilder;
    private UserAccountBuilder userAccountBuilder;
    private UserBuilder userBuilder;

    @Before
    public void setUp() {
        clientAccountDao = new DefaultClientAccountDao();
        clientAccountBuilder = new ClientAccountBuilder();
        userAccountBuilder = new UserAccountBuilder();
        userBuilder = new UserBuilder();
        ReflectionTestUtils.setField(clientAccountDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetAllClientAccounts() throws DaoException {
        List<ClientAccount> expected = new ArrayList<>();
        expected.add(clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100));
        expected.add(clientAccountBuilder.build(2, "email 2", "password 2", new Timestamp(1234), AccountType.CLIENT, 100));
        expected.add(clientAccountBuilder.build(3, "email 3", "password 3", new Timestamp(1234), AccountType.CLIENT, 100));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.ClientAccount account")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<ClientAccount> actual = clientAccountDao.getAllClientAccounts();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.ClientAccount account");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testGetAllClientAccountsReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.ClientAccount account")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<ClientAccount> actual = clientAccountDao.getAllClientAccounts();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.ClientAccount account");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllClientAccountsThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.getAllClientAccounts();

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetClientAccounts() throws DaoException {
        User user = userBuilder.build(1, "name 1", "address 1", "serial 1");
        Set<UserAccount> expected = new HashSet<>();
        expected.add(userAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT));
        expected.add(userAccountBuilder.build(2, "email 2", "password 2", new Timestamp(1234), AccountType.CLIENT));
        expected.add(userAccountBuilder.build(3, "email 3", "password 3", new Timestamp(1234), AccountType.CLIENT));
        user.setUserAccounts(expected);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(user);

        Set<UserAccount> actual = clientAccountDao.getClientAccounts(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetClientAccountsReturnsNull() throws DaoException {
        User user = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(User.class, 1)).thenReturn(user);

        Set<UserAccount> actual = clientAccountDao.getClientAccounts(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(User.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetClientAccountsThrowsDaoException() throws DaoException {
        User user = userBuilder.build(1, "name 1", "address 1", "serial 1");

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.getClientAccounts(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetClientAccountById() throws DaoException {
        ClientAccount expected = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(expected);


        ClientAccount actual = clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetClientAccountReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(null);

        ClientAccount actual = clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetClientAccountThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetInitializedClientAccountById() throws DaoException {
        ClientAccount expected = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(expected);


        ClientAccount actual = clientAccountDao.getInitializedClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetInitializedClientAccountReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(null);

        ClientAccount actual = clientAccountDao.getInitializedClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetInitializedClientAccountThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.getInitializedClientAccountById(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testInsertClientAccount() throws DaoException {
        ClientAccount expected = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery("FROM com.iquestgroup.model.UserAccount clientAccount " +
                "WHERE clientAccount.email = :email")).thenReturn(query);
        when(query.setParameter("email", expected.getEmail())).thenReturn(query);
        when(query.list()).thenReturn(null);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(expected);

        clientAccountDao.insertClientAccount(expected);
        ClientAccount actual = clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).save(expected);
        verify(query, times(1)).setParameter("email", expected.getEmail());
        verify(query, times(1)).list();
        verify(transaction, times(1)).commit();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInsertClientAccountAlredyExists() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);
        String expected = "Client account with email: " + clientAccount.getEmail() + " already exists in the database.";
        List<ClientAccount> clientAccountList = new ArrayList<>();
        clientAccountList.add(clientAccount);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery("FROM com.iquestgroup.model.UserAccount clientAccount " +
                "WHERE clientAccount.email = :email")).thenReturn(query);
        when(query.setParameter("email", clientAccount.getEmail())).thenReturn(query);
        when(query.list()).thenReturn(clientAccountList);
        doNothing().when(transaction).commit();

        String actual = clientAccountDao.insertClientAccount(clientAccount);

        verify(sessionFactory, times(1)).openSession();
        verify(query, times(1)).setParameter("email", clientAccount.getEmail());
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testInsertClientAccountThrowsDaoException() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.insertClientAccount(clientAccount);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testUpdateClientAccount() throws DaoException {
        ClientAccount expected = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(ClientAccount.class, 1)).thenReturn(expected);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(expected);

        clientAccountDao.updateClientAccount(expected);
        ClientAccount actual = clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(2)).get(ClientAccount.class, 1);
        verify(session, times(1)).update(expected);
        verify(transaction, times(1)).commit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateClientAccountDoesNotExist() throws DaoException {
        String expected = "Client account not existent in database.";
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(ClientAccount.class, 1)).thenReturn(null);

        String actual = clientAccountDao.updateClientAccount(clientAccount);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testUpdateClientAccountThrowsDaoException() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.updateClientAccount(clientAccount);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testDeleteClientAccount() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);
        ClientAccount expected = null;

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(ClientAccount.class, 1)).thenReturn(clientAccount);
        doNothing().when(transaction).commit();

        clientAccountDao.deleteClientAccount(1);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(null);

        ClientAccount actual = clientAccountDao.getClientAccountById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(2)).get(ClientAccount.class, 1);
        verify(session, times(1)).delete(clientAccount);
        verify(transaction, times(1)).commit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteClientAccountDoesNotExist() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);
        String expected = "Client account does not exist in the database.";

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(ClientAccount.class, 1)).thenReturn(null);
        doNothing().when(transaction).commit();

        String actual = clientAccountDao.deleteClientAccount(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testDeleteClientAccountThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        clientAccountDao.deleteClientAccount(1);

        verify(sessionFactory, times(1)).openSession();
    }
}
