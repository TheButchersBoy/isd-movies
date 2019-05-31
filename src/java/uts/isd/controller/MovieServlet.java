/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import uts.isd.model.Movie;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.MovieDAO;

/**
 *
 * @author Alvin
 */

// manages communication/contents between client client side and data model

public class MovieServlet extends HttpServlet {

    private DBConnector db;
    private Connection conn;
    private MovieDAO movieDAO;

    @Override
    public void init() {
        try {
            db = new DBConnector();
            conn = db.openConnection();
            movieDAO = new MovieDAO(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
    
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        String steps = request.getServletPath();

        try {
            switch (steps) {
                
            case "/category":
                // get all movies into a list, and save it to session
                 System.out.println("start showing all movies");
                getAllMovies(request, response);
                break;
                
                case "/search":
                searchMovieByTitleOrGenre(request, response);
                break;
                
            case "/addmovie":
                System.out.println("start adding movie");
                addMovie(request, response);
                
                break;
                
                case "/showimage":
                System.out.println("start displaying image");
                showImage(request, response);
                
                break;
            default:
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    
   private void showImage(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
       
       byte[] tmp = (byte[]) request.getAttribute("movieimg");
       
       ServletOutputStream out = null;

        try {
            out = response.getOutputStream();
            out.write(tmp);
        } finally {
            if (out != null) out.close();
        }
   
   }

    // exports the list of movies to the session on catalogues page
    
    private void getAllMovies(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        
         System.out.println("Getting all movies...");
         
         
        List<Movie> allMovieList = movieDAO.getAllMovies();
       
        request.setAttribute("allMovies", allMovieList);
        
       RequestDispatcher dispatcher = request.getRequestDispatcher("category.jsp");
       
       dispatcher.forward(request, response);
    }
    
    
    
    private void searchMovieByTitleOrGenre(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        
        
        String title = request.getParameter("movieTitleInput");
        
        String genre = request.getParameter("movieGenreInput");
        
        
        List<Movie> searchResult = movieDAO.searchByTitleOrGenre(title, genre);
        
        request.setAttribute("searchResult", searchResult);
        
       RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
       
       dispatcher.forward(request, response);
    }

 
    // creates a new Movie object and uses MovieDAO to add it to database
    private void addMovie(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        System.out.println("start adding movie...");
        
        String title = request.getParameter("movieTitleInput");
         System.out.println( "title: "+ title);
        
        String genre = request.getParameter("movieGenreInput");
        System.out.println( "genre: "+ genre);
        int year = Integer.parseInt(request.getParameter("movieYearInput"));  
        System.out.println( "year: "+ year);
        String description = request.getParameter("movieDescriptionInput");
        System.out.println( "description: "+ description);
        
        int copies = Integer.parseInt(request.getParameter("movieCopiesInput"));
        System.out.println( "copies: "+ copies);
        
        double price = Double.parseDouble(request.getParameter("moviePriceInput"));
        System.out.println( "price: "+ price);
        
          int newMovieID = movieDAO.getMovieNumbers() + 1;
          System.out.println("id:"+ newMovieID);
          
   System.out.println("id:"+ newMovieID + " title:"+ title + 
                         " genre: "+ genre + "year: "+ year +"description:"+  description
                         +"copy: "+ copies + " price:"+  price);
   
        // Retrieves <input type="file" name="movieImgInput">
        
        Part filePart = request.getPart("movieImgInput");
        
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
    
    InputStream fileContent = filePart.getInputStream();
    
    
        
byte[] movieImg = IOUtils.toByteArray(fileContent);

     //   byte[] movieImg = Files.readAllBytes(imgPath.toPath());

      
        
        Movie movie = new Movie(newMovieID, title, 
                         genre,  year,  description, 
                         copies,  price,movieImg);
        
        
        
       if( movieDAO.addMovie(movie)){
           response.sendRedirect("index.jsp"); 
       }else
       {
           response.sendRedirect("error.jsp");
       }

        // return to all movies web page
        

    }
    
    // release resource of the application
    
    @Override 
     public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

