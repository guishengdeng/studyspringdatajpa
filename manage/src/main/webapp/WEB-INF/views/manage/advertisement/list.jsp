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
            function showDiv(id) {
                $("#cat-disable-confirm-modal").modal();
                $("#advertisementId").val(id);
            }
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var advertisementId = $("#advertisementId").val();
                window.location.href = "/advertisement/delete?id="+advertisementId+"";
                $("#cat-disable-confirm-modal").modal("hide");
            });
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
                <li>
                    广告管理
                </li>
                <li class="active">
                    广告列表
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
                                    <a href="advertisement/add.do" class="btn btn-sm btn-primary"><i
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
                                    <th class="center">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${advertisements}" var="advertisement">

                                    <tr>
                                        <td>${advertisement.picturesLink}</td>
                                        <td>${advertisement.clickLink}</td>
                                        <td><fmt:formatDate value="${advertisement.beginTimestamp}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                        <td><fmt:formatDate value="${advertisement.endTimestamp}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                        <td>
                                            <c:out value="${advertisement.residenceTime}"></c:out></td>
                                        <td>${advertisement.priority}</td>
                                        <td>
                                            <div>
                                                <a href="advertisement/edit?id=${advertisement.id}" class="btn btn-xs btn-info">
                                                    <i class="ace-icon fa fa-pencil bigger-120">修改</i>
                                                </a>
                                                <a onclick="showDiv('${advertisement.id}')" class="btn btn-xs btn-danger">
                                                    <i class="ace-icon fa fa-ban bigger-120">删除</i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <button type="button" class="bootbox-close-button close"
                                                    data-dismiss="modal" aria-hidden="true">×
                                            </button>
                                            <div class="bootbox-body">您确定要删除广告<span
                                                    id="name-of-ban-cat"></span> ?
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-cancel-ban btn-default">
                                                取消
                                            </button>
                                            <button type="button" class="btn btn-confirm-ban btn-primary">
                                                确认
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
        <input type="hidden" id="advertisementId">
    </jsp:body>
</depotnextdoor:page>