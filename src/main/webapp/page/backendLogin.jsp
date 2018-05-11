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

    <title>CRM-系统登录</title>

    <!-- Bootstrap Core CSS -->
    <link href="/wechat-tools/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/wechat-tools/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/wechat-tools/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/wechat-tools/js/metisMenu/metisMenu.min.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-coffee"></i> 后台管理系统登录</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty message}">
                        <div class="alert ${message.status}">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                ${message.msg}
                        </div>
                    </c:if>
                    <form id="loginForm" method="post" action="/wechat-tools/manage/user/login">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="账号" name="username" type="text" id="username" autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" name="password" type="password" id="password" value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">记住用户名
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button id="loginBtn" type="button" class="btn btn-lg btn-success btn-block">进入系统</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/wechat-tools/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/wechat-tools/js/bootstrap.min.js"></script>
<script src="/wechat-tools/js/datatables/media/js/jquery.dataTables.js"></script>
<script src="/wechat-tools/js/datatables/media/js/dataTables.bootstrap.min.js"></script>
<script src="/wechat-tools/js/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="/wechat-tools/js/sb-admin-2.js"></script>

<script>

    $("#loginBtn").click(function(){
        if(!$("#username").val()) {
            $("#username").focus();
            return;
        }
        if(!$("#password").val()) {
            $("#password").focus();
            return;
        }
        $("#loginForm").submit();
    });
</script>


</body>

</html>
