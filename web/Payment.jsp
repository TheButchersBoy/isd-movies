<%-- 
    Document   : Payment
    Created on : 2019-5-26, 15:41:29
    Author     : Frank
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.Payment"%>
<%@page import="uts.isd.model.dao.PaymentDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<c:import url="/Paymentservlet" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
    </head>
    <body>
        <%  
            ArrayList<Payment> payments = (ArrayList<Payment>)session.getAttribute("payments"); 
        %>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 4rem">Bill list</h1>
            <div style="display: flex">
                <div class="panel panel-default" style="width: 75%;  padding-left: 1rem; padding-right: 1rem; display: flex;">
                    <div class="panel-body" style="display: flex; flex-direction: column; flex-grow: 1">
                        <h3 style="margin-top: 1rem; margin-bottom: 2rem;">Payment details</h3>
                        <div style="display: flex">
                            <% Payment payment = (Payment)pageContext.getAttribute("payment"); %>
                            <p style="flex-grow: 1">Total Amount</p>
                            <p>$ </p>
                        </div>
                        <form action="Paymentservlet" method="post" style="display: flex; justify-content: flex-end; margin-bottom: 0">
                            <input type="submit" class="btn btn-primary" name="action" value="Confirm Payment">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
