<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new User</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<c:if test="${user !=null}">
		<div align="center">
			<h2>Edit User</h2>
		</div>

	</c:if>
	<c:if test="${user ==null}">
		<div align="center">
			<h2>Create new User</h2>
		</div>

	</c:if>

	<c:if test="${message !=null}">
		<div align="center">
			<h3>${message}</h3>
		</div>
	</c:if>

	<div align="center">
		<c:if test="${user !=null}">
			<form action="update_user" method="post"
				onsubmit="return validateFormInput()">
				<input type="hidden" name="userId" value="${user.userId}">
		</c:if>

		<c:if test="${user ==null}">
			<form action="create_user" method="post"
				onsubmit="return validateFormInput()">
		</c:if>

		<form action="create_user" method="post"
			onsubmit="return validateFormInput()">
			<table>
				<tr>
					<td align="right">Email</td>
					<td align="left"><input type="text" id="email" name="email"
						size="20" value="${user.email}" /></td>

				</tr>
				<tr>
					<td align="right">Full Name</td>
					<td align="left"><input type="text" id="fullname"
						name="fullname" size="20" value="${user.fullName}" /></td>

				</tr>
				<tr>
					<td align="right">Password</td>
					<td align="left"><input type="password" id="password"
						name="password" size="20" value="${user.password}" /></td>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"> <input type="button" value="Cancel"
						onclick="javascript:history.go(-1);"></td>
			</table>
		</form>

	</div>


	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");
		if (fieldEmail.value.length == 0) {
			alert("Email is requiered!");
			field.focus();
			return false;
		}

		if (fieldFullName.value.length == 0) {
			alert("Full Name is requiered!");
			field.focus();
			return false;
		}
		if (fieldPassword.value.length == 0) {
			alert("Password is requiered!");
			field.focus();
			return false;
		}
		return true;

	}
</script>

</html>