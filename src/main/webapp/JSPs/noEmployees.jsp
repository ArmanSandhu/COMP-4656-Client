<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Oops!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin.css" type="text/css" title="no title" charset="utf-8" />
</head>
<body>
	<h2>Oops!</h2>
	<p>There was a problem in the attempt to connect to the Web Service. Check if the Web Service has been deployed and click the button to try again!</p>
	<form action="index.jsp">
		<input type="submit" value="Attempt to Reconnect"/>
	</form>
</body>
</html>