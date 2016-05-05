package com.iquestgroup.webApp.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Handles all incoming requests and forwards to a dispatcher which dispatches to appropriate controllers.
 *
 * @author Stefan Pamparau
 */
public class FrontController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
