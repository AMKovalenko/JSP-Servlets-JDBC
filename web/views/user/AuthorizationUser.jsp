<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 20.09.2017
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login user</title>
</head>
<body>
<h2>Аутентификация пользователя:</h2><br>
<form action="${pageContext.servletContext.contextPath}/user/authorization" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text", name="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text", name="password"></td>
        </tr>
    </table><br>
    <input type="submit", value="Войти">
    <input type="submit" value="Регистрация"
           onclick="window.location='${pageContext.servletContext.contextPath}/views/user/CreateUser.jsp';" /><br>
</form>
</body>
</html>
