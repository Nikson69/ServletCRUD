<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CRUD</title>
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

    <h1>Welcome <%=userName %> !</h1>
    <form action="/logout" method="post" class="editor">
    </br><input class="b2" type="submit" value="Выйти">
    </form>
    <form action="/admin" method="post" class="editor">
    </br><input class="b2" type="submit" value="admin">
    </form>
</body>
</html>
