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

<c:if test="${ param.error  == true}">
    Книга не была изменина
</c:if>

<h1>${sessionScope.user.email} хотим тебе предложить</h1>

<c:forEach var="book" items="${requestScope.books}">
    <h2>${book.title}</h2>
    <h3>Кол-во страниц: ${book.pages}</h3>
    <h4>Жанр: ${book.genre}</h4>
    <h5><a href=${pageContext.request.contextPath}/book/${book.id}>Подробнее</a></h5>
</c:forEach>

<%@ include file="footer.jsp" %>
</body>
</html>
