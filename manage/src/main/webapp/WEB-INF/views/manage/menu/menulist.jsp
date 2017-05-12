<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="主菜单" >
    <jsp:attribute name="script">
        <script type="application/javascript">
        <%--<sec:authorize access="hasRole('OPT_MAINMENU_DELETE')"--%>
            //这是点击删除按钮时触发的事件
            $(".mainmenu-ban-btn").click(function () {
                var $id=$(this).data("id");//获得data-id的属性值
                $("#id-of-mainmenu").val($id);
                $("#name-of-ban-mainmenu").html($(this).data("name"));
                $("#mainmenu-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#mainmenu-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var mainmenu_id = $("#id-of-mainmenu").val();
               /* $.ajax({
                     type:"POST",
                     url:"../manage/mainMenus/delete.do"
                     data:{"id":mainmenu_id},
                     success:function(data){

                     },error:function(){

                    }
                });*/
                $.post("manage/mainMenus/delete.do", {
                    "id": mainmenu_id
                }, function (result) {
                    console.log(result);
                    if (result) {
                        //这就是逻辑删除
                        $("#tr-" + mainmenu_id).remove();
                    }
                }, "json");
                $("#mainmenu-disable-confirm-modal").modal("hide");
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
                        菜单管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-mainmenu">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                        菜单列表
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
                                    <th class="hidden-md hidden-sm hidden-xs">菜单名称</th>
                                    <th>公司类型</th>
                                    <th>代码</th>
                                    <th class="hidden-md hidden-sm hidden-xs">菜单描述</th>
                                    <th class="hidden-md hidden-sm hidden-xs">子菜单管理</th>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${mainMenus}" var="mainMenu">
                                    <tr id="tr-${mainMenu.id}">
                                        <td class="hidden-md hidden-sm hidden-xs">${mainMenu.name}</td>
                                        <td>${mainMenu.companyType}</td>
                                        <td>${mainMenu.code}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${mainMenu.description}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">

                                                <a href="/manage/mainMenus/detail.do?id=${mainMenu.id}">子菜单管理</a>

                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_DELETE')">
                                                    <a  data-id="${mainMenu.id}"
                                                        data-name="${mainMenu.name}"
                                                        <%--href="manage/mainMenus/delete?id=${mainMenu.id}"--%>
                                                        class="btn btn-minier btn-danger mainmenu-ban-btn">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                    <%--修改操作--%>
                                                <sec:authorize access="hasAuthority('OPT_MAINMENU_EDIT')">
                                                    <a href="manage/mainMenus/edit?id=${mainMenu.id}"
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
                    <%--做删除修改时的模态框--%>
                    <div id="mainmenu-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除当前记录<span
                                            id="name-of-ban-mainmenu"></span> ?
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

