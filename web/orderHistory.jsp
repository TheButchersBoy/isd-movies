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
            String cancelOrderErrorId = (String)session.getAttribute("cancelOrderErrorId");
            String removeMovieErrorId = (String)session.getAttribute("removeMovieErrorId");
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
                                    <li class="list-group-item" style="display: flex; align-items: center">
                                        <p style="margin-bottom: 0; flex-grow: 1"><%= movie.getTitle() %></p>
                                        <p style="margin-bottom: 0">$<%= String.format("%.2f", movie.getPrice()) %></p>
                                        <c:if test="<%= order.getStatus().equals("Saved") %>">
                                            <form action="OrderServlet" method="post" style="margin: 0; margin-left: 2rem">
                                                <input type="hidden" name="orderId" value="<%= order.getId() %>">
                                                <input type="hidden" name="movieId" value="<%= movie.getId() %>">
                                                <input type="submit" class="btn btn-default" name="action" value="Remove"> 
                                            </form> 
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="panel-footer">
                                <form action="OrderServlet" method="post" style="margin: 0; display: flex; justify-content: flex-end; align-items: center">
                                    <input type="hidden" name="orderId" value="<%= order.getId() %>">
                                    <c:if test="<%= cancelOrderErrorId != null && cancelOrderErrorId.equals(order.getId()) %>">
                                        <p style="color: red; flex-grow: 1">Unable to cancel a finalised order.</p>
                                    </c:if>
                                        <c:if test="<%= removeMovieErrorId != null && removeMovieErrorId.equals(order.getId()) %>">
                                        <p style="color: red; flex-grow: 1">Unable to remove all movies in an order. Please cancel order instead.</p>
                                    </c:if>
                                    <c:if test="<%= order.getStatus().equals("Saved") %>">
                                      <input type="submit" class="btn btn-primary" style="margin-right: 1rem" name="action" value="Submit">  
                                    </c:if>
                                    <input type="submit" class="btn btn-danger" name="action" value="Cancel"> 
                                </form>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
