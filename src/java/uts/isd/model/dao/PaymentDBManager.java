/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.model.dao;

import java.sql.Connection;
import uts.isd.model.Payment;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import uts.isd.model.Movie;

/**
 *
 * @author Frank
 */
public class PaymentDBManager {
    
    private Connection conn;
    
    public PaymentDBManager(Connection conn) throws SQLException {
        
        this.conn = conn;
        
    }

    public void initialisePayment(HttpSession session) {
        
        Payment payment = new Payment();
        movies.add(new Movie("1", 20));
        movies.add(new Movie("2", 20));
        payment.setMovies(movies);
        payment.setTotalAmount(40);
        pyment.setId("00000001");
        
        session.setAttribute("Payment", payment);
        
    }
    
    public void PaymentOrder(int id, Double totalAmount) throws SQLException {
        String insertPaymentSql = "INSERT INTO ORDERS(ID, TOTALAMOUNT, DATE) " + "VALUES (?,?,?,?)";
        PreparedStatement add = conn.prepareStatement(insertOrderSql);
        
        int idInt = (new Random()).nextInt(999999);
        String id = Integer.toString(idInt);
        
        addOrder.setString(1, id);
        addOrder.setString(2, userId);
        addOrder.setDouble(3, totalPrice);
        addOrder.setDate(4, new java.sql.Date(new Date().getTime()));
        
        addOrder.executeUpdate();
        
        for (Movie movie: movies) {
            String insertOrderMovieSql = "INSERT INTO ORDER_MOVIE(ORDERID, MOVIEID) " + "VALUES (?,?)";
            PreparedStatement addOrderMovie = conn.prepareStatement(insertOrderMovieSql);
            
            addOrderMovie.setString(1, id);
            addOrderMovie.setString(2, movie.getId());
            
            addOrderMovie.executeUpdate();
        }
    }
}
