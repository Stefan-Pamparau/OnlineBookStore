package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.BookFacade;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultBookFacade implements BookFacade {
    @Autowired
    private BookService bookService;

    @Override
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Override
    public void insertBook(Book book) {
        bookService.insertBook(book);
    }

    @Override
    public void deleteBook(Integer bookID) {
        bookService.deleteBook(bookID);
    }

    @Override
    public void updateBook(Book book) {
        bookService.updateBook(book);
    }

    @Override
    public Author getBookAuthor(Integer bookID) {
        return bookService.getBookAuthor(bookID);
    }
}
