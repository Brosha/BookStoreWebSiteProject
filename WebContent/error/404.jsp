<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Not found Error</title>
</head>
<body>
<div align="center">
	<div>
	
		<img src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
	</div>
	<div>
		<h2>Sorry, the requested page could not be found.</h2>
		<a href="javascript:history.go(-1)">Go back</a>
	</div>
</div>		
</body>
</html>