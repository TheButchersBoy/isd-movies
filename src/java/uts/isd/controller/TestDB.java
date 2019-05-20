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
            
            int key = (new Random()).nextInt(999999);
            String id = "" + key;
//            System.out.print("User email: ");
//            String email = in.nextLine();
//            System.out.print("User  dname: ");
//            String name = in.nextLine();
//            System.out.print("User password: ");
//            String password = in.nextLine();
//            System.out.print("User DOB: ");
//            String dob = in.nextLine();
//            System.out.print("User favorite color: ");
//            String favcol = in.nextLine();
            //db.addUser(ID, email, name, password, dob, favcol);
            db.addUser(id, "email", "name", "password", "dob", "favcol");
            System.out.println("Added new user to database.");
            connector.closeConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}