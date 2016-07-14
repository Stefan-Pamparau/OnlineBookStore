package com.iquestgroup.webApp.controllers;

import com.iquestgroup.service.AuthorService;
import com.iquestgroup.webApp.dispatcher.ControllerDispatcher;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles all incoming requests and forwards to a dispatcher which dispatches to appropriate controllers.
 *
 * @author Stefan Pamparau
 */
public class FrontController extends HttpServlet {
    private ControllerDispatcher controllerDispatcher;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controllerDispatcher = (ControllerDispatcher) webApplicationContext.getBean("ControllerDispatcher");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        controllerDispatcher.dispatch(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        controllerDispatcher.dispatch(request, response);
    }
}
