<%-- 
    Document   : order
    Created on : 13/05/2019, 4:48:06 PM
    Author     : jessicawiradinata
--%>

<%@page import="uts.isd.model.Movie"%>
<%@page import="uts.isd.model.Order"%>
<%@page import="uts.isd.model.dao.OrderDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<c:import url="/OrderServlet" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <%  
            Order order = (Order)session.getAttribute("order");
            String saveOrderError = (String)session.getAttribute("saveOrderError");
            String outOfStockError = (String)session.getAttribute("outOfStockError");
            String noUserError = (String)session.getAttribute("noUserError");
        %>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 4rem">Your Order</h1>
            <div style="display: flex">
                <div style="width: 50%; margin-right: 4rem">
                    <ul class="list-group list-group-flush">
                        <c:forEach items="<%= order.getMovies() %>" var="movie">
                            <% Movie movie = (Movie)pageContext.getAttribute("movie"); %>
                            <li class="list-group-item" style="display: flex;">
                                <div style="flex-grow: 1">
                                    <h4><%= movie.getTitle() %></h4>
                                    <p style="margin-bottom: 0">$<%= String.format("%.2f", movie.getPrice()) %></p>
                                    <p>Stock: <%= movie.getStock() %></p>
                                </div>
                                <form action="OrderServlet" method="post" style="display: flex; align-items: center; margin-bottom: 0">
                                    <input type="hidden" name="movieId" value="<%= movie.getId() %>">
                                    <input type="submit" class="btn btn-danger" name="action" value="Remove Movie">
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="panel panel-default" style="width: 50%;  padding-left: 1rem; padding-right: 1rem; display: flex;">
                    <div class="panel-body" style="display: flex; flex-direction: column; flex-grow: 1">
                        <h3 style="margin-top: 1rem; margin-bottom: 2rem;">Order details</h3>
                        <div style="display: flex; flex-grow: 1">
                            <p style="flex-grow: 1">Total price</p>
                            <p>$<%= String.format("%.2f", order.getTotalPrice()) %></p>
                        </div>
                        <form action="OrderServlet" method="post" style="display: flex; justify-content: flex-end; align-items: center; margin: 0">
                            <c:if test="<%= saveOrderError != null %>">
                                <p style="color: red; flex-grow: 1">Unable to save order with no movies. Please add movie(s) to your order.</p>
                            </c:if>
                            <c:if test="<%= outOfStockError != null %>">
                                <p style="color: red; flex-grow: 1">Order contains out of stock movie(s). Please remove out of stock movie(s) before saving order.</p>
                            </c:if>
                            <c:if test="<%= noUserError != null %>">
                                <p style="color: red; flex-grow: 1">Please login before saving an order.</p>
                            </c:if>
                            <input type="submit" class="btn btn-danger" style="margin-right: 1rem" name="action" value="Clear">
                            <input type="submit" class="btn btn-primary" name="action" value="Save">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
