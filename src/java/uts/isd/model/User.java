package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable{ // TODO: Do we need to implement Serializable for models?
    
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dob;
    
    public User() {
        super();
    }
    
    public User(String id, String email, String password, String firstName, String lastName, String dob) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    
    public String getID() {
        return id;
    }
    
    public void setID(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDOB() {
        return dob;
    }
    
    public void setDOB(String dob) {
        this.dob = dob;
    }
    
}
