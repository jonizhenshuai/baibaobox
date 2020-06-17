<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/article.css" rel="stylesheet">

    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../../js/article.js"></script>
    <title>推文管理</title>
</head>
<body>
<div class="container">
    <!--  搜索栏  -->
    <div class="top">
    <div class="col-md-6" id="search">
        <div class="input-group">
            <input type="text" class="form-control" disabled>
            <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>
        </div>
    </div>
    <a href="/jsp/edit" target="mainframe">编写新推文</a>
    </div>
    <br><br><br>
    <div id="ReadLikeNumber">
        <h3>今日网站流览量：</h3><h3 id="readNum"></h3>
        <h3>今日网站点赞量：</h3><h3 id="likeNum"></h3>
    </div>
    <br>
    <c:if test="${sessionScope.saldfjlskfffds=='sdadwededa'}" >
        <input type="text" class="input-sm" placeholder="请输入想要添加的分类" id="new_type"><button class="new">添加新分类</button>
        <%--<input type="text" class="input-sm" placeholder="请输入想要删除的分类" id="del_type"><button class="del">删除分类</button>--%>
        <br><br>
        <table class="table table-condensed">
            <thead>
            <tr>
                <th>分类名称</th>
                <th>排序</th>
                <th>排序修改</th>
                <th>名称修改</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="types">
            </tbody>
        </table>
    </c:if>

    <br><br>

    <div class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="query_classification">
            文章分类
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu" id="query_articleList">

        </ul>
    </div>

<div class="" id="articles">

</div>



</ul>
</div>





</body>
</html>