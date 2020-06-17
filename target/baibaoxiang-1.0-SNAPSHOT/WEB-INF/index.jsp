<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2019.5.12
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>百宝箱</title>
    <meta name="viewport"
          content=" height = device-height,
                    width = device-width,
                    initial-scale = 1.0,
                    minimum-scale = 1.0,
                    maximum-scale = 1.0,
                    user-scalable = no"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/front/css.css"/>
    <link rel="stylesheet" href="/css/front/style.css"/>
    <link rel="stylesheet" href="/css/front/StaffFloat.css"/>
    <script src="/js/front/jquery-3.2.1.js"></script>
    <script src="/js/front/js.js"></script>
    <script src="/js/front/AreaMessage.js"></script>
    <script src="/js/front/RequestSchool.js"></script>
    <script src="/js/front/ModifyType.js"></script>
    <script src="/js/front/RequestArea.js"></script>
    <script src="/js/front/WeiXinFit.js"></script>
    <script src="/js/front/ScrollShowToTop.js"></script>
    <script src="/js/front/CutPage.js"></script>
</head>
<body>
<div id="chooseSchool" style="position:fixed;width: 100%;z-index: 3;left: 600px">
    <div style="margin-left: 2rem;padding-top:1.2rem;padding-bottom: 1rem"><span>地区选择：</span></div>
    <div id="school" class="input-group mb-3">
        <span>学校：</span>
        <select id="schoolName" class="custom-select" id="inputGroupSelect02" STYLE="padding-left: 1rem">
            <c:forEach items="${school}" var="school">
                <option value="${school.no}">${school.name}</option>
            </c:forEach>
        </select>
    </div>
    <div id="area" class="input-group mb-3">
        <span>校区：</span>
        <select id="areaName" class="custom-select" id="inputGroupSelect03" STYLE="padding-left: 1rem;">
            <c:forEach items="${area}" var="area">
                <option value="${area.no}">${area.name}</option>
            </c:forEach>
        </select>
    </div>
</div>
<div id="Navigation" style="position:fixed;width: 100%;z-index: 2;">
    <div id="showSearch" style="width:100%">
        <div id="nav" class="pos-f-t">
            <nav class="navbar navbar-dark bg-dark" style="height:2.1rem">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <!--<span class="navbar-toggler-icon"></span>-->
                    <img src="/images/front/area.svg"/>
                </button>
                <h5 class="text-white" style="margin: auto">广东第二师范学院(花都校区)</h5>
                <a href="search.html"><img src="/images/front/search.svg"></a>
            </nav>
        </div>
        <div class="find_nav">
            <div class="find_nav_left">
                <div class="find_nav_list">
                    <ul>
                        <c:forEach items="${articleTypeList}" var="articleType">
                            <li value="${articleType.id}"><a href="javascript:void(0)">${articleType.type}</a></li>
                        </c:forEach>
                        <li class="sideline"></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<table class="table" >
    <a href="#" id="back-to-top" title="Back to top">∧</a>
    <div style="width: 100%;height: 95px"></div>
    <tbody>
    <c:forEach items="${articleList}" var="article">
        <tr>
            <td class="firstTd">
                <div class="Title"><a href="/detail?no=${article.no}">${article.title}</a></div>
                <div>
                    <img class="littleIcon" src="/images/front/love.svg"/>
                    &nbsp;<span>${article.likeNum}</span>
                    &nbsp; <img class="littleIcon" src="/images/front/read.svg"/>
                    <span>${article.readNum}</span>
                </div>
            </td>
            <td width="30%">
                <img src="${article.picturePath}" class="rounded " alt="..."></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<div id="warn" style="position:fixed; bottom:0;z-index:999;margin-bottom: 0;display: none"
     class="alert alert-warning alert-dismissible fade show" role="alert">
    为了您更好的浏览体验，我们建议您点击右上角，使用浏览器访问
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>--%>
<div id="mess" style="display: none">
    <img src="/images/front/loading.gif" >&nbsp;<span>正在加载数据..</span>
</div>
<input id="page" type="hidden" value="2">
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(" tbody").on("click", 'tr', function () {
            location.href = $(this).find(" a").attr("href")
        })
    })
</script>
</body>
</html>
