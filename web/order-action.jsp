<%-- 
    Document   : order-action
    Created on : 26/05/2019, 1:33:43 PM
    Author     : jessicawiradinata
--%>

<%@page import="uts.isd.model.Order"%>
<%@page import="uts.isd.model.dao.OrderDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<c:import url="/ConnServlet" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Order</title>
    </head>
    <body>
        <%    
            OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
            
            Order order = (Order)session.getAttribute("order");
            orderDBManager.addOrder(order.getUserId(), order.getMovies(), order.getTotalPrice());
            
            session.setAttribute("order", new Order());
        %>
        <p>Order submitted! Click <a href="index.jsp">here</a> to return to the main page.</p>
    </body>
</html>
