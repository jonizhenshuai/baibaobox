<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <!-- Include external CSS. -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css">

    <!-- Include Editor style. -->
    <link href="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
    <link href="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/css/froala_style.min.css" rel="stylesheet" type="text/css" />

    <link href="../css/edit.css" rel="stylesheet" />
</head>

<body>
<p id="area" hidden>${article.area.no}</p>
<p id="articleID" hidden>${article.no}</p>
<p id="managerID" hidden>${sessionScope.id}</p>
<form class="form-horizontal" role="form" id="edit_form" enctype="multipart/form-data" method="post">
    <div class="form-group ">
        <label for="title" class="col-sm-2 control-label">标题</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="title" placeholder="请输入标题" required="required" value="${article.title}">
        </div>
    </div>
    <div class="form-group ">
        <label for="type" class="col-sm-2 control-label">类型</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="type" placeholder="请输入类型" value="${article.articleType.type}" readonly>
            <p id="typeid" hidden>${article.articleType.id}</p>
            <div class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="classification">
                    选择类型
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu" id="article_type">
                </ul>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label for="picPath" class="col-sm-2 control-label">选择封面</label>
        <br>
        <div class="col-md-4">
            <input type="file" class="form-control"  id="picPath" required>
            <img class="head-img">
            <c:if test="${article.picturePath!=null}">
                <img src="${article.picturePath}" alt="封面图片" class="returnPic">
            </c:if>
        </div>
    </div>
    <br>
    <div class="dataReturn" hidden>${article.message}</div>
    <textarea id="edit">

    </textarea>
    <div class="choose">
        <input type="button" class="submitVal" value="发布">
    </div>

    <input type="button" class="change" value="修改" hidden style="background-color: #337ab7;width: 100px;height: 50px;color: white ;margin: 0 auto;">




</form>




<!-- Include external JS libs. -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>

<!-- Include Editor JS files. -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/js/froala_editor.pkgd.min.js"></script>
<script type="text/javascript" src="../js/edit.js"></script>
</body>
</html>