package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.Product;
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
 */                                              
                                                // product dao implementation
                                               // implements product dao
public class ProductDaoImpl implements ProductDao {
                                                 
    private static final String PRODUCT_TXTFILE = "Products.txt";
    private static final String DELIMITER = ",";

    private List<Product> loadProducts() throws PersistenceException {
        Scanner sc;
        List<Product> prodList = new ArrayList<>();
        try {               // create new scanner for reading in products file
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_TXTFILE)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException(
                "\tERROR - UNABLE TO LOAD PRODUCTS!\n", e);
            }
        String currentLine;
        String[] currentTokens;
        sc.nextLine();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();        // read in next line from file
            currentTokens = currentLine.split(DELIMITER);  // tokenize scanner
            if (currentTokens.length == 3) {
                Product prod = new Product();           // set product details
                prod.setProdType(currentTokens[0]);
                prod.setProdCostPerSqFt(new BigDecimal(currentTokens[1]));
                prod.setLabCostPerSqFt(new BigDecimal(currentTokens[2]));
                                             // add product into product list
                prodList.add(prod);
            } else {
            }
            }
        sc.close();
        if (prodList.isEmpty()) {
            return null;
        } else {
            return prodList;
        }
    }

    @Override
    public void loadProdAllTypes() throws PersistenceException {
        loadProducts();
    }
    
    @Override
    public Product getProduct(String prodType) throws PersistenceException {
        List<Product> prodList;
        prodList = loadProducts();
        if (prodList != null) { // if product list doesn't equal null
            Product prod;
            prod = prodList.stream() // load product list stream
                    .filter(new PredicateImpl(prodType))
                    .findFirst().orElse(null);
            return prod;
        } else {
            return null;
        }
    }
    
    private static class PredicateImpl implements Predicate<Product> {

        private final String prodType;

        public PredicateImpl(String prodType) {
            this.prodType = prodType;
        }

        @Override
        public boolean test(Product p) {
            return p.getProdType().equalsIgnoreCase(prodType);
        }
    }
}