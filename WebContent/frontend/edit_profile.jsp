<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit My Profile</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/jquery-ui.min.css">	

	<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>

	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">		
				Edit My Profile		
		</h2>	
	</div>

	<c:if test="${message !=null}">
		<div align="center">
			<h4 class = "message">${message}</h4>
		</div>
	</c:if>
	
	<div align="center">		
			<form action="update_profile" method="post" id="customerForm">
					
			<table class="form">				
				<tr>
					<td align="right">E-mail:</td>
					<td align="left"><b>${loggedCustomer.email}</b> (Cannot be changed)</td>

				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullName"
						name="fullName" size="45"  value="${loggedCustomer.fullname}"/></td>

				</tr>				
				
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone"
						name="phone" size="45" value="${loggedCustomer.phone}"/></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" size="45" value="${loggedCustomer.address}"/></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" id="city"
						name="city" size="45" value="${loggedCustomer.city}"/></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" id="zipcode"
						name="zipcode" size="45" value="${loggedCustomer.zipcode}" /></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" size="45"value="${loggedCustomer.country}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<i>(Leave password fields blank if you  don't want to change password)</i> 
					</td>
				</tr>				
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45"/></td>
				</tr>
				
				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirmPassword"
						name="confirmPassword" size="45" /></td>
				</tr>
				
				
				<tr>
				
				
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit">Save</button> 
					<button type="reset" id="buttonCancel">Cancel</button></td>
			</table>
		</form>

	</div>


	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">


	$(document).ready(function() {
						
		
		$("#customerForm").validate({
			rules:{
				email: {
					required: true,
					email: true
				},
				fullName:"required",
				
				confirmPassword: {
					
					equalTo: "#password"
				},
				phone: "required",				
				address: "required",
				zipcode: "required",
				city: "required",
				country: "required"
			},
			 messages:{
				email: {
					
					required: "Please enter a e-mail address",
					email: "Pleasr enter a valid e-mail address"
				},
				fullName:"Please enter Full Name",
				
				confirmPassword:{
					
					equalTo: "Confirm password does not match password"
				},
				address: "Please enter address",
				phone: "Please enter phone",
				zipcode: "Please enter Zip Code",
				country: "Please enter country",
				city: "Please enter city"
				
			 }
			
			
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
			
		});
	} );
	

	
</script>

</html>