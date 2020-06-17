$(function() {
    //加载文本编辑器+
    $('#edit').on('froalaEditor.initialized', function (e, editor) {
        if ($('.dataReturn').html()!=""){
            $('#edit').froalaEditor('html.set',$('.dataReturn').html());
        }
    }).froalaEditor({
        imageUploadURL:'/article/uploadArticleImg',
        enter: $.FroalaEditor.ENTER_BR,
        language:'zh_cn',
    });

    //判断修改还是发布
    if ($("#type").val()!=""){
        $(".submitVal").hide();
        $(".change").show();
    }

    //头像
    $('#picPath').on('change',function(){

        // 如果没有选择图片 直接退出
        if(this.files.length <=0){
            return false;
        }
        // 图片上传到服务器
        var pic1 = this.files[0];
        var formData = new FormData();
        // 服务端要求参数是 pic1
        formData.append('file',pic1);
        $.ajax({
            url: '/article/uploadArticleImg',
            type: 'post',
            data: formData,
            cache: false, //上传文件不需要缓存
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success:function(data){
                $('.returnPic').hide();
                $('.head-img').attr('src',data.link);
                var img=new Image();
                img.src=$('.head-img').src;
                var realWidth = img.width;
                var realHeight = img.height;
                console.log(realHeight);
                console.log(realWidth);
            }
        })
    });

    //发布推文
    $(".submitVal").on('click',function () {
        var title = $.trim($("#title").val());
        var type = $.trim($("#type").val());
        var typeid = $.trim($("#typeid").text());
        var message = $("#edit").froalaEditor('html.get', true);
        var area = $("#area").text();
        var picture = $('.head-img').attr('src');
        var author = $("#managerID").text();
        var date = new Date();
        var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        var data1 ={title:title,articleType:{id:typeid},area:{no:area},message:message,createTime:date1,readNum:"0",likeNum:"0",
            picturePath:picture,manager:{id:author},top:"4"};
        if($.trim(title).length==0||title==""){
            alert("标题未填");
            return false;
        }else if($.trim(type).length==0||type==""){
            alert("类型未填");
            return false;
        } else if($.trim(area).length==0||area==""){
            alert("地区未填");
            return false;
        } else {
            $.ajax({
                type:"POST",
                url:"/article/",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data1),
                success:function (data) {
                    if (data["msg"]=="发布成功") {
                        window.location.href="../success.jsp";
                    }else {
                        alert("发布失败");
                    }
                }
            });
        }
    });

    //修改推文
    $(".change").on('click',function () {
        var no = $("#articleID").text();
        var title = $.trim($("#title").val());
        var type = $.trim($("#typeid").text());
        var picture = $('.head-img').attr('src');
        var message = $("#edit").froalaEditor('html.get', true);
        var area = $("#area").text();
        if ($(".returnPic").attr("src") != ""){
            var picture = $('.head-img').attr('src');
        }else {
            var picture = $('.returnPic').attr('src');
        }
        // var author = $("#username").text();
        var date = new Date();
        //获取当前日期，格式为yyyy-mm-dd
        var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();

        var data1 ={no:no,title:title,articleType:{id:type},area:{no:area},message:message,createTime:date1,readNum:"0",likeNum:"0",
            picturePath:picture,top:"4"};
        if($.trim(title).length==0||title==""){
            alert("标题未填");
            return false;
        }else if($.trim(type).length==0||type==""){
            alert("类型未填");
            return false;
        }else {
            $.ajax({
                type:"POST",
                url:"/article/updateArticle",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data1),
                success:function (data) {
                    if(data==0){
                        alert("修改失败");
                        return;
                    }
                    window.location.href="../success.jsp";
                }
            });
        }
    });

    //选择文章类型
    $("#classification").on('click',function () {
        $.ajax({
            url:"/articleType",
            type:"post",
            success:function (data) {
                $("#article_type").children().remove();
                $.each(data,function (index,item) {
                    $("#article_type").append("<li><a href='#' class='type1' id='"+item.id+"'>"+item.type+"</a></li>");
                });
            }
        });
    });

    //添加类型到输入框
    $(document).on('click',".type1",function () {
        //获取下拉框的内容
        var type = $(this).text();
        $("#typeid").text($(this).attr("id"));
        //添加到输入框内容
        $("#type").val(type);
    });



});
