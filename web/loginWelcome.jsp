<%-- 
    Document   : loginWelcome
    Created on : 20/05/2019, 6:43:53 PM
    Author     : Kyle
--%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Welcome Page!</title>
    </head>
    
    <%
        User user = (User)session.getAttribute("userLogin");
    %>
    <body>
        <h1>Welcome Back,</h1>
        <button class="button" type="button" onclick="location.href = 'index.jsp'" > Main Page </button>
        <button class="button" type="button" onclick="location.href = 'userProfile.jsp'" > Profile Page </button>
    </body>
</html>
