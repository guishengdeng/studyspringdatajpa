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
            <sec:authorize access="hasRole('OPT_CAT_DELETE')">
            </sec:authorize>

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
                                    <th>用户名</th>
                                    <th>名字</th>
                                    <th class="hidden-md hidden-sm hidden-xs">联系电话</th>
                                    <th class="hidden-md hidden-sm hidden-xs">角色</th>
                                    <th class="hidden-md hidden-sm hidden-xs">状态</th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${admins}" var="user">
                                    <tr id="tr-${user.username}">

                                        <td>${user.username}</td>
                                        <td>${user.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${user.phone}</td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <gbck:statusLabel selectedStatus="${user.status.value}"/>
                                        </td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_USER_RESET')">
                                                    <a data-id="${user.username}"
                                                       data-name="${user.username}"
                                                       class="btn btn-minier btn-warning user-reset-pwd-btn">
                                                        <i class="ace-icon fa fa-key bigger-120"></i>
                                                    </a>
                                                </sec:authorize>

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
</gbck:page>
