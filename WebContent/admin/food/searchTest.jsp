<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/eureka_webservice/resources/css/dabao/jquery.autocomplete.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="/eureka_webservice/resources/js/dabao/jquery.autocomplete.js"></script>
<style>
input {
	font-size: 120%;
}
</style>
</head>
<body>
	<h3>Food</h3>
	<form action= "/eureka_webservice//LoadAdminSearchFood" method="post">
	<input type="text" id="food" name="food" />
	<input type="Submit">

	<script>
		$("#food").autocomplete("searchTest2.jsp");
	</script>


</body>
</html>