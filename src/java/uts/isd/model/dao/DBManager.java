package uts.isd.model.dao;

//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.SQLException;
import java.sql.*;

public class DBManager {
    private Statement st;
    private Connection conn;
    
    public DBManager(Connection conn) {// throws SQLException { TODO: only need if createStatement()
        //st = conn.createStatement();
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
//            insertUser.setString(3, dob);
            
//            String sql = "INSERT INTO APP.USERS(ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME) " + 
//                    "VALUES('" + id + "' 000000000', 'email', 'password', 'nathan', 'carr')";
//                    "VALUES(" + id + ", " +
//                    email + ", " + 
//                    password + ", " + 
//                    firstName + ", " + 
//                    lastName + ")";
//            st.executeUpdate(sql);
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace(); // TODO: remove?
        } catch(Exception e) {
            //Handle errors for Class.forName - TODO: do we need this?
            e.printStackTrace(); // TODO: remove?
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
             // nothing we can do
         }
    }
}
    
//    Define also methods that:
//
//    Add a user to the database.
//    Find a user by 'ID'
//    Find a user by 'ID' and 'password'
//    Update a user's information in the database
//    Delete a user from the database
