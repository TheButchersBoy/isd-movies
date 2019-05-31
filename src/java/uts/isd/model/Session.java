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
    
    public Session() {}
    
    public Session(String id, Date date) {
        this.id = id;
        this.date = date;
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
    
    
    
    
    
    
    
}
