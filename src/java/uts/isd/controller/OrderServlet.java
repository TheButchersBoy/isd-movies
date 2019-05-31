/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Movie;
import uts.isd.model.Order;
import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.OrderDBManager;

/**
 *
 * @author jessicawiradinata
 */
public class OrderServlet extends HttpServlet {
    
    private DBConnector db;
    private OrderDBManager manager;
    private Connection conn;
    
    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        conn = db.openConnection();
        session.setAttribute("db", db);
        session.setAttribute("conn", conn);
        
        try {
            manager = new OrderDBManager(conn);
            session.setAttribute("orderDBManager",manager);
        } catch (Exception ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (session.getAttribute("order") == null) {
            manager.initialiseOrder(session);
        }
        
        try {
            getOrders(request);
        } catch (Exception ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        clearErrors(request);
        
        try {
            if (action.equals("Submit")) {
                submitOrder(request, response);
            } else if (action.equals("Remove Movie")) {
                removeMovieFromOrderSession(request, response);
            } else if (action.equals("Clear")) {
                clearOrder(request, response);
            } else if (action.equals("Cancel")) {
                cancelOrder(request, response);
            } else if (action.equals("Save")) {
                saveOrder(request, response);
            } else if (action.equals("Remove")) {
                removeMovie(request, response);
            } else if (action.equals("Add Movie")) {
                addMovieToOrderSession(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void submitOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
        String orderId = request.getParameter("orderId");
        ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders");
        
        for (Order order: orders) {
            if (order.getId().equals(orderId)) {
                orderDBManager.submitOrder(order.getId());
                response.sendRedirect("orderHistory.jsp");
            }
        }
    }
    
    private void saveOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
        User user = (User)session.getAttribute("user");
            
        Order order = (Order)session.getAttribute("order");
        
        if (order.getMovies().size() > 0) {
            boolean containsOutOfStockMovie = false;
            
            for (Movie movie: order.getMovies()) {
                if (movie.getStock() < 1) {
                    containsOutOfStockMovie = true;
                }
            }
            
            if (!containsOutOfStockMovie) {
                orderDBManager.addOrder(user.getId(), order.getMovies(), order.getTotalPrice());

                session.setAttribute("order", new Order());

                response.sendRedirect("orderHistory.jsp");
            } else {
                session.setAttribute("outOfStockError", "error");
                
                response.sendRedirect("order.jsp");
            }
            
        } else {
            session.setAttribute("saveOrderError", "error");
            response.sendRedirect("order.jsp");
        }
    }
    
    private void clearOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        session.setAttribute("order", new Order());
        
        response.sendRedirect("order.jsp");
    }
    
    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        String orderId = request.getParameter("orderId");
        ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders");
        
        for (Order order: orders) {
            if (order.getId().equals(orderId)) {
                if (order.isCancellable()) {
                    manager.cancelOrder(orderId);
                }
                else {
                    session.setAttribute("cancelOrderErrorId", order.getId());
                } 
                
                response.sendRedirect("orderHistory.jsp");
            }
        }
    }
    
    private void removeMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
        String orderId = request.getParameter("orderId");
        String movieId = request.getParameter("movieId");
        ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders");
        
        for (Order order: orders) {
            if (order.getId().equals(orderId)) {
                if (order.getMovies().size() > 1) {
                    order.removeMovie(movieId);
                    order.updateTotalPrice();
                    orderDBManager.removeMovie(orderId, movieId, order.getTotalPrice());
                } else {
                    session.setAttribute("removeMovieErrorId", orderId);
                }
            }
            
            response.sendRedirect("orderHistory.jsp");
        }
    }
    
    private void addMovieToOrderSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");
        Movie movie = new Movie();
        
        order.addMovie(movie);
        order.updateTotalPrice();
        
        session.setAttribute("order", order);
        
        // TODO: send redirect to movies page
    }
    
    private void removeMovieFromOrderSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String movieId = request.getParameter("movieId");
        Order order = (Order)session.getAttribute("order");
        
        order.removeMovie(movieId);
        order.updateTotalPrice();
        
        session.setAttribute("order", order);
        
        response.sendRedirect("order.jsp");
    }
    
    private void getOrders(HttpServletRequest request) throws SQLException, ParseException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        ArrayList<Order> orders = manager.getOrders(user.getId());
        String orderIdFilter = request.getParameter("orderId");
        String dateFromFilter = request.getParameter("dateFrom");
        String dateToFilter = request.getParameter("dateTo");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (orderIdFilter != null && !orderIdFilter.isEmpty()) {
            orders.removeIf(order -> !order.getId().equals(orderIdFilter));
        }

        if (dateFromFilter != null && !dateFromFilter.isEmpty()) {
            Date dateFrom = dateFormat.parse(dateFromFilter);
            orders.removeIf(order -> order.getDate().before(dateFrom));
        }

        if (dateToFilter != null && !dateToFilter.isEmpty()) {
            Date dateTo = dateFormat.parse(dateToFilter);
            orders.removeIf(order -> order.getDate().after(dateTo));
        }

        session.setAttribute("orders", orders);
    }
    
    private void clearErrors(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        session.removeAttribute("removeMovieErrorId");
        session.removeAttribute("cancelOrderErrorId");
        session.removeAttribute("removeMovieErrorId");
        session.removeAttribute("outOfStockError");
    }
}
