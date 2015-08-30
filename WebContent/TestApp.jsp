<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TestApp</title>
</head>
<body>
<form action="loginServlet">
	<input type="hidden" name="function" value="newLogin">
	Username:</br>
	<input type="text" name="username"></br>
	Password:</br>
	<input type="text" name="password">
	</br>
	<input type="submit">
</form>
</body>
</html>