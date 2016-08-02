package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.LoginService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Mapping(path = "/login", method = HttpMethodType.GET)
    public void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying login form");
        request.getRequestDispatcher("/WEB-INF/views/pages/login/login.jsp").include(request, response);
    }

    @Mapping(path = "/login", method = HttpMethodType.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount userAccount = null;
        try {
            String email = request.getParameter("email");

            logger.info("Trying to authenticate user with email: " + email);
            userAccount = loginService.login(email, request.getParameter("password"));

            if (userAccount != null) {
                logger.debug("Login success");
                request.getSession().setAttribute("loggedUser", userAccount);
                request.getRequestDispatcher("/WEB-INF/views/pages/index.jsp").include(request, response);
            } else {
                logger.debug("Login failed");
                request.getRequestDispatcher("/WEB-INF/views/pages/error/loginError.jsp").include(request, response);
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
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
