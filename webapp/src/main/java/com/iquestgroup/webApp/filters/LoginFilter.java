package com.iquestgroup.webApp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter which handles login logic.
 *
 * @author Stefan Pamparau
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String loginURI = req.getContextPath() + "/login";
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("loggedUser") != null;
        boolean loginRequest = removeMatrixVariables(req.getRequestURI()).equals(loginURI);


        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/pages/login/login.jsp").include(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * Removes matrix variables from the path.
     *
     * @param path - path to be processed
     * @return - a path without matrix variables
     */
    private String removeMatrixVariables(String path) {
        if (path.contains(";")) {
            return path.substring(0, path.indexOf(";"));
        }
        return path;
    }
}