<%
	String email = (String) session.getAttribute("email");
	if (email == null) {
		response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
	}
%>