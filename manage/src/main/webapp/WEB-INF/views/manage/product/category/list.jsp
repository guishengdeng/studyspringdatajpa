<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(".category-ban-btn").click(function () {
                $("#id-of-category").val($(this).data("id"));
                $("#name-of-ban-category").html($(this).data("name"));
                $("#category-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#category-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var categoryId = $("#id-of-category").val();
                $.post("manage/categories/delete.do", {
                    "id": categoryId
                }, function (result) {
                    if (result) {
                        $("#tr-" + categoryId).remove();
                    }
                }, "json");
                $("#category-disable-confirm-modal").modal("hide");
            });
            $(function () {
                $("#category-table").DataTable({
                    "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [1, 5], "orderable": false}],
                    "order": [[0, "asc"]]
                });
            });

            /**
             * 将N级外来数据转化为bootstrap treeview可填充的数据
             */
            loopLevel = 0;
            function obj2treeview(resp) {
                var nodeArray = [];
                for (var i = 0; i < resp.length; i++) {
                    var treeViewNodeObj;
                    var textStr = "name";
                    var nodeStr = "children";
                    var id = "id";

                    var subNode;
                    if (resp[i][nodeStr] != undefined) {
                        loopLevel++;
                        subNode = obj2treeview(resp[i][nodeStr]);
                        loopLevel--;
                    }

                    if (subNode != undefined && subNode != null && subNode.length > 0) {
                        treeViewNodeObj = {
                            enableLinks: true,
                            text: resp[i][textStr],
                            nodes: subNode,
                            href: "manage/categories.do?id=" + resp[i][id]
                        };
                    } else {
                        treeViewNodeObj = {
                            enableLinks: true,
                            text: resp[i][textStr],
                            href: "manage/categories.do?id=" + resp[i][id]
                        };
                    }
                    subNode = null;
                    nodeArray.push(treeViewNodeObj);
                }
                return nodeArray;
            }
            $(function () {
                $.ajax({
                    url: 'manage/categories/treeView.do',
                    type: 'POST',
                    dataType: 'json',
                    //成功执行方法
                    success: function (data) {
                        var tree = obj2treeview(data);
                        $("#treeView").treeview({data: tree})
                    }
                })

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
                    商品管理
                </li>
                <li class="active">
                    分类管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <input type="hidden" id="id-of-category">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                分类列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/categories/add.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon glyphicon glyphicon-plus"></i>
                                    添加
                                </a>
                                </span>
                            </h3>
                                <%-- treeview --%>
                            <div class="pull-left" id="treeView">

                            </div>
                            <div class="pull-left col-sm-8">
                                <table id="category-table" class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>排序</th>
                                        <th>名称</th>
                                        <th>创建时间</th>
                                        <th>状态</th>
                                        <th>有无子分类</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${categories}" var="category">
                                        <tr id="tr-${category.id}">
                                            <td>${category.idx}</td>
                                            <td>${category.name}</td>
                                            <td>${category.createTimeStamp}</td>
                                            <td>${category.status eq 'ENABLE' ? '启用' : '禁用'}</td>
                                            <td>${category.showDel eq 'true' ? '无子分类' : '有子分类'}</td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <a href="manage/categories/${category.id}.do"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${category.id}"
                                                           data-name="${category.name}"
                                                           class="btn btn-minier btn-danger category-ban-btn">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div id="category-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除分类&nbsp;<span
                                            id="name-of-ban-category"></span>&nbsp;吗?
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
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>