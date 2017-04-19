<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="page.user.edit">
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
                    <a href="">
                        升级配置
                    </a>
                </li>

                <li>
                    <a href="">
                        版本列表
                    </a>
                </li>
               <%-- <li class="active">
                    <c:out value="${cmd}"/>
                </li>--%>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                升级配置
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/upgrade/add.do" class="btn btn-sm btn-primary"><i
                                             class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增升级配置
                                </a>
                                <a href="/" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>AndroId:
                            <c:if test="${not empty androId}">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>版本号</th>
                                    <th>类型</th>
                                    <th>是否抢版</th>
                                    <th>是否强制升级</th>
                                    <th>URL</th>
                                    <th>MD5</th>
                                    <th>升级说明</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${androId}" var="upgrade" varStatus="status">
                                <tr>
                                    <td>${upgrade.version}</td>
                                    <td>${upgrade.os}</td>
                                    <td>${upgrade.inhourse}</td>
                                    <td>${upgrade.force}</td>
                                    <td>${upgrade.url}</td>
                                    <td>${upgrade.md5}</td>
                                    <td><pre>${upgrade.info}</pre></td>
                                    <td>
                                        <a class="btn btn-xs btn-danger" href="/upgrade/delete.do?id=${upgrade.id}">
                                            <i class="icon-trash bigger-120"></i>删除
                                        </a>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </c:if><br>
                            IOS:
                            <c:if test="${not empty ios}">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>版本号</th>
                                    <th>类型</th>
                                    <th>是否抢版</th>
                                    <th>是否强制升级</th>
                                    <th>URL</th>
                                    <th>MD5</th>
                                    <th>升级说明</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ios}" var="upgrade" varStatus="status">
                                    <tr>
                                        <td>${upgrade.version}</td>
                                        <td>${upgrade.os}</td>
                                        <td>${upgrade.inhourse}</td>
                                        <td>${upgrade.force}</td>
                                        <td>${upgrade.url}</td>
                                        <td>${upgrade.md5}</td>
                                        <td><pre>${upgrade.info}</pre></td>
                                        <td>
                                            <a class="btn btn-xs btn-danger" href="/upgrade/delete.do?id=${upgrade.id}">
                                                <i class="icon-trash bigger-120"></i>删除
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
