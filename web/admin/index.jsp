<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <title>CRUD</title>


    <style> <%@include file="../css/main.css" %> </style>

</head>
<body>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("userName")) userName = cookie.getValue();
        }
    }
%>

<h2>Hi <%=userName %></h2>
<form action="/logout" method="post" class="editor">
    </br><input class="b2" type="submit" value="Выйти">
</form>
<form action="/user" method="post" class="editor">
    </br><input class="b2" type="submit" value="user">
</form>

<div id="centr">
    <div class="reg">
        <form action="reg" method="post">
            Для регистрации введите</br>
            Имя:
            <input name="name" type="name">
            Логин:
            <input type="login" name="login">
            Пароль:
            <input type="password" name="password">
            <br><input class="b1" type="submit" value="Зарегистрировать">
        </form>
    </div>
    <table border="1" cellpadding="5">
        <caption><h2>Список пользователей</h2></caption>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Логин</th>
            <th>Пароль</th>
            <th>Привелегии</th>
            <th>Изменить пользователя</th>
            <th>Удалить пользователя</th>
        </tr>

        <c:forEach var="usersDataSet" items="${clients}">
            <tr>

                <td><c:out value="${usersDataSet.getId()}" /></td>
                <td><c:out value="${usersDataSet.getName()}" /></td>
                <td><c:out value="${usersDataSet.getLogin()}" /></td>
                <td><c:out value="${usersDataSet.getPassword()}" /></td>
                <td><c:out value="${usersDataSet.getRole()}" /></td>
                <td>
                    <form action="edit" method="get">
                        <input class="b2" type="submit" value="Изменить">
                        <input name="idEdit" type="hidden" value="${usersDataSet.getId()}">
                    </form>
                </td>
                <td>
                    <form action="delete" method="post">
                        <input name="idDelete" type="hidden" value="${usersDataSet.getId()}">
                        <input class="b3" name="idDelete" type="submit" value="Удалить" >
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
