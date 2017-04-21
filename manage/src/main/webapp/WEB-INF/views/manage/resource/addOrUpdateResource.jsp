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
                    <a href="manage/mainMenus.do">
                        菜单管理
                    </a>
                </li>
                <li>
                    <a href="manage/menuItems.do">
                        子菜单管理
                    </a>
                </li>
                <li>
                    <a href="manage/resources.do">
                        操作管理
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
                                操作管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/menuItems/detail.do?id=${menuitem_id}" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回子菜单
                                </a>
                            </span>
                            </h3>

                            <form action="manage/resources/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <c:if test="${not empty resource}">
                                    <input type="hidden" name="id" value="${resource.id}">
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        描述
                                    </label>

                                    <div class="col-sm-9">
                                            <%--三元运算符 ${empty menuItem ? '' : 'readonly'}--%>
                                        <input  type="text"
                                                id="code"
                                                placeholder=""
                                                name="description"
                                                value="${resource.description}"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="link">
                                        名称
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="link" name="name" placeholder=""
                                               value="${resource.name}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="symbol">
                                        权限
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="symbol" name="symbol" placeholder=""
                                               value="${resource.symbol}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="menuItem">
                                        子菜单
                                    </label>
                                    <div class="col-sm-9">
                                            <%--这是当前资源所属的子菜单--%>
                                        <c:if test="${not empty resource.menuItem}">
                                            <input type="checkbox" name="menuItem" id="menuItem" value="${resource.menuItem.id}" checked="checked"/>
                                            <label for="menuItem">${resource.menuItem.name}</label>
                                        </c:if>
                                        <c:if test="${not empty menuItems}">
                                            <c:forEach items="${menuItems}" var="menuItem"  varStatus="status">
                                                <input type="checkbox" name="menuItem" id="menuItem" value="${menuItem.id}"/>
                                                <label for="menuItem">${menuItem.name}</label>
                                            </c:forEach>
                                        </c:if>

                                    </div>

                                </div>
                                <sec:authorize access="hasAnyAuthority('OPT_MENUITEM_ADD', 'OPT_MENUITEM_EDIT')">
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



