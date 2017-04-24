<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="子菜单列表">
     <jsp:attribute name="script">
        <script type="application/javascript">
          <%-- <sec:authorize access="hasRole('OPT_MENUITEM_DELETE')"> --%>
                $(".menuitem-ban-btn").click(function () {
                    //为隐藏的input标签赋值
                    $("#id-of-menuitem").val($(this).data("id"));
                    $("#name-of-ban-menuitem").html($(this).data("name"));
                    $("#menuitem-disable-confirm-modal").modal();
                });
                $(".btn-cancel-ban").click(function () {
                    $("#menuitem-disable-confirm-modal").modal("hide");
                });

                $(".btn-confirm-ban").click(function () {
                    var $menuitem_id = $("#id-of-menuitem").val();
                    $.post("manage/menuItems/delete.do", {
                        "id": $menuitem_id
                    }, function (result) {
                        console.log(result);
                        if (result) {
                            $("#tr-" + $menuitem_id).remove();
                        }
                    }, "json");
                    $("#menuitem-disable-confirm-modal").modal("hide");
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
                    子菜单管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">代码</div>

                <div class="profile-info-value">
                    <span class="editable" id="code">${mainMenu.code}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name">公司类型</div>

                <div class="profile-info-value">

                    <span class="editable" id="companyType">${mainMenu.companyType}</span>

                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 描述 </div>

                <div class="profile-info-value">
                    <span class="editable" id="description">${mainMenu.description}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 图标 </div>

                <div class="profile-info-value">
                    <span class="editable" id="icon">${mainMenu.icon}</span>
                </div>
            </div>

            <div class="profile-info-row">
                <div class="profile-info-name"> 名称 </div>

                <div class="profile-info-value">
                    <span class="editable" id="name">${mainMenu.name}</span>
                </div>
            </div>

        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-menuitem">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                        子菜单列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_MAINMENU_ADD')">
                                    <a href="manage/menuItems/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </sec:authorize>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>描述</th>
                                    <th>图标</th>
                                    <th class="hidden-md hidden-sm hidden-xs">链接</th>
                                    <th class="hidden-md hidden-sm hidden-xs">名称</th>
                                    <th class="hidden-md hidden-sm hidden-xs">权限</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作列表</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${menuItems}" var="menuItem" varStatus="status">
                                    <tr id="tr-${menuItem.id}">

                                        <td>${menuItem.description}</td>
                                        <td>${menuItem.icon}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${menuItem.link}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${menuItem.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <a href="/manage/menuItems/detail.do?id=${menuItem.id}">操作管理</a>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_DELETE')">
                                                    <a data-id="${menuItem.id}"
                                                       data-name="${menuItem.name}"
                                                      <%-- href="manage/menuItems/delete.do?id=${menuItem.id}"--%>
                                                       class="btn btn-minier btn-danger menuitem-ban-btn">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                    <%--修改操作--%>
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_EDIT')">
                                                    <a href="manage/menuItems/edit?id=${menuItem.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
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
                        <%--做删除修改时的模态框--%>
                    <div id="menuitem-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除当前记录<span
                                            id="name-of-ban-menuitem"></span> ?
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

