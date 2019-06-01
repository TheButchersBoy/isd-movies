/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jessicawiradinata
 */
public class OrderTest {
    
    private Order order;
    private String orderId = "111";
    private String userId = "222";
    private Double totalPrice = 10.0;
    private Date date = new Date();
    private String status = "Cancelled";
    
    public OrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        order = new Order(orderId, userId, new ArrayList(), totalPrice, date, status);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Order.
     */
    @Test
    public void testGetId() {
        assertEquals(order.getId(), orderId);
    }

    /**
     * Test of setId method, of class Order.
     */
    @Test
    public void testSetId() {
        String newOrderId = "999";
        order.setId(newOrderId);
        
        assertEquals(order.getId(), newOrderId);
    }

    /**
     * Test of getUserId method, of class Order.
     */
    @Test
    public void testGetUserId() {
        assertEquals(order.getUserId(), userId);
    }

    /**
     * Test of setUserId method, of class Order.
     */
    @Test
    public void testSetUserId() {
        String newUserId = "000";
        order.setUserId(newUserId);
        
        assertEquals(order.getUserId(), newUserId);
    }

    /**
     * Test of getTotalPrice method, of class Order.
     */
    @Test
    public void testGetTotalPrice() {
        assertEquals(order.getTotalPrice(), totalPrice);
    }

    /**
     * Test of setTotalPrice method, of class Order.
     */
    @Test
    public void testSetTotalPrice() {
        Double newTotalPrice = 99.0;
        order.setTotalPrice(newTotalPrice);
        
        assertEquals(order.getTotalPrice(), newTotalPrice);
    }

    /**
     * Test of getDate method, of class Order.
     */
    @Test
    public void testGetDate() {
        assertEquals(order.getDate(), date);
    }

    /**
     * Test of setDate method, of class Order.
     */
    @Test
    public void testSetDate() {
        Date newDate = new Date();
        order.setDate(newDate);
        
        assertEquals(order.getDate(), newDate);
    }

    /**
     * Test of getStatus method, of class Order.
     */
    @Test
    public void testGetStatus() {
        assertEquals(order.getStatus(), status);
    }

    /**
     * Test of setStatus method, of class Order.
     */
    @Test
    public void testSetStatus() {
        String newStatus = "Saved";
        order.setStatus(newStatus);
        
        assertEquals(order.getStatus(), newStatus);
    }

    /**
     * Test of isCancellable method, of class Order.
     */
    @Test
    public void testIsCancellable() {
        order.setStatus("Saved");
        
        assertEquals(order.isCancellable(), true);
        
        order.setStatus("Submitted");
        
        assertEquals(order.isCancellable(), false);
    }
    
}
