package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Snack;
import java.util.List;

/**
 *
 * @author brian russick
 */
public interface VMDao {    
    
    public List<Snack> getSnackList();                  // retrieve all snacks                                                     
    public Snack purchaseSnack(int snackIdNum);        // buy snack                                                   
    public Snack getIdNum(int snackIdNum);            // retrieve snack by id #
}