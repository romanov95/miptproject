<%@ page import="servlets.User" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Страница успешного входа в систему</title>
</head>
<body>
<%
  User user = new User((User)session.getAttribute("userSession"));
%>

Вход посетителя <%= user.getLogin()%> в систему прошел успешно
<form action="Login" method="post">
  <input type="submit" name="cancelButton" value="Выйти">
</form>

</body>
</html>