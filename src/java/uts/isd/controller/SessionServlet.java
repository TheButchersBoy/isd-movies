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
import uts.isd.model.Sessions;
import uts.isd.model.User;
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
    private ArrayList<Session> sessionList;
    
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
        String action = request.getParameter("action");
        System.out.println(action);
        try{
            if (action.equals("Clear Sessions")) {
                clearSessions(request, response);
            }
        }catch (Exception ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    private void clearSessions(HttpServletRequest request, HttpServletResponse response) {
//        
//    }

    private void getSessions(HttpServletRequest request) throws SQLException, ParseException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userId = user.getId();
        sessionList = manager.getSessions(userId);
        
        String dateFromFilter = request.getParameter("dateFrom");
        String dateToFilter = request.getParameter("dateTo");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (dateFromFilter != null && !dateFromFilter.isEmpty()) {
            Date dateFrom = dateFormat.parse(dateFromFilter);
            sessionList.removeIf(ses -> ses.getDate().before(dateFrom));
        }

        if (dateToFilter != null && !dateToFilter.isEmpty()) {
            Date dateTo = dateFormat.parse(dateToFilter);
            sessionList.removeIf(ses -> ses.getDate().after(dateTo));
        }

        for(Session session1: sessionList) {
            System.out.println(session1.getId());
        }
        System.out.println(sessionList.size());
        session.setAttribute("sessionList", sessionList);
    }
    
    private void clearSessions(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        //SessionsDBManager sessionDBManager = (SessionsDBManager)session.getAttribute("sessionsDBManager");
        User user = (User)session.getAttribute("user");
        //ArrayList<Session> sessionList1 = (ArrayList<Session>)session.getAttribute("sessionList");
        System.out.println("testset");
        System.out.println(sessionList.size());
        for (Session ses: sessionList) {
            if (ses.getUserId().equals(user.getId())) {
                manager.clearSessions(ses.getUserId());
                response.sendRedirect("userProfile.jsp");
            }
        }
    }
        
    }
