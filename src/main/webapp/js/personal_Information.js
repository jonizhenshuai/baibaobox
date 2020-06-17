$(function () {
    //页面加载时加载头像
    $("img").attr("src",$("#path").text());

    $("#change").click(function () {
        var name = $.trim($("#name").val());
        var path = $.trim($("#path").val());
        if(confirm("是否决定修改?")){
            $("form").submit();
        }
    });
});