package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private static String userId = "999999999";
    private static String userEmail = "nathan@test.com";
    
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
        try {
            // Delete test user
            PreparedStatement deleteTestUser = conn.prepareStatement("DELETE FROM USERS WHERE ID = '" + userId + "'");
            deleteTestUser.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUser method, of class UserDBManager.
     */
    @Test
    public void testAddUser() throws Exception { 
        // check user does not exist
        boolean doesUserExist = manager.doesUserExist(userEmail);
        assertEquals(doesUserExist, false);
        // add user
        manager.addUser(userId, userEmail, "Password1", "Nathan", "Carr", "0455455455");
        // check user does exist
        doesUserExist = manager.doesUserExist(userEmail);
        assertEquals(doesUserExist, true);
    }
    
    /**
     * Test of updateUser method, of class UserDBManager.
     */
    @Test
    public void testUpdateUser() throws Exception { 
        // add new user
        manager.addUser(userId, userEmail, "Password1", "Nathan", "Carr", "0455455455");
        String newEmail = "test@test.com";
        String newFirstName = "Chester";
        String newLastName = "Jim";
        String newMobile = "0411411411";
        // update user details
        manager.updateUser(userId, newEmail, newFirstName, newLastName, newMobile);        
        // check details are updated
        User user = manager.getUserById(userId);
        assertEquals(user.getEmail(), newEmail);
        assertEquals(user.getFirstName(), newFirstName);
        assertEquals(user.getLastName(), newLastName);
        assertEquals(user.getMobile(), newMobile);
    }
    
    /**
     * Test of deleteUser method, of class UserDBManager.
     */
    @Test
    public void testDeleteUser() throws Exception { 
        // add user
        manager.addUser(userId, userEmail, "Password1", "Nathan", "Carr", "0455455455");
        // check does exist
        boolean doesUserExist = manager.doesUserExist(userEmail);
        assertEquals(doesUserExist, true);
        // delete user
        manager.deleteUser(userId);
        // check user does exist
        doesUserExist = manager.doesUserExist(userEmail);
        assertEquals(doesUserExist, false);
    }
}
