<%-- 
    Document   : payment-details
    Created on : 2019-5-26, 14:25:35
    Author     : Frank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Details</title>
    </head>
    <body>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 3rem">Payment Details</h1>
            <h3 style="margin-bottom: 2rem">Payment History</h3>
            <div style="width: 100%">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Payment #00000001</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; flex-direction: column; align-items: center">
                            <p><b>$40</b></p>
                            <p><b>Cards</b></p>
                        </div>
                    </li>
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Payment #00000002</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; flex-direction: column; align-items: center">
                            <p><b>$20</b></p>
                            <p><b>Paypal</b></p>
                        </div>
                    </li>
                    <li class="list-group-item" style="display: flex;">
                        <div style="flex-grow: 1">
                            <h4>Payment #00000003</h4>
                            <p style="margin-bottom: 0">10/10/2018</p>
                        </div>
                        <div style="display: flex; flex-direction: column; align-items: center">
                            <p><b>$20</b></p>
                            <p><b>Bpay</b></p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </body>
</html>
