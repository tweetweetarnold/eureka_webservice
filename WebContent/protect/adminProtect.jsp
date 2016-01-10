
<%@page import="model.*"%>
<%
	String tokenID = (String) session.getAttribute("tokenID");
	Admin admin = (Admin) session.getAttribute("admin");

	if (admin == null || tokenID == null) {
		response.sendRedirect("/eureka_webservice/ProcessAdminLogoutServlet");
	}
%>
