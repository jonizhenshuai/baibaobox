<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/top.css" rel="stylesheet">
    <title>top</title>
</head>
<body>
    <span class="quit"><a href="/manager1/logout" target="_parent">退出登录</a></span>
    <div class="username"><a href="/jsp/personal_Information" target="mainframe">${sessionScope.username}</a></div>
</body>
</html>