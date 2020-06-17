<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/editpwd.css">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../js/editpwd.js" ></script>
    <title>密码管理</title>
</head>
<body>
<div class="edit_form">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label for="initial_password" class="col-sm-2 control-label">原始密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="initial_password" placeholder="请输入原始密码">
            </div>
        </div>
        <div class="form-group">
            <label for="new_password" class="col-sm-2 control-label">新密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="new_password" placeholder="请输入新密码">
            </div>
        </div>
        <div class="form-group">
            <label for="confirm_password" class="col-sm-2 control-label">确认密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="confirm_password" placeholder="再次输入新密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" id="change">修改</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>