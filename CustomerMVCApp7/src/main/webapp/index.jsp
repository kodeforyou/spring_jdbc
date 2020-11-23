<html>
<body>
	<u>Add a Customer by entering below details:</u>
	<form action="./addCustomer.do" method="POST">
		<br>Enter name:<input type="text" name="name" /> 
		<br>Enter age:<input type="text" name="age" /> 
		<br>Enter phone number:<input type="text" name="phoneNumber" /> 
		<input type="submit" value="register A Customer" />
	</form>

	<u>Get a Customer details by entering id:</u>
	<form action="./getCustomer.do" method="POST">
		<br>Enter Id:<input type="text" name="id" /> 
		<input type="submit" value="find A Customer" />
	</form>
	
	<u>Remove a Customer details by entering id:</u>
	<form action="./removeCustomer.do" method="POST">
		<br>Enter Id:<input type="text" name="id" /> 
		<input	type="submit" value="remove A Customer" />
	</form>
	
	<u>get all Customer details :</u>
	<form action="./getAllCustomers.do" method="POST">
		<input type="submit" value="get All Customers" />
	</form>
</body>
</html>
