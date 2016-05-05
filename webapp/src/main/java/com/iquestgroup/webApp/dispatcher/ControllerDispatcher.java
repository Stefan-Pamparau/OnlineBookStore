package com.iquestgroup.webApp.dispatcher;

import com.iquestgroup.webApp.controllers.AbstractController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dispatcher used to dispatch HttpRequests to appropriate controllers.
 *
 * @author Stefan Pamparau
 */
public class ControllerDispatcher {
    private List<AbstractController> controllers;

    /**
     * Dispatches based of the request URL and annotated methods in the controllers list.
     *
     * @param request  - Http request from client
     * @param response - Http response to client
     */
    public void dispatch(HttpServletRequest request, HttpServletResponse response) {
        // TODO : implement annotation processing
    }

    public List<AbstractController> getControllers() {
        return controllers;
    }

    public void setControllers(List<AbstractController> controllers) {
        this.controllers = controllers;
    }
}
