package com.iquestgroup.database.impl;

import com.iquestgroup.database.BookDAO;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public class DefaultBookDAO implements BookDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getAllBooks() {
        List<Book> resultList = new ArrayList<>();

        List books = entityManager.createQuery("SELECT b FROM Book b").getResultList();

        for (Object book : books) {
            resultList.add((Book) book);
        }

        return resultList;
    }

    @Override
    public void insertBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void deleteBook(Integer bookID) {
        Book book = entityManager.find(Book.class, bookID);
        entityManager.remove(book);
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public Author getBookAuthor(Integer bookID) {
        Book book = entityManager.find(Book.class, bookID);

        return book.getAuthor();
    }
}
