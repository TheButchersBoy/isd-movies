package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.*;

public class ConnServlet extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private OrderDBManager orderDBManager;
    private Connection conn;

    @Override //Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override //Add the DBConnector, DBManager, Connection instances to the session
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
        HttpSession session = request.getSession();
        conn = db.openConnection();   
        //TODO: don't need try catch yet
        manager = new DBManager(conn); 
        session.setAttribute("db", db);
        session.setAttribute("manager", manager);
        session.setAttribute("conn", conn);
        
        try {
            orderDBManager = new OrderDBManager(conn);
            session.setAttribute("orderDBManager", orderDBManager);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
