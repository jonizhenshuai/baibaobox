<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/jquery.mCustomScrollbar.css">
    <link href="../../css/school.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../../js/school.js"></script>
    <title>学校管理</title>
</head>
<body>
<div class="all">
    <div class="left" data-mcs-theme="minimal-dark">
        <button type="button" class="btn btn-primary" id="query" style="left: 0px">查询学校</button>
        <table class="table table-condensed">
            <thead>
            <tr>
                <th>学校名称</th>
                <th>所属校区</th>
                <th>学校代号</th>
                <th>操作</th>
            </tr>
            </thead>

            <!--一个tbody标签代表一间学校或者校区的信息 -->
            <tbody id="schools">
            </tbody>
        </table>

        <button type="button" class="btn btn-danger" id="delete" data-toggle="modal" data-target="#myModal">删除所选</button>

    </div>


    <div class="right">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">学校名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="name" placeholder="请输入学校名称">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="addSchool">添加学校</button>
                </div>
            </div>
            <br><br><br>


            <div class="form-group" >
                <label for="area" class="col-sm-2 control-label">所属校区</label>
                <button class="btn btn-warning" id="querySchool" value="查询学校">查询学校</button>
                <div class="col-sm-6">
                    <select class="form-control" id="areaSelect">
                        <option value="-1" id="opt_area" style="color: #b70f07">请点击查询按钮</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="area" class="col-sm-2 control-label">校区/学校地址</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="area" placeholder="请输入校区/学校地址">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary" id="addArea">添加校区</button>
                </div>
            </div>
        </form>
    </div>
    <br><br><br>

</div>
</body>
</html>