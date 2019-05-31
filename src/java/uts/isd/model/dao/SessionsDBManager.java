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
import uts.isd.model.Sessions;
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
        Sessions sessions = new Sessions();
        ArrayList<Session> sessionList = new ArrayList();
        sessionList.add(new Session("1", new java.sql.Date(new Date().getTime())));
        
        sessions.setSessions(sessionList);
        sessions.setUserId("987654321");
        session.setAttribute("sessions", sessions);
    }
    
    public void addSessions(String userId, ArrayList<Session> sessionList) throws SQLException {
        String sql = "INSERT INTO USERSESSIONS(ID, USERID, DATE) " + "VALUES (?,?,?)";
        PreparedStatement addSessions = conn.prepareStatement(sql);
        
        int rdmNo = (new Random()).nextInt(9999);
        String id = Integer.toString(rdmNo);

        addSessions.setString(1, id);
        addSessions.setString(2, userId);
        addSessions.setDate(3, new java.sql.Date(new Date().getTime()));
        addSessions.executeUpdate();
    }
    
    public ArrayList<Session> getSessions(String userId) throws SQLException {
        String sessionSql = "SELECT * FROM USERSESSIONS WHERE USERID = ? ORDER BY DATE DESC";
        PreparedStatement getSessions = conn.prepareStatement(sessionSql);
        getSessions.setString(1,userId);
        
        ArrayList<Session> sessionList = new ArrayList();
        ResultSet rs = getSessions.executeQuery();
        
        while(rs.next()) {
            Session session = new Session();
            
            session.setId(rs.getString("ID"));
            session.setId(rs.getString("USERID"));
            session.setDate(rs.getDate("DATE"));
            
            sessionList.add(session);
        }
        return sessionList;
    }

}    
        
    


