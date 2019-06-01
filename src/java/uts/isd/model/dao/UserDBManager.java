package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Random;
import uts.isd.model.User;

public class UserDBManager {
    private Connection conn;
    
    public UserDBManager(Connection conn) {
        this.conn = conn;
    }
    
    public User login(String email, String password) throws SQLException {
        String sql = "select * from USERS where email = ? AND password = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet resultSet = stmt.executeQuery();
        User user = new User();
        while(resultSet.next()) {
            user.setId(resultSet.getString("ID"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setMobile(resultSet.getString("MOBILE"));
        
            String sesSql = "INSERT INTO USERSESSIONS(ID, USERID, DATE) " + "VALUES (?,?,?)";
            PreparedStatement addSessions = conn.prepareStatement(sesSql);

            int rdmNo = (new Random()).nextInt(9999);
            String id = Integer.toString(rdmNo);

            addSessions.setString(1, id);
            addSessions.setString(2, user.getId());
            addSessions.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            addSessions.executeUpdate();
        }
        // clean up
        stmt.close();
        return user;
    }
    
    public boolean doesUserExist(String email) throws SQLException {
        boolean userExists = false;
        String sql = "SELECT EMAIL FROM USERS WHERE EMAIL = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()) {
            if (resultSet.getString("EMAIL") != null) {
                userExists = true;
            }  
        }
        // clean up
        resultSet.close(); 
        stmt.close();
        return userExists;
    }
    
    public User getUserById(String id) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        
        ResultSet resultSet = stmt.executeQuery();
        User user = new User();
        while(resultSet.next()) {
            user.setId(id);
            user.setEmail(resultSet.getString("EMAIL"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            user.setMobile(resultSet.getString("MOBILE"));
        }
        return user;
    }
    
    public void addUser(String id, String email, String password, 
        String firstName, String lastName, String mobile ) throws SQLException {
        String sql = "INSERT INTO USERS(ID, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, MOBILE) " +
                "VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, email);
        stmt.setString(3, password);
        stmt.setString(4, firstName);
        stmt.setString(5, lastName);
        stmt.setString(6, mobile);
        stmt.executeUpdate();
        // clean up
        stmt.close();
    }
    
    public void updateUser(String id, String email, String firstName, String lastName, String mobile) throws SQLException {
        String sql = "UPDATE USERS " + 
                "SET EMAIL = ?, FIRST_NAME = ?, LAST_NAME = ?, MOBILE = ? " + 
                "WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        stmt.setString(4, mobile);
        stmt.setString(5, id);
        stmt.executeUpdate();
        // clean up
        stmt.close();
    }
    
    public void deleteUser(String id ) throws SQLException {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.executeUpdate();
        // clean up
        stmt.close();
    }
}

