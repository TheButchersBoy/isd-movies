package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private String URL = "jdbc:derby://localhost:1527/movieDB";
    private String dbuser = "omsadmin";
    private String dbpass = "omsadmin";
    private String driver = "org.apache.derby.jdbc.ClientDriver";
    private Connection connection;

    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        System.out.println("Connecting to a selected database...");
        connection = DriverManager.getConnection(URL, dbuser, dbpass);
        System.out.println("Connected database successfully...");
    }

    public Connection openConnection(){
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}