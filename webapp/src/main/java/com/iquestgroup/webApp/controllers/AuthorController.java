package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller mapped to respond to author specific requests.
 *
 * @author Stefan Pamparau
 */
@Component("AuthorController")
public class AuthorController extends AbstractController {
    @Autowired
    private AuthorService authorService;

    @Mapping(path = "/authors/list", method = HttpMethodType.GET)
    public void listAllAuthors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/listAuthors.jsp");

        try {
            request.setAttribute("authors", authorService.getAllAuthors());
            requestDispatcher.include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/authors/insert", method = HttpMethodType.GET)
    public void displayAuthorInsertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/insertAuthor.jsp").include(request, response);
    }

    @Mapping(path = "/authors/insert", method = HttpMethodType.POST)
    public void insertAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Author author = new Author();
            author.setName(request.getParameter("name"));
            author.setAge(Integer.parseInt(request.getParameter("age")));

            String result = authorService.insertAuthor(author);

            request.setAttribute("message", result);
            request.setAttribute("authors", authorService.getAllAuthors());

            request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/listAuthors.jsp").forward(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/authors/delete", method = HttpMethodType.GET)
    public void displayAuthorDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/deleteAuthor.jsp").include(request, response);
    }

    @Mapping(path = "/authors/delete", method = HttpMethodType.POST)
    public void deleteAuthorByIdFromRequestParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteAuthor(Integer.parseInt(request.getParameter("authorId")), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/listAuthors.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

    }

    @Mapping(path = "/authors/delete/\\d+", method = HttpMethodType.GET)
    public void deleteAuthorByByUrlId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
            deleteAuthor(Integer.parseInt(parts[parts.length - 1]), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/authors/listAuthors.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void deleteAuthor(Integer authorId, HttpServletRequest request) throws ServiceException {
        String result = authorService.deleteAuthor(authorId);
        request.setAttribute("message", result);
        request.setAttribute("authors", authorService.getAllAuthors());
    }
}
