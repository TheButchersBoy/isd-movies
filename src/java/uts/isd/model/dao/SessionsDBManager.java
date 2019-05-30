/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;

/**
 *
 * @author Kyle Zeng
 */
public class SessionsDBManager {
    private Connection conn;
    
    public SessionsDBManager(Connection conn) {
        this.conn = conn;
    }
}

