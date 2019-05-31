/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model;

import java.util.Base64;


/**
 *
 * @author Alvin
 */


/*
By Alvin: the movie model including the various fields for a movie and 
different operations to get them and set them;
*/

public class Movie {
    
    private String id;
    private String title;
    private String genre;
    private int year;
    private String description;
    private int stock;
    private double price;
    private byte[] movieImg; //removed movie picture for easier management. can be implemented later.
    
    private String base64Image;

    public Movie() {
    }
    

    public Movie(String movie_ID, String movieTitle, String genre, int year, String description, int stock, double price,byte[]movieImg) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.movieImg = movieImg;
        
       // byte[] base64 = Base64.
        base64Image = Base64.getEncoder().encodeToString(movieImg);
        
        
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

   

    public String getBase64Image() {
        return base64Image;
    }
    
    
    

//    public Movie(String movieTitle, String genre, int year, String description, int stock, double price,byte[]movieImg) {
//        this.title = title;
//        this.genre = genre;
//        this.year = year;
//        this.description = description;
//        this.stock = stock;
//        this.price = price;
//        this.movieImg = movieImg;
//        
//        base64Image = Base64.getEncoder().encodeToString(movieImg);
//       
//    }

    public Movie(int movie_ID) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
/**/
    public byte[] getMovieImg() {
        return movieImg;
    }

    public void setMovieId(int movie_ID) {
        this.id = id;
    }

    public void setMovieTitle(String movieTitle) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }
/**/
    public void setMovieImg(byte[] movieImg) {
        this.movieImg = movieImg;
        base64Image = Base64.getEncoder().encodeToString(movieImg);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
 
    
}
