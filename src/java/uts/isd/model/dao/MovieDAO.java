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

        String sql = "SELECT * FROM dbadmin.movie WHERE movie_id = ?";
        
        Movie movie = null;

        try (PreparedStatement statement = myConn.prepareStatement(sql)) {
            statement.setInt(1, movieID);
            ResultSet results = statement.executeQuery();

            // found the required movie with specific id: 
            if (results.next()) {
                
                int id = results.getInt("MOVIE_ID");
                
                String title = results.getString("MOVIETITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int copies = results.getInt("copies");
                
                double price = results.getDouble("price");
                byte[] movieImg = results.getBytes("MOVIE_IMG");

                movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         copies,  price,movieImg);
                
            }
            statement.close();
            results.close();
        }
        return movie;
    }
    
// get all movies from database, save it in a list;
    
    public List<Movie> getAllMovies() throws SQLException {
        
        List<Movie> allMovieList = new ArrayList<>();

        String sql = "SELECT * FROM dbadmin.movie";
        
         System.out.println("Allmovies SQL: "+ sql);

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);
            int num = 0;

            while (results.next()) {
                
                System.out.println("Movie Count:  "+ (++num));
                
               int id = results.getInt("MOVIE_ID");
                
                String title = results.getString("MOVIETITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int copies = results.getInt("copies");
                
                double price = results.getDouble("price");
                
                Blob blob = results.getBlob("MOVIE_IMG");    
                
            byte [] movieImg = blob.getBytes( 1, ( int ) blob.length() );
                
           //     byte[] movieImg = results.getBytes("MOVIE_IMG");

               Movie movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         copies,  price,movieImg);
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
                 sql = "SELECT * FROM dbadmin.movie where genre = '"+genreStr.trim()+"'";

        }
        // search by title
        if(genreStr.isEmpty()){
                 sql = "SELECT * FROM dbadmin.movie where movietitle = '"+movieTitle.trim()+"'";

        }
        // search by both genre and title
        if(!movieTitle.isEmpty()&&!genreStr.isEmpty()){
            
          sql = "SELECT * FROM dbadmin.movie where movietitle = '"+movieTitle.trim() +"' AND genre = '"+genreStr.trim()+"'";
        }
        
        
          System.out.println("Search SQL: "+ sql);

       

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);
            
            while (results.next()) {
                
               int id = results.getInt("MOVIE_ID");
                
                String title = results.getString("MOVIETITLE");
                String genre = results.getString("genre");
                int year = results.getInt("MOVIE_YEAR");
                
                String description = results.getString("description");
                int copies = results.getInt("copies");
                
                double price = results.getDouble("price");
                byte[] movieImg = results.getBytes("MOVIE_IMG");

               Movie movie = new Movie( id,  title, 
                         genre,  year,  description, 
                         copies,  price,movieImg);
                searchResult.add(movie);
            }
            statement.close();
            results.close();
        }
        return searchResult;
    }
    

    // see if certain movie exists, return false if not
    
    public boolean isMovieExist(int ID) throws SQLException {
        
        String sql = "SELECT * FROM dbadmin.movie";
        boolean isExist = false;

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                if (results.getInt("MOVIE_ID") == ID) {
                    isExist = true;
                    break;
                }
            }
            statement.close();
            results.close();
        }
        return isExist;
    }
    
    // see if certain movie out of stock
    
    public boolean movieHasStock(int ID) throws SQLException {
        
        String sql = "SELECT * FROM APP.movie";
        boolean hasStock = true;

        try (Statement statement = this.myConn.createStatement()) {
            
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                if (results.getInt("MOVIE_ID") == ID) {
                    if(results.getInt("copies")<=0){
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
        
        String sql = "select * from dbadmin.movie ";
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
        String sql = "INSERT INTO dbadmin.MOVIE"
                + " (movie_id, movietitle,  genre,  movie_year,  description, copies,  price, movie_img) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        
        boolean isAdded = false;
        
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
             statement.setInt(1, movie.getMovieID());
             
            statement.setString(2, movie.getMovieTitle());
            statement.setString(3, movie.getGenre());
            statement.setInt(4,  movie.getYear());
            statement.setObject(5, movie.getDescription());
            statement.setInt(6, movie.getCopies());
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
        String sql = "UPDATE dbadmin.MOVIE SET movietitle = ?,genre = ?,  "
                + "movie_year = ?, description = ?,copies = ?, price = ? "
                + "WHERE movie_id = ?";
        

        boolean isUpdated = false;
        
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
            statement.setString(1, movie.getMovieTitle());
            statement.setString(2, movie.getGenre());
            statement.setInt(3,  movie.getYear());
            statement.setObject(4, movie.getDescription());
            statement.setInt(5, movie.getCopies());
            statement.setDouble(6, movie.getPrice());
            statement.setInt(7, movie.getMovieID());
            
            isUpdated = statement.executeUpdate() > 0;

            statement.close();
        }
        return isUpdated;
    }

    //delete a movie from database
    
    public boolean deleteMovie(int ID) throws SQLException {
        
        String sql = "DELETE FROM dbadmin.movie WHERE movie_id = ?";

        boolean isDeleted = false;
        try (PreparedStatement statement = this.myConn.prepareStatement(sql)) {
            
            statement.setInt(1, ID);
            
            isDeleted = statement.executeUpdate() > 0;

            statement.close();
        }
        return isDeleted;
    }
}