<%--
  Created by IntelliJ IDEA.
  User: ryhor
  Date: 06.04.2023
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>Menu:<a href=/books>Главная</a> Fantasy | Sci-Fi | Fiction | Classic</h1>
<sec:authorize access="isAuthenticated()">
    <b>Привет ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</b>
    <form action="/logout" method="post">
        <input type="submit" value="Logout">
    </form>
</sec:authorize>
