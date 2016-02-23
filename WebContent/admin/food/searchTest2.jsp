<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="services.AutoComplete"%>
<%
 	AutoComplete autoComplete = new AutoComplete();
 
    String query = request.getParameter("q");
     
    List<String> countries = autoComplete.getData(query);
 
    Iterator<String> iterator = countries.iterator();
    while(iterator.hasNext()) {
        String country = (String)iterator.next();
        out.println(country);
    }
%>