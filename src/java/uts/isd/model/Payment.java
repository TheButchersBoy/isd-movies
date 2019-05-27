/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.Date;

/**
 *
 * @author jessicawiradinata
 */
public class Payment {
    
    private int id;
    private Double amount;
    private Double totalAmount;
    private Date date;
    private String method;
    
    public Payment() {}

    public Order(int id, Double amount, Double totalAmount, Date date, String method) {
        this.id = id;
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

    public Double getAomunt() {
        return amount;
    }

    public Double getTotalAomunt() {
        return totalAmount;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
