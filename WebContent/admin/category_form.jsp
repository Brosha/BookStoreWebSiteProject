<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create new Category</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<c:if test="${category !=null}">
		<div align="center">
			<h2 class="pageheading">Edit Category</h2>
		</div>

	</c:if>
	<c:if test="${category ==null}">
		<div align="center">
			<h2 class="pageheading">Create new Category</h2>
		</div>

	</c:if>

	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<c:if test="${category !=null}">
			<form action="update_category" method="post" id="categoryForm">
				<input type="hidden" name="categoryId" value="${category.categoryId}"></form>
		</c:if>

		<c:if test="${category ==null}">
			<form action="create_category" method="post" id="categoryForm">
		</c:if>
		
			<table class="form">
				<tr>
					<td align="right">Name</td>
					<td align="left"><input type="text" id="name" name="name"
						size="20" value="${category.name}" /></td>

				</tr>
				
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					
					<td colspan="2" align="center">
					<button type="submit">Save</button> 
					<button type="reset" onclick="javascript:history.go(-1);">Cancel</button> 
				</td>	
						
						
						</tr>
			</table>
		</form>

	</div>


	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">


	$(document).ready(function() {
		$("#categoryForm").validate({
			rules:{
				name: "required",
					
				},				
			
			 messages:{
				  
					name: "Please enter category name",			 
				 
			 }			
			
		});				
	} );
	
	

	
</script>

</html>