/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.Base64;
/**
 *
 * @author jessicawiradinata,Alvin
 */
public class Movie {
    
    private String id;
    private String title;
    private String description;
    private Double price;
    private int stock;
    private String genre;
    private int year;
    private byte[] movieImg; //removed movie picture for easier management. can be implemented later.
    
     private String base64Image;
    
    public Movie() {}

    public Movie(String id, String title, String description, Double price,int year ,int stock, String genre,byte[]movieImg) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.genre = genre;
        this.year = year;
        this.movieImg = movieImg;
         base64Image = Base64.getEncoder().encodeToString(movieImg);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
        public int getYear() {
        return year;
    }
        
        
        public void setYear(int year) {
        this.year = year;
    }
    
        public byte[] getMovieImg() {
        return movieImg;
    }
    
        public void setMovieImg(byte[] movieImg) {
        this.movieImg = movieImg;
        base64Image = Base64.getEncoder().encodeToString(movieImg);
    }        
            
            
}
