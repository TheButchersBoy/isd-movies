<%-- 
    Document   : loginAction
    Created on : 20/05/2019, 2:38:12 PM
    Author     : Kyle
--%>

<%@page import="uts.isd.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.io.*"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Action</title>
    </head>
    <body>
        <%
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            user userlogin = new user ("email", "password");
                session.setAttribute("userLogin", userlogin);
                response.sendRedirect("welcome.jsp");
        %>
    </body>
</html>
