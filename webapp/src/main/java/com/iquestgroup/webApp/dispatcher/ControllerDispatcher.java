package com.iquestgroup.webApp.dispatcher;

import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;
import com.iquestgroup.webApp.controllers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dispatcher used to dispatch HttpRequests to appropriate controllers.
 *
 * @author Stefan Pamparau
 */
@Component("ControllerDispatcher")
public class ControllerDispatcher implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private AuthorController authorController;
    @Autowired
    private BookController bookController;
    @Autowired
    private ClientAccountController clientAccountController;
    @Autowired
    private ClientController clientController;
    @Autowired
    private LoginController loginController;
    @Autowired
    private PurchaseController purchaseController;

    private List<AbstractController> controllers;

    /**
     * Dispatches based of the request URL and annotated methods in the controllers list.
     *
     * @param request  - Http request from client
     * @param response - Http response to client
     */
    public void dispatch(HttpServletRequest request, HttpServletResponse response) {
        String requestPath = request.getRequestURI();

        for (AbstractController controller : controllers) {
            Class<? extends AbstractController> controllerClass = controller.getClass();
            Method[] controllerMethods = controllerClass.getMethods();

            for (Method method : controllerMethods) {
                Annotation[] methodAnnotations = method.getAnnotations();

                for (Annotation currentAnnotation : methodAnnotations) {
                    if (currentAnnotation.annotationType().equals(Mapping.class)) {
                        Mapping mapping = (Mapping) currentAnnotation;
                        String path = removeMatrixVariables(transformRequestURI(request.getRequestURI()));
                        if (path.matches(mapping.path()) && checkHttpMethodMapping(request, mapping)) {
                            try {
                                method.invoke(getControllerInstance(controllerClass), request, response);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
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

    /**
     * Removes the first part of the request uri.
     *
     * Ex. input uri : /store/authors/list
     * output uri: /authors/list
     *
     * @param requestURI - the request uri
     * @return - the transformed request uri
     */
    private String transformRequestURI(String requestURI) {
        return requestURI.substring(requestURI.indexOf("/", 1));
    }

    /**
     * Checks if the controller method type is mapped to the request http method type.
     *
     * @param request - incoming request
     * @param mapping - annotation which contains the controller method maping information
     * @return - true if the controller method type is equal to the incoming request http method type
     */
    private boolean checkHttpMethodMapping(HttpServletRequest request, Mapping mapping) {
        return HttpMethodType.valueOf(request.getMethod().toUpperCase()) == mapping.method();
    }

    /**
     * Returns the instance of the controller from which the handler method will be invoked.
     *
     * @param controllerClass - class of the controller which is mapped to the specifiec request
     * @return - the corresponding controller instance
     */
    private Object getControllerInstance(Class<? extends AbstractController> controllerClass) {
        for (AbstractController controller : controllers) {
            if (controller.getClass().equals(controllerClass)) {
                return controller;
            }
        }

        return null;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        controllers = new ArrayList<>();
        controllers.add(authorController);
        controllers.add(bookController);
        controllers.add(clientAccountController);
        controllers.add(clientController);
        controllers.add(loginController);
        controllers.add(purchaseController);
    }
}
