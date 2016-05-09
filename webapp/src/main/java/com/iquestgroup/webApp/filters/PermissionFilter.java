package com.iquestgroup.webApp.filters;

import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.UserAccount;

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
 * Filter which checks a current users permissions.
 *
 * @author Stefan Pamparau
 */
public class PermissionFilter implements Filter {
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


        if (loginRequest) {
            chain.doFilter(request, response);
        } else if (loggedIn) {
            String path = req.getRequestURI().substring(req.getContextPath().length());
            if (path.contains("insert") || path.contains("update") || path.contains("delete")) {
                UserAccount userAccount = (UserAccount) session.getAttribute("loggedUser");
                if (!(userAccount.getAccountType() == AccountType.ADMIN)) {
                    request.getRequestDispatcher("/WEB-INF/views/pages/error/permissionsError.jsp").include(request, response);
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
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
