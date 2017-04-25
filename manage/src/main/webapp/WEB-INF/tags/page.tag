<!DOCTYPE html>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="depotnextdoor" uri="http://com.depotnextdoor/tag/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="hideHeader" required="false" type="java.lang.Boolean" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="bodyClasses" required="false" type="java.lang.String" %>
<%@ attribute name="css" fragment="true" %>
<%@ attribute name="script" fragment="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<%--<html manifest="${contextPath}/manifest.appcache?v=0.0.1">--%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <base href="${contextPath}/">
    <title>隔壁仓库 - <c:out value="${title}"/> </title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/css/jquery-ui.custom.min.css" />
    <link rel="stylesheet" href="static-resource/ace/assets/css/chosen.min.css" />


    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/fonts.googleapis.com.css"/>

    <!-- <![jquery-ui.min]-->
    <link rel="stylesheet" href="static-resource/ace/assets/css/jquery-ui.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-part2.min.css"
          class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-ie.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="static-resource/common/css/gbck.css"/>
	<%--<script src="msg.do"></script>--%>
    <!-- inline styles related to this page -->

    <!--[if !IE]> -->
    <script src="static-resource/ace/assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->
    <script src="static-resource/ace/assets/js/ace.min.js"></script>
    <!--[if IE]>
    <script src="staic-resource/ace/assets/js/jquery-1.11.3.min.js"></script>

    <!-- <![endif]-->
    <script src="static-resource/ace/assets/js/jquery-ui.min.js"></script>
    <!-- ace settings handler -->
    <script src="static-resource/ace/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->


    <!--[if lte IE 8]>
    <script src="static-resource/ace/assets/js/html5shiv.min.js"></script>
    <script src="static-resource/ace/assets/js/respond.min.js"></script>
    <![endif]-->


    <!-- <![datepicker]-->
    <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-datepicker3.min.css"/>
    <script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>

    <!-- <![layer]-->
    <link rel="stylesheet" href="static-resource/layer/mobile/need/layer.css"/>
    <script src="static-resource/layer/layer.js"></script>

    <jsp:invoke fragment="css"/>
</head>
<body class="${empty bodyClasses ? 'no-skin' : bodyClasses}">
<c:choose>
    <c:when test="${hideHeader}">
        <jsp:doBody/>
    </c:when>
    <c:otherwise>
        <sec:authentication property="principal.menus" var="menus"/>
        <c:import url="/WEB-INF/views/common/header.jsp"/>
        <div id="main-container" class="main-container ace-save-state">
            <script type="text/javascript">
                try{ace.settings.loadState('main-container')}catch(e){}
            </script>
            <div id="sidebar" class="sidebar responsive ace-save-state">
                <script type="text/javascript">
                    try{ace.settings.loadState('sidebar')}catch(e){}
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                        <button class="btn btn-success">
                            <i class="ace-icon fa fa-signal"></i>
                        </button>

                        <button class="btn btn-info">
                            <i class="ace-icon fa fa-pencil"></i>
                        </button>

                        <button class="btn btn-warning">
                            <i class="ace-icon fa fa-users"></i>
                        </button>

                        <button class="btn btn-danger">
                            <i class="ace-icon fa fa-cogs"></i>
                        </button>
                    </div>

                    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                        <span class="btn btn-success"></span>

                        <span class="btn btn-info"></span>

                        <span class="btn btn-warning"></span>

                        <span class="btn btn-danger"></span>
                    </div>
                </div><!-- /.sidebar-shortcuts -->

                <ul class="nav nav-list">
                    <li>
                        <a href="welcome">
                            <i class="menu-icon fa fa-tachometer"></i>
                            <span class="menu-text"> Dashboard </span>
                        </a>
                        <b class="arrow"></b>
                    </li>
                     <%--这里就是用户登陆成功后,左边显示的选项卡:用户管理，角色管理，菜单管理--%>
                    <c:forEach var="menu" items="${menus}" varStatus="menuStatus">
                        <li class="${_activeMainMenu_ eq menuStatus.index ? 'active open' : ''}">
                            <a href="#" class="dropdown-toggle">
                                <i class="menu-icon ${empty menu.icon ? 'fa fa-list' : menu.icon}"></i>
                                <span class="menu-text"><c:out value="${menu.name}"/></span>
                                <b class="arrow fa fa-angle-down"></b>
                            </a>
                            <b class="arrow"></b>
                            <ul class="submenu">
                                <c:forEach var="child" items="${menu.children}" varStatus="subMenuStatus">
                                    <li class="${_activeMainMenu_ eq menuStatus.index  and _activeSubMenu_ eq subMenuStatus.index ? 'active' : ''}">
                                        <a data-main-menu="${menuStatus.index}" data-sub-menu="${subMenuStatus.index}" data-href="${pageContext.request.contextPath}${child.url}" class="navable-a-tag">
                                            <c:out value="${child.name}"/>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                </ul><!-- /.nav-list -->

                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i id="sidebar-toggle-icon" class="ace-save-state ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>
            <div class="main-content localos-container">
                <script type="text/javascript">
                    try{ace.settings.loadState('main-container')}catch(e){}
                </script>
                <div class="main-content-inner">
                    <div class="page-content">
                        <div class="ace-settings-container" id="ace-settings-container">
                            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                                <i class="ace-icon fa fa-cog bigger-130"></i>
                            </div>

                            <div class="ace-settings-box clearfix" id="ace-settings-box">
                                <div class="pull-left width-50">
                                    <div class="ace-settings-item">
                                        <div class="pull-left">
                                            <select id="skin-colorpicker" class="hide">
                                                <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                                                <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                                <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                                <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                            </select>
                                        </div>
                                        <span>&nbsp; Choose Skin</span>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off">
                                        <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off">
                                        <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off">
                                        <label class="lbl" for="ace-settings-add-container">
                                            Inside
                                            <b>.container</b>
                                        </label>
                                    </div>
                                </div><!-- /.pull-left -->

                                <div class="pull-left width-50">
                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off">
                                        <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off">
                                        <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off">
                                        <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
                                    </div>
                                </div><!-- /.pull-left -->
                            </div><!-- /.ace-settings-box -->
                        </div>
                        <jsp:doBody/>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
<div style="text-align: center;width: 100%;position: fixed;bottom: 0">
    <a target="_blank" href="static-resource/ace/index.html">ACE示例</a>
</div>
</body>
<!-- basic scripts -->

<script type="text/javascript">
    if ('ontouchstart'
        in document.documentElement) document.write("<script src='static-resource/ace/assets/js/jquery.mobile.custom.min.js'>"
        + "<"
        + "/script>");
</script>
<script src="static-resource/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="static-resource/ace/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="static-resource/ace/assets/js/jquery-ui.custom.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.easypiechart.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.sparkline.index.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.flot.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.flot.pie.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.flot.resize.min.js"></script>
<script src="static-resource/ace/assets/js/chosen.jquery.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.dataTables.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.dataTables.bootstrap.min.js"></script>

<!-- ace scripts -->
<script src="static-resource/ace/assets/js/ace-elements.min.js"></script>
<%--<script src="static-resource/ace/assets/js/ace.min.js"></script>--%>

<!-- support for jquery validator -->
<script src="static-resource/ace/assets/js/jquery.validate.min.js"></script>

<!-- support for act beetbox -->
<script src="static-resource/ace/assets/js/bootbox.js"></script>

<script src="static-resource/common/js/gbck.js"></script>

<jsp:invoke fragment="script"/>
</html>
