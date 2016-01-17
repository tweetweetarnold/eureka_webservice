<%@page import="model.*"%>
<%
	Employee emp = (Employee) session.getAttribute("user");
	String tokenID = (String) session.getAttribute("tokenID");
	OrderWindow orderWindow = (OrderWindow) session.getAttribute("orderWindow");

	if (emp == null || tokenID == null) {
		response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
	}
%>

