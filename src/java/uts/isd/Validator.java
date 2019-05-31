package uts.isd;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;
import uts.isd.model.dao.UserDBManager;

public class Validator {

    private final String namePattern = "[a-zA-Z]{1,49}";
    private final String emailPattern = "^\\w{1,19}@[a-zA-Z_]{1,19}\\.[a-zA-Z]{2,9}$";
    private final String passwordPattern = "[a-zA-Z0-9]{8,49}";
    private final String mobilePattern = "[0-9]{8,15}";

    public Validator() {
    }
    
    // Validate all user fields against regex patterns and set session errors accordingly   
    public boolean validateUser(User user, boolean isNewUser, HttpSession session, UserDBManager manager) throws SQLException {
        boolean inputsValid = true;
        
        if (!validateName(user.getFirstName())) {
            session.setAttribute("firstNameError", "Name must be alphabetical");
            inputsValid = false;
        } else {
            session.setAttribute("firstNameError", null);
        }
        
        if (!validateName(user.getLastName())) {
            session.setAttribute("lastNameError", "Name must be alphabetical");
            inputsValid = false;
        } else {
            session.setAttribute("lastNameError", null);
        }

        if (!validateEmail(user.getEmail())) {
            session.setAttribute("emailError", "Must be a valid email. Eg. example@example.com");
            inputsValid = false;
        } else if (isNewUser && manager.doesUserExist(user.getEmail())){
            session.setAttribute("emailError", "Email already registered");
            inputsValid = false;
        } else {
            session.setAttribute("emailError", null);
        }

        if (!validateMobile(user.getMobile())) { // TODO: Add mobile
            session.setAttribute("mobileError", "Mobile must be between 8 and 15 numbers");
            inputsValid = false;
        } else {
            session.setAttribute("mobileError", null);
        }
        
        // validate password as false if any other inputs are invalid 
        // as a password input will not be filled on form load
        if (isNewUser && (inputsValid == false || !validatePassword(user.getPassword()))) {
            session.setAttribute("passwordError", "Password must be alphanumeric and over 8 characters");
            inputsValid = false;
        } else {
            session.setAttribute("passwordError", null);
        }
        return inputsValid;
    }
    
    public boolean validateName(String input) {
        Pattern regEx = Pattern.compile(namePattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }

    public boolean validateEmail(String input) {
        Pattern regEx = Pattern.compile(emailPattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }

    public boolean validatePassword(String input) {
        Pattern regEx = Pattern.compile(passwordPattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }
    
    public boolean validateMobile(String input) {
        Pattern regEx = Pattern.compile(mobilePattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }
}
