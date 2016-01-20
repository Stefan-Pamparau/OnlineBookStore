package com.iquestgroup.webApp;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("AuthorController")
@RequestMapping(path = "authors")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllAuthors() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("store/authors/listAuthors");
        mav.addObject("authors", authorFacade.getAllAuthors());

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayAuthorInsertForm(Model model) {
        model.addAttribute("author", new Author());
        return "/store/authors/insertAuthor";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertAuthor(@ModelAttribute Author author) {
        authorFacade.insertAuthor(author);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/authors/listAuthors");
        mav.addObject("authors", authorFacade.getAllAuthors());

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayAuthorDeleteForm() {
        return "store/authors/deleteAuthor";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteAuthor(@RequestParam("authorID") Integer authorID) {
        ModelAndView mav = new ModelAndView();
        authorFacade.deleteAuthor(authorID);

        mav.setViewName("store/authors/listAuthors");
        mav.addObject("authors", authorFacade.getAllAuthors());

        return mav;
    }

    @RequestMapping(path = "/delete/{authorID}", method = RequestMethod.GET)
    public ModelAndView deleteAuthorByByUrlID(@PathVariable Integer authorID) {
        ModelAndView mav = new ModelAndView();
        authorFacade.deleteAuthor(authorID);

        mav.setViewName("store/authors/listAuthors");
        mav.addObject("authors", authorFacade.getAllAuthors());

        return mav;
    }
}
