package com.sg.flooringmastery2.dao;

import com.sg.flooringmastery2.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class OrderDaoTest {
    
    private OrderDao orderDao;
    
    public OrderDaoTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    orderDao = ctx.getBean("orderDao", OrderDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveToFile method, of class OrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveToFile() throws Exception {
        List<Order> oList= new ArrayList<>();
        LocalDate oDate = LocalDate.parse("09102011",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(333);
        o.setCustomer("Billy Bob Sr");
        o.setState("IN");
        o.setTaxRate(new BigDecimal("6"));
        o.setProduct("Tile");
        o.setCostPerSqFt(new BigDecimal("3.50"));
        o.setLabCostPerSqFt(new BigDecimal("4.15"));
        o.setArea(new BigDecimal("400"));
        o.setMaterialCost(new BigDecimal("140"));
        o.setLabCost(new BigDecimal("300.48"));
        o.setTax(new BigDecimal("50"));
        o.setTotalCost(new BigDecimal("790"));
        o.setDate((oDate));
        oList.add(o);
        
        orderDao.saveToFile();
        assertNotNull(o);
    }
    
    /**
     * Test of getAllOrders method, of class OrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate oDate = LocalDate.parse("11102015",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(20);
        o.setCustomer("Leticia Geoffel");
        o.setState("MI");
        o.setTaxRate(new BigDecimal("5.75"));
        o.setProduct("Carpet");
        o.setCostPerSqFt(new BigDecimal("2.25"));
        o.setLabCostPerSqFt(new BigDecimal("2.10"));
        o.setArea(new BigDecimal("500"));
        o.setMaterialCost(new BigDecimal("75"));
        o.setLabCost(new BigDecimal("140"));
        o.setTax(new BigDecimal("53"));
        o.setTotalCost(new BigDecimal("320"));
        o.setDate((oDate));
        
        orderDao.createOrder(o);
        assertNotNull(orderDao.getAllByDate(oDate).size());        
        assertEquals(1,orderDao.getAllByDate(oDate).size());
        assertNotNull(o);
    }
    
    /**
     * Test of createOrder method, of class OrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateOrder() throws Exception {
        LocalDate oDate = LocalDate.parse("05082008",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(55);
        o.setCustomer("Sherrie Orrez");
        o.setState("IN");
        o.setTaxRate(new BigDecimal("6.00"));
        o.setProduct("Laminate");
        o.setCostPerSqFt(new BigDecimal("1.75"));
        o.setLabCostPerSqFt(new BigDecimal("2.10"));
        o.setArea(new BigDecimal("430"));
        o.setMaterialCost(new BigDecimal("345.00"));
        o.setLabCost(new BigDecimal("200.99"));        
        o.setTax(new BigDecimal("35"));
        o.setTotalCost(new BigDecimal("650"));
        o.setDate((oDate));

        orderDao.createOrder(o);
        Order dao = orderDao.getOrder(o);
        assertNotNull(orderDao.getOrder(o)); 
        assertEquals(o, dao);
        assertNotNull(o);
    }
    
    /**
     * Test of removeOrder method, of class OrderDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate oDate = LocalDate.parse("01022036",DateTimeFormatter.ofPattern("MMddyyyy"));
        Order o = new Order();
        o.setOrderNum(177);
        o.setCustomer("John");
        o.setState("OH");
        o.setTaxRate(new BigDecimal("6.75"));
        o.setProduct("Wood");
        o.setCostPerSqFt(new BigDecimal("100"));
        o.setLabCostPerSqFt(new BigDecimal("500"));
        o.setArea(new BigDecimal("250"));
        o.setMaterialCost(new BigDecimal("50.00"));
        o.setLabCost(new BigDecimal("15.00"));
        o.setTax(new BigDecimal("66"));
        o.setTotalCost(new BigDecimal("500"));
        o.setDate(oDate);
        
        orderDao.createOrder(o);
        orderDao.removeOrder(o);
        assertNotNull(orderDao.removeOrder(o)); 
        assertEquals(0,orderDao.getAllByDate(oDate).size());
        assertNotNull(o);
    }
}