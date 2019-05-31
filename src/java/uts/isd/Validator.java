package uts.isd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uts.isd.model.User;

public class Validator {

    private final String namePattern = "[a-zA-Z]{1,49}";
    private final String emailPattern = "^\\w{1,19}@[a-zA-Z_]{1,19}\\.[a-zA-Z]{2,9}$";
    private final String passwordPattern = "[a-zA-Z0-9]{8,49}";
    private final String mobilePattern = "[0-9]{8,15}";

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
        Pattern regEx = Pattern.compile(mobilePattern);
        Matcher matcher = regEx.matcher(input);
        return matcher.matches();
    }
}
