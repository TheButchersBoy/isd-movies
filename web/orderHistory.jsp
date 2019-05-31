<%-- 
    Document   : order-history
    Created on : 18/05/2019, 10:42:24 PM
    Author     : jessicawiradinata
--%>

<%@page import="java.util.Objects"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="uts.isd.model.*"%>
<%@include file="navbar.jsp" %>
<c:import url="/OrderServlet" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
    </head>
    <body>
        <%  
            ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("orders"); 
            String orderId = request.getParameter("orderId");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
        %>
        <div action="orderHistory.jsp" method="get" style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 3rem">Order History</h1>
            <h3 style="margin-bottom: 2rem">Search</h3>
            <form style="display: flex; margin-bottom: 6rem">
                <div style="margin-right: 2rem">
                    <label>Order ID</label>
                    <input type="text" class="form-control" name="orderId" placeholder="eg. 123456789" value="<%= Objects.toString(orderId, "") %>">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (from)</label>
                    <input type="date" class="form-control" name="dateFrom" placeholder="dd/MM/yyyy" value="<%= dateFrom %>">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (to)</label>
                    <input type="date" class="form-control" name="dateTo" placeholder="dd/MM/yyyy"  value="<%= dateTo %>">
                </div>
                <div style="display: flex; align-items: flex-end">
                    <input type="submit" class="btn btn-primary" name="action" value="Search">
                </div>
            </form>
            <div style="width: 50%">
                <ul style="display: flex; flex-direction: column; padding: 0">
                    <c:forEach items="<%= orders %>" var="order">
                        <% Order order = (Order)pageContext.getAttribute("order"); %>
                        <li class="panel panel-primary" style="display: flex; flex-direction: column; margin-bottom: 3rem">
                            <div class="panel-heading" style="display: flex;">
                                <h4 class="panel-title" style="flex-grow: 1">Order #<%= order.getId() %></h4>
                                <h4 class="panel-title">$<%= String.format("%.2f", order.getTotalPrice()) %></h4>
                            </div>
                            <div class="panel-body" style="display: flex">
                                <p style="flex-grow: 1"><%= order.getDate() %></p>
                                <p><%= order.getStatus() %></p>
                            </div>
                            <ul class="list-group">
                                <p style="margin-left: 1.5rem"><b>Movies</b></p>
                                <c:forEach items="<%= order.getMovies() %>" var="movie">
                                    <% Movie movie = (Movie)pageContext.getAttribute("movie"); %>
                                    <li class="list-group-item" style="display: flex; justify-content: space-between">
                                        <p style="margin-bottom: 0"><%= movie.getTitle() %></p>
                                        <p style="margin-bottom: 0">$<%= String.format("%.2f", movie.getPrice()) %></p>
                                    </li>
                                </c:forEach>
                            </ul>
                            <c:if test="<%= !order.isCancelled() %>">
                                <div class="panel-footer" style="display: flex; justify-content: flex-end">
                                    <form action="OrderServlet" method="post" style="margin: 0">
                                        <input type="hidden" name="orderId" value="<%= order.getId() %>">
                                        <input type="submit" class="btn btn-default" name="action" value="Cancel"> 
                                    </form>
                                </div>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
