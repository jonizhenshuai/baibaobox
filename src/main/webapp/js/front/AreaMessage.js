$(document).ready(function () {
    var $chooseSchool = $("#chooseSchool");
    /*展示地址栏*/
    $(".navbar-toggler").on("click", function () {
        if ($chooseSchool.css("left") != '0px') {
            $chooseSchool.css("display", "block")
            $($chooseSchool).animate({"left": 0}, 500);
        }
    })
    /*退出地址栏*/
    $("body").on("click", function (e) {
        var target = $(e.target);
        if (!target.is($("#areaName")) && !target.is($("#schoolName"))) {
            if ($chooseSchool.css("left") == '0px') {
                $($chooseSchool).animate({"left": 600}, 500);
                setTimeout(function f() {
                    $chooseSchool.css("display", "none")
                }, 500)
            }
        }
    })
})
