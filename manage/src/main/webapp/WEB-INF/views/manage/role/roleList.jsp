<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="page.user.list">
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
                    角色管理
                </li>

            </ul><!-- /.breadcrumb -->
        </div>
        <%----%>
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
                                    <a href="manage/roles/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </sec:authorize>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>描述</th>
                                    <th>名称</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${roles}" var="role" varStatus="status">
                                    <tr id="tr-${role.name}">
                                        <td>${role.description}</td>
                                        <td>${role.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_ROLE_DELETE')">
                                                    <a href="manage/roles/delete?id=${role.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-trash-o bigger-120">删除</i>
                                                    </a>
                                                </sec:authorize>

                                                <sec:authorize access="hasAuthority('OPT_ROLE_EDIT')">
                                                    <a href="manage/roles/edit?id=${role.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                                    </a>
                                                </sec:authorize>

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



