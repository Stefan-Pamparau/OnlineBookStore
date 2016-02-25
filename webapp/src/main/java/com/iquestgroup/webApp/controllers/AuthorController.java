package com.iquestgroup.webApp.controllers;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller("AuthorController")
@RequestMapping(path = "authors")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(path = "/list", method = RequestMethod.GET) public ModelAndView listAllAuthors() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            mav.addObject("authors", authorFacade.getAllAuthors());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public ModelAndView displayAuthorInsertForm(Model model) {
        model.addAttribute("author", new Author());
        return new ModelAndView("/store/authors/insertAuthor", "author", new Author());
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertAuthor(@Valid @ModelAttribute Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/store/authors/insertAuthor");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            authorFacade.insertAuthor(author);
            mav.addObject("authors", authorFacade.getAllAuthors());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET) public String displayAuthorDeleteForm() {
        return "store/authors/deleteAuthor";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteAuthor(@RequestParam("authorID") Integer authorID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            authorFacade.deleteAuthor(authorID);
            mav.addObject("authors", authorFacade.getAllAuthors());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete/{authorID}", method = RequestMethod.GET)
    public ModelAndView deleteAuthorByByUrlID(@PathVariable Integer authorID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            authorFacade.deleteAuthor(authorID);
            mav.addObject("authors", authorFacade.getAllAuthors());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
