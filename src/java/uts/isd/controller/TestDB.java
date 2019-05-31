package uts.isd.controller;

import uts.isd.model.dao.DBManager;
import uts.isd.model.dao.DBConnector;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import uts.isd.model.Movie;
import uts.isd.model.Order;
import uts.isd.model.dao.OrderDBManager;

public class TestDB {
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection connection = connector.openConnection();
            DBManager db = new DBManager(connection);
            OrderDBManager orderDb = new OrderDBManager(connection);
            
//            testAddUser(db);
//            testAddOrder(orderDb);
//            testGetOrders(orderDb);
            
            connector.closeConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void testAddUser(DBManager db) { // TODO: Do we need to refactor this to non-static?
        int key = (new Random()).nextInt(999999);
        String id = "" + key;
        db.addUser(id, "email", "name", "password", "dob", "favcol");
        System.out.println("Added new user to database.");
    }
    
    private static void testAddOrder(OrderDBManager db) {
        ArrayList<Movie> movies = new ArrayList();
       // movies.add(new Movie("1001", "The Avengers", "", 40.55, 50, "Action"));
       // movies.add(new Movie("1002", "Antman", "", 20.0, 35, "Action"));

        try {
            db.addOrder("987654321", movies, 40.55);
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testGetOrders(OrderDBManager db) {
        try {
            ArrayList<Order> orders = db.getOrders();
            for (Order order: orders) {
                for (Movie movie: order.getMovies()) {
                    System.out.println(movie.getTitle());
                }
                System.out.println(order.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}