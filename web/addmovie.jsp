
<%-- 
    Document   : Add movie
    Created on : 2019-5-20, 18:07:56
    Author     : alvin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.Movie"%>
<%@page import="uts.isd.model.dao.MovieDAO"%>
<%@page import="uts.isd.model.dao.DBConnector"%>
<%@page import="java.sql.Connection"%>
<%@page import="uts.isd.controller.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<style> 
            table { 
                border-collapse: collapse; 
            } 
            th { 
                background-color:green; 
                Color:white; 
            } 
            th, td { 
                width:250px; 
                text-align:center; 
                border:1px solid black; 
                padding:5px 
              
            } 
            .geeks { 
                border-right:hidden; 
            } 
            .gfg { 
                border-collapse:separate; 
                border-spacing:0 15px; 
            } 
            h1 { 
                color:green; 
            } 
        </style> 
   <jsp:include page="navbar.jsp">
       
      <jsp:param name="active" value="addmovie" />
      
    </jsp:include>
    
    <h1 align = "center">Add New Movie</h1>
    <div align = "center" style="border:1px solid orange;">
            <form action="addmovie" method="post"  enctype="multipart/form-data" >
                
                
                <table >
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
                    
                    <tr>
                        <td>
                             <label for="moviePriceInput">Price:</label>
                        </td>
                        <td>
                                <input type="text" class="form-control" name="moviePriceInput" id="moviePriceInput" placeholder="$0.00" required>
              
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td>
                              <label for="movieDescriptionInput">Description:</label>
                        </td>
                        <td>
                             <textarea rows=4 class="form-control" name="movieDescriptionInput" 
                            id="movieDescriptionInput" placeholder="More Information..." required></textarea>
                   
                        </td>
                    </tr>
                    
                    <br>
                    <tr>
                        <td>
                              <label for="movieCopiesInput">Movie Copies: </label>
                        </td>
                        <td>
                                <input type="text" class="form-control" name="moviestockInput" id="movieCopiesInput" placeholder="1" required>
              
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td>
                             <label for="movieYearInput">Release Year:</label>
                        </td>
                        <td>
                                <input type="text" class="form-control" name="movieYearInput" id="movieYearInput" required>
             
                        </td>
                    </tr>
                   
                     <!---->
                    <tr>
                        <td>
                              <label for="movieImgInput">Movie Picture:</label>
                        </td>
                       
                        <td>
                              <input type="file" class="form-control" name="movieImgInput" id="movieImgInput" required>
<br><br
                        </td>
                        
                    </tr>
                    
                    
                    
                    
                </table>
                
                
              <button type="submit" class="btn btn-primary">Add Movie</button>
              
            </form>
        <br>
    </div>
  </body>
</html>
