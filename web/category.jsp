<%-- 
    Document   : Category
    Created on : 2019-5-20, 18:07:56
    Author     : alvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
    </head>
    <body>
        <div class="col-lg-8">
     <div class="filter genre" style="float: left; line-height: 25px;">
         <div class="heading"> Category </div>
         <ul class="checkbox c3">
             <li><input name="genre[]" type="checkbox" id="genre_crime" value="1"><label for="genre_crime">Crime</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_drama" value="2"><label for="genre_drama">Drama</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_mystery" value="3"><label for="genre_mystery">Mystery</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_action" value="5"><label for="genre_action">Action</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_adventure" value="6"><label for="genre_adventure">Adventure</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_comedy" value="7"><label for="genre_comedy">Comedy</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_family" value="8"><label for="genre_family">Family</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_animation" value="9"><label for="genre_animation">Animation</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_romance" value="10"><label for="genre_romance">Romance</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_horror" value="11"><label for="genre_horror">Horror</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_thriller" value="12"><label for="genre_thriller">Thriller</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_documentary" value="13"><label for="genre_documentary">Documentary</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_sci-fi" value="14"><label for="genre_sci-fi">Sci-Fi</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_fantasy" value="15"><label for="genre_fantasy">Fantasy</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_war" value="16"><label for="genre_war">War</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_biography" value="17"><label for="genre_biography">Biography</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_history" value="18"><label for="genre_history">History</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_musical" value="19"><label for="genre_musical">Musical</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_sport" value="20"><label for="genre_sport">Sport</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_tv-show" value="21"><label for="genre_tv-show">TV Show</label></li>
           
         </ul>
     </div>
 </div>

 <div class="col-lg-3">
     <div class="filter quality">
         <div class="heading"> Release </div>
         <ul class="checkbox c1">
             <li><input name="release[]" type="checkbox" id="release_2019" value="2019"><label for="release_2019">2019</label></li>
             <li><input name="release[]" type="checkbox" id="release_2018" value="2018"><label for="release_2018">2018</label></li>
             <li><input name="release[]" type="checkbox" id="release_2017" value="2017"><label for="release_2017">2017</label></li>
             <li><input name="release[]" type="checkbox" id="release_2016" value="2016"><label for="release_2016">2016</label></li>
             <li><input name="release[]" type="checkbox" id="release_2015" value="2015"><label for="release_2015">2015</label></li>
             <li><input name="release[]" type="checkbox" id="release_2014" value="2014"><label for="release_2014">2014</label></li>
             <li><input name="release[]" type="checkbox" id="release_2013" value="2013"><label for="release_2013">2013</label></li>
         </ul>
     </div>
 </div>
<div class="submit"> <button class="btn btn-primary" type="submit">Search Movies</button> </div>

<input type="submit" class="btn btn-default" name="submitBtn" value="Search Movies"/>
    </body>
</html>