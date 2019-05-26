<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<link rel="stylesheet" type="text/css" href="styles/bookList.css"/>	
<link rel="stylesheet" type="text/css" href="styles/navBar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">               
            <li>
                <a class="navbar-brand" href="index.jsp">ISD Movies</a>
            </li>         
        </div>
        <ul class="nav navbar-nav">                 
            <li class="categories">
                <a id="hometext" href="category.jsp">Categories</a>
            </li>   
            <li class="search">
                <a id="hometext" href="index.jsp">&#x1F50D;</a>
            </li>   
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="register">
                <a id="registertext" href="register.jsp">
                    <span class="glyphicon glyphicon-user"> Register</span>
                </a>
            </li>
            <li class="login">
                <a id="logintext" href="login.jsp">
                    <span class="glyphicon glyphicon-log-in"> Login</span>
                </a>
            </li>
        </ul>
    </div>
</nav>