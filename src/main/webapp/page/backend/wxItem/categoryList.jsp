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
    <link rel="stylesheet" href="/wechat-tools/css/bootstrap-dialog.min.css">
    <link href="/wechat-tools/css/bootstrapValidator.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <%@ include file="../../include/nav.jsp"%>
    <%--<link rel="import" href="../include/nav.jsp" id="page1">--%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">消息分类</h4>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            消息分类列表
                            <a href="javascript:;" id="addNewCategory" class="btn btn-success btn-xs pull-right"><i class="fa fa-plus"></i> 新增</a>
                        </div>
                        <div class="panel-body">
                            <table class="table" id="categoryTable">
                                <thead>
                                <tr>
                                    <%--<th>ID标识</th>--%>
                                    <th>名称</th>
                                    <th width="35%">内容</th>
                                    <th width="20%">创建时间</th>
                                    <th width="20%">最近修改时间</th>
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


<div class="modal fade" id="newCategoryModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加分类</h4>
            </div>
            <div class="modal-body">
                <form id="newCategoryForm" class="form-horizontal" role="form" data-toggle="validator">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类别名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="name"
                                   data-bv-notempty="true"
                                   data-bv-notempty-message="类别名不能为空"
                                   data-bv-stringlength="true"
                                   data-bv-stringlength-max="20"
                                   data-bv-stringlength-message="类别名长度必须小于20个字符"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                            <%--<input type="<textarea name=" " id="" cols="30" rows="10"></textarea>" class="form-control" name="content"/>--%>
                            <textarea class="form-control" name="content"  cols="30" rows="10" placeholder="请输入类别内容"
                                      data-bv-stringlength="true"
                                      data-bv-stringlength-max="200"
                                      data-bv-stringlength-message="内容长度必须小于200个字符"></textarea>
                        </div>
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10" >
                            <p class="mt8" style="margin-top: 8px;">
                                <span style="margin: 0;font-size: 12px;color: #999999;">最多输入200个字符</span>
                            </p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="form-group">
                            <button type="submit" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" id="saveBtn" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="editCategoryModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑分类</h4>
            </div>
            <div class="modal-body">
                <form id="editCategoryForm" class="form-horizontal">
                    <input type="hidden" name="id" id="id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">类别名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="name"
                                   data-bv-notempty="true"
                                   data-bv-notempty-message="类别名不能为空"
                                   data-bv-stringlength="true"
                                   data-bv-stringlength-max="20"
                                   data-bv-stringlength-message="类别名长度必须小于20个字符"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                             <textarea class="form-control" name="content" cols="30" rows="10" placeholder="请输入类别内容"
                                       data-bv-stringlength="true"
                                       data-bv-stringlength-max="200"
                                       data-bv-stringlength-message="内容长度必须小于200个字符"></textarea>
                        </div>
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10" >
                            <p class="mt8" style="margin-top: 8px;">
                                <span style="margin: 0;font-size: 12px;color: #999999;">最多输入200个字符</span>
                            </p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="submit" id="editBtn" class="btn btn-primary">保存</button>
                    </div>
                </form>
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
<script src="/wechat-tools/js/bootstrap-dialog.min.js"></script>
<script src="/wechat-tools/js/bootstrapValidator.min.js"></script>
<script>
    $(function() {
        var dt = $("#categoryTable").DataTable({
            "processing": true, //loding效果
            "serverSide": true, //服务端处理
            "searchDelay": 1000,//搜索延迟
            "order": [[2, 'desc']],//默认排序方式
            "lengthMenu": [5, 10, 25, 50, 100],//每页显示数据条数菜单
            "ajax": {
                url: "/wechat-tools/backend/wxItem/categorys.json", //获取数据的URL
                type: "get" //获取数据的方式
            },
            "columns": [  //返回的JSON中的对象和列的对应关系
                {
                    "data": function (row) {
                        if (row.name.length > 15) {
                            return row.name.substring(0, 15) + "...";
                        } else {
                            return row.name;
                        }
                    }, "name": "name"
                },
                {
                    "data": function (row) {
                        if (row.content.length > 25) {
                            return row.content.substring(0, 25) + "...";
                        } else {
                            if (row.content.length == 0)
                                return "无";
                            return row.content;
                        }
                    }, "name": "content"
                },
//                {"data": "content", "name": "content"},
                {"data": "createTime", "name": "create_time"},
                {"data": "updateTime", "name": "update_time"},
                {
                    "data": function (row) {
                        return  "<a href='javascript:;' class='editLink' data-id='" + row.id + "'>编辑</a> " +
                            "<a href='javascript:;' class='delLink' data-id='" + row.id + "'>删除</a>";
                    }
                }
            ],
            "columnDefs": [ //具体列的定义
                {
                    "targets": [0,1],
                    "searchable": true
                },
                {
                    "targets": [2,3],
                    "orderable": true
                },
                {
                    "targets": [0],
                    "orderable": false
                }
            ],
            "language": {
                "lengthMenu": "显示 _MENU_ 条记录",
                "search": "搜索:",
                "info": "从 _START_ 到 _END_ 共 _TOTAL_ 条记录",
                "processing": "加载中...",
                "zeroRecords": "暂无数据",
                "infoEmpty": "从 0 到 0 共 0 条记录",
                "infoFiltered": "(从 _MAX_ 条记录中读取)",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });
        //添加新消息分类
        $("#addNewCategory").click(function() {
            $("#newCategoryForm")[0].reset();
            $("#newCategoryModal").modal('show');
        });
        function newFormValidator() {
            $('#newCategoryForm').bootstrapValidator().on('success.form.bv',function (e) {
                e.preventDefault();
                var name = ($("input[name = 'name']"))[0].value;
                console.log(name);
                var content = ($("textarea[name = 'content']"))[0].value;
                console.log(content);
//            $("#newCategoryForm").serialize()
                $.ajax({
                    url: "/wechat-tools/backend/wxItem/newCategory",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    // 向后端传递的数据
                    data: JSON.stringify({
                        "name":name,
                        "content":content
                    }),
                    success: function (result) {
                        if("success" == result) {
                            $("#newCategoryForm")[0].reset();
                            $("#newCategoryModal").modal("hide");
                            dt.ajax.reload();
                        }else if("duplicate" == result){
                            BootstrapDialog.alert({title:"提示",message:"该分类已添加，请勿重复添加!"});
                        }else{
                            BootstrapDialog.alert({title:"提示",message:"添加分类异常!"});
                        }
                    },
                    error: () => {
                    console.log("err");
            }
            });
            })
        }
        $("#saveBtn").click(function(){
            //开启验证
            newFormValidator();
        });
        $("#newCategoryModal").on('hidden.bs.modal',function(e){
            //移除上次的校验配置
//            $("#newCategoryForm").data('bootstrapValidator').destroy();
            $('#newCategoryForm').data('bootstrapValidator',null);
            //重新添加校验
            newFormValidator();
        });
        //删除用户
        $(document).delegate(".delLink", "click", function () {
            var id = $(this).attr("data-id");
            BootstrapDialog.confirm({
                title:"删除数据",
                message:"是否要删除该分类?",
//                    btnOKClass: 'btn-warning',
                callback: function (res) {
                    if (res) {
                        $.post("/wechat-tools/backend/wxItem/del", {"id": id}).done(function (result) {
                            if (result.code == 0) {
                                dt.ajax.reload();
                            }else{
                                BootstrapDialog.alert({title:"提示",message:result.msg});
                            }
                        }).fail(function () {
                            BootstrapDialog.alert({title:"提示",message:"删除出现异常"});
                        });
                    }
                }
            });
        });
//                $.post("/wechat-tools/backend/wxItem/del", {"id": id}).done(function (result) {
//                    if (result.code == 0) {
//                        dt.ajax.reload();
//                    }else{
//                        BootstrapDialog.alert(result.msg);
//                    }
//                }).fail(function () {
//                    BootstrapDialog.alert("删除出现异常");
//                });

        //编辑用户
        $(document).delegate(".editLink", "click", function () {
            $("#editCategoryForm")[0].reset();
            var id = $(this).attr("data-id");
            $.get("/wechat-tools/backend/wxItem/category.json", {"id": id}).done(function (result) {
                if (result.code == 0) {
                    var data = result.data;
                    $("input[name='id']").val(data.id);
                    $($("input[name='name']")[1]).val(data.name);
                    $($("textarea[name='content']")[1]).val(data.content);
                } else {
                    BootstrapDialog.alert({title:"提示",message:result.msg});
                }
            }).fail(function () {

            });
            $("#editCategoryModal").modal("show");
        });
        function editFormValidator() {
            $('#editCategoryForm').bootstrapValidator().on('submit',function (e) {
                e.preventDefault();
                var id = $("input[name='id']").val();
                var name = $($("input[name='name']")[1]).val();
                console.log(name);
                var content = $($("textarea[name ='content']")[1]).val();
                console.log(content);
                $.ajax({
                    url: "/wechat-tools/backend/wxItem/editCategory",
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json;charset=UTF-8",
                    // 向后端传递的数据
                    data: JSON.stringify({
                        "id":id,
                        "name":name,
                        "content":content,
                    }),
                    success: function (result) {
                        if("success" == result) {
                            $("#editCategoryForm")[0].reset();
                            $("#editCategoryModal").modal("hide");
                            dt.ajax.reload();
                        }else if("duplicate" == result){
                            BootstrapDialog.alert({title:"提示",message:"该分类已添加，请勿重复添加!"});
                        }
                        else{
                            BootstrapDialog.alert({title:"提示",message:"分类编辑异常!"});
                        }
                    },
                    error: () => {
                    console.log("err");
            }
            });
            });
        }
        $("#editBtn").click(function () {
            editFormValidator();
        });
        $("#editCategoryModal").on('hidden.bs.modal',function(e){
            //移除上次的校验配置
//            $("#editCategoryForm").data('bootstrapValidator').destroy();
            $('#editCategoryForm').data('bootstrapValidator',null);
            //重新添加校验
            newFormValidator();
        });

    });
</script>

</body>

</html>
