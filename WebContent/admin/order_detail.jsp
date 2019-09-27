<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Order Details - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>	
</head>
<body>
	<jsp:directive.include file= "header.jsp"/>
	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId}</h2>
				
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
		<h2>Order Overview:</h2>
	
		<table cellpadding="5">
			<tr>
				<td><b>Ordered By</b></td>
				<td>${order.customer.fullname}</td> 				
			</tr>			
			<tr>
				<td><b>Book Copies</b></td>
				<td>${order.bookCopies}</td>				
			</tr>
			<tr>
				<td><b>Total Amount:</b></td>
				<fmt:setLocale value="en-EN"/>
				<td><fmt:formatNumber type="currency" currencySymbol="$" value="${order.total}"/></td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Payment Method:</b>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Shipping Address:</b>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status:</b>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b>
				<td>${order.orderDate}</td>
			</tr>	
		
		</table>		
	</div>
	
	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>No</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>				
				<th>Subtotal</th>							
				
			</tr>		
		
			<c:forEach var="orderDetail" items="${order.orderDetails}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>
						<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.book.base64Image}" width="48" height="64"/>
						${orderDetail.book.title}
					</td>
					<td>${orderDetail.book.author}</td>
					<td>
						<fmt:setLocale value="en-EN"/>
						<fmt:formatNumber type="currency" currencySymbol="$" value="${orderDetail.book.price}"/>
					</td>						
					<td>${orderDetail.quantity}</td>
					<td>
						<fmt:setLocale value="en-EN"/>
						<fmt:formatNumber type="currency" currencySymbol="$" value="${orderDetail.subtotal}"/>						
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					Total:
				</td>
				<td>
					${order.bookCopies}
				</td>
				<td>
					<fmt:setLocale value="en-EN"/>
					<fmt:formatNumber type="currency" currencySymbol="$" value="${order.total}"/>
				</td>
			</tr>
		</table>
			
	</div>
	&nbsp;
	<div>
		</br>
		<a href="">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete this Order</a>
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