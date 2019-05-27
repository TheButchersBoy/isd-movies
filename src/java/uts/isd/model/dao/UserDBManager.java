package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class UserDBManager {
    private Connection conn;
    
    public UserDBManager(Connection conn) {
        this.conn = conn;
    }
    
    public boolean doesUserExist(String email ) throws SQLException {
        boolean userExists = false;
        String sql = "SELECT EMAIL FROM USERS WHERE EMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet result = stmt.executeQuery();
        if (result.getString("email") != null) {
            userExists = true;
        }
        // clean up
        result.close(); // TODO: Add this everywhere?
        stmt.close();
        return userExists;
    }
    
    public void addUser(String id, String email, String password, 
        String firstName, String lastName, String mobile ) throws SQLException {
        String sql = "INSERT INTO USERS(ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, MOBILE) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement insertUser = conn.prepareStatement(sql);
        insertUser.setString(1, id);
        insertUser.setString(2, email);
        insertUser.setString(3, password);
        insertUser.setString(4, firstName);
        insertUser.setString(5, lastName);
        insertUser.setString(6, mobile);
        insertUser.executeUpdate();
    }
    
    public void updateUser(String id, String email, String firstName, String lastName, String mobile) throws SQLException {
        String sql = "UPDATE USERS " + 
                "SET (EMAIL = ?, FIRST_NAME = ?, LAST_NAME = ?, MOBILE = ?) " + 
                "WHERE ID = ?";
        PreparedStatement insertUser = conn.prepareStatement(sql);
        insertUser.setString(1, email);
        insertUser.setString(2, firstName);
        insertUser.setString(3, lastName);
        insertUser.setString(4, mobile);
        insertUser.setString(5, id);
        insertUser.executeUpdate();
    }
    
    public void deleteUser(String id ) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        PreparedStatement insertUser = conn.prepareStatement(sql);
        insertUser.setString(1, id);
        insertUser.executeUpdate();
    }
}

