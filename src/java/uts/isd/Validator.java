package uts.isd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uts.isd.model.User;
import javax.servlet.http.*;

public class Validator {

    private final String namePattern = "[a-zA-Z]+";
    private final String emailPattern = "[a-zA-Z0-9]+@[a-z]+.com";
    private final String passwordPattern = "[a-zA-Z0-9]{5,10}";
    private final String dobPattern = "[1-2]{1}[0-9]{3}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}";
    private final String mobilePattern = "[1-2]{1}[0-9]{3}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}";

    public Validator() {
    }
    
    public boolean validateUser(User user) {
        // TODO: validate everything
        return true;
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
//        Pattern regEx = Pattern.compile(mobilePattern);
//        Matcher matcher = regEx.matcher(input);
//        return matcher.matches();
        return true;
    }

    public boolean validateDob(String input) { // TODO: Remove
        Pattern regEx = Pattern.compile(dobPattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }
}
