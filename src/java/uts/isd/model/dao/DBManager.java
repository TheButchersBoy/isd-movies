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
    
    //Find User
    public User findUser(String email, String password) throws SQLException {
        String searchQueryString = "select * from Users where email='" + email + "'AND password='" + password + "'";
        ResultSet rs = st.executeQuery(searchQueryString);
        boolean hasUser = rs.next();
        User userFromDB = null;
        
        if(hasUser) {
            String sID = rs.getString("id");
            String sPassword = rs.getString("password");
            String sEmail = rs.getString("email");
            String sFirstName = rs.getString("firstName");
            String sLastName = rs.getString("lastName");
            String sDOB = rs.getString("dob");
            
            userFromDB = new User (sID, sPassword, sEmail, sFirstName, sLastName, sDOB);
        }
    rs.close();
    return userFromDB;
    }
    
    public boolean checkUser (String email, String password) throws SQLException {
        String searchQueryString = "select * from Users where email='" + email + "'AND password='" + password + "'";
        ResultSet rs = st.executeQuery(searchQueryString);
        boolean hasUser = rs.next();
        return hasUser;
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

