<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CRM-客户关系管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="/wechat-tools/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/wechat-tools/js/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/wechat-tools/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/wechat-tools/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/wechat-tools/js/morris/morris.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--emoji表情-->
    <link href="http://cdn.staticfile.org/emoji/0.2.2/emoji.css" rel="stylesheet" type="text/css" />
    <script src="http://cdn.staticfile.org/emoji/0.2.2/emoji.js"></script>
</head>

<body>

<div id="wrapper">

    <%@ include file="../include/nav.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="page-header">
                        <h4><i class="fa fa-home"></i> 后台管理系统首页</h4>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">用户分布</div>
                        <div class="panel-body">
                            <div id="myfirstchart" style="height: 500px;"></div>
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

<script src="/wechat-tools/js/echarts/echarts.min.js"></script>
<script src="/wechat-tools/js/echarts/china.js"></script>
<script src="/wechat-tools/js/echarts/theme/shine.js"></script>
<script src="/wechat-tools/js/echarts/theme/macarons.js"></script>
<!-- Custom Theme JavaScript -->
<script src="/wechat-tools/page/backend/js/home.js"></script>
<%--<script src="/wechat-tools/js/sb-admin-2.js"></script>--%>
<%--<script src="/wechat-tools/js/morris/raphael-min.js"></script>--%>
<%--<script src="/wechat-tools/js/morris/morris.min.js"></script>--%>
<script>
    $(function(){
        <%--Morris.Bar({--%>
            <%--element: 'myfirstchart',--%>
            <%--data: ${json},--%>
            <%--xkey: 'custtype',--%>
            <%--ykeys: ['total'],--%>
            <%--labels: ['total']--%>
        <%--}).on('click', function(i, row){--%>
            <%--console.log(i, row);--%>
        <%--});--%>
    });
</script>

</body>

</html>