/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpSession;
import uts.isd.model.Movie;
import uts.isd.model.Order;

/**
 *
 * @author jessicawiradinata
 */
public class OrderDBManager {
    
    private Connection conn;
    
    public OrderDBManager(Connection conn) throws SQLException {
        this.conn = conn;
    }
    
    public void initialiseOrder(HttpSession session) {
        // Mock
        Order order = new Order();
        ArrayList<Movie> movies = new ArrayList();
      //  movies.add(new Movie("1001", "The Avengers", "Action", "2011",40.55, 50, ""));
       // movies.add(new Movie("1002", "Antman", "", 20.0, 35, "Action"));
        order.setMovies(movies);
        order.setTotalPrice(60.55);
        order.setUserId("987654321");
        
        session.setAttribute("order", order);
//        if (session.getAttribute("order") == null) {
//            Order order = new Order();
//            
//            order.setTotalPrice(0.0);
//            order.setUserId("987654321"); // TODO: get user id from session
//            
//            session.setAttribute("order", order);
//        }
    }
    
    public void addOrder(String userId, ArrayList<Movie> movies, Double totalPrice) throws SQLException {
        String insertOrderSql = "INSERT INTO ORDERS(ID, USERID, TOTALPRICE, DATE, STATUS) " + "VALUES (?,?,?,?,?)";
        PreparedStatement addOrder = conn.prepareStatement(insertOrderSql);
        
        int idInt = (new Random()).nextInt(999999);
        String id = Integer.toString(idInt);
        
        addOrder.setString(1, id);
        addOrder.setString(2, userId);
        addOrder.setDouble(3, totalPrice);
        addOrder.setDate(4, new java.sql.Date(new Date().getTime()));
        addOrder.setString(5, "Submitted");
        
        addOrder.executeUpdate();
        
        for (Movie movie: movies) {
            String insertOrderMovieSql = "INSERT INTO ORDER_MOVIE(ORDERID, MOVIEID) " + "VALUES (?,?)";
            PreparedStatement addOrderMovie = conn.prepareStatement(insertOrderMovieSql);
            
            addOrderMovie.setString(1, id);
            addOrderMovie.setString(2, movie.getId());
            
            addOrderMovie.executeUpdate();
            
            String updateMovieStockSql = "UPDATE MOVIES SET STOCK = STOCK - 1 WHERE ID = ?";
            PreparedStatement updateMovieStock = conn.prepareStatement(updateMovieStockSql);
            
            updateMovieStock.setString(1, movie.getId());
            
            updateMovieStock.executeUpdate();
        }
    }
    
    public void cancelOrder(String orderId) throws SQLException {
        String cancelOrderSql = "UPDATE ORDERS SET STATUS = 'Cancelled' WHERE ID = ?";
        PreparedStatement cancelOrder = conn.prepareStatement(cancelOrderSql);
        
        cancelOrder.setString(1, orderId);
        
        cancelOrder.executeUpdate();
    }
    
    public ArrayList<Order> getOrders() throws SQLException {
        // TODO: get orders by user id
        String ordersSql = "SELECT * FROM ORDERS ORDER BY DATE DESC";
        PreparedStatement getOrders = conn.prepareStatement(ordersSql);
        
        ArrayList<Order> orders = new ArrayList<Order>();
        ResultSet resultSet = getOrders.executeQuery();
        
        while(resultSet.next()) {
            Order order = new Order();
            
            order.setId(resultSet.getString("ID"));
            order.setUserId(resultSet.getString("USERID"));
            order.setTotalPrice(resultSet.getDouble("TOTALPRICE"));
            order.setDate(resultSet.getDate("DATE"));
            order.setStatus(resultSet.getString("STATUS"));
            
            orders.add(order);
        }
        
        for (Order order: orders) {
            String orderMovieSql = "SELECT MOVIES.ID, MOVIES.TITLE, MOVIES.PRICE, MOVIES.DESCRIPTION "
                    + "FROM ORDER_MOVIE "
                    + "INNER JOIN MOVIES ON ORDER_MOVIE.MOVIEID = MOVIES.ID "
                    + "WHERE ORDER_MOVIE.ORDERID = ?";
            PreparedStatement getOrderMovie = conn.prepareStatement(orderMovieSql);
            
            getOrderMovie.setString(1, order.getId());
            
            ResultSet getOrderMovieResult = getOrderMovie.executeQuery();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            
            while(getOrderMovieResult.next()) {
                Movie movie = new Movie();
                
                movie.setId(getOrderMovieResult.getString("ID"));
                movie.setTitle(getOrderMovieResult.getString("TITLE"));
                movie.setDescription(getOrderMovieResult.getString("DESCRIPTION"));
                movie.setPrice(getOrderMovieResult.getDouble("PRICE"));
                
                movies.add(movie);
            }
            
            order.setMovies(movies);
        }
        
        return orders;
    }
}
