package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Client;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.ClientService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Controller mapped to respond to client account requests.
 *
 * @author Controller
 */
@Controller("ClientAccountController")
@RequestMapping(path = "/clientAccounts")
public class ClientAccountController {
    @Autowired
    ClientService clientService;
    @Autowired
    private ClientAccountService clientAccountService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllClientAccounts() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        try {
            mav.addObject("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/list/{clientId}", method = RequestMethod.GET)
    public ModelAndView listClientAccountsByClientId(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        try {
            mav.addObject("clientAccounts", clientAccountService.getClientAccounts(clientId));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public String displayInsertClientAccountForm(Model model) {
        model.addAttribute("clientAccount", new ClientAccount());
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert/{clientId}", method = RequestMethod.GET)
    public String displayInsertClientAccountForm(@PathVariable Integer clientId, Model model) {
        model.addAttribute("clientAccount", new ClientAccount());
        model.addAttribute("clientId", clientId);
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClientAccount(@Valid @ModelAttribute ClientAccount clientAccount, BindingResult bindingResult, @RequestParam("clientId") Integer clientId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("store/clientAccounts/insertClientAccount");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        try {
            Client client = clientService.getClientById(clientId);
            clientAccount.setClient(client);
            String result = clientAccountService.insertClientAccount(clientAccount);

            mav.addObject("message", result);
            mav.addObject("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "delete", method = RequestMethod.GET)
    public String displayDeleteForm() {
        return "store/clientAccounts/deleteClientAccount";
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public ModelAndView deleteClientAccount(@RequestParam("clientAccountId") Integer clientAccountId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        deleteClientAccount(clientAccountId, mav);

        return mav;
    }

    @RequestMapping(path = "delete/{clientAccountId}", method = RequestMethod.GET)
    public ModelAndView deleteClientAccountByIdFromRequestUrl(@PathVariable("clientAccountId") Integer clientAccountId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        deleteClientAccount(clientAccountId, mav);

        return mav;
    }

    private void deleteClientAccount(Integer clientAccountId, ModelAndView mav) {
        try {
            String result = clientAccountService.deleteClientAccount(clientAccountId);

            mav.addObject("message", result);
            mav.addObject("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "addBalance/{clientAccountId}", method = RequestMethod.GET)
    public String displayAddBalanceForm(@PathVariable Integer clientAccountId, Model model) {
        model.addAttribute("clientAccountId", clientAccountId);
        return "store/clientAccounts/addBalance";
    }

    @RequestMapping(path = "addBalance", method = RequestMethod.POST)
    public ModelAndView addBalance(@RequestParam("clientAccountId") Integer clientAccountId, @RequestParam("balance") Integer balance) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        try {
            String result = clientAccountService.addBalance(clientAccountId, balance);

            mav.addObject("message", result);
            mav.addObject("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
