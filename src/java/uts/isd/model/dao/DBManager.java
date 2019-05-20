package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.*;

public class DBManager {
    private Statement st;
    private Connection conn;
    
    public DBManager(Connection conn) {
        this.conn = conn;
    }
    
    public void addUser(String id, String email, String password, 
        String firstName, String lastName, String dob ) {
        try {
            String sql = "INSERT INTO APP.USERS(ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) " + 
                 "VALUES(?,?,?,?,?)";
            PreparedStatement insertUser = conn.prepareStatement(sql);
            insertUser.setString(1, id);
            insertUser.setString(2, email);
            insertUser.setString(3, password);
            insertUser.setString(4, firstName);
            insertUser.setString(5, lastName);
            insertUser.executeUpdate();
            // insertUser.setString(3, dob);
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace(); // TODO: need this?
        } catch(Exception e) {
            e.printStackTrace(); // TODO: need this?
        } finally{
            closeConnection();
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

