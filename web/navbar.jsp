<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<link rel="stylesheet" type="text/css" href="styles/navBar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%
    User userNav = (User) session.getAttribute("user");
%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <li>
                <a class="navbar-brand" href="category">ISD Movies</a>
            </li>
        </div>
        <ul class="nav navbar-nav">
            <li class="search">
                <a class="navbar-texts" href="search.jsp">Search Movie</a>
            </li>
            <li class="addmovie">
                <a class="navbar-texts" href="addmovie.jsp">Add Movie</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <%if (userNav == null) {%>
                <li class="order">
                    <a href="order.jsp">
                        <span class="glyphicon glyphicon-shopping-cart"> Cart</span>
                    </a>
                </li>
                <li>
                    <a href="register.jsp">
                        <span class="glyphicon glyphicon-user"> Register</span>
                    </a>
                </li>
                <li>
                    <a href="login.jsp">
                        <span class="glyphicon glyphicon-log-in"> Login</span>
                    </a>
                </li>
            <%} else {%>
                <li class="payment">
                    <a id="paymenttext" href="payment-details.jsp">
                        <span class="glyphicon glyphicon-user"> Payment</span>
                    </a>
                </li>
                <li class="orderhistory">
                    <a href="orderHistory.jsp">
                        <span class="glyphicon glyphicon-list"> OrderHistory</span>
                    </a>
                </li>
                <li class="order">
                    <a href="order.jsp">
                        <span class="glyphicon glyphicon-shopping-cart"> Cart</span>
                    </a>
                </li>
                <li>
                    <a href="userDetails.jsp">
                        <span class="glyphicon glyphicon-user">UserDetails</span>
                    </a>
                </li>
                <li>
                    <a href="userProfile.jsp">
                        <span class="glyphicon glyphicon-list-alt">UserProfile</span>
                    </a>
                </li>
                 <li>
                    <a href="UserServlet?action=logout">
                        <span class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>
            <%}%>
        </ul>
    </div>
</nav>
