/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import uts.isd.model.Movie;

/**
 *
 * @author Alvin
 */

public class MovieDAO {

    private Connection myConn;

    public MovieDAO(Connection conn) throws SQLException {
        this.myConn = conn;
    }

    // Search for a movie by ID
    public Movie getMovieByID(int movieID) throws SQLException {
       // search for the movie by ID from the database

        String sql = "SELECT * FROM movies WHERE id = ?";
        
        Movie movie = null;

        try (PreparedStatement statement = myConn.prepareStatement(sql)) {
            statement.setInt(1, movieID);
            ResultSet results = statement.executeQuery();

            // found the required movie with specific id: 
            if (results.next()) {
                
                String id = results.getString("id");
                
                String title = results.getString("TITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int stock = results.getInt("stock");
                
                double price = results.getDouble("price");
                byte[] movieImg = results.getBytes("MOVIE_IMG");

                movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         stock,  price,movieImg);
                
            }
            statement.close();
            results.close();
        }
        return movie;
    }
    
// get all movies from database, save it in a list;
    
    public List<Movie> getAllMovies() throws SQLException {
        
        List<Movie> allMovieList = new ArrayList<>();

        String sql = "SELECT * FROM movies";
        
         System.out.println("Allmovies SQL: "+ sql);

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);
            int num = 0;

            while (results.next()) {
                
                System.out.println("Movie Count:  "+ (++num));
                
               String id = results.getString("id");
                
                String title = results.getString("TITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int stock = results.getInt("stock");
                
                double price = results.getDouble("price");
                
                Blob blob = results.getBlob("MOVIE_IMG");    
                
            byte [] movieImg = blob.getBytes( 1, ( int ) blob.length() );
                
           //     byte[] movieImg = results.getBytes("MOVIE_IMG");

               Movie movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         stock,  price,movieImg);
                allMovieList.add(movie);
            }
            statement.close();
            results.close();
        }
        return allMovieList;
    }
    
    
    // search movie by Title and/or Genre /Year and save the result in a list
    
    public List<Movie> searchByTitleOrGenre(String movieTitle,String genreStr) throws SQLException {
        
        List<Movie> searchResult = new ArrayList<>();
         String sql = null;
        
         // search by genre
        if(movieTitle.isEmpty()){
                 sql = "SELECT * FROM movies where genre = '"+genreStr.trim()+"'";

        }
        // search by title
        if(genreStr.isEmpty()){
                 sql = "SELECT * FROM movies where TITLE = '"+movieTitle.trim()+"'";

        }
        // search by both genre and title
        if(!movieTitle.isEmpty()&&!genreStr.isEmpty()){
            
          sql = "SELECT * FROM movies where TITLE = '"+movieTitle.trim() +"' AND genre = '"+genreStr.trim()+"'";
        }
        
        
          System.out.println("Search SQL: "+ sql);

       

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);
            
            while (results.next()) {
                
               String id = results.getString("id");
                
                String title = results.getString("TITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int stock = results.getInt("stock");
                
                double price = results.getDouble("price");
                byte[] movieImg = results.getBytes("MOVIE_IMG");

               Movie movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         stock,  price,movieImg);
                searchResult.add(movie);
            }
            statement.close();
            results.close();
        }
        return searchResult;
    }
    

    // see if certain movie exists, return false if not
    

    
    // see if certain movie out of stock
    
    public boolean movieHasStock(int id) throws SQLException {
        
        String sql = "SELECT * FROM movies";
        boolean hasStock = true;

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                if (results.getInt("id") == id) {
                    if(results.getInt("stock")<=0){
                        hasStock = false;
                        break;
                    }
                    
                }
            }
            statement.close();
            results.close();
        }
        return hasStock;
    }
    
    // get number of all movies
    public int getMovieNumbers() throws SQLException{
        
        String sql = "select * from movies";
        int count = 0;

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                count++;
            }
            statement.close();
            results.close();
            
        }
        return count;
    
    }


    // add a new movie
    public boolean addMovie(Movie movie) throws SQLException {
        String sql = "INSERT INTO MOVIEs"
                + "(ID,TITLE,genre,movie_year,description,stock,  price, movie_img) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        
        boolean isAdded = false;
        
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
                    int key = (new Random()).nextInt(999999); // TODO: Refactor, don't just have random
        String id = "" + key; 
            statement.setString(1, id);
             
            statement.setString(2, movie.getTitle());
            statement.setString(3, movie.getGenre());
            statement.setInt(4,  movie.getYear());
            statement.setObject(5, movie.getDescription());
            statement.setInt(6, movie.getStock());
            statement.setDouble(7, movie.getPrice());
             statement.setBytes(8, movie.getMovieImg());
           
           System.out.println("SQL: "+ sql);
           
            isAdded = statement.executeUpdate() > 0;

            statement.close();
        }
        return isAdded;
    }

    //Edit and update movie in the databse:
    
    public boolean updateMovie(Movie movie) throws SQLException {
        String sql = "UPDATE MOVIEs SET TITLE = ?,genre = ?,  "
                + "movie_year = ?, description = ?,stock = ?, price = ? "
                + "WHERE id = ?";
        

        boolean isUpdated = false;
        
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
            statement.setString(1, movie.getTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3,  movie.getYear());
            statement.setObject(4, movie.getDescription());
            statement.setInt(5, movie.getStock());
            statement.setDouble(6, movie.getPrice());
            statement.setString(7, movie.getId());
            
            isUpdated = statement.executeUpdate() > 0;

            statement.close();
        }
        return isUpdated;
    }

    //delete a movie from database
    
    public boolean deleteMovie(int id) throws SQLException {
        
        String sql = "DELETE FROM movies WHERE id = ?";

        boolean isDeleted = false;
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            isDeleted = statement.executeUpdate() > 0;

            statement.close();
        }
        return isDeleted;
    }
}