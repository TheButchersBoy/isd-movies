<%-- 
    Document   : Category
    Created on : 2019-5-20, 18:07:56
    Author     : alvin
--%>

<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    
     <jsp:include page="navbar.jsp">
       
      <jsp:param name="active" value="allmovies" />
      
    </jsp:include>
    <body >
        
        

        
        <%
            List<Movie> movieList = (List<Movie>)request.getAttribute("allMovies");
            
                 String result = (String)request.getAttribute("result");
        
//out.println(movieList.size());
        
            
        %>

        
          
            
           
            
            
            
              <%
         if(movieList!=null){
            
            request.setAttribute("result", "No Movies Found!");
            
        
        %>
        
         <div class="container">
            <h1>Movies</h1>       
            <br/>
            <div class="row"> 
                
                 <%
            for (Movie movie: movieList){
                // get image from byter[];
                request.setAttribute("movieimg", movie.getMovieImg());
  //div class="row"              
//width:25%,class="col-sm-3"
//class="img img-responsive"
                %>
                
               <div class="col-sm-3">
                    <a href="#" class="thumbnail">
                        <div class="frontpage_square">
                           
                            <img src="data:image/jpg;base64,<%=movie.getBase64Image()%>" alt="img/grey-square.png" 
                                 width="100%" height="50%"/>
                            
                        </div>
                    </a>
                   <br>
                   Title: <%=movie.getTitle()%>, Price: $<%=movie.getPrice()%>, Genre: <%=movie.getGenre() %>, Stock: <%=movie.getStock()%>
                   <br>
                   <form action="OrderServlet" method="post" style="display: flex; margin: 0; margin-top: 1rem">
                        <input type="hidden" name="movieId" value="<%= movie.getId() %>">
                        <input type="hidden" name="movieTitle" value="<%= movie.getTitle() %>">
                        <input type="hidden" name="moviePrice" value="<%= movie.getPrice() %>">
                        <input type="hidden" name="movieStock" value="<%= movie.getStock() %>">
                        <input type="submit" class="btn btn-primary" name="action" value="Add Movie">
                    </form>
                    <% String movieAddedErrorId = (String)session.getAttribute("movieAddedErrorId"); %>
                    <c:if test="<%= movieAddedErrorId != null && movieAddedErrorId.equals(movie.getId()) %>">
                        <p style="color: red; flex-grow: 1">Movie already added. Cannot add duplicate movies.</p>
                    </c:if>
                </div> 
                
                
                
                <%
                    }
                %>
                
                
                
                
            </div>
         </div>
        
        
                            
                           
        
        <%
        }
        %>
            
        
        
        
        
    
    </body>
</html>
