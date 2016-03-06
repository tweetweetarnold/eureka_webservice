<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="services.AutoComplete"%>
<%@page import="model.OrderPeriod"%>
<%
	OrderPeriod orderPeriod = (OrderPeriod) session.getAttribute("orderPeriod");

	AutoComplete autoComplete = new AutoComplete(orderPeriod.getCanteen());

	String query = request.getParameter("q");

	List<String> countries = autoComplete.getData(query);

	Iterator<String> iterator = countries.iterator();
	while (iterator.hasNext()) {
		String country = (String) iterator.next();
		out.println(country);
	}
%>