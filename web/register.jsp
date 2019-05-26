<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<%
    String firstName = ""; //(String) session.getAttribute("firstName");
    String lastName = ""; //(String) session.getAttribute("lastName");
    String email = ""; //(String) session.getAttribute("email");
    String mobile = ""; //(String) session.getAttribute("mobileError");
    
    String firstNameError = (String) session.getAttribute("firstNameError");
    String lastNameError = (String) session.getAttribute("lastNameError");
    String emailError = (String) session.getAttribute("emailError");
    String passwordError = (String) session.getAttribute("passwordError");
    String mobileError = (String) session.getAttribute("mobileError");
%>
<%@include file="navbar.jsp" %>
<html>
    <body id="register">            
        <div class="container">
            <h1>Register</h1>
            <br />
            <form action="UserServlet" method="post">        
                <div class="form-group">              
                    <!--TODO: Refactor table styling-->
                    <table class="table" style="width:50%">
                        <tr>
                            <td><label for="firstName">First Name:</td>                
                            <td><input type="text" class="form-control" name="firstName" value="<%=firstName%>"/></td>
                        </tr>
                        <%if (firstNameError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="red"><%=firstNameError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="lastName">Last Name:</td>                
                            <td><input type="text" class="form-control" name="lastName" value="<%=lastName%>"/></td>
                        </tr>
                        <%if (lastNameError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="red"><%=lastNameError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="email">Email</td>                
                            <td><input type="text" class="form-control" name="email" value="<%=email%>"/></td>
                        </tr>
                        <%if (emailError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="red"><%=emailError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="password">Password</td>                
                            <td><input type="password" class="form-control" name="password"/></td>
                        </tr>
                        <%if (passwordError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="red"><%=passwordError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="mobile">Mobile</td>                
                            <td><input type="text" class="form-control" name="mobile" value="<%=mobile%>"/></td>                
                        </tr>
                        <%if (mobileError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="red"><%=mobileError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td></td>
                            <td align="right">
                                <input type="submit" class="btn btn-default" name="action" value="register"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form> 
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>