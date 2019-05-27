/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.*;
import uts.isd.model.User;

/**
 *
 * @author Kyle
 */
public class UserDAO {
    static Connection currentCon = null;
    static ResultSet rs = null;
    
    public static User login(User user) {
        Statement statement = null;
        
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "select * from Users where email='" + email + "'AND password='" + password + "'";
        
        System.out.println("Your email is" + email);
        System.out.println("Your password is" + password);
        System.out.println("Query:" + sql);
        
        try{
           //Connect to DB
           currentCon = DBConnector.openConnection();
           statement = currentCon.createStatement();
           rs = statement.executeQuery(sql);
           boolean more = rs.next();
           
            if (!more) 
         {
            System.out.println("Sorry, you are not a registered user! Please sign up first");
            user.setValid(false);
         } 
	        
         //if user exists set the isValid variable to true
         else if (more) 
         {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
	     	
            System.out.println("Welcome " + firstName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setValid(true);
         }
      } 

      catch (SQLException ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 
	    
      //some exception handling
      finally 
      {
         if (rs != null)	{
            try {
               rs.close();
            } catch (SQLException e) {}
               rs = null;
            }   
	
         if (statement != null) {
            try {
               statement.close();
            } catch (SQLException e) {}
               statement = null;
            }
	
         if (currentCon != null) {
            try {
               currentCon.close();
            } catch (SQLException e) {
            }

            currentCon = null;
         }
      }

return user;
	
      }	
}
