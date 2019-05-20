package uts.isd.controller;

import uts.isd.model.dao.DBManager;
import uts.isd.model.dao.DBConnector;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class TestDB {
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection connection = connector.openConnection();
            DBManager db = new DBManager(connection);
            
            testAddUser(db);
            
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

}