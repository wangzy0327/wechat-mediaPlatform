<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>后台管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/wechat-tools/js/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/wechat-tools/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/wechat-tools/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="/wechat-tools/js/datatables/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/wechat-tools/css/jquery.tagsinput.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <%@ include file="../include/nav.jsp"%>
    <%--<link rel="import" href="../include/nav.jsp" id="page1">--%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">图文管理</h4>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            图文消息列表
                            <a href="javascript:;" id="addNewUser" class="btn btn-success btn-xs pull-right"><i class="fa fa-plus"></i> 新增</a>
                        </div>
                        <div class="panel-body">
                            <table class="table" id="userTable">
                                <thead>
                                <tr>
                                    <%--<th>ID标识</th>--%>
                                    <th>标题</th>
                                    <th>描述</th>
                                    <th width="5%" >分类</th>
                                    <th width="15%">创建时间</th>
                                    <th width="15%">最近修改时间</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td ></td>
                                    <td></td>
                                    <td style="margin: 0 auto"></td>
                                    <td style="margin: 0 auto"></td>
                                    <td style="margin: 0 auto"></td>
                                    <td style="margin: 0 auto"></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


<div class="modal fade" id="newUserModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加图文消息</h4>
            </div>
            <div class="modal-body">
                <form id="newUserForm" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容URL</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="url">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片URL</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="imgUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类别</label>
                        <div class="col-sm-10">
                            <div class="checkbox" id = "category">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tags_1" class="col-sm-2 control-label" >标签</label>
                        <div class="col-sm-10" >
                            <input id="tags_1" type="text" class="form-control"  />
                        </div>
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10" >
                            <p class="mt8" style="margin-top: 8px;">
                                <span style="margin: 0;font-size: 12px;color: #999999;">最多添加3个标签</span>
                            </p>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label for="tags_1" class="col-sm-2 control-label">标签</label>--%>
                    <%--<div class="col-sm-10" >--%>
                    <%--<input  id="tags_1" style="width: 100%;" type="text" class="tags" value="" />--%>
                    <%--<p class="mt8" style="margin-top: 8px;">--%>
                    <%--<span style="margin: 0;font-size: 12px;color: #999999;">最多添加3个标签</span>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="editUserModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form id="editUserForm" class="form-horizontal">
                    <input type="hidden" name="id" id="id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="username" id="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="tel" id="tel">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <div class="checkbox">
                                <label class="radio-inline">
                                    <input type="radio" name="state" value="正常" id="ok"> 正常
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="state" value="禁用" id="disable"> 禁用
                                </label>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="editBtn" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- jQuery -->
<script src="/wechat-tools/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/wechat-tools/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/wechat-tools/js/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/wechat-tools/js/sb-admin-2.js"></script>
<script src="/wechat-tools/js/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="/wechat-tools/js/datatables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/wechat-tools/js/jquery.tagsinput.min.js"></script>
<script>
    $(function(){

        var dt = $("#userTable").DataTable({
            "processing": true, //loding效果
            "serverSide":true, //服务端处理
            "searchDelay": 1000,//搜索延迟
            "order":[[0,'desc']],//默认排序方式
            "lengthMenu":[5,10,25,50,100],//每页显示数据条数菜单
            "ajax":{
                url:"/wechat-tools/backend/items.json", //获取数据的URL
                type:"get" //获取数据的方式
            },
            "columns":[  //返回的JSON中的对象和列的对应关系
//                {"data":"id","name":"id"},
//                {"data":"itemId","name":"item_id"},
                {"data":function(row){
                    if(row.title.length > 15){
                        return row.title.substring(0,15)+"...";
                    }else{
                        return row.title;
                    }
                },"name":"title"},
                {"data":function(row){
                    if(row.description.length > 25){
                        return row.description.substring(0,25)+"...";
                    }else{
                        if(row.description.length == 0)
                            return "无";
                        return row.description;
                    }
                },"name":"description"},
                {"data":function(row){
                    if(row.category.name.length > 10){
                        return row.category.name.substring(0,10)+"...";
                    }else{
                        return row.category.name;
                    }
                },"name":"category"},
                {"data":"createTime","name":"create_time"},
                {"data":"updateTime","name":"update_time"},
                {"data":function(row){
                    return "<a href='javascript:;' class='editLink' data-id='"+row.id+"'>编辑</a> <a href='javascript:;' class='delLink' data-id='"+row.id+"'>删除</a>";
                }}
            ],
            "columnDefs":[ //具体列的定义
                {
                    "targets":[0],
                    "searchable": true
                },
                {
                    "targets":[3,4],
                    "orderable":true
                },
                {
                    "targets":[1,2],
                    "orderable":false
                }
            ],
            "language":{
                "lengthMenu":"显示 _MENU_ 条记录",
                "search":"搜索:",
                "info": "从 _START_ 到 _END_ 共 _TOTAL_ 条记录",
                "processing":"加载中...",
                "zeroRecords":"暂无数据",
                "infoEmpty": "从 0 到 0 共 0 条记录",
                "infoFiltered":"(从 _MAX_ 条记录中读取)",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });
        //添加新用户
        $("#addNewUser").click(function() {
            $.ajax({
                type: "post",
                url: "/wechat-tools/backend/category.json",
                async: true,
                success: function (result) {
                    var code = result.code;
                    var data = result.data;
//                    console.log(data);
                    if (code == 0) {
                        var str = "";
                        for (var i in data) {
//                            console.log(data[i].id);
//                            console.log(data[i].name);
                            str += '<label class="radio-inline">'+
                                '<input type = "radio"  value = '+data[i].id+' name="category.id">' +data[i].name+
                                '</label>';
                        }
                        $("#category").html(str);
                    }
                }
            });
            $("#newUserModal").modal('show');
            $('#tags_1').tagsInput({
                width:'auto',
                onChange: function(){
                    let len = $("#tags_1_tagsinput .tag").length;
                    tags_1_addTag.style.display = "";
                    if(len >= 3){
                        tags_1_addTag.style.display = "none";
                    }
                }
            });
        });
        $("#saveBtn").click(function(){
            var title = $("input[name = 'title']").val();
            if(title.length>30){
                title = title.substring(0,30);
            }
            console.log(title);
            var description = $("input[name = 'description']").val();
            if(title.length>50){
                title = title.substring(0,50);
            }
            console.log(description);
            var url = $("input[name = 'url']").val();
            console.log(url);
            var imgUrl = $("input[name = 'imgUrl']").val();
            console.log(imgUrl);
            var category = {};
            category.id = parseInt($("input:radio:checked").val());
            console.log(category.id);
            var spanTags = $(".tag").children("span");
            console.log(spanTags.length);
            var tags = new Array();
            for(i = 0;i<spanTags.length;i++){
                console.log($.trim($(spanTags[i]).text().substring(0,10)));
                tags.push({name:$.trim($(spanTags[i]).text().substring(0,10))});
            }
//            $("#newUserForm").serialize()
            $.ajax({
//            /wechat-tools/backend/category.json
                url: "/wechat-tools/backend/new",
                type: "POST",
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                // 向后端传递的数据
                data: JSON.stringify({
                    "title":title,
                    "description":description,
                    "url":url,
                    "imgUrl":imgUrl,
                    "category":category,
                    "tags":tags
                }),
                success: function (result) {
                    if("success" == result) {
                        $("#newUserForm")[0].reset();
                        $("#newUserModal").modal("hide");
                        dt.ajax.reload();
                    }else if("duplicate" == result){
                        alert("该图文消息已添加，请勿重复添加!");
                    }
                    else if("fail" == result){
                        alert("url不正确，请检查url!");
                    }
                },
                error: () => {
                console.log("err");
        }
        });
//            $.post("/wechat-tools/backend/new",
//                {   "title":title,
//                    "description":description,
//                    "url":url,
//                    "imgUrl":imgUrl,
//                    "category.id":category,
//                    "tags":tags
//                }).done(function(result){
//                    if("success" == result) {
//                        $("#newUserForm")[0].reset();
//                        $("#newUserModal").modal("hide");
//                        dt.ajax.reload();
//                    }else if("fail" == result){
//                        alert("url不正确，请检查url");
//                    }
//                }).fail(function(){
//                alert("添加时出现异常");
//            });
        });
        //删除用户
        $(document).delegate(".delLink","click",function(){
            var id = $(this).attr("data-id");
            if(confirm("确定要删除该数据吗?")) {
                $.post("/account/del",{"id":id}).done(function(result){
                    if("success" == result) {
                        dt.ajax.reload();
                    }
                }).fail(function(){
                    alert("删除出现异常");
                });

            }
        });

        //编辑用户
        $(document).delegate(".editLink","click",function(){
            $("#editUserForm")[0].reset();
            var id = $(this).attr("data-id");
            $.get("/account/user.json",{"id":id}).done(function(result){
                $("#id").val(result.id);
                $("#username").val(result.username);
                $("#tel").val(result.tel);

                $(".role").each(function(){
                    var roleList = result.roleList;
                    for(var i = 0;i < roleList.length;i++) {
                        var role = roleList[i];
                        if($(this).val() == role.id) {
                            $(this)[0].checked = true;
                        }
                    }
                });

                if(result.state == "正常") {
                    $("#ok")[0].checked = true;
                } else {
                    $("#disable")[0].checked = true;
                }



            }).fail(function(){

            });

            $("#editUserModal").modal("show");
        });

        $("#editBtn").click(function(){

            $.post("/account/edit",$("#editUserForm").serialize()).done(function(result){
                if(result == "success") {
                    $("#editUserModal").modal("hide");
                    dt.ajax.reload();
                }
            }).fail(function(){
                alert("修改用户异常");
            });

        });
    });
</script>

</body>

</html>
