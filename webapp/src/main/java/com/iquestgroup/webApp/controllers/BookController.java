package com.iquestgroup.webApp.controllers;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.facade.BookFacade;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("BookController")
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookFacade bookFacade;
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllBooks() {
        ModelAndView mav = new ModelAndView();
        System.out.println(bookFacade.getAllBooks());
        mav.setViewName("store/books/listBooks");
        mav.addObject("books", bookFacade.getAllBooks());

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertBookForm() {
        return "store/books/insertBook";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertBook(@ModelAttribute Book book, @RequestParam("authorID") Integer authorID) {
        Author author = authorFacade.getAuthorByID(authorID);
        book.setAuthor(author);
        bookFacade.insertBook(book);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");
        mav.addObject("books", bookFacade.getAllBooks());

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayDeleteAuthorForm() {
        return "store/books/deleteBook";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteBook(@RequestParam("bookID") Integer bookID) {
        bookFacade.deleteBook(bookID);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");
        mav.addObject("books", bookFacade.getAllBooks());

        return mav;
    }

    @RequestMapping(path = "/delete/{bookID}", method = RequestMethod.POST)
    public ModelAndView deleteBookByURLID(@PathVariable Integer bookID) {
        bookFacade.deleteBook(bookID);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");
        mav.addObject("books", bookFacade.getAllBooks());

        return mav;
    }
}
