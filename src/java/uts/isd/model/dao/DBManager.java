package uts.isd.model.dao;

import uts.isd.model.User;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.*;

public class DBManager {
    private Statement st;
    private Connection conn;
    
    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
     
    public boolean doesUserExist(String email ) {
        boolean userExists = false;
        try {
            String sql = "SELECT EMAIL FROM APP.USERS WHERE EMAIL = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            
            ResultSet result = stmt.executeQuery();
            if (result.getString("email") != null) {
                userExists = true;
            }

            // clean up
            result.close();
            stmt.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace(); // TODO: need this?
        } catch(Exception e) {
            e.printStackTrace(); // TODO: need this?
        }

        return userExists;
    }
    
    public void addUser(String id, String email, String password, 
        String firstName, String lastName, String mobile ) {
        
        try {
            String sql = "INSERT INTO APP.USERS(ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, MOBILE) " + 
                 "VALUES(?,?,?,?,?,?)";
            PreparedStatement insertUser = conn.prepareStatement(sql);
            insertUser.setString(1, id);
            insertUser.setString(2, email);
            insertUser.setString(3, password);
            insertUser.setString(4, firstName);
            insertUser.setString(5, lastName);
            insertUser.setString(6, mobile);
            insertUser.executeUpdate();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace(); // TODO: need this?
        } catch(Exception e) {
            e.printStackTrace(); // TODO: need this?
        } finally{
            //closeConnection();
        }
    }
    
    public boolean checkUser (String email, String password) throws SQLException {
        String sql = "select * from Users where email='" + email + "'AND password='" + password + "'";
        PreparedStatement insertUser = conn.prepareStatement(sql);
        insertUser.setString(1,email);
        insertUser.setString(2,password);
        
        ResultSet rs = insertUser.executeQuery();
        return rs.next();
    }
    
    public void updateUser(String id, String email, String password, 
        String firstName, String lastName, String mobile) {
        
        try {
            String sql = "UPDATE APP.USERS " + 
                "SET (FIRST_NAME = ?, LAST_NAME = ?, EMAIL = ?, PASSWORD = ?, MOBILE = ?) " + 
                 "WHERE ID = ?";
            PreparedStatement insertUser = conn.prepareStatement(sql);
            insertUser.setString(1, firstName);
            insertUser.setString(2, lastName);
            insertUser.setString(3, email);
            insertUser.setString(4, password);
            insertUser.setString(5, mobile);
            insertUser.setString(6, id);
            insertUser.executeUpdate();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace(); // TODO: need this?
        } catch(Exception e) {
            e.printStackTrace(); // TODO: need this?
        } finally{
            //closeConnection();
        }
    }
    
    public void deleteUser(String id ) {
        
        try {
            String sql = "DELETE FROM APP.USERS WHERE ID = ?";
            PreparedStatement insertUser = conn.prepareStatement(sql);
            insertUser.setString(1, id);
            insertUser.executeUpdate();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace(); // TODO: need this?
        } catch(Exception e) {
            e.printStackTrace(); // TODO: need this?
        } finally{
            //closeConnection();
        }
    }
    
    private void closeConnection() {
        try{
            if(st!=null) {
               st.close();
            }
         } catch(SQLException se2){
             // Nothing we can do
         }
    }
}

