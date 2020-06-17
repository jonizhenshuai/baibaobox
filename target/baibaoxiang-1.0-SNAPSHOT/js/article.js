$(function () {
    //加载今日点赞数
    ReadLikeNumber();

    //加载文章类型
    getArticleType();

    //调整顺序
    $(document).on('click','#edit_sort',function () {
        var oldSequenceNum = $(this).parents().children(".item_sequenceNum").text();
        var newSequenceNum = $(this).parents().parents().children().children("#sort").val().trim();
        var id = $(this).parents().parents().children(".item_no").text();
        if(!isNaN(newSequenceNum)){
            $.ajax({
                url: '/articleType/updateArticleSequenceNum',
                type: 'POST',
                data:{id:id,oldSequenceNum:oldSequenceNum,newSequenceNum:newSequenceNum},
                success:function (data) {
                    if (data==1){
                        window.location.reload();
                    } else {
                        alert("修改失败");
                    }
                },
                error:function () {
                    alert("修改失败");
                }
            });
        }else {
            alert("必须是数字！");
        }

    });

    //修改文章类型
    $(document).on('click','#edit_name',function () {
        var id = $(this).parents().parents().children(".item_no").text();
        var type = $(this).parents().parents().children().children("#item_type").val().trim();
        var SequenceNum = $(this).parents().parents().children(".item_sequenceNum").text();
        function ArticleType() {
            this.id = id;
            this.type = type;
            this.SequenceNum = SequenceNum;
        }
        var articleType = new ArticleType();
        if(type!=""){
            $.ajax({
                type: 'POST',
                url: '/articleType/updateType',
                // data : {articleType: JSON.stringify(articleType)},
                data: {id:id,type:type,SequenceNum:SequenceNum},
                cache: false,
                success: function (data) {
                    if (data == 1){
                        window.location.reload();
                    }else {
                        alert("修改失败");
                    }
                }
            });
        }else {
            alert("输入不能为空");
        }

    });


    //删除操作
    $(document).on("click",'.delete',function () {
        if(confirm("是否决定删除?")){
            var no=$(this).parent().parent().parent().children(".panel-body").text();
            $.ajax({
                type:'delete',
                url:'/article/'+no,
                success:function () {
                    $(".modular").trigger('click');
                },
                error:function () {
                    alert("删除失败");
                }
            });
        }

    });

    //编辑推文
    $(document).on('click','.edit',function () {
        var no=$(this).parent().parent().parent().children(".panel-body").text();
        $(this).attr('href',"/jsp/editArticle?no="+no);
    });

    //添加新的分类，仅超级管理员可用
    $(".new").on('click',function () {
       let newType = $.trim($("#new_type").val());
       if (newType!=null&&newType!="") {
           $.ajax({
               type:"post",
               url:"/articleType/add",
               data:{TypeNew:newType},
               success:function (data) {
                   if(data==1){
                       alert("添加成功");
                       window.location.reload();
                   }else {
                       alert("权限不足")
                   }
               }
           });
       }else {
           alert("添加类型不能为空");
       }

    });


    //删除分类，仅超级管理员可用
    $(document).on('click','#del',function () {
        var delType = $(this).parents().parents().children(".item_type").text();
        if (delType!=null&&delType!="") {
            $.ajax({
                type:"post",
                url:"/articleType/deleteArticleType",
                data:{type:delType},
                success:function (data) {
                    alert(data["msg"]);
                    window.location.reload();
                }
            });
        }
    });


    //查询文章类型
    $("#query_classification").on('click',function () {
        $.ajax({
            type : "POST",
            url: "/articleType",
            // dataType:'json',
            success :function (data) {
                $("#query_articleList").children().remove();
                $.each(data,function (index,item) {
                    $("#query_articleList").append("<li><a href='#' class='modular' id='"+item.id+"'>"+item.type+"</a></li>");
                });
            }
        });
    });

    //点击下拉菜单中的元素，请求对应数据
    $(document).on('click','.modular',function () {
        var data1 = {type:$(this).attr("id")};
        console.log(data1);
        $.ajax({
            type : "POST",
            url: "/article/type",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:data1,
            success :function (data) {
                $("#articles").children().remove();
                $.each(data, function(index, item){//遍历json中每一个单元
                    $("#articles").append(         //添加新元素（具体内容不重要）
                        "<li> " +
                        "<div class='panel panel-default'>"+
                        "<div class='panel-heading'>"+item.title+"</div>"+
                        "<div class='panel-body' id='no' hidden>"+item.no+"</div>"+
                        "<div>作者："+item.manager.name+" 区域："+item.area.school.name+item.area.name+" 类型："+item.articleType.type+"</div>"+
                        "<div class='panel-footer'><span>点赞数"+item.likeNum+"</span><span>阅读数"+item.readNum+"</span>"+
                        "<div class='manage'>"+
                        "<div class='delete'></div>"+
                        "<a target='mainframe' class='edit' href='/jsp/editArticle'></a>"+
                        "<div class='up'><div class=\"dropdown\">\n" +
                        "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" >\n" +
                        "            置顶权限\n" +
                        "            <b class=\"caret\"></b>\n" +
                        "        </a>\n" +
                        "        <ul class=\"dropdown-menu\" id=\"level\">\n" +
                        "<li>1</li><li>2</li><li>3</li><li>4</li> \n" +
                        "        </ul>\n" +
                        "    </div></div>"+
                        "</div>"+
                        "</div>"+
                        "</li>"
                    )
                });
            },
            error:function (data) {
                //stringify()用于从一个对象解析出字符串
                $("#articles").children().remove();
                //JSON.parse用于从一个字符串中解析出json对象
                $.each(data, function(index, item){//遍历json中每一个单元
                    $("#articles").append(         //添加新元素（具体内容不重要）
                        "<li> " +
                        "<div class='panel panel-default'>"+
                        "<div class='panel-heading'>"+item.title+"</div>"+
                        "<div class='panel-body' id='no' hidden>"+item.no+"</div>"+
                        // "<div>作者："+item.manager.name+" 区域："+item.manager.area.name+" 类型："+item.articleType+"</div>"+
                        "<div class='panel-footer'><span>点赞数"+item.likeNum+"</span><span>阅读数"+item.readNum+"</span>"+
                        "<div class='manage'>"+
                        "<div class='delete'></div>"+
                        "<a target='mainframe' class='edit' href='/jsp/editArticle'></a>"+
                        "<div class='up'><div class=\"dropdown\">\n" +
                        "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" >\n" +
                        "            置顶权限\n" +
                        "            <b class=\"caret\"></b>\n" +
                        "        </a>\n" +
                        "        <ul class=\"dropdown-menu\" id=\"level\">\n" +
                        "<li>1</li><li>2</li><li>3</li><li>4</li> \n" +
                        "        </ul>\n" +
                        "    </div></div>"+
                        "</div>"+
                        "</div>"+
                        "</li>"
                    )
                });
            }
        });
    });

    //修改权限
    $(document).on('click','#level li',function () {
        if(confirm("是否决定修改置顶?")){
            $.ajax({
                type:"POST",
                url:"/article/setTop",
                data:{no:$("#no").text(),top:$(this).text()},
                success:function (data) {
                    alert(data["msg"]);
                }
            });
        };
    });

});

function ReadLikeNumber(){
    var date = new Date();
    var time = date.getFullYear()+"-";
    time +=(date.getMonth()+1)+"-";
    time+= (date.getDate()+1);
    $.ajax({
        url:"/dayTotal/all",
        type:"post",
        data:{time:time},
        async:false,
        success:function(data){
            $("#readNum").text(data.readNum);
            $("#likeNum").text(data.likeNum);
        }
    });
}

function getArticleType() {
    $.ajax({
        type : "POST",
        url: "/articleType",
        success :function (data) {
            $("#type").children().remove();
            $.each(data,function (index,item) {
                $("#types").append("<tr>\n" +
                    "            <td class=\"item_type\">"+item.type+"</td>\n" +
                    "            <td class=\"item_no\" hidden>"+item.id+"</td>\n" +
                    "            <td class=\"item_sequenceNum\">"+item.sequenceNum+"</td>\n" +
                    "            <td><input type=\"text\" class=\"input-sm\" placeholder=\"请输入想要修改的顺序\" id=\"sort\"></td>\n" +
                    "            <td><input type=\"text\" class=\"input-sm\" placeholder=\"请输入想要修改的名称\" id=\"item_type\"></td>\n" +
                    "            <td><button class=\"btn btn-primary\" id='edit_name'>修改名称</button><button class=\"btn btn-warning\" id='edit_sort'>修改顺序</button><button class=\"btn btn-danger\" id=\"del\">删除分类</button></td>\n" +
                    "        </tr>");
            });
        }
    });
}