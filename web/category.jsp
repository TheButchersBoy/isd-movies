<%-- 
    Document   : Category
    Created on : 2019-5-20, 18:07:56
    Author     : alvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="">
     <div class="filter genre">
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
             <li><input name="genre[]" type="checkbox" id="genre_sitcom" value="22"><label for="genre_sitcom">Sitcom</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_costume" value="23"><label for="genre_costume">Costume</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_psychological" value="24"><label for="genre_psychological">Psychological</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_kungfu" value="25"><label for="genre_kungfu">Kungfu</label></li>
             <li><input name="genre[]" type="checkbox" id="genre_mythological" value="26"><label for="genre_mythological">Mythological</label></li>
         </ul>
     </div>
 </div>
 <div class="">
     <div class="filter country">
         <div class="heading"> Country </div>
         <ul class="checkbox c2">
             <li><input name="country[]" type="checkbox" id="country_united-kingdom" value="1"><label for="country_united-kingdom">United Kingdom</label></li>
             <li><input name="country[]" type="checkbox" id="country_united-states" value="2"><label for="country_united-states">United States</label></li>
             <li><input name="country[]" type="checkbox" id="country_international" value="3"><label for="country_international">International</label></li>
             <li><input name="country[]" type="checkbox" id="country_korea" value="4"><label for="country_korea">Korea</label></li>
             <li><input name="country[]" type="checkbox" id="country_france" value="5"><label for="country_france">France</label></li>
             <li><input name="country[]" type="checkbox" id="country_euro" value="6"><label for="country_euro">Euro</label></li>
             <li><input name="country[]" type="checkbox" id="country_china" value="7"><label for="country_china">China</label></li>
             <li><input name="country[]" type="checkbox" id="country_hongkong" value="8"><label for="country_hongkong">HongKong</label></li>
             <li><input name="country[]" type="checkbox" id="country_india" value="9"><label for="country_india">India</label></li>
             <li><input name="country[]" type="checkbox" id="country_japan" value="10"><label for="country_japan">Japan</label></li>
             <li><input name="country[]" type="checkbox" id="country_asia" value="11"><label for="country_asia">Asia</label></li>
             <li><input name="country[]" type="checkbox" id="country_thailand" value="12"><label for="country_thailand">Thailand</label></li>
             <li><input name="country[]" type="checkbox" id="country_taiwan" value="13"><label for="country_taiwan">Taiwan</label></li>
         </ul>
     </div>
 </div>
 <div class="">
     <div class="filter type">
         <div class="heading"> Type </div>
         <ul class="checkbox c1">
             <li><input name="type[]" id="type_movie" type="checkbox" value="1"><label for="type_movie">Movies</label></li>
             <li><input name="type[]" id="type_series" type="checkbox" value="2"><label for="type_series">Series</label></li>
         </ul>
     </div>
 </div>
 <div class="">
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

    </body>
</html>
