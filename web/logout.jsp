<%-- 
    Document   : logout
    Created on : 27/05/2019, 6:06:55 PM
    Author     : Kyle
--%>
<%@include file="navbar.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <% 
            session.invalidate(); 
        %>
        <h1>You have successfully logged out!</h1>
        <button class="button" type="button" onclick="location.href = 'category'" > Back Home </button>
    </body>
    <%@include file="footer.jsp" %>
</html>
