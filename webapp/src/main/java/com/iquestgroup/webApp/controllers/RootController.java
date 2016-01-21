package com.iquestgroup.webApp.controllers;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller("RootController")
@RequestMapping(path = "/")
public class RootController {

    @RequestMapping(method = RequestMethod.GET)
    public String showEntryPage() {
        return "index";
    }

    @ExceptionHandler(Throwable.class)
    public String showErrorPage() {
        return "error/pageError";
    }
}
