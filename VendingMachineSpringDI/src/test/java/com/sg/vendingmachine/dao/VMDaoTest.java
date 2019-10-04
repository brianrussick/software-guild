package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Candy;
import java.math.BigDecimal;
import java.util.List;
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
public class VMDaoTest {
    
    VMDao dao = new VMDaoFileImpl();
    
    public VMDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before                                 // set dao into a known good state
    public void setUp() throws Exception { 
        
        List<Candy> test = dao.getAllCandyInventory();
        
        for (Candy candy : test) {
            dao.getCandyInventory(candy.getCandyName());
        } 
    }

    @After
    public void tearDown() {
    }

    /**         
     * Test of getAllCandyInventory method, of class VMDao.
     */
    @Test                                           
    public void testGetAllCandyInventory() throws Exception {
        Candy candy = new Candy("Butterfinger");
        candy.setVMInventory(15);
        candy.setCost(new BigDecimal("3"));
        
        dao.editCandyInventory(candy.getCandyName(), candy);
        
        Candy candy1 = new Candy("100 Grand Bar");
        candy1.setVMInventory(4);
        candy1.setCost(new BigDecimal("1.75"));

        dao.editCandyInventory(candy1.getCandyName(), candy1);
                            // asserts our inventory list now contains 8 items
        assertEquals(8, (int) dao.getAllCandyInventory().size());
    }
    
    /**
     * Test of getOnlyCandyInStock method, of class VMDao.
     */
    @Test
    public void testGetOnlyCandyInStock() throws Exception {
        Candy candy = new Candy("Butterfinger");
        candy.setVMInventory(15);
        candy.setCost(new BigDecimal("3"));
        
        dao.editCandyInventory(candy.getCandyName(), candy);
        
        Candy candy1 = new Candy("100 Grand Bar");
        candy1.setVMInventory(4);
        candy1.setCost(new BigDecimal("1.75"));

        dao.editCandyInventory(candy1.getCandyName(), candy1);
                        // asserts our candy in stock list now contains 8 items
        assertEquals(8, (int) dao.getOnlyCandyInStock().size());
    } 
}