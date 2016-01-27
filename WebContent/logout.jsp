<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dao.*,model.*,services.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Employee e = (Employee) session.getAttribute("user");
	out.println(e);
		if (e != null) {
			session.removeAttribute("user");
			out.println("Logging out");
			response.setHeader("Refresh", "3; URL=/eureka_webservice/login.jsp");
			//response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>