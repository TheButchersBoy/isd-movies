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
public class Order {
    
    private String id;
    private String userId;
    private ArrayList<Movie> movies;
    private Double totalPrice = 0.0;
    private Date date;
    private String status;
    
    public Order() {}

    public Order(String id, String userId, ArrayList<Movie> movies, Double totalPrice, Date date, String status) {
        this.id = id;
        this.userId = userId;
        this.movies = movies;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
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
    
    public void removeMovie(String movieId) {
        movies.removeIf(movie -> movie.getId().equals(movieId));
    }
    
    public void updateTotalPrice() {
        Double price = 0.0;
        
        for (Movie movie: movies) {
            price += movie.getPrice();
        }
        
        totalPrice = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isCancellable() {
        return this.status.equals("Saved");
    }
}
