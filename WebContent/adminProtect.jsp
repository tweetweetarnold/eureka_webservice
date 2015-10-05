<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="model.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	Employee emp = (Employee) session.getAttribute("user");
	String tokenID = (String) session.getAttribute("tokenID");
	Admin admin = (Admin) session.getAttribute("admin");
	if (admin == null && tokenID == null) {
		response.sendRedirect("login.jsp");
	} else if (emp != null && tokenID != null) {
		session.removeAttribute("user");
		session.removeAttribute("tokenID");
		response.sendRedirect("login.jsp");
	}
%>
</head>
<body>

</body>
</html>