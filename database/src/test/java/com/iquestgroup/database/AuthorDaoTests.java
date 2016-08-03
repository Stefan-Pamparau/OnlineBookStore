package com.iquestgroup.database;

import com.iquestgroup.database.builder.AuthorBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultAuthorDao;
import com.iquestgroup.model.Author;

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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

/**
 * Unit Tests for the {@link AuthorDao} class.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorDaoTests {

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query query;
    @Mock
    private Transaction transaction;

    private AuthorDao authorDao;

    private AuthorBuilder authorBuilder;

    @Before
    public void setUp() {
        authorDao = new DefaultAuthorDao();
        authorBuilder = new AuthorBuilder();
        ReflectionTestUtils.setField(authorDao, "sessionFactory", sessionFactory);
    }

    @Test
    public void testGetAllAuthors() throws DaoException {
        List<Author> expected = new ArrayList<>();
        expected.add(authorBuilder.build(1, "name 1", 21));
        expected.add(authorBuilder.build(2, "name 2", 22));
        expected.add(authorBuilder.build(3, "name 3", 23));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Author")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<Author> actual = authorDao.getAllAuthors();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Author");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllAuthorsReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Author")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<Author> actual = authorDao.getAllAuthors();

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Author");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllAuthorsThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(new HibernateException("dummy message"));

        List<Author> actual = authorDao.getAllAuthors();
    }

    @Test
    public void testGetAuthorById() throws DaoException {
        Author expected = authorBuilder.build(1, "name 1", 21);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(expected);

        Author actual = authorDao.getAuthorByID(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAuthorByIdReturnNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(null);

        Author actual = authorDao.getAuthorByID(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAuthorByIdThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(DaoException.class);

        Author actual = authorDao.getAuthorByID(1);
    }

    @Test
    public void testGetAllAuthorsByNamePattern() throws DaoException {
        List<Author> expected = new ArrayList<>();
        expected.add(authorBuilder.build(1, "name 1", 21));
        expected.add(authorBuilder.build(2, "name 2", 22));
        expected.add(authorBuilder.build(3, "name 3", 23));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + "name" + "%'")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<Author> actual = authorDao.getAuthorByNamePattern("name");

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + "name" + "%'");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllAuthorsByNamePatternReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + "name" + "%'")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<Author> actual = authorDao.getAuthorByNamePattern("name");

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Author author where author.name like '%" + "name" + "%'");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllAuthorsByNamePatternThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        List<Author> actual = authorDao.getAuthorByNamePattern("name");
    }

    @Test
    public void testInsertAuthor() throws DaoException {
        Author insertedAuthor = authorBuilder.build(4, "name 4", 24);
        List<Author> expected = new ArrayList<>();
        expected.add(authorBuilder.build(1, "name 1", 21));
        expected.add(authorBuilder.build(2, "name 2", 22));
        expected.add(authorBuilder.build(3, "name 3", 23));
        expected.add(insertedAuthor);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Author")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        authorDao.insertAuthor(insertedAuthor);
        List<Author> actual = authorDao.getAllAuthors();

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).save(insertedAuthor);
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Author");
        verify(transaction, times(1)).commit();
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testInsertAuthorThrowsDaoException() throws DaoException {
        Author insertedAuthor = authorBuilder.build(1, "name 4", 24);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        authorDao.insertAuthor(insertedAuthor);
    }

    @Test
    public void testUpdateAuthor() throws DaoException {
        Author persistedAuthor = authorBuilder.build(1, "name 1", 24);
        Author expected = authorBuilder.build(1, "updated name", 24);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Author.class, 1)).thenReturn(persistedAuthor);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(expected);

        persistedAuthor.setName("updated name");
        authorDao.updateAuthor(persistedAuthor);
        Author actual = authorDao.getAuthorByID(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(2)).get(Author.class, 1);
        verify(session, times(1)).update(expected);
        verify(transaction, times(1)).commit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateAuthorDoesNotExists() throws DaoException {
        Author nonPersistedAuthor = authorBuilder.build(1, "name 1", 24);
        String expected = "Cannot updated author. Author does not exist in the database.";

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Author.class, 1)).thenReturn(null);

        String actual = authorDao.updateAuthor(nonPersistedAuthor);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testUpdateAuthorThrowsDaoException() throws DaoException {
        Author persistedAuthor = authorBuilder.build(1, "name 1", 24);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(persistedAuthor);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        Author author = authorDao.getAuthorByID(1);
        author.setName("updated name");
        authorDao.updateAuthor(author);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).get(Author.class, 1);
    }

    @Test
    public void testDeleteAuthor() throws DaoException {
        Author persistedAuthor = authorBuilder.build(1, "name 1", 24);
        Author expected = null;

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Author.class, 1)).thenReturn(persistedAuthor);
        doNothing().when(session).delete(persistedAuthor);
        doNothing().when(transaction).commit();

        authorDao.deleteAuthor(1);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(expected);

        Author actual = authorDao.getAuthorByID(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(2)).get(Author.class, 1);
        verify(session, times(1)).delete(persistedAuthor);
        verify(transaction, times(1)).commit();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDeleteAuthorDoesNotExist() throws DaoException {
        Author persistedAuthor = authorBuilder.build(1, "name 1", 24);
        String expected = "Author with id: " + 1 + " does not exist in the database!";

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Author.class, 1)).thenReturn(null);

        String actual = authorDao.deleteAuthor(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testDeleteAuthorThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(DaoException.class);

        authorDao.deleteAuthor(1);
    }
}
