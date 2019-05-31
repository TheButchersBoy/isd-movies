<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java"%>
<%@include file="navbar.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Welcome Page!</title>
    </head>
    
    <body>
        <div class="container">
            <h1 style="text-align: center">  
                <% User currentUser = (User)session.getAttribute("user"); %>
                Welcome Back, <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>
            </h1>
            <div style="text-align: center">
                <button style="font-size: 150%" class="btn btn-primary" type="button" onclick="location.href = 'index.jsp'" > Return Home </button>
                <button style="font-size: 150%" class="btn btn-primary" type="button" onclick="location.href = 'userProfile.jsp'" > Profile Page </button>
                <button style="font-size: 150%" class="btn btn-primary" type="button" onclick="location.href = 'logout.jsp'" > Logout </button>
            </div>
        </div>
    </body>
    <%@include file="footer.jsp"%>
</html>
