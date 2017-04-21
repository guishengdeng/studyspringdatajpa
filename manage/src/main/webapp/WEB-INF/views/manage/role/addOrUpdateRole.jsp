<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="page.user.edit">
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
                    <a href="manage/roles.do">
                        角色管理
                    </a>
                </li>
                <li class="active">
                    <c:out value="${cmd}"/>
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
                                角色管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/roles.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回角色列表
                                </a>
                            </span>
                            </h3>

                            <form action="manage/roles/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <c:if test="${not empty role}">
                                    <input type="hidden" name="id" value="${role.id}">
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        描述
                                    </label>

                                    <div class="col-sm-9">
                                        <input  type="text"
                                                id="code"
                                                placeholder=""
                                                name="description"
                                                value="${role.description}"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        名称
                                    </label>

                                    <div class="col-sm-9">
                                        <input  type="text"
                                                name="name"
                                                value="${role.name}"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        子菜单
                                    </label>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty role.menuItems}">
                                            <c:forEach items="${role.menuItems}" var="menuItem" varStatus="status">
                                                <input type="checkbox" name="menuItems" id="menuItem" value="${menuItem.id}" checked="checked"/>
                                                <label for="menuItem">${menuItem.name}</label>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty menuItems}">
                                            <c:forEach items="${menuItems}" var="menuItem" varStatus="status">
                                                <input type="checkbox" name="menuItems" id="menuItem" value="${menuItem.id}"/>
                                                <label for="menuItem">${menuItem.name}</label>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        资源
                                    </label>
                                    <div class="col-sm-9">
                                        <c:if test="${not empty role.resources}">
                                            <c:forEach items="${role.resources}" var="resource" varStatus="status">
                                                <input type="checkbox" name="resources" id="resource" value="${resource.id}" checked="checked"/>
                                                <label for="resource">${resource.name}</label>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty resources}">
                                            <c:forEach items="${resources}" var="resource" varStatus="status">
                                                <input type="checkbox" name="resources" id="resource" value="${resource.id}"/>
                                                <label for="resource">${resource.name}</label>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyAuthority('OPT_ROLE_ADD', 'OPT_ROLE_EDIT')">
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button class="btn btn-info" type="submit">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                提交
                                            </button>

                                            &nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </button>
                                        </div>
                                    </div>
                                </sec:authorize>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>

