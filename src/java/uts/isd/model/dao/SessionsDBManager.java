/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.isd.model.Session;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author Kyle Zeng
 */
public class SessionsDBManager {
    private Connection conn;
    
    public SessionsDBManager(Connection conn) {
        this.conn = conn;
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
            
}

