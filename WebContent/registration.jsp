<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Please enter your particulars</h1>
	<form method="POST" action="/eureka_webservice/RegistrationServlet">
	
				Name:	<input type="text" name="employeeName"></br>
			Username:	<input type="text" name="username"></br>
			Password:	<input type="password" name="password"></br>
	Re-enter Password:	<input type="password" name="confirmPwd"></br>
		Contact Number:	<input type="text" name="contactNumber"></br>
		Bank Account:	<input type="text" name="bankAcc"></br>
		Company Name:	<input type="text" name="company"></br>
	<input type="submit" value="Register">
	</form>
	
	<% String error = (String) request.getAttribute("error");
	if (error != null) {
		out.println(error);
	}
	 request.removeAttribute("error");
	%>
	
	
</body>
</html>