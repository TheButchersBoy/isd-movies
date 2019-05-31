<%-- 
    Document   : payment-details
    Created on : 2019-5-26, 14:25:35
    Author     : Frank
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uts.isd.model.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="uts.isd.model.*"%>
<%@include file="navbar.jsp" %>
<c:import url="/Paymentservlet" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Details</title>
    </head>
    <body>
        <%  
            ArrayList<Payment> payments = (ArrayList<Payment>)session.getAttribute("payments"); 
            String paymentId = request.getParameter("paymentId");
            String date = request.getParameter("date");
        %>
        <div style="display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem">
            <h1 style="margin-bottom: 3rem">Payment Details</h1>
            <h3 style="margin-bottom: 2rem">Payment List</h3>
            <div style="width: 100%">
                <table class='table' width="100%" border="2">
                    <tr>    <th>Payment id</th>
                       	<th>Date</th>
                         <th>Amount</th>
                	<th>Method</th>  </tr>
                    <tr>
                    <c:forEach items="<%= payments %>" var="payment">
                        <% Payment payment = (Payment)pageContext.getAttribute("payment"); %>
                        <td><%= payment.getId() %></td>
                       	<td><%= payment.getDate() %></td>
                         <td>$<%= payment.getAmount() %></td>
                	<td><%= payment.getMethod() %></td>  </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>