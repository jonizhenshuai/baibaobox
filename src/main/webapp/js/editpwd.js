$(function () {
    $("#change").click(function () {

        var initial_password= $.trim($("#initial_password").val());

        var new_password = $.trim($("#new_password").val());

        var confirm_password = $.trim($("#confirm_password").val());
        if (isChinese(initial_password)||isChinese(new_password)||isChinese(confirm_password)){
            alert("密码不允许有中文");
        }else {
            if (new_password==confirm_password){
                $.ajax({
                    type : "post",
                    url:"/manager1/updatepassword",
                    data:{oldPassword:initial_password,newPassword:new_password },
                    async:false,
                    success:function (data) {
                        alert(data["msg"]);
                        $("#initial_password").val("");
                        $("#new_password").val("");
                        $("#confirm_password").val("");
                    }
                });
            }else {
                alert("确认密码不正确");
            }
        }
    });
});

/**
 * 判断输入框是否为中文
 * @param temp
 * @returns {boolean}
 */
function isChinese(temp)
{
    var re=/^[a-zA-Z0-9_]{0,}$/;
    if (re.test(temp))
        return false ;
    return true ;
}