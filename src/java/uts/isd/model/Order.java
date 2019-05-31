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

    // Constructs an order object with the provided properties
    public Order(String id, String userId, ArrayList<Movie> movies, Double totalPrice, Date date, String status) {
        this.id = id;
        this.userId = userId;
        this.movies = movies;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }

    // Returns order id
    public String getId() {
        return id;
    }

    // Sets order id
    public void setId(String id) {
        this.id = id;
    }

    // Gets user id of the order
    public String getUserId() {
        return userId;
    }

    // Sets user id of the order
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Gets movies
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    // Sets movies
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    // Gets total price
    public Double getTotalPrice() {
        return totalPrice;
    }

    // Sets total price
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Gets date
    public Date getDate() {
        return date;
    }

    // Sets date
    public void setDate(Date date) {
        this.date = date;
    }
    
    // Adds a movie to the order
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
   
    // Removes a movie from the order
    public void removeMovie(String movieId) {
        movies.removeIf(movie -> movie.getId().equals(movieId));
    }
    
    // Updates order total price based on the total price of movies in the order
    public void updateTotalPrice() {
        Double price = 0.0;
        
        for (Movie movie: movies) {
            price += movie.getPrice();
        }
        
        totalPrice = price;
    }

    // Gets order status
    public String getStatus() {
        return status;
    }

    // Sets order status
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Checks whether the order can be cancelled
    public boolean isCancellable() {
        return this.status.equals("Saved");
    }
}
