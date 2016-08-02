package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("BookController")
public class BookController extends AbstractController {

    private static final Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @Mapping(path = "/books/list", method = HttpMethodType.GET)
    public void listAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.debug("Retrieving all books from the database");
            request.setAttribute("books", bookService.getAllBooks());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/books/listBooks.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/books/insert", method = HttpMethodType.GET)
    public void displayInsertBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying insert book form");
        request.getRequestDispatcher("/WEB-INF/views/pages/store/books/insertBook.jsp").include(request, response);
    }

    @Mapping(path = "/books/insert/\\d+", method = HttpMethodType.GET)
    public void displayInsertBookFormWithSpecifiedAuthorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

        logger.debug("Displaying insert book form with id: " + parts[parts.length - 1]);
        request.setAttribute("authorId", parts[parts.length - 1]);
        request.getRequestDispatcher("/WEB-INF/views/pages/store/books/insertBook.jsp").include(request, response);
    }

    @Mapping(path = "/books/insert", method = HttpMethodType.POST)
    public void insertBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Author author = authorService.getAuthorByID(Integer.parseInt(request.getParameter("authorId")));
            Book book = new Book();
            book.setAuthor(author);
            book.setTitle(request.getParameter("title"));
            book.setGenre(request.getParameter("genre"));
            book.setInStock(Integer.parseInt(request.getParameter("inStock")));
            book.setPrice(Integer.parseInt(request.getParameter("price")));

            logger.info("Inserting book: " + book);
            String result = bookService.insertBook(book);

            request.setAttribute("message", result);
            request.setAttribute("books", bookService.getAllBooks());

            request.getRequestDispatcher("/WEB-INF/views/pages/store/books/listBooks.jsp").include(request, response);

        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/books/delete", method = HttpMethodType.GET)
    public void displayDeleteAuthorForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying book delete form");
        request.getRequestDispatcher("/WEB-INF/views/pages/store/books/deleteBook.jsp").include(request, response);
    }

    @Mapping(path = "/books/delete", method = HttpMethodType.POST)
    public void deleteBookByIdFromRequestParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookId = request.getParameter("bookId");

            logger.info("Deleting book with id: " + bookId);
            deleteBook(Integer.parseInt(bookId), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/books/listBooks.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/books/delete/\\d+", method = HttpMethodType.GET)
    public void deleteBookByURLID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

            logger.info("Deleting book with id:" + parts[parts.length - 1]);
            deleteBook(Integer.parseInt(parts[parts.length - 1]), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/books/listBooks.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void deleteBook(Integer bookId, HttpServletRequest request) throws ServiceException {
        String result = bookService.deleteBook(bookId);

        request.setAttribute("message", result);
        request.setAttribute("books", bookService.getAllBooks());
    }
}
