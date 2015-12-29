<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Canteen"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<tr>
			<th>index</th>
			<th>Canteen</th>
			<th>Address</th>
		</tr>
		<%
			ArrayList<Canteen> canteenList = (ArrayList<Canteen>) request.getAttribute("canteenList");

			for (int i = 0; i<canteenList.size(); i++) {
				int index = i+1;
				Canteen c = canteenList.get(i);
				String name = c.getName();
				String address = c.getAddress();
		%>
		<tr>
			<td><%=index %></td>
			<td><%=name %></td>
			<td><%=address %></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>