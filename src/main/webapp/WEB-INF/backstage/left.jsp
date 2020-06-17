<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/left.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../../js/left.js" ></script>
    <title>Title</title>
</head>
<body>
<div class="content">
    <!--标题-->
    <div class="page-header">
        <h2>百宝箱</h2>
    </div>

    <!--功能选项-->
    <ul class="nav nav-pills nav-stacked" id="func">
        <h3 style="color: white">管理功能</h3>
        <li><a href="/jsp/article" target="mainframe">推文管理</a></li>
        <c:if test="${sessionScope.saldfjlskfffds=='sdadwededa'}" >
            <li><a href="/jsp/rights_management" target="mainframe">权限管理</a></li>
            <li><a href="/jsp/school" target="mainframe">学校管理</a></li>
            <li><a href="/jsp/searchManager" target="mainframe">索引管理</a></li>
        </c:if>
        <li><a href="/jsp/editpwd" target="mainframe">密码管理</a></li>
        <h3 style="color: white">账号信息</h3>
        <li><a href="/jsp/personal_Information" target="mainframe">个人资料</a></li>
        <li><a href="/jsp/main" target="mainframe">账号注销</a></li>
        <hr>

    </ul>
</div>
</body>
</html>