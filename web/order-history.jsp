<%-- 
    Document   : order-history
    Created on : 18/05/2019, 10:42:24 PM
    Author     : jessicawiradinata
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="uts.isd.model.*"%>
<%@include file="navbar.jsp" %>
<c:import url="/ConnServlet" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
    </head>
    <body>
        <%    
            OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
            ArrayList<Order> orders = orderDBManager.getOrders();
            System.out.print(orders);
        %>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 3rem">Order History</h1>
            <h3 style="margin-bottom: 2rem">Search</h3>
            <div style="display: flex; margin-bottom: 6rem">
                <div style="margin-right: 2rem">
                    <label>Order number</label>
                    <input type="text" class="form-control" placeholder="eg. 123456789">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (from)</label>
                    <input type="date" class="form-control" placeholder="dd/MM/yyyy">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (to)</label>
                    <input type="date" class="form-control" placeholder="dd/MM/yyyy">
                </div>
                <div style="display: flex; align-items: flex-end">
                    <button type="button" class="btn btn-primary">
                        Search
                    </button>
                </div>
            </div>
            <div style="width: 50%">
                <ul class="list-group list-group-flush">
                    <c:forEach items="<%= orders %>" var="order">
                        <%
                            Order order = (Order)pageContext.getAttribute("order");
                        %>
                        <li class="list-group-item" style="display: flex; flex-direction: column">
                            <div style="display: flex;">
                                <h4 style="flex-grow: 1">Order #<%= order.getId() %></h4>
                                <h4>$<%= String.format("%.2f", order.getTotalPrice()) %></h4>
                            </div>
                            <p><%= order.getDate() %></p>
                            <div style="display: flex; flex-direction: column">
                                <p style="margin-bottom: 0"><b>Movies:</b></p>
                                <c:forEach items="<%= order.getMovies() %>" var="movie">
                                    <%
                                        Movie movie = (Movie)pageContext.getAttribute("movie");
                                    %>
                                    <div style="display: flex; justify-content: space-between">
                                        <p style="margin-bottom: 0"><%= movie.getTitle() %></p>
                                        <p style="margin-bottom: 0">$<%= String.format("%.2f", movie.getPrice()) %></p>
                                    </div>
                                </c:forEach>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
