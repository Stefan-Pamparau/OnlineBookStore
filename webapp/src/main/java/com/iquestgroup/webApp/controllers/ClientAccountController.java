package com.iquestgroup.webApp.controllers;

import com.iquestgroup.model.AccountType;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.User;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.UserService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private static final Logger logger = Logger.getLogger(ClientAccountController.class);

    @Autowired
    UserService userService;
    @Autowired
    private ClientAccountService clientAccountService;

    @Mapping(path = "/clientAccounts/list", method = HttpMethodType.GET)
    public void listAllClientAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.debug("Retrieving all client accounts from the database");
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/clientAccounts/list/\\d+", method = HttpMethodType.GET)
    public void listClientAccountsByClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

            logger.info("Retrieving all client accounts whose user has the id: " + parts[parts.length - 1]);
            request.setAttribute("clientAccounts", clientAccountService.getClientAccounts(Integer.parseInt(parts[parts.length - 1])));
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/clientAccounts/insert", method = HttpMethodType.GET)
    public void displayInsertClientAccountForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying client account insert form");
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/insertClientAccount.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/insert/\\d+", method = HttpMethodType.GET)
    public void displayInsertClientAccountFormWithSpecifiedClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

        logger.debug("Displaying client account insert form with client id: " + parts[parts.length - 1]);
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

            logger.info("Inserting client account info: " + clientAccount);
            String result = clientAccountService.insertClientAccount(clientAccount);

            request.setAttribute("message", result);
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());

            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

    }

    @Mapping(path = "/clientAccounts/delete", method = HttpMethodType.GET)
    public void displayDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying client account delete form");
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/deleteClientAccount.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/delete", method = HttpMethodType.POST)
    public void deleteClientAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String clientAccountId = request.getParameter("clientAccountId");

            logger.info("Deleting client account with id: " + clientAccountId);
            deleteClientAccount(Integer.parseInt(clientAccountId), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/clientAccounts/delete/\\d+", method = HttpMethodType.GET)
    public void deleteClientAccountByIdFromRequestUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

            logger.info("Deleting client account with id: " + parts[parts.length - 1]);
            deleteClientAccount(Integer.parseInt(parts[parts.length - 1]), request);
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private void deleteClientAccount(Integer clientAccountId, HttpServletRequest request) throws ServiceException {
        String result = clientAccountService.deleteClientAccount(clientAccountId);

        request.setAttribute("message", result);
        request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
    }

    @Mapping(path = "/clientAccounts/addBalance/\\d+", method = HttpMethodType.GET)
    public void displayAddBalanceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

        logger.info("Displaying add balance form for account with id:" + parts[parts.length - 1]);
        request.setAttribute("clientAccountId", parts[parts.length - 1]);
        request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/addBalance.jsp").include(request, response);
    }

    @Mapping(path = "/clientAccounts/addBalance", method = HttpMethodType.POST)
    public void addBalance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String clientAccountId = request.getParameter("clientAccountId");
            String balance = request.getParameter("balance");

            logger.info("Adding balance: " + balance + " account with id: " + clientAccountId);
            String result = clientAccountService.addBalance(Integer.parseInt(clientAccountId), Integer.parseInt(balance));

            request.setAttribute("message", result);
            request.setAttribute("clientAccounts", clientAccountService.getAllClientAccounts());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/clientAccounts/listClientAccounts.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
