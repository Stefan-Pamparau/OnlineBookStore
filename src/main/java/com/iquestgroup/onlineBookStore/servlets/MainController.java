package com.iquestgroup.onlineBookStore.servlets;

import com.iquestgroup.onlineBookStore.DAO.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private LoginDAO loginDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showEntryPage() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");
        mav.addObject("welcomeMessage", "Welcome to my online book store!");
        mav.addObject("loginMessage", "Please login first");

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/showLoginPage")
    public String showLoginPage() {
        return "LoginPage";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(loginDAO.checkUser(username, password)) {
            return "redirect:/mainPage";
        }
        else {
            return "redirect:/invalidLoginPage";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mainPage")
    public String showMainPage() {
        return "MainPage";
    }

    @ExceptionHandler(Throwable.class)
    public String showErrorPage() {
        return "ErrorPage";
    }
}
