$(function () {
    //加载滚动条
    $(".left").mCustomScrollbar();

    //查询学校地区填充到左侧列表
    $("#query").on('click',function () {
        $.ajax({
            type : "GET",
            url: "/allarea",
            // dataType:'json',
            success :function (data) {
                $("#schools").children().remove();
                $.each(data,function (index,item) {
                    $("#schools").append("<tr>\n" +
                        "                <td>"+item.school.name+"</td>\n" +
                        "                <td>"+item.name+"</td>\n" +
                        "                <td class='itemNo' style='text-align: center'>"+item.no+"</td>\n" +
                        "                <td><input type=\"checkbox\" name=\"schoolNo\" </td>\n" +
                        "            </tr>");
                })
            }
        });
    });

    //查询学校至选择框
    $("#querySchool").on('click',function (e) {
        e.preventDefault();
        $.ajax({
            type : "GET",
            url: "/school/allSchool",
            success :function (data) {
                $("#areaSelect").children().remove();
                $("#areaSelect").append("<option>==请选择==</option>");
                $.each(data,function (index,item) {
                    $("#areaSelect").append("<option id='opt_school' value='"+item.no+"'>"+item.name+"</option>");
                });
            }
        });
    });
    
    //添加学校
    $("#addSchool").on('click',function () {
        var name = $.trim($("#name").val());
        if(name==""){
            alert("名称不能为空")
            return;
        }
        $.ajax({
            url:'/school/insert',
            type: 'POST',
            data: {name:name},
            dataType:"json",
            contentType:"application/x-www-form-urlencoded",
            success:function (data) {
                alert(data["msg"]);
                $("#name").val("");
                $("#area").val("");
                $("#query").trigger('click');
            }
        });
    });

    //删除学校
    $("#delete").on('click',function () {
        if(confirm("是否决定删除学校?")){
            var checked=[];
            $("input[name='schoolNo']:checked").each(function (i) {
                checked[i]=$(this).parents().parents().children(".itemNo").text();
            });
            var ids = checked.join(",");
            $.ajax({
                url:"/deleteAreaBatch",
                type:"POST",
                data: {ids:ids},
                success:function(data) {
                    $("#query").trigger('click');
                },
                error:function (data) {
                    $("#query").trigger('click');
                }
            });
        }
    });

    //添加校区
    $("#addArea").on('click',function () {
        let school = $("#areaSelect").find("option:selected").text();
        let area = $("#area").val().trim();
        if (school=="请点击查询按钮"||school==null||school=="==请选择=="){
            alert("未选择学校")
        }else if(area==null||area==""){
            alert("区域不能为空");
        }else {
            $.ajax({
                url:"/addArea",
                type:"POST",
                data:{schoolName:school,areaName:area},
                success:function (data) {
                    alert(data["msg"]);
                    window.location.reload();
                }
            });
        }
    });
});