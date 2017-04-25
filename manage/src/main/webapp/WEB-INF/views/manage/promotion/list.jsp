<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
                var obj${status.count} = document.getElementById('roleId_${role.id}');
                if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
            function showDiv(id) {
                $("#cat-disable-confirm-modal").modal();
                $("#promotionsId").val(id);
            }
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var promotionsId = $("#promotionsId").val();
                window.location.href = "${pageContext.request.contextPath}/promotions/delete?id="+promotionsId+"";
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    活动管理
                </li>
                <li class="active">
                    活动列表
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
                                <a href="promotions/new.do" class="btn btn-sm btn-primary"><i
                                             class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增活动
                                </a>
                            </span>
                            </h3>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>活动标题</th>
                                    <th>活动链接</th>
                                    <th>活动图片</th>
                                    <th>创建人</th>
                                    <th>创建日期</th>
                                    <th>显示顺序</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="promotions" items="${promotions}">
                                    <tr>
                                        <td>${promotions.title}</td>
                                        <td>${promotions.url}</td>
                                        <td>
                                            <img id="image" src="${imgURL}${promotions.logo}" width="100px" height="100px">
                                            <input name="logo" type="hidden" id="logo_container"
                                                   value="${promotions.logo}" class="form-control required">
                                        </td>
                                        <td>${promotions.adminId}</td>
                                        <td><fmt:formatDate value="${promotions.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                                        <td>${promotions.idx}</td>
                                        <td>
                                            <a class="btn btn-xs btn-info"
                                               href="${pageContext.request.contextPath}/promotions/${promotions.id}.do">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                <span>修改</span></a>&nbsp;
                                                <%----%>
                                            <a class="btn btn-xs btn-danger"
                                               onclick="showDiv('${promotions.id}')">
                                                <i class="ace-icon fa fa-ban bigger-120"></i>
                                                <span>禁用</span></a>
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
                                            <div class="bootbox-body">您确定要禁用活动<span
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
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
        <input type="hidden" id="promotionsId">
    </jsp:body>
</depotnextdoor:page>
