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
import uts.isd.model.Order;
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
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String action= request.getParameter("action");
        
        try {
            if ("Submit".equals(action)) {
                submitOrder(request, response);
            } else if ("Remove".equals(action)) {
                removeMovie(request, response);
            } else if ("Cancel".equals(action)) {
                cancelOrder(request, response);
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
            
        Order order = (Order)session.getAttribute("order");
        orderDBManager.addOrder(order.getUserId(), order.getMovies(), order.getTotalPrice());

        session.setAttribute("order", new Order());
        
        response.sendRedirect("orderAction.jsp");
    }
    
    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        session.setAttribute("order", new Order());
        
        response.sendRedirect("order.jsp");
    }
    
    private void removeMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        
        ArrayList<Order> orders = manager.getOrders();
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
}
