<jsp:useBean id="user" scope="request" type="models.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
    <form action="edit" method="post" class="editor">
        Изменить пользователя ID ${user.id} <input name="id" type="hidden" value="${user.id}">
        </br> Имя:
        <input name="name" value="${user.name}">
        </br>Логин:
        <input name="login" value="${user.login}">
        </br>Пароль:
        <input type="password" name="password" value="${user.password}">
        </br>Права пользователя
        <select  name="role">
            <option value="${user.role}">${user.role}</option>
            <option value="${user.role.equals("user") ? "admin" : "user"}">${user.role.equals("user") ? "admin" : "user"}</option>
        </select>
        </br><input class="b2" type="submit" id="editorr"  value="Измеинть">
    </form>
</body>
</html>
