/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kyle Zeng
 */
public class Session implements Serializable{
    private String id;
    private String userId;
    private Date date;
    
    public Session() {}
    
    public Session(String id, String userId, Date date) {
        this.id = id;
        this.userId = userId;
        this.date = date;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
    
    
}
