package com.iquestgroup.webApp;

import com.iquestgroup.facade.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/")
public class RootController {
    @Autowired
    private AuthorFacade authorFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String showEntryPage() {
        return "index";
    }

    @RequestMapping(value = "authors", method = RequestMethod.GET)
    public void listAllAuthors() {
        System.out.println(authorFacade.getAllAuthors());
    }

    @ExceptionHandler(Throwable.class)
    public String showErrorPage() {
        return "pageError";
    }
}
