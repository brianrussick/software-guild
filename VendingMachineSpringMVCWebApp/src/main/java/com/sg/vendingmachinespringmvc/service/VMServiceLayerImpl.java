package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Snack;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import com.sg.vendingmachinespringmvc.dao.VMDao;

/**
 *
 * @author brian russick
 */
public class VMServiceLayerImpl implements VMServiceLayer {

    VMDao dao;

    @Inject
    public VMServiceLayerImpl(VMDao dao) {
        this.dao = dao;
    }

    @Override
    public Snack getIdNum(int snackIdNum) {
        return dao.getIdNum(snackIdNum);
    }

    @Override
    public List<Snack> getSnackList() {
        return dao.getSnackList();
    }
    
    @Override
    public String purchaseSnack(int snackIdNum, BigDecimal userCash) throws NoInventoryException, InsufficientFundsException {
        Snack snack = dao.getIdNum(snackIdNum);
        if (snack.getSnackStockNum() <= 0) {
            throw new NoInventoryException("SOLD OUT!!!");
        }
        BigDecimal snackCost = snack.getSnackCost();
        if (userCash.compareTo(snackCost) >= 0) {
            dao.purchaseSnack(snackIdNum);
        } else {
            BigDecimal chgDue = snack.getSnackCost().subtract(userCash);
            throw new InsufficientFundsException("Please insert: $" + chgDue.toString());
        }
        return "Thank you!!!";
    }

    @Override
    public Change snacksChgRtn(BigDecimal userCash, BigDecimal snackCost) {

        BigDecimal bdChg = userCash.subtract(snackCost);
        BigDecimal bdOneHundred = new BigDecimal("100");
        BigDecimal bdCalculateChg = bdChg.multiply(bdOneHundred);
        BigDecimal convertChg = bdCalculateChg.setScale(0);

        String chgString = convertChg.toString();
        Integer change = Integer.parseInt(chgString);

        Integer quarters = 0;
        Integer dimes = 0;
        Integer nickels = 0;
        Integer pennies = 0;
        Integer chgBalance = 0;

        if ((change / 25) >= 1) {
            quarters = change / 25;
            chgBalance = change % 25;

        if ((chgBalance / 10) >= 1) {
            dimes = chgBalance / 10;
            chgBalance = chgBalance % 10;
        }
        if ((chgBalance / 5) >= 1) {
            nickels = chgBalance / 5;
            chgBalance = chgBalance % 5;
            }
        } else if ((change / 10) >= 1) {
            dimes = change / 10;
            chgBalance = change % 10;

        if ((chgBalance / 5) >= 1) {
            nickels = chgBalance / 5;
            chgBalance = chgBalance % 5;
            }
        } else if ((change / 5) >= 1) {
            nickels = change / 5;
            chgBalance = change % 5;
        } else {
            chgBalance = change;
        }
        pennies = chgBalance;
        Change chgRtn = new Change();
        chgRtn.setQuarter(quarters);
        chgRtn.setDime(dimes);
        chgRtn.setNickel(nickels);
        chgRtn.setPenny(pennies);
        return chgRtn;
    }
}