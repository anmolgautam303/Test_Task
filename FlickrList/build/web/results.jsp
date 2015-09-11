<%-- 
    Document   : results

    Author     : anmol
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.utilities.FlickrPic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="cssTwo.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/JavaScript">
function sortDateDescending(a, b) {
  var date1 = $(a).find(".date").text();
  date1 = date1.split('.');
  date1 = new Date(date1[2], date1[1] - 1, date1[0]);
  var date2 = $(b).find(".date").text();
  date2 = date2.split('.');
  date2 = new Date(date2[2], date2[1] - 1, date2[0]);
  return date1 < date2 ? 1 : -1;
}

function sortNameAscending(a, b) {
  var date1 = $(a).find(".name").text();
  var date2 = $(b).find(".name").text();
  return date1 > date2 ? 1 : -1;
}

$(document).ready(function () {
  var desc = false;
  document.getElementById("test").onchange = function () {
    if (document.getElementById("test").value == "alphabetically") {
      $('ol > li').sort(sortNameAscending).appendTo('ol');
    } else if (document.getElementById("test").value == "date") {
      $('ol > li').sort(sortDateDescending).appendTo('ol');
    }
    return false;
  };

  //Sorts descending based on value of date as default.
  $('ol > li').sort(sortDateDescending).appendTo('ol');
});
        </script>    
        <title>Flickr List</title>
    </head>
    <body>
        <h1>Results for tags:<%= session.getAttribute( "search" ) %></h1>
     Order by  
    <select id="test">
	<option selected="selected" value="date">Date</option>
	<option value="alphabetically">Alphabetically</option>
    </select>
     
     
    <form class="stick-to-right" method="post" action="index.html">
        Want to search again, hit the 'Back' button
        <input type="submit" value="Back"/>
    </form>
     
     <br/>
     <br/>
     
   <ol id="list">
   
        <%
         ArrayList <FlickrPic> flickrList = (ArrayList)session.getAttribute( "lista" );
          if (flickrList.size() == 0) {
			out.println(" <h1>No Photos Found! Please try again</h1>");
		  } else
		  for(FlickrPic photo : flickrList) {
              SimpleDateFormat formatter=new SimpleDateFormat("dd.MM.yyyy");
              String dates=formatter.format(photo.getDateUpload());
              out.println("<li><span class=\"date\">"
                      +dates+"</span>--<span class=\"name\">"
                      +photo.getTitle()+"</span>--<a target=\"_blank\" href="
                      +photo.getUrl()+">Link</a><span>--"
                      +photo.getTagsList()
                      +"</span></li>");  
          }
        %>
   </ol>
            
    </body>
</html>
