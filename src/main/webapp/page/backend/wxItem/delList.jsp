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
                    <h4 class="page-header">消息回收</h4>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            消息回收列表
                            <%--<a href="javascript:;" id="addNewUser" class="btn btn-success btn-xs pull-right"><i class="fa fa-plus"></i> 新增</a>--%>
                        </div>
                        <div class="panel-body">
                            <table class="table" id="delWxItemTable">
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
        var dt = $("#delWxItemTable").DataTable({
            "processing": true, //loding效果
            "serverSide": true, //服务端处理
            "searchDelay": 1000,//搜索延迟
            "order": [[3, 'desc']],//默认排序方式
            "lengthMenu": [5, 10, 25, 50, 100],//每页显示数据条数菜单
            "ajax": {
                url: "/wechat-tools/backend/wxItem/delItems.json", //获取数据的URL
                type: "get" //获取数据的方式
            },
            "columns": [  //返回的JSON中的对象和列的对应关系
//                {"data":"id","name":"id"},
//                {"data":"itemId","name":"item_id"},
                {
                    "data": function (row) {
                        if (row.title.length > 15) {
                            return row.title.substring(0, 15) + "...";
                        } else {
                            return row.title;
                        }
                    }, "name": "title"
                },
                {
                    "data": function (row) {
                        if (row.description.length > 25) {
                            return row.description.substring(0, 25) + "...";
                        } else {
                            if (row.description.length == 0)
                                return "无";
                            return row.description;
                        }
                    }, "name": "description"
                },
                {
                    "data": function (row) {
                        if (row.category.name.length > 10) {
                            return row.category.name.substring(0, 10) + "...";
                        } else {
                            return row.category.name;
                        }
                    }, "name": "category"
                },
                {"data": "createTime", "name": "create_time"},
                {"data": "updateTime", "name": "update_time"},
                {
                    "data": function (row) {
                        return "<a href='" + row.url + "' target='view_window' class='previewLink' data-id='" + row.id + "'>预览</a> " +
//                            "<a href='javascript:;' class='editLink' data-id='" + row.id + "'>编辑</a> " +
                            "<a href='javascript:;' class='restoreLink' data-id='" + row.id + "'>还原</a> ";
                    }
                }
            ],
            "columnDefs": [ //具体列的定义
                {
                    "targets": [0,2],
                    "searchable": true
                },
                {
                    "targets": [3, 4],
                    "orderable": true
                },
                {
                    "targets": [1,2],
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
        //还原图文消息
        $(document).delegate(".restoreLink", "click", function () {
            var id = $(this).attr("data-id");
            BootstrapDialog.confirm({
                title:"还原数据",
                message:"确定要还原该数据吗?",
//                    btnOKClass: 'btn-warning',
                callback: function (res) {
                    if (res) {
                        $.post("/wechat-tools/backend/wxItem/restore", {"id": id}).done(function (result) {
                            if (result.code == 0) {
                                dt.ajax.reload();
                            }else{
                                BootstrapDialog.alert({title:"提示",message:result.msg});
                            }
                        }).fail(function () {
                            BootstrapDialog.alert({title:"提示",message:"还原出现异常"});
                        });
                    }
                }
            });
        });

    });
</script>

</body>

</html>
