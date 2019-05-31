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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDBManager;


/**
 *
 * @author Frank
 */
public class Paymentservlet extends HttpServlet {
    
    private DBConnector db;
    private PaymentDBManager manager;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        conn = db.openConnection();
        session.setAttribute("db", db);
        session.setAttribute("conn", conn);
        
        try {
            manager = new PaymentDBManager(conn);
            session.setAttribute("paymentDBManager",manager);
        } catch (Exception ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (session.getAttribute("payment") == null) {
            session.setAttribute("payment", new Payment());
        }
        
        try {
            getPayment(request);
        } catch (Exception ex) {
            Logger.getLogger(Paymentservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action= request.getParameter("action");
        
        try {
            if ("Submit".equals(action)) {
                submitPayment(request, response);
            } else if ("Remove".equals(action)) {
                removePayment(request, response);
            } else if ("Cancel".equals(action)) {
                cancelPayment(request, response);
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
    
    private void submitPayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        PaymentDBManager paymentDBManager = (PaymentDBManager)session.getAttribute("paymentDBManager");
            
        Payment payment = (Payment)session.getAttribute("payment");
        paymentDBManager.addPayment(payment.getAmount(), payment.getDate(), payment.getMethod());
        session.setAttribute("payment", new Payment());
        
        response.sendRedirect("payment-details.jsp");
    }
    
    private void cancelPayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        
        session.setAttribute("payment", new Payment());
        
        response.sendRedirect("payment.jsp");
    }
    
    private void removePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String paymentId = request.getParameter("paymentId");
        Payment payment = (Payment)session.getAttribute("payment");
        
        payment.updateAmount();
        
        session.setAttribute("payment", payment);
        
        response.sendRedirect("payment.jsp");
    }
    
    private void getPayment(HttpServletRequest request) throws SQLException, ParseException {
        HttpSession session = request.getSession();
        
        ArrayList<Payment> payments = manager.getPayments();
        String paymentIdFilter = request.getParameter("paymentId");
        String dateFromFilter = request.getParameter("dateFrom");
        String dateToFilter = request.getParameter("dateTo");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (paymentIdFilter != null && !paymentIdFilter.isEmpty()) {
            payments.removeIf(new Predicate<Payment>() {
                @Override
                public boolean test(Payment payment) {
                    return !paymentIdFilter.equals(payment.getId());
                }
            });
        }
        
        if (dateFromFilter != null && !dateFromFilter.isEmpty()) {
            Date dateFrom = dateFormat.parse(dateFromFilter);
            payments.removeIf(payment -> payment.getDate().before(dateFrom));
        }

        if (dateToFilter != null && !dateToFilter.isEmpty()) {
            Date dateTo = dateFormat.parse(dateToFilter);
            payments.removeIf(order -> order.getDate().after(dateTo));
        }
        System.out.println("****** " + payments.get(0).getMethod());
        session.setAttribute("payments", payments);
    }
}
