<%@page import="model.*"%>
<%
	Employee emp = (Employee) session.getAttribute("user");
	String tokenID = (String) session.getAttribute("tokenID");
	OrderPeriod orderPeriod = (OrderPeriod) session.getAttribute("orderPeriod");

	if (emp == null || tokenID == null) {
		response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
	}
	if (orderPeriod == null) {
		if (!response.isCommitted()) {
			response.sendRedirect("/eureka_webservice/pages/payment.jsp");
		}
	}
	if (session.getAttribute("suspended") != null) {
		boolean suspended = Boolean.parseBoolean((String) session.getAttribute("suspended"));
		if (suspended) {
			if (!response.isCommitted()) {
				response.sendRedirect("/eureka_webservice/pages/payment.jsp");

			}
		}
	}
%>

