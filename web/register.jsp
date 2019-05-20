<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<%
    String nameError = "esfesfesesfes";//(String) session.getAttribute("nameError");
    String emailError = (String) session.getAttribute("emailError");
    String passwordError = (String) session.getAttribute("passwordError");
    String dobError = (String) session.getAttribute("dobError");
%>
<%@include file="navbar.jsp" %>
<html>
    <body id="register">            
        <div class="container">
            <h1>Register</h1>
            <br />
            <form action="register_action.jsp" method="POST">        
                <div class="form-group">                    
                    <table class="table" style="width:35%">
                        <tr>
                            <td><label for="email">Name:</td>                
                            <td><input type="text" class="form-control" name="name"/></td>
                        </tr>
                        <%if (nameError != null) {%>
                            <tr>
                                <td></td>
                                <td><label class="red"><%=nameError%></label><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="email">Email</td>                
                            <td><input type="text" class="form-control" name="email"/></td>
                        </tr>
                        <%if (emailError != null) {%>
                            <tr>
                                <td></td>
                                <td><label class="red"><%=emailError%></label><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="email">Password</td>                
                            <td><input type="password" class="form-control" name="password"/></td>
                        </tr>
                        <%if (passwordError != null) {%>
                            <tr>
                                <td></td>
                                <td><label class="red"><%=passwordError%></label><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="email">DOB</td>                
                            <td><input type="text" class="form-control" name="dob"/></td>                
                        </tr>
                        <%if (dobError != null) {%>
                            <tr>
                                <td></td>
                                <td><label class="red"><%=dobError%></label><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td></td>
                            <td align="right">
                                <input type="submit" class="btn btn-default" name="submitBtn" value="Register"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form> 
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>