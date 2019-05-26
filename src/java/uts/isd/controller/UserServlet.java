package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.Validator;
import uts.isd.model.User;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // do something
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action= request.getParameter("action");
        // TOOD: Add try-catch here?
        if("register".equals(action)) {
            registerUser(request, response);
        } else if("update".equals(action)) {
            updateUserDetails(request, response);
        }
    }
    
    private void registerUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int key = (new Random()).nextInt(999999); // TODO: Refactor, don't just have random
        String id = "" + key; 
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        User user = new User(id, firstName, lastName, email, password, mobile);
        
        HttpSession session = request.getSession();
        if (validateUser(user, session)) {
            // Add user to database
            // TODO:
            // db.addUser(user);
            session.setAttribute("user", user);
            // Send to home
            RequestDispatcher dispatcher = request.getRequestDispatcher( "/index.jsp");
            dispatcher.forward(request, response);
            
        } else {
            // Send to register
            RequestDispatcher dispatcher = request.getRequestDispatcher( "/register.jsp");
            dispatcher.forward(request, response);
            
        }
    }
    
    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
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
            // Update user details in database
            // TODO:
            // db.updateUser(id, fisrtName, lastName, email, mobile);
            session.setAttribute("showUpdateBanner", "show");
        } else {
            session.setAttribute("showUpdateBanner", null);
        }
        // Send to user details page
        RequestDispatcher dispatcher = request.getRequestDispatcher( "/userDetails.jsp");
        dispatcher.forward(request, response);
        
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

        if (!validator.validatePassword(user.getPassword())) {
            session.setAttribute("passwordError", "Password must be alphanumeric and over 8 characters");
            inputsValid = false;
        } else {
            session.setAttribute("passwordError", null);
        }

        if (!validator.validateMobile(user.getMobile())) { // TODO: Add mobile
            session.setAttribute("mobileError", "Mobile...");
            inputsValid = false;
        } else {
            session.setAttribute("mobileError", null);
        }
        return inputsValid;
    }
}
