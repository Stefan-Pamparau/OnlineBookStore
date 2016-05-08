package com.iquestgroup.webApp.controllers;

import com.iquestgroup.service.PurchaseService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import com.iquestgroup.webApp.annotations.HttpMethodType;
import com.iquestgroup.webApp.annotations.Mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @Autowired
    private PurchaseService purchaseService;

    @Mapping(path = "/purchases/list", method = HttpMethodType.GET)
    public void listAllPurchases(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("purchases", purchaseService.getAllPurchases());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/list/\\d+", method = HttpMethodType.GET)
    public void listAllPurchasesForClientAccountId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");

        try {
            request.setAttribute("purchases", purchaseService.getPurchasesByClientAccountId(Integer.parseInt(parts[parts.length - 1])));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/purchase", method = HttpMethodType.GET)
    public void displayPurchaseBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/purchaseBook.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/purchase/\\d+", method = HttpMethodType.GET)
    public void displayPurchaseBookFormWithRetrievedClientId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] parts = request.getRequestURI().substring(request.getContextPath().length()).split("/");
        request.setAttribute("clientAccountId", parts[parts.length - 1]);

        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/purchaseBook.jsp").include(request, response);
    }

    @Mapping(path = "/purchases/purchase", method = HttpMethodType.POST)
    public void purchaseBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String result = purchaseService.purchaseBook(Integer.parseInt(request.getParameter("clientAccountId")), Integer.parseInt(request.getParameter("bookId")));

            request.setAttribute("message", result);
            request.setAttribute("purchases", purchaseService.getAllPurchases());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/views/pages/store/purchases/listPurchases.jsp").include(request, response);
    }
}
