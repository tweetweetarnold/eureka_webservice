<%@page import="model.*"%>
<%
	Employee emp = (Employee) session.getAttribute("user");
	String tokenID = (String) session.getAttribute("tokenID");
	OrderWindow orderWindow = (OrderWindow) session.getAttribute("orderWindow");

	if (emp == null || tokenID == null || orderWindow == null) {
		response.sendRedirect("/eureka/ProcessLogoutServlet");
	}
%>

