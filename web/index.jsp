<%--
  Created by IntelliJ IDEA.
  model.User: Александр
  Date: 18.09.2017
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Главная страница</title>
</head>
<body>
Здравствуйте, ${login}!!! <br>
<a href="${pageContext.servletContext.contextPath}/user/edit">Редактировать</a><br>
<a href="${pageContext.servletContext.contextPath}/user/view">Список пользователей</a><br>
<a href="${pageContext.servletContext.contextPath}/user/create">Зарегистрироваться</a><br>
<a href="${pageContext.servletContext.contextPath}/user/authorization">Войти</a>
</body>
</html>
