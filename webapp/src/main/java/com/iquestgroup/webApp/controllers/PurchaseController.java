package com.iquestgroup.webApp.controllers;

import com.iquestgroup.service.PurchaseService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller mapped to purchase requests.
 *
 * @author Stefan Pamparau
 */
@Component("PurchaseController")
@RequestMapping(path = "purchases")
public class PurchaseController extends AbstractController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView listAllPurchases(HttpServletRequest request, HttpServletResponse response, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/purchases/listPurchases");

        try {
            mav.addObject("purchases", purchaseService.getAllPurchases());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/list/{clientAccountId}", method = RequestMethod.GET)
    public ModelAndView listAllPurchasesForClientAccountId(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientAccountId, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/purchases/listPurchases");

        try {
            mav.addObject("purchases", purchaseService.getPurchasesByClientAccountId(clientAccountId));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(path = "/purchase", method = RequestMethod.GET)
    public String displayPurchaseBookForm(HttpServletRequest request, HttpServletResponse response) {
        return "store/purchases/purchaseBook";
    }

    @RequestMapping(path = "/purchase/{clientAccountId}", method = RequestMethod.GET)
    public String displayPurchaseBookFormWithRetrievedClientId(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer clientAccountId, Model model) {
        model.addAttribute("clientAccountId", clientAccountId);

        return "store/purchases/purchaseBook";
    }

    @RequestMapping(path = "/purchase", method = RequestMethod.POST)
    public ModelAndView purchaseBook(HttpServletRequest request, HttpServletResponse response, @RequestParam("clientAccountId") Integer clientAccountId,
                                     @RequestParam("bookId") Integer bookId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("store/purchases/listPurchases");

        try {
            String result = purchaseService.purchaseBook(clientAccountId, bookId);

            mav.addObject("message", result);
            mav.addObject("purchases", purchaseService.getAllPurchases());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return mav;
    }
}
