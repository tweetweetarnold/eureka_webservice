<%@page import="model.*"%>
<%
	String tokenID = (String) session.getAttribute("tokenID");
	Employee emp = (Employee) session.getAttribute("user");
	OrderPeriod orderPeriod = (OrderPeriod) session.getAttribute("orderPeriod");

	if (emp == null || tokenID == null) {
		response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
	}
%>

