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
<c:import url="/ConnServlet" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <%    
            OrderDBManager orderDBManager = (OrderDBManager)session.getAttribute("orderDBManager");
            orderDBManager.initialiseOrder(session);
            
            Order order = (Order)session.getAttribute("order");
        %>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 4rem">Your Order</h1>
            <div style="display: flex">
                <div style="width: 50%; margin-right: 4rem">
                    <ul class="list-group list-group-flush">
                        <c:forEach items="<%= order.getMovies() %>" var="movie">
                            <%
                                Movie movie = (Movie)pageContext.getAttribute("movie");
                            %>
                            <li class="list-group-item" style="display: flex;">
                                <div style="flex-grow: 1">
                                    <h4><%= movie.getTitle() %></h4>
                                    <p>$<%= String.format("%.2f", movie.getPrice()) %></p>
                                </div>
                                <div style="display: flex; align-items: center">
                                    <button type="button" class="btn btn-danger">
                                        Remove
                                    </button>
                                </div>
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
                        <div style="display: flex; justify-content: flex-end">
                            <button type="button" class="btn btn-danger" style="margin-right: 1rem">
                                Cancel
                            </button>
                            <button type="button" class="btn btn-primary" onclick="location.href = 'order-action.jsp'">
                                Submit order
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
