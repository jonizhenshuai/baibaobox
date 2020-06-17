<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/cancellation.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <title>Title</title>
</head>
<body>
<div class="all">
    <!--  搜索栏  -->
    <div class="search col-md-8">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="输入用户id">
            <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>
        </div>

    </div>
    <br><br><br>
    <!--显示搜寻结果-->
    <table class="table table-condensed">
        <thead>
        <tr>
            <th>用户id</th>
            <th>所属校区</th>
            <th>学校代号(自动生成)</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" name="schoolName">abc123</td>
            <td>花都校区</td>
            <td>1</td>
        </tr>
        </tbody>




    </table>
    <button type="button" class="btn btn-danger" id="new">删除所选</button>
</div>
</body>
</html>