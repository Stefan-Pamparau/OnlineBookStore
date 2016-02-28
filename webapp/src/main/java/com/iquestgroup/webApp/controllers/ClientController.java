package com.iquestgroup.webApp.controllers;

import com.iquestgroup.facade.ClientFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller("ClientController")
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    private ClientFacade clientFacade;

    @RequestMapping(path = "/list", method = RequestMethod.GET) public ModelAndView listAllClients() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            mav.addObject("clients", clientFacade.listAllClients());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET) public String displayInsertClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "store/clients/insertClient";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClient(@Valid @ModelAttribute Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("store/clients/insertClient");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = clientFacade.insertClient(client);

            mav.addObject("message", result);
            mav.addObject("clients", clientFacade.listAllClients());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET) public String displayDeleteClientForm() {
        return "store/clients/deleteClient";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteClient(@RequestParam("clientID") Integer clientID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = clientFacade.deleteClient(clientID);

            mav.addObject("message", result);
            mav.addObject("clients", clientFacade.listAllClients());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/delete/{clientID}", method = RequestMethod.GET)
    public ModelAndView deleteClientByUrlID(@PathVariable Integer clientID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = clientFacade.deleteClient(clientID);

            mav.addObject("message", result);
            mav.addObject("clients", clientFacade.listAllClients());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/purchase", method = RequestMethod.GET)
    public String displayPurchaseBookForm() {
        return "store/clients/purchaseBook";
    }

    @RequestMapping(path = "/purchase/{clientId}", method = RequestMethod.GET)
    public String displayPurchaseBookFormWithRetrievedClientId(@PathVariable Integer clientId, Model model) {
        model.addAttribute("clientId", clientId);

        return "store/clients/purchaseBook";
    }

    @RequestMapping(path = "/purchase", method = RequestMethod.POST)
    public ModelAndView purchaseBook(@RequestParam("clientID") Integer clientID,
        @RequestParam("bookID") Integer bookID) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clients/listClients");

        try {
            String result = clientFacade.purchaseBook(clientID, bookID);

            mav.addObject("message", result);
            mav.addObject("clients", clientFacade.listAllClients());
        } catch (FacadeException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
