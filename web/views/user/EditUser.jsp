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
    <title>Edit user</title>
</head>
<body>
<h2>Редактирование данных пользователя:</h2><br>
<form action="${pageContext.servletContext.contextPath}/user/edit" method="post">
    <input type="hidden", name="id" value="${user.id}">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text", name="login" value="${user.login}"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text", name="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text", name="firstName" value="${user.name}"></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input type="text", name="lastName" value="${user.surname}"></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><input type="text", name="email" value="${user.email}"></td>
        </tr>
    </table><br><br>
    <input type="submit", value="Редактировать данные">
</form>
</body>
</html>
