<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            function showDiv(id) {
                $("#cat-disable-confirm-modal").modal();
                $("#upgradeId").val(id);
            }
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var upgradeId = $("#upgradeId").val();
                window.location.href = "/upgrade/delete.do?id="+upgradeId+"";
                $("#cat-disable-confirm-modal").modal("hide");
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
                    APP配置
                </li>
                <li>
                    升级配置
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
                                升级配置
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/upgrade/add.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增升级配置
                                </a>
                            </span>
                            </h3>Android:
                            <c:if test="${not empty android}">
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
                                    <c:forEach items="${android}" var="upgrade" varStatus="status">
                                        <tr>
                                            <td>${upgrade.version}</td>
                                            <td>${upgrade.os}</td>
                                            <td>${upgrade.inhourse}</td>
                                            <td>${upgrade.force}</td>
                                            <td>${upgrade.url}</td>
                                            <td>${upgrade.md5}</td>
                                            <td><pre>${upgrade.info}</pre></td>
                                            <td>
                                                <a class="btn btn-xs btn-danger cat-ban-btn" onclick="showDiv('${upgrade.id}')" >
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>删除
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
                                                <a class="btn btn-xs btn-danger" onclick="showDiv('${upgrade.id}')">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>删除
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <button type="button" class="bootbox-close-button close"
                                                    data-dismiss="modal" aria-hidden="true">×
                                            </button>
                                            <div class="bootbox-body">您确定要删除配置<span
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
                            <!-- PAGE CONTENT ENDS -->
                        </div><!-- /.col -->
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        </div>
        <input type="hidden" id="upgradeId">
    </jsp:body>
</depotnextdoor:page>