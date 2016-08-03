package com.iquestgroup.database;

import com.iquestgroup.database.builder.ClientAccountBuilder;
import com.iquestgroup.database.builder.PurchaseBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultPurchaseDao;
import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.Purchase;

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
 * Unit tests for {@link PurchaseDao}.
 *
 * @author Stefan Pamparau
 */
@RunWith(MockitoJUnitRunner.class)
public class PurchaseDaoTests {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private Transaction transaction;

    private PurchaseDao purchaseDao;
    private PurchaseBuilder purchaseBuilder;
    private ClientAccountBuilder clientAccountBuilder;

    @Before
    public void setUp() {
        purchaseDao = new DefaultPurchaseDao();
        purchaseBuilder = new PurchaseBuilder();
        clientAccountBuilder = new ClientAccountBuilder();
        ReflectionTestUtils.setField(purchaseDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetAllPurchases() throws DaoException {
        List<Purchase> expected = new ArrayList<>();
        expected.add(purchaseBuilder.build(new Timestamp(1234)));
        expected.add(purchaseBuilder.build(new Timestamp(1231235)));
        expected.add(purchaseBuilder.build(new Timestamp(1234126)));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Purchase purchase inner join fetch purchase.book " +
            "inner join fetch purchase.clientAccount")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<Purchase> actual = purchaseDao.getAllPurchases();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Purchase purchase inner join fetch purchase.book " +
            "inner join fetch purchase.clientAccount");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllPurchasesReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Purchase purchase inner join fetch purchase.book " +
            "inner join fetch purchase.clientAccount")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<Purchase> actual = purchaseDao.getAllPurchases();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Purchase purchase inner join fetch purchase.book " +
            "inner join fetch purchase.clientAccount");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllPurchasesThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        purchaseDao.getAllPurchases();

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetPurchaseById() throws DaoException {
        Purchase expected = purchaseBuilder.build(new Timestamp(1234));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Purchase.class, 1)).thenReturn(expected);

        Purchase actual = purchaseDao.getPurchaseById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Purchase.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetPurchaseByIdReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Purchase.class, 1)).thenReturn(null);

        Purchase actual = purchaseDao.getPurchaseById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Purchase.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetPurchaseByIdThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(DaoException.class);

        purchaseDao.getPurchaseById(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetAllPurchasesByClientAccountId() throws DaoException {
        Set<Purchase> expected = new HashSet<>();
        expected.add(purchaseBuilder.build(new Timestamp(1234)));
        expected.add(purchaseBuilder.build(new Timestamp(1231235)));
        expected.add(purchaseBuilder.build(new Timestamp(1234126)));
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);
        clientAccount.setPurchases(expected);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(clientAccount);

        Set<Purchase> actual = purchaseDao.getPurchasesByClientAccountId(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllPurchasesByClientAccountIdReturnsNull() throws DaoException {
        ClientAccount clientAccount = clientAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(ClientAccount.class, 1)).thenReturn(clientAccount);

        Set<Purchase> actual = purchaseDao.getPurchasesByClientAccountId(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(ClientAccount.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllPurchasesByClientAccountIdThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(DaoException.class);

        purchaseDao.getPurchasesByClientAccountId(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testInsertPurchase() throws DaoException {
        Purchase expected = purchaseBuilder.build(new Timestamp(1234));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Purchase.class, 1)).thenReturn(expected);

        purchaseDao.insertPurchase(expected);
        Purchase actual = purchaseDao.getPurchaseById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(transaction, times(1)).commit();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testInsertPurchaseThrowsDaoException() throws DaoException {
        Purchase expected = purchaseBuilder.build(new Timestamp(1234));

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        purchaseDao.insertPurchase(expected);

        verify(sessionFactory, times(1)).openSession();
    }
}
