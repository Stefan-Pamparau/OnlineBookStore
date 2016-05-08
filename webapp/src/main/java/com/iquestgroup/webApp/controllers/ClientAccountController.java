package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.AccountType;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Mapping(path = "/clientAccounts/list/\\d+", method = HttpMethodType.GET)
    public void listClientAccountsByClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
            request.setAttribute("clientAccounts", clientAccountService.getClientAccounts(Integer.parseInt(parts[parts.length - 1])));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/insert", method = HttpMethodType.GET)
    public void displayInsertClientAccountForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/insertClientAccount.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/insert/\\d+", method = HttpMethodType.GET)
    public void displayInsertClientAccountFormWithSpecifiedClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
        request.setAttribute("clientAccount", new UserAccount());
        request.setAttribute("clientId", parts[parts.length - 1]);
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/insertClientAccount.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/insert", method = HttpMethodType.POST)
    public void insertClientAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = userService.getUserById(Integer.parseInt(request.getParameter("clientId")));
            ClientAccount clientAccount = new ClientAccount();
            clientAccount.setUser(user);
            clientAccount.setEmail(request.getParameter("email"));
            clientAccount.setPassword(request.getParameter("password"));
            clientAccount.setBalance(Integer.parseInt(request.getParameter("balance")));
            clientAccount.setAccountType(AccountType.CLIENT);
            String result = clientAccountService.insertClientAccount(clientAccount);

            request.setAttribute("message", result);
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/delete", method = HttpMethodType.GET)
    public void displayDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/deleteClientAccount.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/delete", method = HttpMethodType.POST)
    public void deleteClientAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        deleteClientAccount(Integer.parseInt(request.getParameter("clientAccountId")), request);

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/delete/\\d+", method = HttpMethodType.GET)
    public void deleteClientAccountByIdFromRequestUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
        deleteClientAccount(Integer.parseInt(parts[parts.length - 1]), request);

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
    }

    private void deleteClientAccount(Integer clientAccountId, HttpServletRequest request) {
        try {
            String result = clientAccountService.deleteClientAccount(clientAccountId);

            request.setAttribute("message", result);
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Mapping(path = "/clientAccounts/addBalance/\\d+", method = HttpMethodType.GET)
    public void displayAddBalanceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
        request.setAttribute("clientAccountId", parts[parts.length - 1]);
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/addBalance.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/addBalance", method = HttpMethodType.POST)
    public void addBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String result = clientAccountService.addBalance(Integer.parseInt(request.getParameter("clientAccountId")), Integer.parseInt(request.getParameter("balance")));

            request.setAttribute("message", result);
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
    }
}
