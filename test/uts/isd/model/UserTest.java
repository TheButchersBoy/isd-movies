package uts.isd.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nathancarr
 */
public class UserTest {
    
    private User user;
    private String id = "999999999";
    private String email = "test@test.com";
    private String password = "Password1";
    private String firstName = "Test";
    private String lastName = "Tester";
    private String mobile = "0455455455";
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        user = new User(id, firstName, lastName, email, password, mobile);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
        assertEquals(user.getId(), id);
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
        String newId = "111111111";
        user.setId(newId);
        assertEquals(user.getId(), newId);
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        assertEquals(user.getFirstName(), firstName);
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        String newFirstName = "Jackie";
        user.setFirstName(newFirstName);
        assertEquals(user.getFirstName(), newFirstName);
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        assertEquals(user.getLastName(), lastName);
    }

    /**
     * Test of setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        String newLastName = "Chan";
        user.setLastName(newLastName);
        assertEquals(user.getLastName(), newLastName);
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        assertEquals(user.getEmail(), email);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        String newEmail = "jackiechan@gmail.com";
        user.setEmail(newEmail);
        assertEquals(user.getEmail(), newEmail);
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        assertEquals(user.getPassword(), password);
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        String newPassword = "Password2";
        user.setPassword(newPassword);
        assertEquals(user.getPassword(), newPassword);
    }

    /**
     * Test of getMobile method, of class User.
     */
    @Test
    public void testGetMobile() {
        assertEquals(user.getMobile(), mobile);
    }

    /**
     * Test of setMobile method, of class User.
     */
    @Test
    public void testSetMobile() {
        String newMobile = "0411411411";
        user.setMobile(newMobile);
        assertEquals(user.getMobile(), newMobile);
    }
    
}
