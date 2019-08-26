<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title} - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div class="center">
		<table class="book">	
			<tr >
				<td colspan="3" align="left">
					<p id="book-title">${book.title}</p>			
					<p id="book-author">by ${book.author}</p>		
					
				</td>		
			</tr>
			<tr>
				<td rowspan="2">
					<img class="book_larger" src="data:image/jpg;base64,${book.base64Image}"/>
				</td>
				<td valign="top" align="left">
					Rating *****
				</td>
				
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price}</h2>
					<br/><br/>
					<button type="submit">Add to Cart</button>					
				</td>				
			</tr>
			<tr>
				<td id="description">
					${book.description}
				</td>			
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td><h2>Customer Reviews</h2></td>
				<td colspan="2" align="center">
					<button>Write a Customer Review</button>
				</td>
			</tr>
			
		</table>				
		
		
	</div>
	<jsp:directive.include file="footer.jsp"/>

</body>
</html>