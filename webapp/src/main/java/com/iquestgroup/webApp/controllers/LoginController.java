package com.iquestgroup.webApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller mapped to respond to login and logout requests.
 *
 * @author Stefan Pamparau
 */
@Component("LoginController")
public class LoginController extends AbstractController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(path = "login/error", method = RequestMethod.GET)
    public String loginError() {
        return "error/loginError";
    }
}
