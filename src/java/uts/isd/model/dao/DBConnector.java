package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private String URL = "jdbc:derby://localhost:1527/omsdatabase";
    private String dbuser = "username";
    private String dbpass = "password";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    static Connection connection;
    static Connection con;

    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        System.out.println("Connecting to a selected database...");
        connection = DriverManager.getConnection(URL, dbuser, dbpass);
        System.out.println("Connected database successfully...");
    }

    public static Connection openConnection(){
        return DBConnector.connection;
    }
    
     public static Connection getConnection()
      {
         try
         {
            String url = "jdbc:derby://localhost:1527/omsdatabase"; 
            Class.forName("org.apache.derby.jdbc.ClientDriver");            
            try
            {            	
               con = DriverManager.getConnection(url,"username","password"); 
            }           
            catch (SQLException ex)
            {
               ex.printStackTrace();
            }
         }
         catch(ClassNotFoundException e)
         {
            System.out.println(e);
         }
      return con;
}

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}