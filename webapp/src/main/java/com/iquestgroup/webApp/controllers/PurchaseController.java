package com.iquestgroup.webApp.controllers;

import com.iquestgroup.service.PurchaseService;
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
 * Controller mapped to purchase requests.
 *
 * @author Stefan Pamparau
 */
@Component("PurchaseController")
public class PurchaseController extends AbstractController {

    private static final Logger logger = Logger.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;

    @Mapping(path = "/purchases/list", method = HttpMethodType.GET)
    public void listAllPurchases(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.debug("Retrieving all purchases from database");
            request.setAttribute("purchases", purchaseService.getAllPurchases());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/purchases/list/\\d+", method = HttpMethodType.GET)
    public void listAllPurchasesForClientAccountId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

            logger.info("Retrieving all purchases for account with id: " + parts[parts.length - 1]);
            request.setAttribute("purchases", purchaseService.getPurchasesByClientAccountId(Integer.parseInt(parts[parts.length - 1])));
            request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    @Mapping(path = "/purchases/purchase", method = HttpMethodType.GET)
    public void displayPurchaseBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Displaying purchase book form");
        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/purchaseBook.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/purchase/\\d+", method = HttpMethodType.GET)
    public void displayPurchaseBookFormWithRetrievedClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

        logger.info("Displaying purchase book form for client with id: " + parts[parts.length - 1]);
        request.setAttribute("clientAccountId", parts[parts.length - 1]);

        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/purchaseBook.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/purchase", method = HttpMethodType.POST)
    public void purchaseBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String clientAccountId = request.getParameter("clientAccountId");
            String bookId = request.getParameter("bookId");

            logger.info("Purchasing book with id: " + bookId + " for client account with id: " + clientAccountId);
            String result = purchaseService.purchaseBook(Integer.parseInt(clientAccountId), Integer.parseInt(bookId));

            request.setAttribute("message", result);
            request.setAttribute("purchases", purchaseService.getAllPurchases());
            request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
