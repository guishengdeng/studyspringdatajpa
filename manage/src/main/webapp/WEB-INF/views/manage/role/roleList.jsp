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
            <%--<sec:authorize access="hasRole('OPT_USER_DELETE')">--%>
            $(".role-ban-btn").click(function () {
                $("#id-of-role").val($(this).data("id"));
                $("#name-of-ban-role").html($(this).data("name"));
                $("#role-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#role-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var role_id = $("#id-of-role").val();
                $.post("manage/roles/delete.do", {
                       "id": role_id
                    }, function (result) {
                    if (result) {
                          $("#tr-" + role_id).remove();
                     }
                });
                $("#role-disable-confirm-modal").modal("hide");
            });
        <%--</sec:authorize>--%>
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
                    角色管理
                </li>

            </ul><!-- /.breadcrumb -->
        </div>
        <%----%>
        <div class="page-content">
            <input type="hidden" id="id-of-role">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                角色列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <%--当用户用户该权限是才显示该添加按钮--%>
                                <sec:authorize access="hasAuthority('OPT_ROLE_ADD')">
                                    <a href="manage/roles/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </sec:authorize>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>描述</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${roles}" var="role" varStatus="status">
                                    <tr id="tr-${role.id}">
                                        <td>${role.name}</td>
                                        <td>${role.description}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_ROLE_DELETE')">
                                                    <a <%--href="manage/roles/delete?id=${role.id}"--%>
                                                       data-id="${role.id}"
                                                       data-name="${role.name}"
                                                       class="btn btn-minier btn-danger role-ban-btn">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>

                                                <sec:authorize access="hasAuthority('OPT_ROLE_EDIT')">
                                                    <a href="manage/roles/edit?id=${role.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
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
                    <%--点击删除时的模态框--%>
                    <div id="role-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除该记录<span
                                            id="name-of-ban-role"></span> ?
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
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>



