/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SessionsDBManager;

/**
 *
 * @author Kyle Zeng
 */
public class SessionServlet extends HttpServlet {
    private DBConnector db;
    private SessionsDBManager manager;
    private Connection conn;
    
    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        conn = db.openConnection();
        session.setAttribute("db", db);
        session.setAttribute("conn", conn);
        
        try {
            manager = new SessionsDBManager(conn);
            session.setAttribute("sessionsDBManager",manager);
        } catch (Exception ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (session.getAttribute("sessions") == null) {
            manager.initialiseSessions(session);
        }
        try {
            //getSessions(request);
        } catch (Exception ex) {
            Logger.getLogger(SessionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if ("clearSessions".equals(action)) {
//                clearSessions(request, response);
//    }
 }
//    private void clearSessions(HttpServletRequest request, HttpServletResponse response) {
//        
//    }
    
}
