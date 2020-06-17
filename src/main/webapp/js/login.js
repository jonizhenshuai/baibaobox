$(function () {

    $("#login").click(function () {
        var username = $.trim($("#username").val());
        var password = $.trim($("#password").val());
        var validatecode = $.trim($("#validatecode").val());
        $("#validatecode").val(validatecode.toUpperCase());
        $("#form").submit();
         if(username!=null&&username!=""){
           if (password!=null&&password!="") {
                if (validatecode!=null&&validatecode!="") {
                    $("#form").submit();
                }else {
                    alert("验证码不能空");
                }
            }else {
                alert("密码不能空");
            }
         }else {
             alert("用户名不能空");
         }
    });
});