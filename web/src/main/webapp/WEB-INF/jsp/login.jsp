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
    <title>Login</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="emailId">Email:</label><br>
    <input type="email" id="emailId" name="email"><br>

    <label for="passwordId">Password:</label><br>
    <input type="password" id="passwordId" name="password"><br>

    <input type="submit" value="Submit">
</form>
<a href="${pageContext.request.contextPath}/registration">Регистрация</a>

<c:if test="${ param.error  == true}">
    Неправильный Логин или Пароль
</c:if>

<%@ include file="footer.jsp" %>
</body>
</html>

