<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>OAuth2.0网页授权</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="" rel="stylesheet">
    <!-- 这里要替换成你自己的BootStrap引入方式 -->
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--<script type="text/javascript" src="../../js/self_detail.js"></script>-->
    <link rel="stylesheet" type="text/css" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
</head>
<body>
<c:choose>
    <c:when test="${subscribe==0}">
        <ul class="media-list">
            <li class="media">
                <div class="media-body" style="margin:auto;text-align: center;">
                    <h4 class="media-heading" style="margin-top: 5px;margin-bottom: 10px;">请先关注公众号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;扫一扫下方二维码关注</h4>
                    <p>openId：${curUser.openId}</p>
                    <p>关注状态：未关注</p>
                </div>
                <div class="thumbnail">
                    <img src = "<%=basePath%>/image/0.jpg" class="media-object">
                </div>
            </li>
        </ul>
    </c:when>
    <c:when test="${subscribe==1}">
        <ul class="media-list">
            <li class="media">
                <div class="pull-left">
                    <div class="thumbnail">
                        <img src=${curUser.headimgurl} class="media-object">
                    </div>
                </div>

                <div class="media-body">
                    <h4 class="media-heading" style="margin-top: 5px;margin-bottom: 10px;">昵称：${curUser.nicknameStr}</h4>
                    <p>openId：${curUser.openId}</p>
                    <p>
                        <c:choose>
                            <c:when test="${curUser.gender==2}">
                                性别：女
                                <br />
                            </c:when>
                            <c:when test="${curUser.gender==1}">
                                性别：男
                                <br />
                            </c:when>
                            <c:otherwise>
                                性别：未知
                                <br />
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p>国家：${curUser.country}&nbsp;&nbsp;&nbsp;地区：${curUser.province}&nbsp;&nbsp;${curUser.city}</p>
                </div>
            </li>
        </ul>
    </c:when>
</c:choose>

</body>
</html>
