package com.iquestgroup.database;

import com.iquestgroup.database.builder.BookBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultBookDao;
import com.iquestgroup.model.Book;

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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link BookDao}.
 *
 * @author Stefan Pamparau
 */
@RunWith(MockitoJUnitRunner.class)
public class BookDaoTest {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private Transaction transaction;

    private BookDao bookDao;

    private BookBuilder bookBuilder;

    @Before
    public void setUp() {
        bookDao = new DefaultBookDao();
        bookBuilder = new BookBuilder();
        ReflectionTestUtils.setField(bookDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetAllBooks() throws DaoException {
        List<Book> expected = new ArrayList<>();
        expected.add(bookBuilder.build(1, "title 1", "genre 1", 1, 100));
        expected.add(bookBuilder.build(2, "title 2", "genre 2", 2, 101));
        expected.add(bookBuilder.build(3, "title 3", "genre 3", 3, 102));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<Book> actual = bookDao.getAllBooks();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllBooksReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<Book> actual = bookDao.getAllBooks();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllBooksThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(DaoException.class);

        List<Book> actual = bookDao.getAllBooks();
    }
}
