package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Snack;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface VMServiceLayer {
    
    public List<Snack> getSnackList();                  // retrieve snacks list
    public Snack getIdNum(int snackIdNum);             // get snack by id #
                                                      // buy snack
    public String purchaseSnack(int snackIdNum, BigDecimal userCash) throws NoInventoryException, InsufficientFundsException;
                                                    // change return
    public Change snacksChgRtn(BigDecimal userCash, BigDecimal snackCost);
}