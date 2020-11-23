<html>
 <body bgcolor="PINK" >
   <center>
   <%@ page isELIgnored ="false" %>
	<h1>Spring MVC JDBC Customer Application Error Page</h1>
    	<hr>
			Message : <%= request.getAttribute("msg") %> <br/>
			Message : ${msg}
   </center>
 </body>
</html>
