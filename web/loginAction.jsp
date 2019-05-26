<%-- 
    Document   : loginAction
    Created on : 20/05/2019, 2:38:12 PM
    Author     : Kyle
--%>
<%@page import="uts.isd.model.dao.*"%>
<%@page import="uts.isd.model.*"%>
<%@page import="uts.isd.controller.*"%>
<%@page import="java.sql.*"%>
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
            DBManager manager = (DBManager)session.getAttribute("manager");
            boolean status = manager.checkUser(email, password);
            User user = new User ("id", "email", "password","firstName", "lastName", "dob");
            
            if (email == null || password == null) {
                out.print("Invalid parameters");
            }
            
            if(status){
                session.setAttribute("userLogin", user);
                response.sendRedirect("loginWelcome.jsp");
            }
            else {
                session.setAttribute("existErr", "User profile does not exist!");
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>
