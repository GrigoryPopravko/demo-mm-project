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
    <title>Каталог Книг</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/book/create" method="post">
    <label for="titleId">Title:</label><br>
    <input type="text" id="titleId" name="title"><br>

    <select id="genreId" name="genre">
        <c:forEach var="genre" items="${requestScope.genres}">
            <option value="${genre}">${genre}</option>
        </c:forEach>
    </select>

    <label for="pagesId">Page Amount:</label><br>
    <input type="number" id="pagesId" name="pages"><br>

    <label for="authorsIds">Author:</label>
    <select id="authorsIds" name="authorsIds">
        <c:forEach var="author" items="${requestScope.authors}">
            <option value="${author.id}">${author.fullName}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Submit">
</form>

<%@ include file="footer.jsp" %>
</body>
</html>
