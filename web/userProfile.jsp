<%-- 
    Document   : userProfile
    Created on : 25/05/2019, 1:33:33 PM
    Author     : Kyle
--%>

<%@page import="uts.isd.model.Session"%>
<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Sessions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<c:import url="/SessionServlet" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <form action="SessionServlet" method="GET">
        <div>
            <h1 style="text-align: center">  
                <% User currentUser = (User)session.getAttribute("user"); %>
                Here is your Sessions History, <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
            </h1>             
        <% Sessions sessions = (Sessions)pageContext.getAttribute("sessions"); %>
        <c:forEach items="<%= sessions.getSessions() %>" var="session">
            <% Session ses = (Session)pageContext.getAttribute("session"); %>
            <li class="list-group-item" style="display: flex;">
                <div style="flex-grow: 1">
                    <h2><%= ses.getDate() %></h2>
                </div>
            </li>
        </c:forEach>
        </div>
        </form>
    </body>
    <%@include file="footer.jsp" %>
</html>
