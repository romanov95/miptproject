<%@ page import="servlets.User" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Страница подтверждения успешной регистрации посетителя</title>
</head>
<body>
<h1>Регистрация посетителя успешно завершена</h1>
<%--<jsp:useBean id="login" class="servlets.User" scope="application"/>--%>
<%--Пользователь: <%= login.getLogin()%><br>--%>
<%--Зарегистрирован.<br><br>--%>
<%
  User user = new User();
  user.setUser((User)session.getAttribute("userSession"));
%>
Пользователь: <%= user.getLogin()%>
Зарегистрирован.
<a href="login.jsp">Войти в систему</a>
</body>
</html>