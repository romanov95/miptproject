<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Страница успешного входа в систему</title>
</head>
<body>
<br>
<h1>Вход посетителя в систему прошел успешно</h1>
<jsp:useBean id="login" class="servlets.User" scope="application"/>
Пользователь: <%= login.getLogin()%><br>
</body>
</html>