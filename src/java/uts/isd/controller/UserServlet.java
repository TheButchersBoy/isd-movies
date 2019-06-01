package uts.isd.controller;

import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDBManager;

public class UserServlet extends HttpServlet {
    private DBConnector db;
    private UserDBManager manager;
    private Connection conn;
    
    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        conn = db.openConnection();   
        manager = new UserDBManager(conn); 
        session.setAttribute("db", db);
        session.setAttribute("userDBManager", manager);
        session.setAttribute("conn", conn);
        
        try {
            String action = request.getParameter("action");
            if("logout".equals(action)) {
                logout(response, session);
            } else if ("delete".equals(action)) {
                deleteUser(response, session);
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
               
        HttpSession session = request.getSession();
        conn = db.openConnection();   
        manager = new UserDBManager(conn); 
        session.setAttribute("db", db);
        session.setAttribute("userDBManager", manager);
        session.setAttribute("conn", conn);
                
        try {
            String action = request.getParameter("action");
            if("login".equals(action)) {
                login(request, response);
            } else if("register".equals(action)) {
                registerUser(request, response);
            } else if("update".equals(action)){
                updateUserDetails(request, response);
            }
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try{
            String email = (String) request.getParameter("email");
            String password = (String) request.getParameter("password");
            String encodedPassword = encodePassword(password);
            User user = manager.login(email, encodedPassword);
            if(user.getEmail() != null) {
                // Decode password
                String decodedPassword = decodePassword(user.getPassword());
                user.setPassword(decodedPassword);
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                response.sendRedirect("loginWelcome.jsp");
            }
            else
                response.sendRedirect("index.jsp");
        }    
        catch (IOException theException) {
            System.out.println(theException);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int key = (new Random()).nextInt(999999);
        String id = "" + key; 
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        HttpSession session = request.getSession();
        session.setAttribute("firstNameFormVal", firstName);
        session.setAttribute("lastNameFormVal", lastName);
        session.setAttribute("emailFormVal", email);
        session.setAttribute("mobileFormVal", mobile);
        User user = new User(id, firstName, lastName, email, password, mobile);
        
        Validator validator = new Validator();
        if (validator.validateUser(user, true, session, manager)) {
            String encodedPassword = encodePassword(password);
            manager.addUser(id, email, encodedPassword, firstName, lastName, mobile);
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp"); // Send to home
        } else {
            response.sendRedirect("register.jsp"); // Send to register
        }
    }  
    
    private void deleteUser(HttpServletResponse response, HttpSession session) throws IOException, SQLException {
        User user = (User) session.getAttribute("user");
        String id = user.getId();
        manager.deleteUser(id);
        logout(response, session);
    }
    
    private void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.invalidate();
        response.sendRedirect("index.jsp");
    }
    
    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException { 
        HttpSession session = request.getSession();
        // Get existing user id and password from user in session
        User existingUser = (User) session.getAttribute("user");
        String id = existingUser.getId();
        String password = existingUser.getPassword();
        // Get values to update from request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        User updatedUser = new User(id, firstName, lastName, email, password, mobile);
        
        Validator validator = new Validator();
        if (validator.validateUser(updatedUser, false, session, manager)) {
            session.setAttribute("user", updatedUser);
            manager.updateUser(id, email, firstName, lastName, mobile);
            session.setAttribute("showUpdateBanner", "show");
        } else {
            session.setAttribute("showUpdateBanner", null);
        }
        response.sendRedirect("userDetails.jsp"); // Send to user details page
    }
    
    private String encodePassword(String password) {
        // Encode password using BASE64
        return Base64.encodeBase64String(password.getBytes());
    }
    
    private String decodePassword(String encodedPassword) {
        // Decode BASE64 password
        byte[] decoded = Base64.decodeBase64(encodedPassword);
        return new String(decoded);
    }
    
    @Override //Destroy the servlet and release the resources of the application
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
