<%@page import="org.json.simple.JSONArray" %>
<%@page import="java.util.*" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="model.Canteen" %>
<%@ page import="com.google.gson.Gson" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TestApp</title>
</head>
<body>
<form action="RetrieveCanteenServlet" method="post">
	
	<input type="submit" value="START">
</form>

<%

if(request.getAttribute("canteenArray")!= null){
	JSONArray canteenArray = (JSONArray) request.getAttribute("canteenArray");
	ArrayList<Canteen> listdata = new ArrayList<Canteen>();     
	
	if (canteenArray != null) { 
	   for (int i=0;i<canteenArray.size();i++){ 
		   	//Add Canteen to the ArrayList!
	    	listdata.add((Canteen)canteenArray.get(i));
		   	
		   	//Test the Canteen Object
		   	Canteen c = (Canteen)canteenArray.get(i);
		   	out.println(i+1 +" "+ c.getAddress());
		   	%>
		   	<br/>
		   	<%
		   	System.out.println(c.getAddress());
	   } 
	} 
	
}

%>

</body>
</html>