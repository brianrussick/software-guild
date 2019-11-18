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
public class TaxServiceLayerTest {
    
    private TaxServiceLayer taxServiceLayer;
    
    public TaxServiceLayerTest() {
   
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    taxServiceLayer = ctx.getBean("taxServiceLayer", TaxServiceLayer.class);
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
     * Test of calcOrderTax method, of class TaxServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testCalcOrderTax() throws Exception {
        LocalDate oDate = LocalDate.parse("05152015",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setState("PA");
        BigDecimal stateTaxRate = new BigDecimal(6.75);
        o.setTaxRate(stateTaxRate);
        o.setDate(oDate);
        
        taxServiceLayer.calcOrderTax(o);
        assertEquals(stateTaxRate,o.getTaxRate());
        assertNotNull(o);
        assertNotNull(stateTaxRate);
        assertNotNull(oDate);
    }
    
    @Test
    public void testCalcOrderTax2() throws Exception {
        LocalDate oDate = LocalDate.parse("12292015",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setState("MI");
        BigDecimal stateTaxRate = new BigDecimal(5.75);
        o.setTaxRate(stateTaxRate);
        o.setDate(oDate);
        
        taxServiceLayer.calcOrderTax(o);
        assertEquals(stateTaxRate,o.getTaxRate());
        assertNotNull(o);
        assertNotNull(stateTaxRate);
        assertNotNull(oDate);
    }
}