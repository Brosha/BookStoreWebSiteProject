<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Results for ${keyword} - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	<div class="center">
	<c:if test="${fn:length(listBooks)==0}">
		<h2>No Results for "${keyword}" found</h2>
	</c:if>
	<c:if test="${fn:length(listBooks)>0}">							
		<div class="book_group">
			<h2 align="center">Results for "${keyword}":</h2>
			<c:forEach items="${listBooks}" var="book">
				<div>
					<div id="search-image" >
						<div align="left">
							<a href="view_book?id=${book.bookId}">
								<img class="book_small" src="data:image/jpg;base64,${book.base64Image}"/>
							</a>
						</div>
			
					</div>
					<div id="search-title">
						<div>
							<h2><a href="view_book?id=${book.bookId}">
								<b>${book.title}</b>
							</a></h2>
						</div>
						<div>Rating</div>
						<div>
							<i>${book.author}</i>
						</div>
						<div>
							<p>${fn:substring(book.description, 0,100)}...</p>
						</div>
									
					</div>
					<div id="search-price">
						<h3>$${book.price}</h3>
						<h3><a>Add to Cart</a></h3>
					</div>	
				</div>	
			</c:forEach>
		</div>
	</c:if>
	</div>
	<jsp:directive.include file="footer.jsp"/>

</body>
</html>