/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.Session;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Kyle Zeng
 */
public class SessionsDBManager {
    private Connection conn;
    
    public SessionsDBManager(Connection conn) {
        this.conn = conn;
    }
    
    public void initialiseSessions(HttpSession session) {
        
        ArrayList<Session> sessions = new ArrayList();
        sessions.add(new Session("1", new java.sql.Date(new Date().getTime())));
        
        session.setAttribute("sessions", sessions);
    }
    
    public void addSessions(ArrayList<Session> sessions) throws SQLException {
        for (Session session : sessions){   
            String sql = "INSERT INTO USERSESSIONS(ID, DATE) " + "VALUES (?,?)";
            PreparedStatement addSessions = conn.prepareStatement(sql);
            int rdmNo = (new Random()).nextInt(9999);
            String id = Integer.toString(rdmNo);
            
            addSessions.setString(1, id);
            addSessions.setString(2, session.getId());
            addSessions.setDate(3, new java.sql.Date(new Date().getTime()));
            addSessions.executeUpdate();
        }
    }        
        public ArrayList<Session> getSessions() throws SQLException {
        String sessionSql = "SELECT * FROM USERSESSIONS BY DATE DESC";
        PreparedStatement getSessions = conn.prepareStatement(sessionSql);
        
        ArrayList<Session> sessions = new ArrayList<Session>();
        ResultSet rs = getSessions.executeQuery();
        
        while(rs.next()) {
            Session session = new Session();
            
            session.setId(rs.getString("ID"));
            session.setDate(rs.getDate("DATE"));
            
            sessions.add(session);
        }
        return sessions;
    }
}

