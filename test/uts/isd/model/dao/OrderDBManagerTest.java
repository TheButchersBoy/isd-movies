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
    }

    /**
     * Test of addOrder method, of class OrderDBManager.
     */
    @Test
    public void testAddOrder() throws Exception {
        String userId = "999999999";
        ArrayList<Movie> movies = new ArrayList();
        Double totalPrice = 10.0;

        manager.addOrder(userId, movies, totalPrice);
        
        ArrayList<Order> orders = manager.getOrders(userId);
        orders.removeIf(order -> !order.getUserId().equals(userId));
        Order expectedOrder = orders.get(0);
        expectedOrder.getMovies().forEach(movie -> System.out.println("expected " + movie.getTitle()));
        movies.forEach(movie -> System.out.println("movie " + movie.getTitle()));
        
        assertNotEquals(expectedOrder, null);
        assertEquals(expectedOrder.getUserId(), userId);
        assertEquals(expectedOrder.getTotalPrice(), 10.0, 0.0);
        
        PreparedStatement deleteTestOrder = conn.prepareStatement("DELETE FROM ORDERS WHERE USERID = '999999999'");
        deleteTestOrder.executeUpdate();
        PreparedStatement deleteTestOrderMovie = conn.prepareStatement("DELETE FROM ORDER_MOVIE WHERE MOVIEID = '9999'");
        deleteTestOrderMovie.executeUpdate();
    }

    /**
     * Test of getOrders method, of class OrderDBManager.
     */
//    @Test
//    public void testGetOrders() throws Exception {
//        System.out.println("getOrders");
//        OrderDBManager instance = null;
//        ArrayList<Order> expResult = null;
//        ArrayList<Order> result = instance.getOrders();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
