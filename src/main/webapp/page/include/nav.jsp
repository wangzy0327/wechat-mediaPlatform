<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <%--<a class="navbar-brand" href="home.html"><i class="fa fa-coffee"></i> CRM</a>--%>
        <a class="navbar-brand" href="home.html"><i class="fa fa-coffee"></i>&nbsp;后台管理</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">

        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-gear fa-fw"></i>&nbsp;设置</a>
                </li>
                <li class="divider"></li>
                <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i>&nbsp;安全退出</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links 顶部导航栏结束-->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">

            <ul class="nav" id="side-menu">
                <li>
                    <%--<a href="/home"><i class="fa fa-dashboard fa-fw"></i> 首页</a>--%>
                </li>
                <li>
                    <a href="/customer"><i class="fa fa-users fa-fw"></i>&nbsp;客户</a>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <%--<a href="/progress"><i class="fa fa-table fa-fw"></i> 跟进</a>--%>
                </li>
                <li>
                    <%--<a href="/task"><i class="fa fa-edit fa-fw"></i> 待办</a>--%>
                </li>
                <li>
                    <%--<a href="chart.html"><i class="fa fa-bar-chart"></i> 统计</a>--%>
                        <a href="#manage" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-bar-chart"></i>&nbsp;JavaScript</a>
                        <ul id="manage" class="nav nav-list collapse">
                            <li ><a href="#"><i class="fa fa-edit fa-fw"></i>&nbsp;jQuery</a></li>
                            <li ><a href="#"><i class="fa fa-table fa-fw"></i>&nbsp;Vue</a></li>
                            <li ><a href="#"><i class="fa fa-dashboard fa-fw"></i>&nbsp;NodeJS</a></li>
                        </ul>
                    <!-- /.nav-second-level -->
                </li>
                <%--<shiro:hasRole name="管理员">--%>
                    <li>
                        <a href="#message" class="nav-header side-nav-item item-title collapsed" data-toggle="collapse"><i class="fa fa-sitemap fa-fw"></i> 消息管理</a>
                        <ul id="message" class="nav nav-list collapse">
                            <li ><a href="/wechat-tools/backend/wxItem/list"><i class="fa fa-edit fa-fw"></i>&nbsp;图文管理</a></li>
                            <li ><a href="/wechat-tools/backend/wxItem/categoryList"><i class="fa fa-table fa-fw"></i>&nbsp;消息分类</a></li>
                            <li ><a href="/wechat-tools/backend/wxItem/delList"><i class="fa fas fa-recycle fa-fw"></i>&nbsp;消息回收</a></li>
                        </ul>
                        <%--<a href="/backend"><i class="fa fa-sitemap fa-fw"></i> 消息管理</a>--%>

                        <%--<i class="fas fa-recycle"></i>--%>
                    </li>
                <%--</shiro:hasRole>--%>
            </ul>

        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
