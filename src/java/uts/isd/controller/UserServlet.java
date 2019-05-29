package uts.isd.controller;

import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.Validator;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DBManager;
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
        
        String action = request.getParameter("action");
        if("logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");

        }
    }
    
//    @Override //Add the DBConnector, DBManager, Connection instances to the session
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");       
//        HttpSession session = request.getSession();
//        conn = db.openConnection();   
//        try {
//            //TODO: don't need try catch yet
//            manager = new DBManager(conn);
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        session.setAttribute("db", db);
//        session.setAttribute("manager", manager);
//        session.setAttribute("conn", conn);
//    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();
        conn = db.openConnection();   
        //TODO: don't need try catch yet
        manager = new UserDBManager(conn);
        session.setAttribute("db", db);
        session.setAttribute("manager", manager);
        session.setAttribute("conn", conn);
        
        String action= request.getParameter("action");
        // TOOD: Add try-catch here?
        if("register".equals(action)) {
            registerUser(request, response);
        } else if("update".equals(action)) {
            try {
                updateUserDetails(request, response);
//        } else if ("login".equals(action)) {
//                login(request, response);          
//        } 
            } catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
//    private void login(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException, SQLException {
//        
//        HttpSession session = request.getSession();
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        User user = manager.findUser(email, password);
//        
//        if (manager != null && manager.checkUser(email, password)){
//            session.setAttribute("userLogin", user);
//            RequestDispatcher rd=request.getRequestDispatcher("loginWelcome.jsp");  
//        rd.forward(request,response); 
//        }
//        else {
//            session.setAttribute("existErr", "User profile does not exist!");
//            RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
//        rd.forward(request,response); 
//        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        conn = db.openConnection();   
        manager = new UserDBManager(conn); 
        session.setAttribute("db", db);
        session.setAttribute("userUserDBManager", manager);
        session.setAttribute("conn", conn);
        
        try {
            String action = request.getParameter("action");
            if("register".equals(action)) {
                registerUser(request, response);
            } else if("update".equals(action)){
                updateUserDetails(request, response);
            }
        } catch (IOException | SQLException | ServletException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
//    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        int key = (new Random()).nextInt(999999); // TODO: Refactor, don't just have random
//        String id = "" + key; 
//        String firstName = request.getParameter("firstName");
//        String lastName = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String mobile = request.getParameter("mobile");
//        HttpSession session = request.getSession();
//        session.setAttribute("firstNameFormVal", firstName);
//        session.setAttribute("lastNameFormVal", lastName);
//        session.setAttribute("emailFormVal", email);
//        session.setAttribute("mobileFormVal", mobile);
//        User user = new User(id, firstName, lastName, email, password, mobile);
//        
//        if (validateUser(user, session)) {
//            String encodedPassword = encodePassword(password);
//            manager.addUser(id, email, encodedPassword, firstName, lastName, mobile);
//            session.setAttribute("user", user);
//            response.sendRedirect("index.jsp"); // Send to home
//        } else {
//            response.sendRedirect("register.jsp"); // Send to register
//        }
//    }    
    
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
        
        if (validateUser(updatedUser, session)) {
            session.setAttribute("user", updatedUser);
            manager.updateUser(id, email, firstName, lastName, mobile);
            session.setAttribute("showUpdateBanner", "show");
        } else {
            session.setAttribute("showUpdateBanner", null);
        }
        // Send to user details page
        response.sendRedirect("userDetails.jsp");
    }
    
    private boolean validateUser(User user, HttpSession session) {
        Validator validator = new Validator();
        boolean inputsValid = true;
                
        if (!validator.validateName(user.getFirstName())) {
            session.setAttribute("firstNameError", "Name must be alphabetical");
            inputsValid = false;
        } else {
            session.setAttribute("firstNameError", null);
        }
        
        if (!validator.validateName(user.getLastName())) {
            session.setAttribute("lastNameError", "Name must be alphabetical");
            inputsValid = false;
        } else {
            session.setAttribute("lastNameError", null);
        }

        if (!validator.validateEmail(user.getEmail())) {
            session.setAttribute("emailError", "Must be a valid email. Eg. example@example.com");
            inputsValid = false;
//        } else if (omsApp.emailAlreadyExists(email)){
//            session.setAttribute("emailError", "Email already registered");
//            inputsValid = false;
        } else {
            session.setAttribute("emailError", null);
        }

        if (!validator.validateMobile(user.getMobile())) { // TODO: Add mobile
            session.setAttribute("mobileError", "Mobile...");
            inputsValid = false;
        } else {
            session.setAttribute("mobileError", null);
        }
        
        if (inputsValid == false || !validator.validatePassword(user.getPassword())) {
            session.setAttribute("passwordError", "Password must be alphanumeric and over 8 characters");
            inputsValid = false;
        } else {
            session.setAttribute("passwordError", null);
        }
        return inputsValid;
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
