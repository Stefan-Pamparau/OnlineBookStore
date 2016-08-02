package com.iquestgroup.database;

import com.iquestgroup.database.builder.AuthorBuilder;
import com.iquestgroup.database.builder.BookBuilder;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.database.impl.DefaultBookDao;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
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
    private AuthorBuilder authorBuilder;

    @Before
    public void setUp() {
        bookDao = new DefaultBookDao();
        bookBuilder = new BookBuilder();
        authorBuilder = new AuthorBuilder();
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
        bookDao.getAllBooks();
    }

    @Test
    public void testGetAllBooksByTitlePattern() throws DaoException {
        List<Book> expected = new ArrayList<>();
        expected.add(bookBuilder.build(1, "title 1", "genre 1", 1, 100));
        expected.add(bookBuilder.build(2, "title 2", "genre 2", 2, 101));
        expected.add(bookBuilder.build(3, "title 3", "genre 3", 3, 102));

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + "name" + "'%")).thenReturn(query);
        when(query.list()).thenReturn(expected);

        List<Book> actual = bookDao.getAllBooksByTitlePattern("name");

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + "name" + "'%");
        verify(query, times(1)).list();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllBooksByTitlePatternReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + "name" + "'%")).thenReturn(query);
        when(query.list()).thenReturn(null);

        List<Book> actual = bookDao.getAllBooksByTitlePattern("name");

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).createQuery("FROM com.iquestgroup.model.Book book inner join fetch book.author WHERE book.title LIKE '%" + "name" + "'%");
        verify(query, times(1)).list();
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetAllBooksByTitlePatternThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.getAllBooksByTitlePattern("name");

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetBookById() throws DaoException {
        Book expected = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(expected);

        Book actual = bookDao.getBookById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBookByIdReturnsNull() throws DaoException {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(null);

        Book actual = bookDao.getBookById(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetBookByIdThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.getBookById(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetBooksOfAuthor() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);
        Author author = authorBuilder.build(1, "name 1", 25);
        Set<Book> expected = new HashSet<>();
        expected.add(book);
        author.setBooks(expected);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(author);

        List<Book> actual = bookDao.getBooksOfAuthor(author);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(new ArrayList<>(expected), actual);
    }

    @Test
    public void testGetBooksOfAuthorReturnsNull() throws DaoException {
        Author author = authorBuilder.build(1, "name 1", 25);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Author.class, 1)).thenReturn(null);

        List<Book> actual = bookDao.getBooksOfAuthor(author);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Author.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetBooksOfAuthorThrowsDaoException() throws DaoException {
        Author author = authorBuilder.build(1, "name 1", 25);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.getBooksOfAuthor(author);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testInsertBook() throws DaoException {
        Book expected = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(expected);

        bookDao.insertBook(expected);
        Book actual = bookDao.getBookById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).save(expected);
        verify(transaction, times(1)).commit();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testInsertBookThrowsDaoException() throws DaoException {
        Book expected = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.insertBook(expected);

        verify(sessionFactory, times(2)).openSession();
    }

    @Test
    public void testUpdateBook() throws DaoException {
        Book expected = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Book.class, 1)).thenReturn(expected);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(expected);

        bookDao.updateBook(expected);
        Book actual = bookDao.getBookById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(1)).update(expected);
        verify(transaction, times(1)).commit();
        verify(session, times(2)).get(Book.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateBookDoesNotExist() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Book.class, 1)).thenReturn(null);

        String actual = bookDao.updateBook(book);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals("Book does not exist in the database.", actual);
    }

    @Test(expected = DaoException.class)
    public void testUpdateBookThrowsDaoException() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.updateBook(book);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testDeleteBook() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Book.class, 1)).thenReturn(book);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(null);

        bookDao.deleteBook(1);
        Book actual = bookDao.getBookById(1);

        verify(sessionFactory, times(2)).openSession();
        verify(session, times(2)).get(Book.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testDeleteBookDoesNotExist() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);
        String expected = "Book with id " + book.getId() + " does not exist in the database!";

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.get(Book.class, 1)).thenReturn(book);
        doNothing().when(transaction).commit();

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(null);

        String actual = bookDao.deleteBook(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = DaoException.class)
    public void testDeleteBookThrowsDaoException() throws DaoException {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        String actual = bookDao.deleteBook(1);

        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    public void testGetBookAuthor() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);
        Author expected = authorBuilder.build(1, "name 1", 25);
        book.setAuthor(expected);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(book);

        Author actual = bookDao.getBookAuthor(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetBookAuthorReturnsNull() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.get(Book.class, 1)).thenReturn(book);

        Author actual = bookDao.getBookAuthor(1);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).get(Book.class, 1);
        Assert.assertEquals(null, actual);
    }

    @Test(expected = DaoException.class)
    public void testGetBookAuthorThrowsDaoException() throws DaoException {
        Book book = bookBuilder.build(1, "title 1", "genre 1", 1, 100);

        when(sessionFactory.openSession()).thenThrow(HibernateException.class);

        bookDao.getBookAuthor(1);

        verify(sessionFactory, times(1)).openSession();
    }
}
