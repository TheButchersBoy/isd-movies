<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<%
    User userDetails = (User) session.getAttribute("user");
    String firstName = userDetails.getFirstName();
    String lastName = userDetails.getLastName();
    String email = userDetails.getEmail();
    String mobile = userDetails.getMobile();
    
    String firstNameError = (String) session.getAttribute("firstNameError");
    String lastNameError = (String) session.getAttribute("lastNameError");
    String emailError = (String) session.getAttribute("emailError");
    String mobileError = (String) session.getAttribute("mobileError");
    
    String showUpdateBanner = (String) session.getAttribute("showUpdateBanner");
%>
<%@include file="navbar.jsp" %>
<script>
function confirmDelete() {
    var answer=confirm("Are you sure you want to detele your account?");
    if (answer === true) {
        return true;
    }
    return false;
}
</script>
<html>
    <body id="register">            
        <div class="container">
            <h1>User Details</h1>
            <%if (showUpdateBanner != null) {%>
                <div class="alert alert-success" role="alert">
                    Successfully updated
                </div>
            <%} else {%>
                <br />
            <%}%>
            <form action="UserServlet" method="post">        
                <div class="form-group">              
                    <!--TODO: Refactor table styling-->
                    <table class="table" style="width:50%">
                        <tr>
                            <td><label for="firstName">First Name:</td>                
                            <td><input type="text" class="form-control" name="firstName" maxlength="49" value="<%=firstName%>"/></td>
                        </tr>
                        <%if (firstNameError != null) {%>
                            <tr>
                                <td></td>
                                <td><p class="error-text"><%=firstNameError%></p><td>
                            </tr>
                        <%}%>
                        <tr>
                            <td><label for="lastName">Last Name:</td>                
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
                            <td>
                                <input type="submit" class="btn btn-primary" name="action" value="update"/>
                            </td>
                        </tr>
                    </table>
                    
                </div>
            </form>
            <a href="UserServlet?action=delete" class="btn btn-danger" onclick="return confirmDelete()" >Delete account</a>
        </div>
    </body>
    <%@include file="footer.jsp" %>
    <%
        // Reset banner
        session.setAttribute("showUpdateBanner", null);
    %>
</html>