/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uts.isd.controller.ConnServlet;
import uts.isd.model.Movie;
import uts.isd.model.Order;

/**
 *
 * @author jessicawiradinata
 */
public class OrderDBManagerTest {
    
    private static DBConnector db;
    private static Connection conn;
    private static OrderDBManager manager;
    private static String userId = "999999999";
    
    public OrderDBManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
        } catch (Exception ex) {
            Logger.getLogger(OrderDBManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            db.closeConnection();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
        try {
            manager = new OrderDBManager(conn);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            PreparedStatement deleteTestOrder = conn.prepareStatement("DELETE FROM ORDERS WHERE USERID = '" + userId + "'");
            deleteTestOrder.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addOrder method, of class OrderDBManager.
     */
    @Test
    public void testAddOrder() throws Exception {
        ArrayList<Movie> movies = new ArrayList();
        Double totalPrice = 10.0;

        manager.addOrder(userId, movies, totalPrice);
        
        ArrayList<Order> orders = manager.getOrders(userId);
        orders.removeIf(order -> !order.getUserId().equals(userId));
        Order expectedOrder = orders.get(0);
        
        assertNotEquals(expectedOrder, null);
        assertEquals(expectedOrder.getUserId(), userId);
    }

    /**
     * Test of getOrders method, of class OrderDBManager.
     */
    @Test
    public void testGetOrders() throws Exception {
        manager.addOrder(userId, new ArrayList(), 10.0);
        ArrayList<Order> orders = manager.getOrders(userId);
        
        assertEquals(orders.size(), 1);
    }

    /**
     * Test of cancelOrder method, of class OrderDBManager.
     */
    @Test
    public void testCancelOrder() throws Exception {
        manager.addOrder(userId, new ArrayList(), 10.0);
        ArrayList<Order> orders = manager.getOrders(userId);
        orders.removeIf(_order -> !_order.getUserId().equals(userId));
        Order order = orders.get(0);
        
        manager.cancelOrder(order.getId());
        
        orders = manager.getOrders(userId);
        orders.removeIf(_order -> !_order.getUserId().equals(userId));
        order = orders.get(0);
        
        assertEquals(order.getStatus(), "Cancelled");
    }

    /**
     * Test of submitOrder method, of class OrderDBManager.
     */
    @Test
    public void testSubmitOrder() throws Exception {
        manager.addOrder(userId, new ArrayList(), 10.0);
        ArrayList<Order> orders = manager.getOrders(userId);
        orders.removeIf(_order -> !_order.getUserId().equals(userId));
        Order order = orders.get(0);
        
        manager.submitOrder(order.getId());
        
        orders = manager.getOrders(userId);
        orders.removeIf(_order -> !_order.getUserId().equals(userId));
        order = orders.get(0);
        
        assertEquals(order.getStatus(), "Submitted");
    }
}
