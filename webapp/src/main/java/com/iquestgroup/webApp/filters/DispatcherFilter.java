package com.iquestgroup.webApp.filters;

import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter which maps static resources to the container's default servlet and store related URL's to
 * the FrontController class.
 */
public class DispatcherFilter implements Filter {

    private static final Logger logger = Logger.getLogger(DispatcherFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/static") || path.startsWith("/WEB-INF")) {
            logger.info("Dispatching to container default servlet to server static content");
            chain.doFilter(request, response);
        } else if (path.equals("/")) {
            logger.info("Dispatching to main html");
            request.getRequestDispatcher("/WEB-INF/views/pages/index.jsp").forward(request, response);
        } else {
            logger.info("Dispatching to the applications front controller");
            request.getRequestDispatcher("/store" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
