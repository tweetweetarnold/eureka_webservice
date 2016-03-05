<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body onunload="return myFunction()">

<p>Close this window, press F5 or click on the link below to invoke the onbeforeunload event.</p>

<a href="http://www.w3schools.com">Click here to go to w3schools.com</a>
      
<script>
function myFunction() {
    return "Write something clever here...";
}
</script>

</body>
</html>
