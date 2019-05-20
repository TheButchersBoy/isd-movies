<%@page import="uts.isd.Validator"%>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="omsApp" class="uts.isd.MoviesApplication" scope="application"></jsp:useBean>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String dob = request.getParameter("dob");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <div class="container">
            <%
                if (name != null && email != null && password != null && dob != null) {
                    Validator validator = new Validator();
                    boolean inputsValid = true;

                    if (!validator.validateName(name)) {
                        session.setAttribute("nameError", "Name must be alphabetical");
                        inputsValid = false;
                    } else {
                        session.setAttribute("nameError", null);
                    }
                    
                    if (!validator.validateEmail(email)) {
                        session.setAttribute("emailError", "Must be a valied email. Eg. example@example.com");
                        inputsValid = false;
                    } else if (omsApp.emailAlreadyExists(email)){
                        session.setAttribute("emailError", "Email already registered");
                        inputsValid = false;
                    } else {
                        session.setAttribute("emailError", null);
                    }
                    
                    if (!validator.validatePassword(password)) {
                        session.setAttribute("passwordError", "Password must be alphanumeric and over 8 characters");
                        inputsValid = false;
                    } else {
                        session.setAttribute("passwordError", null);
                    }
                    
                    if (!validator.validateDob(dob)) { // TODO: Refactor this funcationality
                        session.setAttribute("dobError", "DOB must be like '1999-01-01'");
                        inputsValid = false;
                    } else {
                        session.setAttribute("dobError", null);
                    }
                    
                    if (inputsValid) {
                        User user = new User(name, email, password, dob);
                        omsApp.registerUser(user);
                        session.setAttribute("user", user);
                        response.sendRedirect("index.jsp");
                    } else {
                        response.sendRedirect("register.jsp");
                    }
                }
            %>
        </div>
    </body>
</html>
