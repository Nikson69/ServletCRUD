<%--
  Created by IntelliJ IDEA.
  User: nikita
  Date: 5/12/18
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
<form action="login" method="post">
    login:
    <input name="login" value="">
    </br>password:
    <input type="password" name="password" value="">
    </br><input class="b2" type="submit"  value="sing in">
</form>
<form action="/user" method="post" class="editor">
    </br><input class="b2" type="submit" value="user">
</form>
<form action="/admin" method="post" class="editor">
    </br><input class="b2" type="submit" value="admin">
</form>
</body>
</html>
