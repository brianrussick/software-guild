package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Snack;
import com.sg.vendingmachinespringmvc.model.UserSelection;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.NoInventoryException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sg.vendingmachinespringmvc.service.VMServiceLayer;

/**
 *
 * @author brian russick
 */
@Controller
public class VMController {

    VMServiceLayer service;

    @Inject
    public VMController(VMServiceLayer service) {
        this.service = service;
    }

    private Change calcChg(BigDecimal userCash, BigDecimal snackCost) {
               // equals snacks change return in the service, return change due
        Change chgDue = service.snacksChgRtn(userCash, snackCost);
        return chgDue;
    }

    private BigDecimal updateUserTotal(BigDecimal selectionUserTotal) {
        BigDecimal userTotal = selectionUserTotal;
        if (selectionUserTotal == null) { // form total is null equals bd "0"
            userTotal = new BigDecimal("0");
        }
        return userTotal;
    }    
                // receive user request & return model/model attribute to index
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewSnacks(HttpServletRequest request, Model model, @ModelAttribute("userSelection") UserSelection selection) {
        List<Snack> snackList = service.getSnackList();
        model.addAttribute("snackList", snackList);
        return "index";
    }
                                                  // user total sent on request
    @RequestMapping(value = "/selectSnack", method = RequestMethod.GET)
    public String selectSnack(HttpServletRequest request, RedirectAttributes redirectAttrs) {
                                                      // retrieve snack details
        String idNum = request.getParameter("snackIdNum");
        String userTotal = request.getParameter("userTotal");
                            // if total > 0 redirect attributes for flash value
            if (userTotal.length() > 0) {                         
            UserSelection selection = new UserSelection();
            BigDecimal total = new BigDecimal(userTotal);
            selection.setUserTotal(total);
            redirectAttrs.addFlashAttribute("userSelection", selection);
            }
            int snackIdNum = Integer.parseInt(idNum); // parse id #
            Snack snack = service.getIdNum(snackIdNum); //retrieve snack by id #                                                    
            redirectAttrs.addFlashAttribute("snack", snack); // redirect to jsp
            return "redirect:/";
    }
                                            // form value calculates parameters
    @RequestMapping(value = "/calculate", params = "dollarButton", method = RequestMethod.POST)
    public String addDollar(@ModelAttribute("userSelection") UserSelection selection, RedirectAttributes redirectAttrs) {

        BigDecimal userTotal = updateUserTotal(selection.getUserTotal());
        BigDecimal dollar = new BigDecimal("1.00"); // dollar = $1.00
        BigDecimal currentTotal = userTotal.add(dollar);
        selection.setUserTotal(currentTotal);
        redirectAttrs.addFlashAttribute("userSelection", selection);
        return "redirect:/";
    }

    @RequestMapping(value = "/calculate", params = "quarterButton", method = RequestMethod.POST)
    public String addQuarter(@ModelAttribute("userSelection") UserSelection selection, RedirectAttributes redirectAttrs) {
        BigDecimal userTotal = updateUserTotal(selection.getUserTotal());
        BigDecimal quarter = new BigDecimal("0.25"); // quarter = $0.25
        BigDecimal currentTotal = userTotal.add(quarter);
        selection.setUserTotal(currentTotal);
        redirectAttrs.addFlashAttribute("userSelection", selection);
        return "redirect:/";
    }

    @RequestMapping(value = "/calculate", params = "dimeButton", method = RequestMethod.POST)
    public String addDime(@ModelAttribute("userSelection") UserSelection selection, RedirectAttributes redirectAttrs) {
        BigDecimal userTotal = updateUserTotal(selection.getUserTotal());
        BigDecimal dime = new BigDecimal("0.10"); // dime = $0.10
        BigDecimal currentTotal = userTotal.add(dime);
        selection.setUserTotal(currentTotal);
        redirectAttrs.addFlashAttribute("userSelection", selection);
        return "redirect:/";
    }

    @RequestMapping(value = "/calculate", params = "nickelButton", method = RequestMethod.POST)
    public String addNickel(@ModelAttribute("userSelection") UserSelection selection, RedirectAttributes redirectAttrs) {
        BigDecimal userTotal = updateUserTotal(selection.getUserTotal());
        BigDecimal nickel = new BigDecimal("0.05"); // nickel = $0.05
        BigDecimal currentTotal = userTotal.add(nickel);
        selection.setUserTotal(currentTotal);
        redirectAttrs.addFlashAttribute("userSelection", selection);
        return "redirect:/";
    }

    @RequestMapping(value = "/calculate", params = "purchaseSnack", method = RequestMethod.POST)
    public String purchaseSnack(@ModelAttribute("userSelection") @Valid UserSelection selection, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
                                         // binding result used for validation
        if (result.hasErrors()) { // if errors get snack list and return index
            List<Snack> snackList = service.getSnackList();
            model.addAttribute("userSelection", selection);
            model.addAttribute("snackList", snackList);
            return "index";
        }
        String idNum = selection.getSnackChoice();
        int snackIdNum = Integer.parseInt(idNum); // parse id #
        Snack purchase = service.getIdNum(snackIdNum); //get snack from service
        String message;
        BigDecimal userCash = selection.getUserTotal();

        try {
            message = service.purchaseSnack(snackIdNum, userCash);
            Change change = calcChg(userCash, purchase.getSnackCost());
            selection.setChange(change.toString());
            selection.setUserTotal(BigDecimal.ZERO);

        } catch (InsufficientFundsException | NoInventoryException e) {
            message = e.getMessage();   // catch errors and display in message
            }
        selection.setMessage(message);
        redirectAttrs.addFlashAttribute("userSelection", selection);
        return "redirect:/";
    }

    @RequestMapping(value = "/calculate", params = "snacksChgRtn", method = RequestMethod.POST)
    public String snacksChgRtn(@ModelAttribute("userSelection") UserSelection selection, RedirectAttributes redirectAttrs) {
        BigDecimal userTotal = selection.getUserTotal();
        int snackChg = userTotal.compareTo(BigDecimal.ZERO);

        if (snackChg >= 1) {
                                                    // create new change object
            UserSelection selectionToReturn = new UserSelection();
            Change chgDue = calcChg(selection.getUserTotal(), BigDecimal.ZERO);
            selectionToReturn.setChange(chgDue.toString());
            redirectAttrs.addFlashAttribute("userSelection", selectionToReturn);
            }
        return "redirect:/";
    }
}