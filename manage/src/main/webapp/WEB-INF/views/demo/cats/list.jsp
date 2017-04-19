<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="猫">
    <jsp:attribute name="css">
        <style type="text/css">
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <%--<sec:authorize access="hasRole('OPT_CAT_DELETE')">--%>
            $(".cat-ban-btn").click(function () {
                $("#id-of-cat").val($(this).data("id"));
                $("#name-of-ban-cat").html($(this).data("name"));
                $("#cat-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var catId = $("#id-of-cat").val();
                $.post("demo/cats/delete.do", {
                    "id": catId
                }, function (result) {
                    if (result) {
                        $("#tr-" + catId).remove();
                    }
                }, "json");
                $("#cat-disable-confirm-modal").modal("hide");
            });
            <%--</sec:authorize>--%>

            <sec:authorize access="hasRole('OPT_CAT_EDIT')">
            </sec:authorize>
        </script>
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="component/navigations.jsp"/>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    示例管理
                </li>
                <li class="active">
                    猫
                </li>
            </ul>
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-cat">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                猫列表
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>名字</th>
                                    <th>描述</th>
                                    <th>销售状态</th>
                                    <th>生命体征</th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${cats}" var="cat">
                                    <tr id="tr-${cat.id}">

                                        <td>${cat.name}</td>
                                        <td>${cat.description}</td>
                                        <td>${cat.saleStatus.name}</td>
                                        <td>${cat.status eq 'ENABLE' ? '存活' : '死亡'}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <%--<sec:authorize access="hasAuthority('OPT_CAT_EDIT')">--%>
                                                    <a href="demo/cats/${cat.id}.do"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                <%--</sec:authorize>--%>
                                                <%--<sec:authorize access="hasAuthority('OPT_CAT_DELETE')">--%>
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${cat.id}"
                                                           data-name="${cat.name}"
                                                           class="btn btn-minier btn-danger cat-ban-btn">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                <%--</sec:authorize>--%>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要杀死猫<span
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
                    <div id="cat-reset-password-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">你是否要重置密码?
                                    </div>
                                </div>
                                <div class="modal-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right" for="password">
                                                新密码
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="password" id="password"
                                                       placeholder="新密码"
                                                       name="pwd" class="col-xs-10 col-sm-5">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="confirmPassword">
                                                确认密码
                                            </label>
                                            <div class="col-sm-9">
                                                <input type="password" id="confirmPassword"
                                                       placeholder="确认密码"
                                                       name="pwd" class="col-xs-10 col-sm-5">
                                            </div>
                                        </div>
                                        <div id="password-not-match-msg" class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="confirmPassword">
                                            </label>
                                            <div class="col-sm-9">
                                                重复录入的新密码不一致
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-cancel-reset btn-default">
                                        取消
                                    </button>
                                    <button type="button" class="btn btn-confirm-reset btn-primary">
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
</gbck:page>
