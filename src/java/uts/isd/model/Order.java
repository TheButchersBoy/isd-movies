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
public class Order {
    
    private String id;
    private Movie[] movies;
    private Double totalPrice;
    private Date date;
    
    public Order() {}

    public Order(String id, Movie[] movies, Double totalPrice, Date date) {
        this.id = id;
        this.movies = movies;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
