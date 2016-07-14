package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.User;
import com.iquestgroup.service.UserService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("ClientController")
public class ClientController extends AbstractController {
    @Autowired
    private UserService userService;

    @Mapping(path = "/clients/list", method = HttpMethodType.GET)
    public void listAllClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("clients", userService.getAllUsers());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/listClients.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/views/pages/error/pageError.jsp").include(request, response);
        }
    }

    @Mapping(path = "/clients/insert", method = HttpMethodType.GET)
    public void displayInsertClientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/insertClient.jsp").include(request, response);
    }

    @Mapping(path = "/clients/insert", method = HttpMethodType.POST)
    public void insertClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setAddress(request.getParameter("address"));
            user.setSerialId(request.getParameter("serialId"));
            String result = userService.insertUser(user);

            request.setAttribute("message", result);
            request.setAttribute("clients", userService.getAllUsers());

            request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/listClients.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/views/pages/error/pageError.jsp").include(request, response);
        }
    }

    @Mapping(path = "/clients/delete", method = HttpMethodType.GET)
    public void displayDeleteClientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/deleteClient.jsp").include(request, response);
    }

    @Mapping(path = "/clients/delete", method = HttpMethodType.POST)
    public void deleteClientByIdFromRequestParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteClient(Integer.parseInt(request.getParameter("clientId")), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/listClients.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/views/pages/error/pageError.jsp").include(request, response);
        }

    }

    @Mapping(path = "/clients/delete/\\d+", method = HttpMethodType.GET)
    public void deleteClientByUrlID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
            deleteClient(Integer.parseInt(parts[parts.length - 1]), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/listClients.jsp").include(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("/WEB-INF/views/pages/error/pageError.jsp").include(request, response);
        }
    }

    private void deleteClient(Integer clientId, HttpServletRequest request) throws ServiceException {
        String result = userService.deleteUser(clientId);

        request.setAttribute("message", result);
        request.setAttribute("clients", userService.getAllUsers());
    }
}
