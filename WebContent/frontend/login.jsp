<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div align="center">
		
		<h2>Customer login</h2>
	<c:if test="${message !=null}">
		<div align="center">
			<h4 class = "message">${message}</h4>
			<c:remove var="message"/>
		</div>
	</c:if>
		
		
		<form id="loginForm"  action="login" method="post" >
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password" size="20"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
				
			</table>
		
		</form>
		
	</div>
<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">


	$(document).ready(function() {
		$("#loginForm").validate({
			rules:{
				email: {
					required: true,
					email: true
				},
				
				password: "required",				
			},
			 messages:{
				 email: {
					required: "Please enter email",
					email: "Pleasr enter an valid email address"
				 },
				
				 password: "Please enter password"
			 }
			
			
		});		
		
	} );
</script>
</html>