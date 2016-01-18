<%@page import="model.*"%>
<%@page import="org.joda.time.DateTime"%>

<%@page import="org.joda.time.format.DateTimeFormat"%>
<%@page import="org.joda.time.format.DateTimeFormatter"%>;
<%@page import="org.joda.time.DateTimeZone"%>
<%@page import="services.AESAlgorithm"%>
<%@page import="org.joda.time.Minutes"%>
<%
	AESAlgorithm aes = new AESAlgorithm();
	String param = (String) session.getAttribute("token");
	if (param.contains(" ")) {
		param = param.replaceAll(" ", "+");
	}
	String token = aes.decrypt(param);

	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm");
	DateTime startDatetime = formatter.parseDateTime(token);

	DateTime currentTime = new DateTime(DateTimeZone.forID("Asia/Singapore"));
	System.out.println(currentTime);

	long difference = currentTime.getMillis() - startDatetime.getMillis();
	System.out.println(difference);
	if (difference > 300000) {
		session.setAttribute("error", "Session Timeout. Please send the request again!");
		response.sendRedirect("eureka_webservice/reset-password.jsp");
	} else {

		String email = (String) session.getAttribute("email");
		if (email == null) {
			response.sendRedirect("/eureka_webservice/ProcessLogoutServlet");
		}
	}
%>

