$(document).ready(function () {
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    $(".table").on('click', 'a', function () {
        if (isWeixin) {
            sessionStorage.setItem("left", $(".sideline").css("left"))
            sessionStorage.setItem("left2", $(".find_nav_list").css("left"))
            sessionStorage.setItem("width", $(".sideline").css("width"))
            var json = new Date().getTime();
            history.pushState({json}, '', window.location.href + "#" + json);
            sessionStorage.setItem("MainContent", $("tbody").html())
        }
    })
    $(function () {
        if (isWeixin) {
           /* $("#warn").css("display", "block");*/
            if (sessionStorage.getItem("MainContent") != null && sessionStorage.getItem("MainContent") != "") {
                window.history.back(-1)
                $("tbody").html(sessionStorage.getItem("MainContent"))
                sessionStorage.setItem("MainContent", "")
                $(".find_nav_list li").first().removeClass("find_nav_cur");
                $(".find_nav_list li").eq(sessionStorage.index).addClass("find_nav_cur")
                $(".sideline").css("left", sessionStorage.getItem("left"))
                sessionStorage.setItem("left", "")
                $(".sideline").css("width", sessionStorage.getItem("width"))
                sessionStorage.setItem("width", "")
                $(".find_nav_list").css("left", sessionStorage.getItem("left2"))
                sessionStorage.setItem("left2", "")
            } else {
                $(".find_nav_list").css("left", 0)
            }
        }
    })
})