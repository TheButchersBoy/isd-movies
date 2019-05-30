/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.util.Date;

/**
 *
 * @author Kyle Zeng
 */
public class Session {
    private String id;
    private Date date;
    private long loginTime;
    private long logoutTime;
    
    
    public Session(String id, Date date, long loginTime, long logoutTime) {
        this.id = id;
        this.date = date;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public long getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
    
    public long getLogoutTime() {
        return logoutTime;
    }
    
    public void setLogoutTime(long logoutTime) {
        this.logoutTime = logoutTime;
    }
    
    
    
    
    
    
    
    
}
