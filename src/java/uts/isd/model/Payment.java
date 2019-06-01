/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jessicawiradinata
 */
public class Payment {
    
    private int id;
    private ArrayList<Payment> payments;
    private Double amount;
    private Date date;
    private String method;
    
    public Payment() {}

    public Payment(int id, Double amount, Date date, String method) {
        this.id = id;
        this.payments = payments;
        this.amount = amount;
        this.date = date;
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public void removePayment(String paymentId) {
        payments.removeIf(payment -> paymentId.equals(payment.getId()));
    }
    
    public void updateAmount() {
        Double price = 0.0;
        
        for (Payment payment: payments) {
            price += payment.getAmount();
        }
        
        amount = price;
    }
}