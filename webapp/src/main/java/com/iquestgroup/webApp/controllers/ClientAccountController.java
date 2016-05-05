package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.Client;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.service.ClientAccountService;
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

/**
 * Controller mapped to respond to client account requests.
 *
 * @author Controller
 */
@Component("ClientAccountController")
@RequestMapping(path = "/clientAccounts")
public class ClientAccountController extends AbstractController {
    @Autowired
    ClientService clientService;
    @Autowired
    private ClientAccountService clientAccountService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllClientAccounts(HttpServletRequest request, HttpServletResponse response) {
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
    public ModelAndView listClientAccountsByClientId(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientId) {
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
    public String displayInsertClientAccountForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("clientAccount", new ClientAccount());
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert/{clientId}", method = RequestMethod.GET)
    public String displayInsertClientAccountForm(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientId, Model model) {
        model.addAttribute("clientAccount", new ClientAccount());
        model.addAttribute("clientId", clientId);
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClientAccount(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute ClientAccount clientAccount, BindingResult bindingResult, @RequestParam("clientId") Integer clientId) {
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
    public String displayDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        return "store/clientAccounts/deleteClientAccount";
    }

    @RequestMapping(path = "delete", method = RequestMethod.POST)
    public ModelAndView deleteClientAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam("clientAccountId") Integer clientAccountId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        deleteClientAccount(clientAccountId, mav);

        return mav;
    }

    @RequestMapping(path = "delete/{clientAccountId}", method = RequestMethod.GET)
    public ModelAndView deleteClientAccountByIdFromRequestUrl(HttpServletRequest request, HttpServletResponse response, @PathVariable("clientAccountId") Integer clientAccountId) {
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
    public String displayAddBalanceForm(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientAccountId, Model model) {
        model.addAttribute("clientAccountId", clientAccountId);
        return "store/clientAccounts/addBalance";
    }

    @RequestMapping(path = "addBalance", method = RequestMethod.POST)
    public ModelAndView addBalance(HttpServletRequest request, HttpServletResponse response, @RequestParam("clientAccountId") Integer clientAccountId, @RequestParam("balance") Integer balance) {
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
