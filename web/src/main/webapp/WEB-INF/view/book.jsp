<%--
  Created by IntelliJ IDEA.
  User: ryhor
  Date: 06.04.2023
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Книга</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:if test="${ param.error  == true}">
    Книга не была создана
</c:if>

<h2>${book.title}</h2>
<h3>Кол-во страниц: ${book.pages}</h3>
<h4>Жанр: ${book.genre}</h4>

<%@ include file="footer.jsp" %>
<form action="${pageContext.request.contextPath}/book/${book.id}/delete" method="post">
    <button>DELETE</button>
</form>

<form action="${pageContext.request.contextPath}/book/${book.id}/update" method="post">
    <label for="titleId">Title:</label><br>
    <input type="text" id="titleId" name="title" value="${book.title}"><br>

    <label for="genreId">Genre:</label><br>
    <input type="text" id="genreId" name="genre" value="${book.genre}"><br>

    <label for="pagesId">Page Amount:</label><br>
    <input type="number" id="pagesId" name="pages" value="${book.pages}"><br>

    <label for="authorsIds">Author:</label>
    <select id="authorsIds" name="authorsIds">
        <c:forEach var="author" items="${book.authors}">
            <option value="1">${book.authors}</option>
        </c:forEach>
    </select>

    <input type="submit" value="UPDATE">
</form>
</body>
</html>

