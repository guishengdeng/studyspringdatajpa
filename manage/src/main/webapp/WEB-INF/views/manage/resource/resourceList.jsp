<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="资源列表" >
    <jsp:attribute name="script">
        <script type="application/javascript">
            <%--<sec:authorize access="hasRole('OPT_MAINMENU_DELETE')"--%>
            //这是点击删除按钮时触发的事件
            $(".resource-ban-btn").click(function () {
                var $id=$(this).data("id");//获得data-id的属性值
                $("#id-of-resource").val($id);
                $("#name-of-ban-resource").html($(this).data("name"));
                $("#resource-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#resource-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var resource_id = $("#id-of-resource").val();
                $.post("manage/resources/delete.do", {
                    "id": resource_id
                }, function (result) {
                    console.log(result);
                    if (result) {
                        //这是进行逻辑删除
                        $("#tr-" + resource_id).remove();
                    }
                }, "json");
                $("#resource-disable-confirm-modal").modal("hide");
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
                    <a href="/manage/mainMenus/detail.do?id=${mainmenu_id}">
                        菜单管理
                    </a>
                </li>
                <li class="active">
                    <a href="/manage/menuItems/detail.do?id=${menuitem_id}">
                        子菜单管理
                    </a>
                </li>
                <li class="active">
                    操作管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <%----%>
        <div class="profile-user-info profile-user-info-striped">

            <div class="profile-info-row">
                <div class="profile-info-name"> 描述 </div>

                <div class="profile-info-value">
                    <span class="editable" id="age">${menuItem.description}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 图标 </div>

                <div class="profile-info-value">
                    <span class="editable" id="signup">${menuItem.icon}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 链接 </div>

                <div class="profile-info-value">
                    <span class="editable" id="link">${menuItem.link}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 名称 </div>

                <div class="profile-info-value">
                    <span class="editable" id="login">${menuItem.name}</span>
                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 权限 </div>

                <div class="profile-info-value">
                    <span class="editable" id="symbol">${menuItem.symbol}</span>
                </div>
            </div>

        </div>
        <div class="page-content">
            <input type="hidden" id="id-of-resource">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                               资源列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_MAINMENU_ADD')">
                                    <a href="manage/resources/add.do?menuItem_id=${menuitem_id}" class="btn btn-sm btn-primary"><i
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
                                        <th class="hidden-md hidden-sm hidden-xs">权限</th>
                                        <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                    </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${resources}" var="resource" varStatus="status">
                                    <tr id="tr-${resource.id}">
                                        <td>${resource.description}</td>
                                        <td>${resource.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_RESOURCE_DELETE')">
                                                    <a data-id="${resource.id}"
                                                       data-name="${resource.name}"
                                                       class="btn btn-minier btn-danger resource-ban-btn">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                    <%--修改操作--%>
                                                <sec:authorize access="hasAuthority('OPT_RESOURCE_EDIT')">
                                                    <a href="manage/resources/edit?id=${resource.id}"
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
                        <%--做删除时的模态框--%>
                    <div id="resource-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除当前记录<span
                                            id="name-of-ban-resource"></span> ?
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



