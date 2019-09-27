<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Orders - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file= "header.jsp"/>
	<div align="center">
		<h2 class="pageheading">Book Orders Management</h2>
		<h3><a href="order_form.jsp">Create new Order</a></h3>
		
	</div>
	<c:if test="${message !=null}">
	<div align="center">
		<h4 class="message" >${message}</h4>
	</div>
	</c:if>
	
	<c:if test="${messageUpdate !=null}">
	<div align="center">
		<h4 class="message">${messageUpdate}</h4>
		
	</div>
		<c:remove var="messageUpdate"/>
	</c:if>
	
	
	<div align="center">
		<table border ="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>			
				
			</tr>
			<c:forEach var="order" items="${listOrder}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.bookCopies}</td>
					<fmt:setLocale value="en-EN"/>
					<td><fmt:formatNumber type="currency" currencySymbol="$" value="${order.total}"/></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>					
					<td>
						<a href="view_order?id=${order.orderId}">Details</a> &nbsp;
						<a href="edit_review?id=${review.reviewId}">Edit</a> &nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${review.reviewId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		
		</table>
		
	</div>
	
	
			
	<jsp:directive.include file= "footer.jsp"/>
	
	
	<script>
	
	$(document).ready(function(){
	
		$(".deleteLink").each(function(){				
			$(this).on("click", function(){
				reviewId =$(this).attr("id");
				if(confirm("Are you shure you want to delete the review with ID "+reviewId+"?")){
					window.location ='delete_review?id='+reviewId;
				}
			})
			
		})
		
	});
		
	</script>
	
	
</body>
</html>