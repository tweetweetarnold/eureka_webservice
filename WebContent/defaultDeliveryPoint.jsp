<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Set<String> buildingSet = (Set<String>) request.getAttribute("buildingSet");
		System.out.println("JSP ... " + buildingSet.size());
		Iterator iter = buildingSet.iterator();
	%>
	Hey There! Please select the building where you want your food
	delivered to!
	<form action="ProcessSetDefaultDeliveryPointServlet" method="post">
		<select name="deliveryPoint">
		<%
			while (iter.hasNext()) {
				String buildingName = (String)iter.next();
		%>
			<option value=<%=buildingName%>><%=buildingName %></option>
		<%
			}
		%>
		</select> 
		<input type="submit">
	</form>
</body>
</html>