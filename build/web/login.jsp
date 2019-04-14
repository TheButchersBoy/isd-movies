<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<html>
    <body id="login">            
        <div class="container">
            <h1>Login</h1>
            <br />
            <form action="login_action.jsp" method="POST">      
                <div class="form-group">        
                    <table class="table" style="width:35%;">
                        <tr>
                            <td><label for="email">Email: </label></td>
                            <td><input type="text" class="form-control" id="email" name="email" /></td>                   
                        </tr>
                        <tr>
                            <td><label for="password">Password: </label></td>                
                            <td><input type="password" class="form-control" id="password" name="password" /></td>
                        </tr>
                        <tr><td></td><td align="right"><input type="submit" class="btn btn-default" name="submitBtn" value="Login" /></td></tr>
                    </table>
                </div>
            </form>   
        </div>
    </body>
    <%@include file="footer.jsp" %>
</html>