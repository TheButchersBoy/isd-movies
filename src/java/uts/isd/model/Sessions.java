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
    private ArrayList<Session> sessionList;
    private String userId;
    
    public Sessions() {}
    
    public Sessions (ArrayList<Session> sessionList, String userId) {
        this.sessionList = sessionList;
        this.userId = userId;
    }

    public ArrayList<Session> getSessions() {
        return sessionList;
    }
    
    public void setSessions(ArrayList<Session> sessionList) {
        this.sessionList = sessionList;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
            
}
