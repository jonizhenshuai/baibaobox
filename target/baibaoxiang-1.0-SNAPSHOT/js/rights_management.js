$(function () {
    //查询管理员
    $("#query").on("click",function () {
        var title = {title:'BBBBB'};
        $.ajax({
            url:"/manager1/title",
            type : "POST",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:title,
            success :function (data) {
                $("#information").children().remove();
                $.each(data,function (index,item) {
                    $("#information").append("<tr><td class='username'><input type=\"checkbox\" name=\"username\">"+item.username+"</td>\n" +
                        "<td>"+item.name+"</td>\n" +
                        "<td>"+item.area.school.name+"</td>"+
                        "<td>"+item.area.name+"</td>"+
                        "<td><button type=\"button\" class=\"btn btn-danger\" id=\"edit\">修改密码</button>"+"</td></tr>");
                });
            }
        });
    });

    //修改密码
    $(document).on('click','#edit',function () {
        var id = $(this).parent().parent().children(".username").text();
        var password = prompt("想要修改的密码:");
        if (isChinese(password)){
            alert("不能为中文");
        }else if(password==""||password==null){
            return;
        }else {
            $.ajax({
                type:"post",
                url:"/manager1/updateBy",
                data:{id:id,password:password},
                success:function (data) {
                    alert(data["msg"]);
                }
            });
        }
    });

    // $("#querySchool").trigger();
    //查询学校
    $("#querySchool").on('click',function (e) {
        e.preventDefault();
        $.ajax({
            type : "GET",
            url: "/school/allSchool",
            success :function (data) {
                $("#school").children().remove();
                $("#area").children().remove();
                $("#school").append("<option>==请选择==</option>");
                $.each(data,function (index,item) {
                    $("#school").append("<option id='opt_school' value='"+item.no+"'>"+item.name+"</option>");
                });
            }
        });
    });

    //查询地区
    $("#school").change(function () {
        var name = $(this).find("option:selected").text();
        $.ajax({
            url: '/findAreaBySchoolName',
            type: 'POST',
            data:{name:name},
            success:function (data) {
                $("#area").children().remove();
                $.each(data,function (index,item) {
                    $("#area").append("<option id='opt_area' value='"+item.no+"'>"+item.name+"</option>");
                });
            }
        });
    });


    //添加管理员
    $("#add").on('click',function () {
        var username = $.trim($("#username").val());
        var name = $.trim($("#name").val());
        var password = $.trim($("#password").val());
        var area = $("#area").find("option:selected").val();
        var school = $("#school").find("option:selected").val();
        if(isChinese(username)||isChinese(password)){
            alert("用户名或密码不能携带中文");
        }else {
            var data1 = {username:username,name:name,password:password,area:{no:area}};
            $.ajax({
                url:'/manager1',
                type: "POST",
                dataType:"json",
                contentType:"application/json; charset=utf-8",
                data: JSON.stringify(data1),
                success:function (data) {
                    alert(data["msg"]);
                    $("#username").val("");
                    $("#name").val("");
                    $("#password").val("");
                    $("#area").val("");
                },
                error:function () {
                    alert("添加失败");
                }
            });
        }

    });

    //删除管理员
    $("#delete").on('click',function () {
        if(confirm("是否决定删除管理员?")){
            var checked=[];
            $("input[name='username']:checked").each(function (i) {
                checked[i]=$(this).parents("td").text();
            });
            var usernames = checked.join(",");
            console.log(usernames);
            $.ajax({
                url:"/manager1/deleteBatch",
                type:"POST",
                data: {usernames:usernames},
                success:function (data) {
                    alert(data["msg"]);
                    $("#query").trigger('click');
                }
            });
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