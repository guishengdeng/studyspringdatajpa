<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="page.user.list" >
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome">
                         首页
                    </a>
                </li>
                <li class="active">
                        菜单管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <c:choose>
                                    <c:when test="${param.enabled == 'false'}">
                                        禁用
                                    </c:when>
                                    <c:otherwise>
                                        正常
                                    </c:otherwise>
                                </c:choose>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_MAINMENU_ADD')">
                                    <a href="manage/mainMenus/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </sec:authorize>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>代码</th>
                                    <th>公司类型</th>
                                    <th class="hidden-md hidden-sm hidden-xs">菜单名称</th>
                                    <th class="hidden-md hidden-sm hidden-xs">菜单描述</th>
                                    <th class="hidden-md hidden-sm hidden-xs">子菜单管理</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${mainMenus}" var="mainMenu">
                                    <tr id="tr-${mainMenu.name}">

                                        <td>${mainMenu.code}</td>
                                        <td>${mainMenu.companyType}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${mainMenu.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${mainMenu.description}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">

                                                <a href="/manage/mainMenus/detail.do?id=${mainMenu.id}">子菜单管理</a>

                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_DELETE')">
                                                    <a href="manage/mainMenus/delete?id=${mainMenu.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-trash-o bigger-120">删除</i>
                                                    </a>
                                                </sec:authorize>
                                                    <%--修改操作--%>
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_EDIT')">
                                                    <a href="manage/mainMenus/edit?id=${mainMenu.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                                    </a>
                                                </sec:authorize>
                                                    <%--<sec:authorize access="hasAuthority('OPT_USER_DELETE')">
                                                        <c:if test="${param.enabled != 'false'}">
                                                            <a data-id="${user.username}"
                                                               data-name="${user.username}"
                                                               class="btn btn-minier btn-danger user-ban-btn">
                                                                <i class="ace-icon fa fa-ban bigger-120"></i>
                                                            </a>
                                                        </c:if>
                                                    </sec:authorize>--%>
                                            </div>
                                        </td>

                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>

