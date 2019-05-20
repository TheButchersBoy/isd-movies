package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable{ // TODO: Do we need to implement Serializable for models?
    
    private String name;
    private String email;
    private String password;
    private String dob;
    
    public User() {
        super();
    }
    
    public User(String name, String email, String password, String dob) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }
    
}
