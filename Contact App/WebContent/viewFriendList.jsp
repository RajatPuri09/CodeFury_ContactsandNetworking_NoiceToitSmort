<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Friends</title>
</head>
<body style="background-color:powderblue;">
<h1 style="background-color:tomato; text-align: center;">My Friends</h1>
<p>${friendList}</p>
<form action="SystemUser" method="get">
<button type="submit" name="userAction" value="backToHome" style="background-color:tomato;">Back to Home</button>
</form>
</body>
</html>
