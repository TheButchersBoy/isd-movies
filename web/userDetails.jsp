<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="styles/styles.css"/>
<%
    String firstNameError = (String) session.getAttribute("firstNameError");
    String lastNameError = (String) session.getAttribute("lastNameError");
    String emailError = (String) session.getAttribute("emailError");
    String mobileError = (String) session.getAttribute("mobileError");
    
    String showUpdateBanner = (String) session.getAttribute("showUpdateBanner");

    User userDetails = (User) session.getAttribute("user");
    String firstName = "";
    String lastName = "";
    String email = "";
    String mobile = "";
    if (userDetails != null) {
        firstName = userDetails.getFirstName();
        lastName = userDetails.getLastName();
        email = userDetails.getEmail();
        mobile = userDetails.getMobile();
    }
%>
<%@include file="navbar.jsp" %>
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
                            <td>
                                <input type="submit" class="btn btn-primary" name="action" value="update"/>
                            </td>
                        </tr>
                    </table>
                    
                </div>
            </form>
            <!--TODO: Add onClick() functionality with "Are you sure?" alert-->
            <input class="btn btn-danger" name="submitBtn" value="Delete account"/>
        </div>
    </body>
    <%@include file="footer.jsp" %>
    <%
        // Reset banner
        session.setAttribute("showUpdateBanner", null);
    %>
</html>