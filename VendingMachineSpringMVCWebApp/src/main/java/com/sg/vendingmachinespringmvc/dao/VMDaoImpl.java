package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Snack;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brian russick
 */
public class VMDaoImpl implements VMDao {

    private Map<Integer, Snack> snacksMap = new HashMap<>();

    @Override
    public Snack purchaseSnack(int snackIdNum) {
        Snack userBuysSnack = snacksMap.get(snackIdNum);
        int currentNumSnackInStock = userBuysSnack.getSnackStockNum();
              // subtract 1 from the current # of snack in stock chosen by user
        userBuysSnack.setSnackStockNum(currentNumSnackInStock - 1); 
        return userBuysSnack;
    }

    @Override
    public List<Snack> getSnackList() {       // retrieve and return snack list
        List<Snack> snackList = new ArrayList<>(snacksMap.values());
                                                        // sorts snacks by id # 
        snackList.sort(Comparator.comparing(Snack::getSnackIdNum));
        return snackList;
    }

    @Override
    public Snack getIdNum(int snackIdNum) {           // retrieve snack by id #
        return snacksMap.get(snackIdNum);            // return snacks map
    }
    
    public VMDaoImpl() {    // set all snack details & associate with snacks map
        Snack snickers = new Snack();
        snickers.setSnackName("Snickers");
        snickers.setSnackStockNum(9);
        snickers.setSnackCost(new BigDecimal("1.85"));
        snickers.setSnackIdNum(1);
        snacksMap.put(1, snickers);

        Snack mms = new Snack();
        mms.setSnackName("M & Ms");
        mms.setSnackStockNum(2);
        mms.setSnackCost(new BigDecimal("1.50"));
        mms.setSnackIdNum(2);
        snacksMap.put(2, mms);

        Snack pringles = new Snack();
        pringles.setSnackName("Pringles");
        pringles.setSnackStockNum(5);
        pringles.setSnackCost(new BigDecimal("2.10"));
        pringles.setSnackIdNum(3);
        snacksMap.put(3, pringles);

        Snack reeses = new Snack();
        reeses.setSnackName("Reese's");
        reeses.setSnackStockNum(4);
        reeses.setSnackCost(new BigDecimal("1.85"));
        reeses.setSnackIdNum(4);
        snacksMap.put(4, reeses);

        Snack pretzels = new Snack();
        pretzels.setSnackName("Pretzels");
        pretzels.setSnackStockNum(9);
        pretzels.setSnackCost(new BigDecimal("1.25"));
        pretzels.setSnackIdNum(5);
        snacksMap.put(5, pretzels);

        Snack twinkies = new Snack();
        twinkies.setSnackName("Twinkies");
        twinkies.setSnackStockNum(3);
        twinkies.setSnackCost(new BigDecimal("1.95"));
        twinkies.setSnackIdNum(6);
        snacksMap.put(6, twinkies);

        Snack doritos = new Snack();
        doritos.setSnackName("Doritos");
        doritos.setSnackStockNum(11);
        doritos.setSnackCost(new BigDecimal("1.75"));
        doritos.setSnackIdNum(7);
        snacksMap.put(7, doritos);

        Snack almondJoy = new Snack();
        almondJoy.setSnackName("Almond Joy");
        almondJoy.setSnackStockNum(0);
        almondJoy.setSnackCost(new BigDecimal("1.85"));
        almondJoy.setSnackIdNum(8);
        snacksMap.put(8, almondJoy);

        Snack trident = new Snack();
        trident.setSnackName("Trident");
        trident.setSnackStockNum(6);
        trident.setSnackCost(new BigDecimal("1.95"));
        trident.setSnackIdNum(9);
        snacksMap.put(9, trident);
    }
}