package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Client;
import com.iquestgroup.service.ClientService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Component("ClientController")
@RequestMapping(path = "/clients")
public class ClientController extends AbstractController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllClients(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            mav.addObject("clients", clientService.getAllClients());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertClientForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("client", new Client());
        return "store/clients/insertClient";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClient(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("store/clients/insertClient");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = clientService.insertClient(client);

            mav.addObject("message", result);
            mav.addObject("clients", clientService.getAllClients());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String displayDeleteClientForm(HttpServletRequest request, HttpServletResponse response) {
        return "store/clients/deleteClient";
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
            String result = clientService.deleteClient(clientId);

            mav.addObject("message", result);
            mav.addObject("clients", clientService.getAllClients());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
