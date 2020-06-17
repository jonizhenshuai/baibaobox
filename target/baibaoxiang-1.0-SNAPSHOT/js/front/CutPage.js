$(document).ready(function () {
//页面滚到底部异步加载下一页数据
    $(window).on("scroll",function () {
        //已经滚动到上面的页面高度
        var scrollTop = parseFloat($(this).scrollTop()),
            //页面高度
            scrollHeight = $(document).height(),
            //浏览器窗口高度
            windowHeight = parseFloat($(this).height()),
            totalHeight = scrollTop + windowHeight;
        //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
        if (totalHeight >= scrollHeight - 50) {
            var page = parseInt($("#page").val());
            $.ajax({
                type: 'post',
                url: '/index/getAreaArticle',
                data: {
                    typeNo: $(".find_nav_cur").val(),
                    areaNo: $("#areaName").find("option:selected").val(),
                    page: page
                },
                beforeSend: function (XMLHttpRequest) {
                    $("#mess").css("display", "block");
                },
                success: function (data) {
                    if (data.length == 0) {
                        $("#mess").html("<span>已经加载全部数据</span>")
                    } else {
                        $.each(data, function (i, article) {
                            $(".table tbody").append("<tr>\n" +
                                "                <td class=\"firstTd\">\n" +
                                "                    <div class=\"Title\"><a href=\"/detail?no=" + article.no + "\">" + article.title + "</a></div>\n" +
                                "                    <div>\n" +
                                "                        <img class=\"littleIcon\" src=\"/images/front/love.svg\"/>\n" +
                                "                        &nbsp;<span>" + article.likeNum + "</span>\n" +
                                "                        &nbsp; <img class=\"littleIcon\" src=\"/images/front/read.svg\"/>\n" +
                                "                        <span>" + article.readNum + "</span>\n" +
                                "                    </div>\n" +
                                "                </td>\n" +
                                "                <td width=\"30%\">\n" +
                                "                    <img src=\"" + article.picturePath + "\" class=\"rounded \" alt=\"...\"></td>\n" +
                                "            </tr>")
                        })
                        $("#page").attr("value", ++page);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $("#mess").html("<span>加载错误,请重新刷新页面</span>")
                }
            });
        }

    });
})