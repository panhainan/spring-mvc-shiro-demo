<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
欢迎您，
<shiro:user>
    <shiro:principal/>
</shiro:user>

<ul>
    <shiro:hasRole name="user">
        <li><a href="/user">用户信息界面</a></li>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
        <li><a href="/manage">管理界面</a></li>
    </shiro:hasRole>
    <li><a style="color: red" href="/logout">退出</a></li>
</ul>
</body>
</html>
