<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% //@ include file="/protect.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="GetEmployeesPaymentOwedServlet" method="post">
		Enter the payment status(i.e "Ok")</br>
		<input type="hidden" name="paymentStatus" value="Ok"/>
		<input type="submit" value="Query"/>
	</form>
</body>
</html>