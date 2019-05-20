<%-- 
    Document   : order-history
    Created on : 18/05/2019, 10:42:24 PM
    Author     : jessicawiradinata
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
    </head>
    <body>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 3rem">Order History</h1>
            <h3 style="margin-bottom: 2rem">Search</h3>
            <div style="display: flex; margin-bottom: 6rem">
                <div style="margin-right: 2rem">
                    <label>Order number</label>
                    <input type="text" class="form-control" placeholder="eg. 123456789">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (from)</label>
                    <input type="date" class="form-control" placeholder="dd/MM/yyyy">
                </div>
                <div style="margin-right: 2rem">
                    <label>Order date (to)</label>
                    <input type="date" class="form-control" placeholder="dd/MM/yyyy">
                </div>
                <div style="display: flex; align-items: flex-end">
                    <button type="button" class="btn btn-primary">
                        Search
                    </button>
                </div>
            </div>
            <div style="width: 50%">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Order #123456789</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; align-items: center">
                            <p><b>$61.50</b></p>
                        </div>
                    </li>
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Order #123456789</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; align-items: center">
                            <p><b>$61.50</b></p>
                        </div>
                    </li>
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Order #123456789</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; align-items: center">
                            <p><b>$61.50</b></p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
