<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		JSONObject employeeDetails = (JSONObject) request.getAttribute("employeeDetails");
		Iterator iter = employeeDetails.keySet().iterator();
		while(iter.hasNext()){
			String key = (String)iter.next();
			
			out.println(employeeDetails.get(key));
		}
	%>
</body>
</html>