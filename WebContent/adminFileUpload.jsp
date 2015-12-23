
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DABAO</title>
</head>
<body>
	<h3>Select a file to upload</h3>
	
	<form action="FileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="file" style="width:228px;" required/>
		<br>
		<br>
		<input type="submit" value="upload"/>
	
	</form>
</body>
</html>