package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.User;
import com.iquestgroup.service.UserService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Component("ClientController")
public class ClientController extends AbstractController {
    @Autowired
    private UserService userService;

    @Mapping(path = "/clients/list", method = HttpMethodType.GET)
    public void listAllClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("clients", userService.getAllUsers());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clients/listClients.jsp").include(request, response);
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertClientForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("client", new User());
        return "store/clients/insertUser";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClient(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("store/clients/insertUser");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = userService.insertUser(user);

            mav.addObject("message", result);
            mav.addObject("clients", userService.getAllUsers());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayDeleteClientForm(HttpServletRequest request, HttpServletResponse response) {
        return "store/clients/deleteUser";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteClientByIdFromRequestParam(HttpServletRequest request, HttpServletResponse response, @RequestParam("clientId") Integer clientId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        deleteClient(clientId, mav);

        return mav;
    }

    @RequestMapping(path = "/delete/{clientId}", method = RequestMethod.GET)
    public ModelAndView deleteClientByUrlID(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        deleteClient(clientId, mav);

        return mav;
    }

    private void deleteClient(@RequestParam("clientId") Integer clientId, ModelAndView mav) {
        try {
            String result = userService.deleteUser(clientId);

            mav.addObject("message", result);
            mav.addObject("clients", userService.getAllUsers());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
