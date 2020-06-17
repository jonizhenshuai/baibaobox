<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/rights_management.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../js/rights_management.js" ></script>
    <title>权限管理</title>
</head>


<body>

<div class="btn_management">
    <button type="button" class="btn btn-primary"  id="query">查询所有管理员</button>
    <button type="button" class="btn btn-danger" id="delete">删除所选管理员</button>
</div>

<!--控制表单样式的div-->
<div class="table_management">
    <table class="table table-bordered">
        <caption>地区管理人员</caption>
        <thead>
        <tr>
            <th>管理员ID</th>
            <th>姓名</th>
            <th>学校</th>
            <th>地区</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="information">
        </tbody>
    </table>
</div>
<br><br>

<%--添加管理员--%>
<div class="new_form">
    <form class="form-horizontal" role="form">
        <div class="form-group ">
            <label for="username" class="col-sm-2 control-label" >新建ID</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username" placeholder="请输入新ID" required>
            </div>
        </div>
        <div class="form-group ">
            <label for="name" class="col-sm-2 control-label">管理员昵称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" placeholder="请输入昵称" required>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" placeholder="请输入密码" required>
            </div>
        </div>
        <div class="form-group" >
            <label for="school" class="col-sm-2 control-label">所属校区</label>
            <div class="col-sm-4">
                <select class="form-control" id="school">
                    <option>请点击查询按钮</option>
                </select>
            </div>
            <div class="col-sm-4" style="text-align: left"><button class="btn btn-warning" id="querySchool">查询学校</button></div>
        </div>
        <div class="form-group" >
            <label for="area" class="col-sm-2 control-label">所属地区</label>
            <div class="col-sm-4">
                <select class="form-control" id="area">
                    <option>==请选择==</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="button" class="btn btn-primary" id="add">添加</button>
            </div>
        </div>
    </form>
</div>


</body>
</html>