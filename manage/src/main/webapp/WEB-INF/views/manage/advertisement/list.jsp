<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.list">
    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
        </script>
    </jsp:attribute>
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
                    广告管理
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                    启动页面广告管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="manage/advertisement/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>图片链接</th>
                                    <th>点击链接</th>
                                    <th class="hidden-md hidden-sm hidden-xs">广告生效时间</th>
                                    <th class="hidden-md hidden-sm hidden-xs">广告过期时间</th>
                                    <th class="hidden-md hidden-sm hidden-xs">停留(毫秒)</th>
                                    <th class="hidden-md hidden-sm hidden-xs">优先级</th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${advertisements}" var="advertisement">

                                    <tr>
                                        <td>${advertisement.picturesLink}</td>
                                        <td>${advertisement.clickLink}</td>
                                        <td><fmt:formatDate value="${advertisement.beginTimestamp}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                        <td><fmt:formatDate value="${advertisement.endTimestamp}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                        <td>${advertisement.residenceTime}</td>
                                        <td>${advertisement.priority}</td>
                                        <td>
                                            <div>
                                                <sec:authorize access="hasAuthority('OPT_ADVERTISEMENTS_DELETE')">
                                                    <a href="manage/advertisement/delete?id=${advertisement.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-trash-o bigger-120">删除</i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize access="hasAuthority('OPT_CAT_EDIT')">
                                                    <a href="manage/advertisement/edit?id=${advertisement.id}" class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                                    </a>
                                                </sec:authorize>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                </div>
            </div>
        </div>
        <!-- PAGE CONTENT ENDS -->
        </div>
        <!-- /.col -->
        </div>
        <!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>