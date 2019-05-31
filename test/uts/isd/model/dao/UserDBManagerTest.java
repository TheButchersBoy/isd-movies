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
import uts.isd.model.User;

public class UserDBManagerTest {
    private static DBConnector db;
    private static Connection conn;
    private static UserDBManager manager;
    
    public UserDBManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDBManagerTest.class.getName()).log(Level.SEVERE, null, ex);
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
        manager = new UserDBManager(conn);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddUser() throws Exception { 
        String email = "nathan@test.com";
        // check user does not exist
        boolean doesUserExist = manager.doesUserExist(email);
        assertEquals(doesUserExist, false);
        // add user
        manager.addUser("999999999", "nathan@test.com", "Password1", "Nathan", "Carr", "0455455455");
        // check user does exist
        doesUserExist = manager.doesUserExist(email);
        assertEquals(doesUserExist, true);
        
        // clean up
        PreparedStatement deleteTestUserMovie = conn.prepareStatement("DELETE FROM USERS WHERE ID = '999999999'");
        deleteTestUserMovie.executeUpdate();
    }
    
    @Test
    public void testUpdateUser() throws Exception { 
        String id = "999999999";
        // add new user
        manager.addUser(id, "nathan@test.com", "Password1", "Nathan", "Carr", "0455455455");
        
        String email = "test@test.com";
        String firstName = "Chester";
        String lastName = "Jim";
        String mobile = "0411411411";
        // update user details
        manager.updateUser(id, email, firstName, lastName, mobile);
        
        // check details are updated
        User user = manager.getUserById(id);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getLastName(), lastName);
        assertEquals(user.getMobile(), mobile);
        
        // clean up
        PreparedStatement deleteTestUserMovie = conn.prepareStatement("DELETE FROM USERS WHERE ID = '999999999'");
        deleteTestUserMovie.executeUpdate();
    }
}
