<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%--当前页面是用户点击用户管理时，显示的页面--%>
<%--导入manage工程下的webapp目录下的WEB-INF目录下的tags目录的page.tag标签--%>
<depotnextdoor:page title="page.user.list" >
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <%--<sec:authorize access="hasRole('OPT_USER_DELETE')">--%>
                $(".user-ban-btn").click(function () {
                    $("#id-of-user").val($(this).data("id"));
                    $("#name-of-ban-user").html($(this).data("name"));
                    $("#user-disable-confirm-modal").modal();
                });
                $(".btn-cancel-ban").click(function () {
                    $("#user-disable-confirm-modal").modal("hide");
                });
                $(".btn-confirm-ban").click(function () {
                    var username = $("#id-of-user").val();
                    $.post("manage/users/delete.do", {
                        "username": username
                    }, function (result) {
                        if (result) {
                            $("#tr-" + username).remove();
                        }
                    });
                    $("#user-disable-confirm-modal").modal("hide");
                });
            <%--</sec:authorize>--%>

            <sec:authorize access="hasRole('OPT_USER_RESET')">
            $(".user-reset-pwd-btn").click(function () {
                var $password = $("#password");
                $password.val("");
                $("#confirmPassword").val("");
                $("#id-of-user").val($(this).data("id"));
                $("#name-of-reset-user").html($(this).data("name"));
                $("#password-not-match-msg").hide();
                $("#user-reset-password-modal").modal();
                $password.focus();
            });
            $(".btn-cancel-reset").click(function () {
                $("#user-reset-password-modal").modal("hide");
            });
            $(".btn-confirm-reset").click(function () {
                var userId = $("#id-of-user").val();
                var password = $("#password").val();
                var confirmPassword = $("#confirmPassword").val();
                if (!password) {
                    return;
                }
                if (password == confirmPassword) {
                    $.post("manage/users/resetPassword.do", {
                        "userId": userId,
                        "pwd": password
                    }, function (result) {
                        if (result) {
                            var passwordNotEqual = '重置密码成功';
                            alert(passwordNotEqual);
                        }
                    });
                    $("#user-reset-password-modal").modal("hide");
                } else {
                    $("#password-not-match-msg").show();
                }
            });
            </sec:authorize>
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
                    用户管理
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <c:choose>
                                    <c:when test="${param.enabled == 'false'}">
                                        禁用
                                    </c:when>
                                    <c:otherwise>
                                        正常
                                    </c:otherwise>
                                </c:choose>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_USER_ADD')">
                                    <a href="manage/users/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </sec:authorize>
                                    <%--href="manage/users?enabled=${enabled == 'false' ? 'ENABLE' : 'DISABLE'}--%>
                                <a href="manage/users?status=${enabled == 'false' ? 'ENABLE' : 'DISABLE'}"
                                   class="${enabled == 'false' ? 'btn btn-sm btn-success' : 'btn btn-sm btn-danger'}">
                                    <%--btn btn-sm btn-danger--%>
                                    <c:choose>
                                        <c:when test="${enabled == 'false'}">
                                            <i class="ace-icon fa fa-eye"></i>
                                            正常用户
                                        </c:when>
                                        <c:otherwise>
                                            <i class="ace-icon fa fa-ban"></i>
                                            已禁用用户
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </span>
                            </h3>
                            <%--用作查询的表单框--%>
                            <form action="manage/users.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>用户名</label>
                                    <input name="username" value='<c:out value="${adminVo.username}" />' type="text" placeholder="用户名"  autocomplete="off">
                                </div>
                                <div class="col-md-2 inline">
                                    <label>状态</label>
                                    <depotnextdoor:statusSelect fieldName="status" selectedStatus="" withNone="true" enableLabel="启用" disableLabel="禁用"/>
                                </div>

                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="hr hr-18 dotted"></div>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>角色</th>
                                    <th class="hidden-md hidden-sm hidden-xs">联系电话</th>
                                    <th class="hidden-md hidden-sm hidden-xs">角色</th>
                                    <th class="hidden-md hidden-sm hidden-xs">状态</th>
                                    <%--center--%>
                                    <th class="hidden-md hidden-sm hidden-xs">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${adminPage.content}" var="user">
                                    <tr id="tr-${user.username}">

                                        <td><c:out value="${user.username}"/></td>
                                        <td><c:out value="${user.name}"/></td>
                                        <td class="hidden-md hidden-sm hidden-xs"><c:out value="${user.phone}"/></td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <depotnextdoor:statusLabel selectedStatus="${user.status.value}"/>
                                        </td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                               <%-- <sec:authorize access="hasAuthority('OPT_USER_RESET')">
                                                    <a data-id="${user.username}"
                                                       data-name="${user.username}"
                                                       class="btn btn-minier btn-warning user-reset-pwd-btn">
                                                        <i class="ace-icon fa fa-key bigger-120"></i>
                                                    </a>
                                                </sec:authorize>--%>
                                                   <sec:authorize access="hasAuthority('OPT_USER_DELETE')">
                                                       <a
                                                          data-id="${user.username}"
                                                          data-name="${user.username}"
                                                          class="btn btn-minier btn-danger user-ban-btn">
                                                           <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                       </a>
                                                   </sec:authorize>
                                                <%--修改操作--%>
                                                <sec:authorize access="hasAuthority('OPT_USER_EDIT')">
                                                    <a href="manage/users/edit?username=${user.username}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize access="hasAuthority('OPT_USER_DELETE')">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${user.username}"
                                                           data-name="${user.username}"
                                                           class="btn btn-minier btn-danger user-ban-btn">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </sec:authorize>
                                            </div>
                                            <div class="hidden-md hidden-lg">
                                                <div class="inline pos-rel">
                                                    <button class="btn btn-minier btn-primary dropdown-toggle"
                                                            data-toggle="dropdown" data-position="auto">
                                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                    </button>

                                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                        <li>
                                                            <a class="green bigger-140 show-details-btn" title="Show Details">
                                                                <i class="ace-icon fa fa-angle-double-down"></i>
                                                                <span class="sr-only">详情</span>
                                                            </a>
                                                        </li>
                                                        <sec:authorize access="hasAuthority('OPT_USER_RESET')">
                                                            <li>
                                                                <a data-id="${user.username}"
                                                                   data-name="${user.username}" >
                                                                    <span class="orange">
                                                                    <i class="ace-icon fa fa-key bigger-120"></i>
                                                                        </span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                        <sec:authorize access="hasAuthority('OPT_USER_EDIT')">
                                                            <li>
                                                                <a href="manage/users/edit?username=${user.username}">
																<span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                        <sec:authorize access="hasAuthority('OPT_USER_DELETE')">
                                                            <li>
                                                                <a class="btn-delete-modal" data-url="manage/localAgency/delete.do"
                                                                   data-id="${user.username}">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="detail-row" id="tr-detail-${user.username}">
                                        <td colspan="8" id="td-detail-${user.username}">
                                            <div class="table-detail">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name">名字</div>
                                                        <div class="profile-info-value">
                                                            <span><c:out value="${user.name}"/></span>
                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-row">
                                                            <div class="profile-info-name">电话</div>
                                                            <div class="profile-info-value"><c:out value="${user.phone}" /></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <depotnextdoor:springPagePagination url="manage/users.do" springPage="${adminPage}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <div id="user-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要禁用用户<span
                                            id="name-of-ban-user"></span> ?
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
                    <div id="user-reset-password-modal" role="dialog" class="modal" tabindex="-1">
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
</depotnextdoor:page>
