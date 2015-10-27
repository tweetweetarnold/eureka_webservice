
<%@page import="model.*"%>
<%
	Employee emp = (Employee) session.getAttribute("user");
	String tokenID = (String) session.getAttribute("tokenID");
	Admin admin = (Admin) session.getAttribute("admin");
	if (admin == null && tokenID == null) {
		response.sendRedirect("login.jsp");
	} else if (emp != null && tokenID != null) {
		session.removeAttribute("user");
		session.removeAttribute("tokenID");
		response.sendRedirect("login.jsp");
	}
%>
