<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<%
    // Set form entries from session
    String firstName = (String) session.getAttribute("firstNameFormVal");
    String lastName = (String) session.getAttribute("lastNameFormVal");
    String email = (String) session.getAttribute("emailFormVal");
    String mobile = (String) session.getAttribute("mobileFormVal");
    if (firstName == null){
        firstName = "";
    }
    if (lastName == null){
        lastName = "";
    }
    if (email == null){
        email = "";
    }
    if (mobile == null){
        mobile = "";
    }
    // Set from errors from session
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
                            <td><label for="firstName">First name</td>                
                            <td><input type="text" class="form-control" name="firstName" maxlength="49" value="<%=firstName%>"/></td>
                        </tr>
                        <%if (firstNameError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=firstNameError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="lastName">Last name</td>                
                            <td><input type="text" class="form-control" name="lastName" maxlength="49" value="<%=lastName%>"/></td>
                        </tr>
                        <%if (lastNameError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=lastNameError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="email">Email</td>                
                            <td><input type="text" class="form-control" name="email" maxlength="49" value="<%=email%>"/></td>
                        </tr>
                        <%if (emailError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=emailError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="password">Password</td>                
                            <td><input type="password" class="form-control" maxlength="49" name="password"/></td>
                        </tr>
                        <%if (passwordError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=passwordError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="mobile">Mobile</td>                
                            <td><input type="text" class="form-control" name="mobile" maxlength="15" value="<%=mobile%>"/></td>                
                        </tr>
                        <%if (mobileError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=mobileError%></p><td>
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
    <%
        // Reset form entries and errors
        session.setAttribute("firstNameFormVal", null);
        session.setAttribute("lastNameFormVal", null);
        session.setAttribute("emailFormVal", null);
        session.setAttribute("mobileFormVal", null);
        
        session.setAttribute("firstNameError", null);
        session.setAttribute("lastNameError", null);
        session.setAttribute("emailError", null);
        session.setAttribute("passwordError", null);
        session.setAttribute("mobileError", null);
    %>
</html>