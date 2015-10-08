<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Modifiers</h1>
	<table>
		<thead>
			<tr>
				<th>S/N</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Option</th>
			</tr>
		</thead>
		<tbody>

			<%
				// 				Food food = (Food) session.getAttribute("selectedFood");
					Modifier m1 = new Modifier("m1", "abc", 2.0, null, new Date());
					Modifier m2 = new Modifier("m2", "abc", 2.0, null, new Date());
					Modifier m3 = new Modifier("m3", "abc", 2.0, null, new Date());
					List<Modifier> modifierList = new ArrayList<Modifier>();
					modifierList.add(m1);
					modifierList.add(m2);
					modifierList.add(m3);
					
						if(modifierList != null){		
							for(int i = 0; i < modifierList.size(); i++){
								Modifier m = modifierList.get(i);
			%>
			<tr>
				<td><%=i+1%></td>
				<td><%=m.getName()%></td>
				<td><%=m.getDescription()%></td>
				<td><%=m.getPrice()%></td>
				<td>
					<button type="submit">Add</button>
				</td>

			</tr>
			<%
				}
						}
			%>
		</tbody>
	</table>


</body>
</html>