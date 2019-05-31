<%-- 
    Document   : search
    Created on : May 28, 2019, 9:15:34 AM
    Author     : alvin
--%>

<%@page import="java.util.List"%>
<%@page import="uts.isd.model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search movies from category</title>
    </head>
    
    <%
        List<Movie> movieSearchResult = (List<Movie>)request.getAttribute("searchResult");
        String result = (String)request.getAttribute("result");
        
        
       
    
    %>
    
    <body>
        <h1>Search Movies</h1>
        <div name="search_part">
            
        
         <div align = "center" style="border:1px solid orange">
         <form action="search" method="post" >
                
                
                <table cellpadding="10">
                    <tr>
                        <td>
                            <label for="movieTitleInput">Title:</label>
                        </td>
                        <td>
                         <input type="text" class="form-control" name="movieTitleInput" id="movieTitleInput" placeholder="Movie Name" required>
                  
                        </td>
                    </tr>
                    <tr>
                        <td>
                             <label for="movieGenreInput">Genre:</label>
                        </td>
                        <td>
                               <input type="text" class="form-control" name="movieGenreInput" id="movieGenreInput" placeholder="Genre" required>
                
                        </td>
                    </tr>
                </table>
             
              <button type="submit" class="btn btn-primary">Search Movie</button>
              <br>
              <label hidden="true" id="resultLabel"> <%request.getAttribute("result");%></label>
    
         </form>
                
     <!--           
              <button class="btn btn-primary" type="submit" name = "backhome" onclick="location.href('category.jsp')">Back to Category </button>
   -->
         </div>
        <br><br><br>
        
        <%
         if(movieSearchResult!=null){
            
            request.setAttribute("result", "No Movie Found!");
               // out.println("MovieSearchResult:" + movieSearchResult.size());
               //response.sendRedirect("search.jsp");

               
               
               
        
        %>
        <div align = "center" >
            
        <%--  searchResult is created in MovieServet: searching from title or genre --%>
        <table style="border: 1px solid greenyellow" cellpadding="10">
           
                        <tr>
                            <th scope ="col">ID</th>
                            <th scope ="col">Title</th>
                            <th scope ="col">Release Year</th>
                            <th scope ="col">Genre</th>
                            <th scope ="col">Price</th>
                            <th scope ="col">Description</th>
                            <th scope ="col">Action</th>
                        </tr>
                    
        <%
            for (Movie movie:movieSearchResult){
               
                %>
                <tr>
                                <td><%= movie.getMovieID()%></td>
                                <td><%= movie.getMovieTitle()%> </td>
                               <td> <%= movie.getYear()%></td>
                                <td><%= movie.getGenre()%></td>
                                <td><%= movie.getPrice()%></td>
                                <td><%= movie.getDescription()%></td>

<!-- for use to add to cart:  -->
                                <td><a href="" >Add to Cart</a></td>

                                
                            </tr>
                        <%
            }
        
        
                            
                            %>
                            
             </table>                          
                                       
       </div>    
        
        <%
        }
        %>
        
        
        
        
       </div>
    </body>
    
    <%
        
    %>
</html>
