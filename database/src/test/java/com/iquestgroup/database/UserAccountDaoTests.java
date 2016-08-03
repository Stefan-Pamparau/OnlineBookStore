package com.iquestgroup.database;

import com.iquestgroup.database.builder.UserAccountBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultUserAccountDao;
import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.UserAccount;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserAccountDao}.
 *
 * @author Stefan Pamparau
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountDaoTests {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;

    private UserAccountDao userAccountDao;
    private UserAccountBuilder userAccountBuilder;

    @Before
    public void setUp() {
        userAccountDao = new DefaultUserAccountDao();
        userAccountBuilder = new UserAccountBuilder();
        ReflectionTestUtils.setField(userAccountDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetUserByEmailAndPassword() throws DaoException {
        UserAccount expected = userAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT);
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(expected);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.UserAccount account WHERE account.email = :email AND account.password = :password")).thenReturn(query);
        when(query.setParameter("email", expected.getEmail())).thenReturn(query);
        when(query.setParameter("password", expected.getPassword())).thenReturn(query);
        when(query.list()).thenReturn(userAccounts);

        UserAccount actual = userAccountDao.getUserAccountByEmailAndPassword(expected.getEmail(), expected.getPassword());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.UserAccount account WHERE account.email = :email AND account.password = :password");
        verify(query, times(1)).setParameter("email", expected.getEmail());
        verify(query, times(1)).setParameter("password", expected.getPassword());
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserByEmailAndPasswordReturnsNull() throws DaoException {
        UserAccount userAccount = userAccountBuilder.build(1, "email 1", "password 1", new Timestamp(1234), AccountType.CLIENT);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.UserAccount account WHERE account.email = :email AND account.password = :password")).thenReturn(query);
        when(query.setParameter("email", userAccount.getEmail())).thenReturn(query);
        when(query.setParameter("password", userAccount.getPassword())).thenReturn(query);
        when(query.list()).thenReturn(null);

        UserAccount actual = userAccountDao.getUserAccountByEmailAndPassword(userAccount.getEmail(), userAccount.getPassword());

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.UserAccount account WHERE account.email = :email AND account.password = :password");
        verify(query, times(1)).setParameter("email", userAccount.getEmail());
        verify(query, times(1)).setParameter("password", userAccount.getPassword());
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }
}
