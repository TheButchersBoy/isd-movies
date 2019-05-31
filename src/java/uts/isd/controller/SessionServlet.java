/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Order;
import uts.isd.model.Session;
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
            getSessions(request);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void getSessions(HttpServletRequest request) throws SQLException, ParseException {
        HttpSession session = request.getSession();
        String userId = "987654321"; // TODO: get userID from session
        ArrayList<Session> sessionList = manager.getSessions(userId);
        
        String dateFromFilter = request.getParameter("dateFrom");
        String dateToFilter = request.getParameter("dateTo");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");

        if (dateFromFilter != null && !dateFromFilter.isEmpty()) {
            Date dateFrom = dateFormat.parse(dateFromFilter);
            sessionList.removeIf(sessions -> sessions.getDate().before(dateFrom));
        }

        if (dateToFilter != null && !dateToFilter.isEmpty()) {
            Date dateTo = dateFormat.parse(dateToFilter);
            sessionList.removeIf(sessions -> sessions.getDate().after(dateTo));
        }

        session.setAttribute("sessionList", sessionList);
    }
    
}
