package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.LoginService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller mapped to respond to login and logout requests.
 *
 * @author Stefan Pamparau
 */
@Component("LoginController")
public class LoginController extends AbstractController {
    @Autowired
    private LoginService loginService;

    @Mapping(path = "/login", method = HttpMethodType.GET)
    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/login/login.jsp").include(request, response);
    }

    @Mapping(path = "/login", method = HttpMethodType.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount userAccount = null;
        try {
            userAccount = loginService.login(request.getParameter("email"), request.getParameter("password"));

            if(userAccount != null) {
                request.getSession().setAttribute("loggedUser", userAccount);
                request.getRequestDispatcher("/WEB-INF/views/pages/index.jsp").include(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/pages/error/loginError.jsp").include(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Mapping(path = "/logout", method = HttpMethodType.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/login.jsp");
    }

    @Mapping(path = "/login/error", method = HttpMethodType.GET)
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("/WEB-INF/views/pages/error/loginError.jsp");
    }
}
