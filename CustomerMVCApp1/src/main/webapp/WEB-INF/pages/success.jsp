<html>
<body bgcolor="lightblue">
	<center>
	<%@ page isELIgnored ="false" %>
		<h1>Spring MVC JDBC Customer Application</h1>
		<hr>
		Message :
		<%=request.getAttribute("customers")%>
		<br /> customer : ${customers}
		<br /> customer : ${customer}
		<br /> Message : ${msg}
	</center>
</body>
</html>
