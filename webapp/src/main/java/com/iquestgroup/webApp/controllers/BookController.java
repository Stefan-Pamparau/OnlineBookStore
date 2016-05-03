package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller("BookController")
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllBooks() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            mav.addObject("books", bookService.getAllBooks());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "store/books/insertBook";
    }

    @RequestMapping(path = "/insert/{authorId}", method = RequestMethod.GET)
    public String insertBookForSpecifiedAuthorId(@PathVariable Integer authorId, ModelMap model) {
        model.addAttribute("authorId", authorId);
        model.addAttribute("book", new Book());
        return "store/books/insertBook";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertBook(@Valid @ModelAttribute Book book, BindingResult bindingResult,
                                   @RequestParam("authorId") Integer authorID) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/store/books/insertBook");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            Author author = authorService.getAuthorByID(authorID);
            book.setAuthor(author);
            String result = bookService.insertBook(book);

            mav.addObject("message", result);
            mav.addObject("books", bookService.getAllBooks());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayDeleteAuthorForm() {
        return "store/books/deleteBook";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteBook(@RequestParam("bookId") Integer bookID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            String result = bookService.deleteBook(bookID);

            mav.addObject("message", result);
            mav.addObject("books", bookService.getAllBooks());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete/{bookID}", method = RequestMethod.GET)
    public ModelAndView deleteBookByURLID(@PathVariable Integer bookID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            String result = bookService.deleteBook(bookID);

            mav.addObject("message", result);
            mav.addObject("books", bookService.getAllBooks());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
