<%@page import="model.*"%>
<%
	String email = (String)session.getAttribute("email");
	if (email == null) {
		response.sendRedirect("ProcessLogoutServlet");
	}
%>

