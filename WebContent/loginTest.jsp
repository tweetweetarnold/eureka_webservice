<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">


<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="ChineseTest">
		<center>ID <input type="text" name="username"></center><br>
		
		<center><input type="submit" value="GO"></center>
	</form>
	<% String output = (String) session.getAttribute("o");
	
	%><%=output%>
</body>
</html>