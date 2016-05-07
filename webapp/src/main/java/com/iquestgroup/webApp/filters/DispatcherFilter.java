package com.iquestgroup.webApp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter which maps static resources to the container's default servlet and
 * store related URL's to the FrontController class.
 */
public class DispatcherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/static") || path.startsWith("/WEB-INF")) {
            chain.doFilter(request, response);
        } else if (path.equals("/")) {
            request.getRequestDispatcher("/WEB-INF/views/pages/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/store" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
