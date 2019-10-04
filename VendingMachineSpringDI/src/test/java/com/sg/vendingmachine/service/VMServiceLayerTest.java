package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VMDao;
import com.sg.vendingmachine.dao.VMDaoStubImpl;
import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brian
 */
public class VMServiceLayerTest {
    
    private VMServiceLayer service;
    
    public VMServiceLayerTest() {
        
        VMDao dao = new VMDaoStubImpl();
        service = new VMServiceLayerImpl(dao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCandyInventory method, of class VMServiceLayer.
     */
    @Test                                    // tests get candy from inventory
    public void getCandyInventory() throws Exception {
        Candy candy = new Candy("Skittles");
        candy.setCost(new BigDecimal("4.75"));
        candy.setVMInventory(7);
    }

    /**
     * Test of inStockStatus method, of class VMServiceLayer.
     */
    @Test
    public void testInStockStatus() throws Exception {
        try {                                // tests Starburst in stock status
            service.inStockStatus("Starburst");
        } catch (VMCurrentItemOutOfStockException e) {
        }
    }
    /**
     * Test of buyCandyInventory method, of class VMServiceLayer.
     */
    @Test
    public void testBuyCandyInventory() throws Exception {
                                               // tests a random candy purchase
        Candy candy = new Candy("random");
        candy.setCost(new BigDecimal("1.75"));
        candy.setVMInventory(11);
        BigDecimal userCash = new BigDecimal("2");
        service.buyCandyInventory(candy, userCash);
    }
    /**
     * Test of getAllCandyInventory method, of class VMServiceLayer.
     */
    @Test
    public void testGetAllCandyInventory() throws Exception {
                                                    // tests inventory at 0
        assertEquals(0, service.getAllCandyInventory().size());
    }
    /**
     * Test of getOnlyCandyInStock method, of class VMServiceLayer.
     */
    @Test
    public void testGetOnlyCandyInStock() throws Exception {
                                                    // tests in stock at 0
        assertEquals(0, service.getOnlyCandyInStock().size());
    }
}
