package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.User;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.ClientAccountService;
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

/**
 * Controller mapped to respond to client account requests.
 *
 * @author Controller
 */
@Component("ClientAccountController")
public class ClientAccountController extends AbstractController {
    @Autowired
    UserService userService;
    @Autowired
    private ClientAccountService clientAccountService;

    @Mapping(path = "/clientAccounts/list", method = HttpMethodType.GET)
    public void listAllClientAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
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
        model.addAttribute("clientAccount", new UserAccount());
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert/{clientId}", method = RequestMethod.GET)
    public String displayInsertClientAccountForm(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientId, Model model) {
        model.addAttribute("clientAccount", new UserAccount());
        model.addAttribute("clientId", clientId);
        return "store/clientAccounts/insertClientAccount";
    }

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ModelAndView insertClientAccount(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute UserAccount userAccount, BindingResult bindingResult, @RequestParam("clientId") Integer clientId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("store/clientAccounts/insertClientAccount");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/clientAccounts/listClientAccounts");

        try {
            User user = userService.getUserById(clientId);
            userAccount.setUser(user);
            String result = clientAccountService.insertClientAccount((ClientAccount) userAccount);

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
