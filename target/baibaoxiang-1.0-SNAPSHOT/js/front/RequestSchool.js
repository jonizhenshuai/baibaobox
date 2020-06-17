$(document).ready(function () {
    //ajax请求学校地址
    $(" #schoolName").change(function () {
        $("#page").attr("value","2");
        var schoolNo = $("#schoolName").val();
        $.post("/index/queryAreaName", {schoolNo: schoolNo}, function (data) {
            $("#areaName").empty();
            for (var i in data) {
                $("#areaName").append("<option value=" + data[i].no + ">" + data[i].name + "</option>")
            }
                var areaNo = $("#areaName").val();
                $.post("/index/changeAreaArticle", {
                    areaNo:  areaNo,
                    typeNo: $(".find_nav_cur").val()
                }, function (data) {
                     $(" h5").html($("#schoolName").find("option:selected").text() + "(" + $("#areaName").find("option:selected").text() + ")");
                    $(".table tbody").empty();
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
                })
        });
    });
})