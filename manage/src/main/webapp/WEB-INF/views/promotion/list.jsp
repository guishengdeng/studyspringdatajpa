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
                        活动发布
                    </a>
                </li>

                <li>
                    <a href="/promotions.do">
                        活动列表
                    </a>
                </li>
            </ul>
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                活动发布
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/promotions/new.do" class="btn btn-sm btn-primary"><i
                                             class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增活动
                                </a>
                                <a href="/" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>
                                        <h4>活动标题</h4>
                                    </th>
                                    <th>
                                        <h4>活动链接</h4>
                                    </th>
                                    <th>
                                        <h4>活动图片</h4>
                                    </th>
                                    <th>
                                        <h4>创建人</h4>
                                    </th>
                                    <th>
                                        <h4>创建日期</h4>
                                    </th>
                                    <th>
                                        <h4>显示顺序</h4>
                                    </th>
                                    <th>
                                        <h4>操作</h4>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="promotions" items="${promotions}">
                                    <tr>
                                        <td>${promotions.title}</td>
                                        <td>${promotions.url}</td>
                                        <td>
                                            <img src="${promotions.logo}" alt="${promotion.title}">
                                        </td>
                                        <td>${promotions.adminId}</td>
                                        <td>${promotions.createTime}</td>
                                        <td>${promotions.idx}</td>
                                        <td>
                                            <a class="btn btn-xs btn-info"
                                               href="${pageContext.request.contextPath}/promotions/${promotions.id}.do">
                                                <i class="icon-edit bigger-120"></i>
                                                <span>修改</span></a>&nbsp;
                                            <a class="btn btn-xs btn-danger"
                                               href="${pageContext.request.contextPath}/promotions/delete.do?id=${promotions.id}">
                                                <i class="icon-edit bigger-120"></i>
                                                <span>禁用</span></a>
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
