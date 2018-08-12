<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/login" method="post">
    Username:<input type="text" name="username"><br><br>
    Password:<input type="password" name="password"><br><br>
    <input type="submit" value="登录">
    ${errorMsg}
</form>
</body>
</html>
