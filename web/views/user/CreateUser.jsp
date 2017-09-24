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
    <title>Create user</title>
</head>
<body>
<h2>Регистрация нового пользователя:</h2><br>
<form action="${pageContext.servletContext.contextPath}/user/create" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text", name="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text", name="password"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text", name="firstName"></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input type="text", name="lastName"></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><input type="text", name="email"></td>
        </tr>
    </table><br><br>
    <input type="submit", value="Создать пользователя">
</form>
</body>
</html>
