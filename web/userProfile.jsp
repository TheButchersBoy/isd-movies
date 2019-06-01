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
        <% 
            ArrayList<Sessions> sessionList = (ArrayList<Sessions>)session.getAttribute("sessionList");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
        %>
        <div>
            <h1 style="text-align: center">  
                <% User currentUser = (User)session.getAttribute("user"); %>
                Here is your Sessions History, <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
            </h1>
        <form style="display: flex; margin-bottom: 6rem" action="userProfile.jsp" method="GET">
                <div style="margin-right: 3rem; margin-left: 5rem">
                    <label>Session date (from)</label>
                    <input type="date" class="form-control" name="dateFrom" placeholder="dd/MM/yyyy" value="<%= dateFrom %>">
                </div>
                <div style="margin-right: 3rem">
                    <label>Session date (to)</label>
                    <input type="date" class="form-control" name="dateTo" placeholder="dd/MM/yyyy"  value="<%= dateTo %>">
                </div>
                <div style="display: flex; align-items: flex-end">
                    <input type="submit" class="btn btn-primary" name="action" value="Search">
                </div>
            </form>
        <div style="display: flex; margin-left: 4rem">
                <div style="width: 50%; margin-right: 4rem">
                    <ul class="list-group list-group-flush">
                        <c:forEach items="<%= sessionList %>" var="session">
                            <% Session ses = (Session)pageContext.getAttribute("session"); %>
                            <li class="list-group-item" style="display: flex;">
                                <div style="flex-grow: 1">
                                    <h4>User ID, <%= ses.getUserId() %> logged in on the <%= ses.getDate() %>.</h4>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
        </div>
                <form action="SessionServlet" method="post" style="margin: 0; margin-left: 6rem">
                    <input type="submit" class="btn btn-danger" name="action" value="Clear Sessions">
                </form>
    <%@include file="footer.jsp" %>
</html>
