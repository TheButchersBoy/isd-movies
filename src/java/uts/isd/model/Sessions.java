/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.ArrayList;

/**
 *
 * @author Kyle Zeng
 */
public class Sessions {
    private ArrayList<Session> sessions;
    private String userId;
    
    public Sessions (ArrayList<Session> sessions, String userId) {
        this.sessions = sessions;
        this.userId = userId;
    }
    
    public ArrayList<Session> getSessions() {
        return sessions;
    }
    
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
            
}
