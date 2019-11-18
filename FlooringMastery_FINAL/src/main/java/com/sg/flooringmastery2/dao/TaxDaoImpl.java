package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.StateTaxRates;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author brian russick
 */                               // tax dao implementation implements tax dao
public class TaxDaoImpl implements  TaxDao { 
   
    private static final String TAXES_TXTFILE = "Taxes.txt";
    private static final String DELIMITER = ",";

    @Override
    public void loadAllStateTaxRates() throws PersistenceException {
        loadTaxes();
    }
    
    private List<StateTaxRates> loadTaxes() throws PersistenceException {
        Scanner sc;
        List<StateTaxRates> stateTaxList = new ArrayList<>();

        try {            // create new scanner for reading in state taxes file
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(TAXES_TXTFILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                    "\tERROR - UNABLE TO LOAD STATE TAXES!\n", e);
        }
        String currentLine;
        String[] currentTokens;
        sc.nextLine();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();        // read in next line from file
            currentTokens = currentLine.split(DELIMITER);  // tokenize scanner
            if (currentTokens.length == 2) {
                StateTaxRates state = new StateTaxRates();  // set tax rate
                state.setState(currentTokens[0]);
                state.setTax(new BigDecimal(currentTokens[1]));
                                                           // add state to map
                stateTaxList.add(state);
            } else {}
        }
        sc.close();

        if (stateTaxList.isEmpty()) {
            return null;
        } else {
            return stateTaxList;
        }
    }
    
    @Override
    public StateTaxRates getTaxByState (String state) throws PersistenceException{
        List<StateTaxRates> stateTaxList = loadTaxes();
        if (stateTaxList == null) {
            return null;
        } else {
            StateTaxRates userChoiceState;
            userChoiceState = stateTaxList.stream()
                    .filter(new Predicate<StateTaxRates>() {
                @Override
                public boolean test(StateTaxRates s) {
                    return s.getState().equalsIgnoreCase(state);
                }
            })
                    .findFirst().orElse(null);
            return userChoiceState;
        }
    }    
}
 