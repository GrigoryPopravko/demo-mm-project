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
    <title>Registration</title>
</head>
<body>
<%@ include file="header.jsp" %>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="nameId">Name:</label><br>
    <input type="text" id="nameId" name="name"><br>

    <label for="surnameId">Surname:</label><br>
    <input type="text" id="surnameId" name="surname"><br>

    <label for="emailId">Email:</label><br>
    <input type="email" id="emailId" name="email"><br>

    <label for="passwordId">Password:</label><br>
    <input type="password" id="passwordId" name="password"><br>

    <label for="genderId">Gender?:</label><br>

    <select name="gender" id="genderId">
        <option value="MALE">Мужчина</option>
        <option value="FEMALE">Женщина</option>
    </select>

    <input type="submit" value="Submit">
</form>

<%@ include file="footer.jsp" %>
</body>
</html>


