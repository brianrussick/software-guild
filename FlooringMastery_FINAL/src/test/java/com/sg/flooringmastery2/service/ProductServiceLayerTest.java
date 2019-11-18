package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dto.Order;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author brian russick
 */
public class ProductServiceLayerTest {
    private ProductServiceLayer productServiceLayer;
    
    public ProductServiceLayerTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    productServiceLayer = ctx.getBean("productServiceLayer", ProductServiceLayer.class);
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
     * Test of loadProduct method, of class ProductServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadProduct() throws Exception {
        
        Order o = new Order();
        o.setProduct("Carpet");
        BigDecimal costA = new BigDecimal(2.25);
        o.setCostPerSqFt(costA);
        BigDecimal costB = new BigDecimal(2.10);
        o.setLabCostPerSqFt(costB);
        
        assertEquals(costA, o.getCostPerSqFt());
        assertEquals(costB, o.getLabCostPerSqFt());
        assertNotNull(o);
    }

    @Test
    public void testLoadProduct2() throws Exception {
        
        Order o = new Order();
        o.setProduct("Wood");
        BigDecimal costA = new BigDecimal(5.15);
        o.setCostPerSqFt(costA);
        BigDecimal costB = new BigDecimal(4.75);
        o.setLabCostPerSqFt(costB);
        
        assertEquals(costA, o.getCostPerSqFt());
        assertEquals(costB, o.getLabCostPerSqFt());
        assertNotNull(o);
    }   
    
    @Test
    public void testLoadProduct3() throws Exception {
        
        Order o = new Order();
        o.setProduct("Tile");
        BigDecimal costA = new BigDecimal(3.50);
        o.setCostPerSqFt(costA);
        BigDecimal costB = new BigDecimal(4.15);
        o.setLabCostPerSqFt(costB);
        
        assertEquals(costA, o.getCostPerSqFt());
        assertEquals(costB, o.getLabCostPerSqFt());
        assertNotNull(o);
    }
    
    @Test
    public void testLoadProduct4() throws Exception {
        
        Order o = new Order();
        o.setProduct("Laminate");
        BigDecimal costA = new BigDecimal(1.75);
        o.setCostPerSqFt(costA);
        BigDecimal costB = new BigDecimal(2.10);
        o.setLabCostPerSqFt(costB);
        
        assertEquals(costA, o.getCostPerSqFt());
        assertEquals(costB, o.getLabCostPerSqFt());
        assertNotNull(o);
    }    
}