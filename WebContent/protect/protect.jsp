<%@page import="model.*"%>
<%
	String tokenID = (String) session.getAttribute("tokenID");
	Employee emp = (Employee) session.getAttribute("user");
	OrderWindow orderWindow = (OrderWindow) session.getAttribute("orderWindow");

	if (emp == null || tokenID == null) {
		response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
	}
%>

