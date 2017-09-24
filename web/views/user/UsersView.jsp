<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 19.09.2017
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User View</title>
</head>
<body>
<h2>Список пользователей</h2><br>
<table border="1" cellspacing="0" cellpadding="1">
    <tr>
        <td>ID</td><td>Login</td><td>Password</td><td>First Name</td><td>Last Name</td><td>E-mail</td><td></td><td></td>
    </tr>
    <c:forEach items="${users}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.email}</td>
            <td><a href="${pageContext.servletContext.contextPath}/user/edit?id=${user.id}">Редактировать</a> </td>
            <td><a href="${pageContext.servletContext.contextPath}/user/delete?id=${user.id}">Удалить</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
