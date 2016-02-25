package com.iquestgroup.webApp.controllers;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.facade.BookFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        mav.setViewName("store/books/listBooks");

        try {
            mav.addObject("books", bookFacade.getAllBooks());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "store/books/insertBook";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertBook(@Valid @ModelAttribute Book book, BindingResult bindingResult,
        @RequestParam("authorID") Integer authorID) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/store/books/insertBook");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            Author author = authorFacade.getAuthorByID(authorID);
            book.setAuthor(author);
            bookFacade.insertBook(book);

            mav.addObject("books", bookFacade.getAllBooks());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayDeleteAuthorForm() {
        return "store/books/deleteBook";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteBook(@RequestParam("bookID") Integer bookID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            bookFacade.deleteBook(bookID);
            mav.addObject("books", bookFacade.getAllBooks());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete/{bookID}", method = RequestMethod.GET)
    public ModelAndView deleteBookByURLID(@PathVariable Integer bookID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/books/listBooks");

        try {
            bookFacade.deleteBook(bookID);
            mav.addObject("books", bookFacade.getAllBooks());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
