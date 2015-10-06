<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page import="com.google.gson.Gson" %>
<%@ page import="model.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="retrieveUserServlet" method="post">
</br>
User ID
<input type="text" name="userID">
<input type="Submit">
</form>

<%
if(request.getAttribute("UserJson")!=null){	

	String jsonOutput = (String)request.getAttribute("UserJson");
	Gson gson = new Gson();
	Employee tempE = gson.fromJson(jsonOutput, Employee.class);
	if(tempE!=null){
		out.println("YAY");
	}
	

	out.print(jsonOutput);
}
%>
</body>
</html>