<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Order - Evergreen Bookstore Administration</title>
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.js"></script>	
</head>
<body>
	<jsp:directive.include file= "header.jsp"/>
	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
				
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
	
	<form action="update_order" method="post" id="orderForm">
	<div align="center">		
	
		<table cellpadding="5">
			<tr>
				<td><b>Ordered By:</b></td>
				<td>${order.customer.fullname}</td> 				
			</tr>
			<tr>
				<td><b>Order Date:</b>
				<td>${order.orderDate}</td>
			</tr>
			
			<tr>
				<td><b>Recipient Name:</b></td>
				<td><input type="text" name="recipientName" value="${order.recipientName}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b></td>
				<td><input type="text" name="recipientPhone" value="${order.recipientPhone}" size="45" /></td>				
			</tr>
			<tr>
				<td><b>Ship To:</b></td>
				<td><input type="text" name="shippingAddress" value="${order.shippingAddress}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><b>Order Status:</b></td>
				<td>
					<select name="orderStatus">
						<option value="Processing">Processing</option>
						<option value="Shipping">Shipping</option>
						<option value="Delivered">Delivered</option>
						<option value="Completed">Completed</option>
						<option value="Canceled">Canceled</option>				
					</select>
				</td>
			</tr>			
								
				
					
		</table>		
	</div>
	
	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>				
				<th>Subtotal</th>							
				<th></th>
			</tr>		
		
			<c:forEach var="orderDetail" items="${order.orderDetails}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>						
						${orderDetail.book.title}
					</td>
					<td>${orderDetail.book.author}</td>
					<td>
						<fmt:setLocale value="en-EN"/>
						<input type="hidden" name="price" value="${orderDetail.book.price}"/>
						<fmt:formatNumber type="currency" currencySymbol="$" value="${orderDetail.book.price}"/>
					</td>						
					<td>
						<input type="hidden" name="bookId" value="${orderDetail.book.bookId}"/>
						<input type="text" name="quantity${status.index+1}" value="${orderDetail.quantity}"size="5" />
					</td>
					<td>
						<fmt:setLocale value="en-EN"/>						
						<fmt:formatNumber type="currency" currencySymbol="$" value="${orderDetail.subtotal}"/>						
					</td>
					<td>
						<a href="remove_book_from_order?id=${orderDetail.book.bookId}">Remove</a>
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
				<td></td>
			</tr>
		</table>
			
	</div>
	&nbsp;
	<div>
		<br/>
		<a href="javascript:showAddBookPopup()">Add Books</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick ="javascript:window.location.href='list_orders';"/>
	</div>
	</form>
			
	<jsp:directive.include file= "footer.jsp"/>
	
	
	<script>
		function showAddBookPopup(){
			var width = 600;
			var height = 280;
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2;
			window.open('add_book_form','_blank', 'width='+width+', height='+height+', top='+top+', left='+left);
		}
	
		$(document).ready(function() {
			$("#orderForm").validate({
				rules:{
					recipientName: "required",
					recipientPhone: "required",
					shippingAddress: "required",
					<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index+1}: {
							required: true,
							number:true,
							min: 1
						
						},					
					</c:forEach>	
				},				
				
				 messages:{
					  
					recipientName: "Please enter recipient Name",
					recipientPhone: "Please enter recipient Phone",
					shippingAddress: "Please enter shipping Address",			 
					<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index+1}: {
						required: "Please enter quantity",
						number: "Quantity must be a number",
						min: "Quantity must be greater than 0"
					},
					
				</c:forEach> 
				 }			
				
			});
			
			$("#buttonCancel").click(function() {
				history.go(-1);
				
			});
			
		} );
		
		
	</script>
	
	
</body>
</html>