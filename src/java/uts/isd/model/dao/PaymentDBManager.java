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
import java.util.Date;
import java.util.Random;
import uts.isd.model.Payment;

/**
 *
 * @author jessicawiradinata
 */
public class PaymentDBManager {
    
    private Connection conn;
    
    public PaymentDBManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public void addPayment(Double amount, Date date, String method) throws SQLException {
        String insertPaymentSql = "INSERT INTO PAYMENT(ID, AMOUNT, DATE, METHOD) " + "VALUES (?,?,?,?)";
        PreparedStatement addPayment = conn.prepareStatement(insertPaymentSql);
        
        int idInt = (new Random()).nextInt(999999);
        String id = Integer.toString(idInt);
        
        addPayment.setString(1, id);
        addPayment.setDouble(2, amount);
        addPayment.setDate(3, new java.sql.Date(new Date().getTime()));
        addPayment.setString(4, method);
        
        addPayment.executeUpdate();
    }
    
    public ArrayList<Payment> getPayments() throws SQLException {
        String paymentsSql = "SELECT * FROM PAYMENT";
        PreparedStatement getPayment = conn.prepareStatement(paymentsSql);
        
        ArrayList<Payment> payments = new ArrayList();
        ResultSet resultSet = getPayment.executeQuery();
        
        while(resultSet.next()) {
            Payment payment = new Payment();
            
            payment.setId(resultSet.getInt("ID"));
            payment.setAmount(resultSet.getDouble("Amount"));
            payment.setDate(resultSet.getDate("DATE"));
            payment.setMethod(resultSet.getString("METHOD"));            
            payments.add(payment);
        }
        return payments;
    }
}