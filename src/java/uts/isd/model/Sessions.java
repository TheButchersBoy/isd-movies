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
    
    public Sessions (ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
    
    public ArrayList<Session> getSessions() {
        return sessions;
    }
    
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
