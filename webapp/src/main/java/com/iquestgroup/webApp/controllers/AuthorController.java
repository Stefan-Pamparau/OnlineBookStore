package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Controller mapped to respond to author specific requests.
 *
 * @author Stefan Pamparau
 */
@Component("AuthorController")
@RequestMapping(path = "authors")
public class AuthorController extends AbstractController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllAuthors(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            mav.addObject("authors", authorService.getAllAuthors());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public ModelAndView displayAuthorInsertForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("author", new Author());
        return new ModelAndView("/store/authors/insertAuthor", "author", new Author());
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertAuthor(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/store/authors/insertAuthor");
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        try {
            String result = authorService.insertAuthor(author);
            mav.addObject("message", result);
            mav.addObject("authors", authorService.getAllAuthors());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayAuthorDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        return "store/authors/deleteAuthor";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteAuthorByIdFromRequestParam(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Integer authorId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        deleteAuthor(authorId, mav);

        return mav;
    }

    @RequestMapping(path = "/delete/{authorId}", method = RequestMethod.GET)
    public ModelAndView deleteAuthorByByUrlId(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer authorId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");

        deleteAuthor(authorId, mav);

        return mav;
    }

    private void deleteAuthor(Integer authorId, ModelAndView mav) {
        try {
            String result = authorService.deleteAuthor(authorId);
            mav.addObject("message", result);
            mav.addObject("authors", authorService.getAllAuthors());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
