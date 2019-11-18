package com.sg.flooringmastery2.service;

import com.sg.flooringmastery2.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class ServiceLayerTest {
    private ServiceLayer serviceLayer;
   
    public ServiceLayerTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    serviceLayer = ctx.getBean("serviceLayer", ServiceLayer.class);
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
     * Test of createOrder method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateOrder() throws Exception {
        Order o = new Order();
        o.setOrderNum(9001);
        o.setCustomer("Randy Spade III");
        o.setState("OH");
        o.setProduct("Carpet");
        o.setArea(new BigDecimal("790"));
        String date = "02281998";
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate oDate = LocalDate.parse(date,dt);
        o.setDate(oDate);
        
        serviceLayer.createOrder(o);
        assertNotNull(o);
        assertNotNull(date);
        assertNotNull(oDate);
    }

    /**
     * Test of getOrderDateSpecific method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrderDateSpecific() throws Exception {
        LocalDate oDate = LocalDate.parse("12122012",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(5);
        o.setCustomer("Dale Jr");
        o.setState("IN");
        o.setTaxRate(new BigDecimal("6"));
        o.setProduct("Laminate");
        o.setCostPerSqFt(new BigDecimal("1.75"));
        o.setLabCostPerSqFt(new BigDecimal("2.10"));
        o.setArea(new BigDecimal("5000"));
        o.setMaterialCost(new BigDecimal("9000"));
        o.setLabCost(new BigDecimal("4000"));
        o.setTax(new BigDecimal("1000"));
        o.setTotalCost(new BigDecimal("17111.99"));
        o.setDate((oDate));
        
        serviceLayer.createOrder(o);
        assertEquals(1,serviceLayer.getOrderDateSpecific(oDate).size());
        assertNotNull(o);
        assertNotNull(oDate);
    }
    
    /**
     * Test of getOrder method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        Order o = new Order();
        o.setOrderNum(3000);
        o.setCustomer("Sam A. Davis II");
        o.setState("MI");
        o.setProduct("Carpet");
        o.setArea(new BigDecimal("111"));
        String date = "04142014";
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate oDate = LocalDate.parse(date,dt);
        o.setDate(oDate);
        
        serviceLayer.createOrder(o);
        serviceLayer.getOrder(o);
        assertNotNull(o);
        assertNotNull(date);
        assertNotNull(oDate);
    }


    /**
     * Test of editOrder method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditOrder() throws Exception {
        Order o = new Order();
        o.setCustomer("Test Tester Man");
        o.setState("IN");
        o.setProduct("Tile");
        o.setArea(new BigDecimal("497"));
        String date = "09042000";
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate oDate = LocalDate.parse(date,dt);
        o.setDate(oDate);
        
        serviceLayer.editOrder(o);
        assertEquals("Test Tester Man", o.getCustomer());
        assertNotNull(o);
        assertNotNull(date);
        assertNotNull(oDate);
    }

    /**
     * Test of removeOrder method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate oDate = LocalDate.parse("05081990",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(789);
        o.setCustomer("Derrick Jetson Jr");
        o.setState("PA");
        o.setTaxRate(new BigDecimal("6.75"));
        o.setProduct("Tile");
        o.setCostPerSqFt(new BigDecimal("3.50"));
        o.setLabCostPerSqFt(new BigDecimal("4.15"));
        o.setArea(new BigDecimal("150"));
        o.setMaterialCost(new BigDecimal("70.50"));
        o.setLabCost(new BigDecimal("300"));        
        o.setTax(new BigDecimal("25"));
        o.setTotalCost(new BigDecimal("730.81"));
        o.setDate((oDate));
        
        serviceLayer.createOrder(o);
        serviceLayer.removeOrder(o);
        assertNotNull(o);
        assertNotNull(oDate);
    }
    
     /**
     * Test of saveToFile method, of class ServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveToFile() throws Exception {
        LocalDate oDate = LocalDate.parse("05081990",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(789);
        o.setCustomer("Derrick Jetson Jr");
        o.setState("PA");
        o.setTaxRate(new BigDecimal("6.75"));
        o.setProduct("Tile");
        o.setCostPerSqFt(new BigDecimal("3.50"));
        o.setLabCostPerSqFt(new BigDecimal("4.15"));
        o.setArea(new BigDecimal("150"));
        o.setMaterialCost(new BigDecimal("70.50"));
        o.setLabCost(new BigDecimal("300"));        
        o.setTax(new BigDecimal("25"));
        o.setTotalCost(new BigDecimal("730.81"));
        o.setDate((oDate));
        
        serviceLayer.createOrder(o);        
        serviceLayer.saveToFile();
    }
}