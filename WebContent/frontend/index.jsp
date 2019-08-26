<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div class="center">			
		<div>
		<h2>New books:</h2>
		<c:forEach items="${listNewBooks}" var="book">
			<div class="book">
				<div>
					<a href="view_book?id=${book.bookId}">
						<img class="book_small" src="data:image/jpg;base64,${book.base64Image}" />
					</a>
				</div>
				<div>
					<a href="view_book?id=${book.bookId}">
						<b>${book.title}</b>
					</a>
				</div>
				<div>Rating</div>
				<div>
					<i>${book.author}</i>
				</div>
				<div>
					<b>$${book.price}</b>
				</div>
				</div>
			
			</c:forEach>
		</div>
		<div class="next_row">	
			<h2>Best-selling Books:</h2>
		</div>	
		<div class="next_row">		
			<h2>Most favored Books:</h2>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp"/>

</body>
</html>