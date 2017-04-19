<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
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
                                子菜单管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/menuItems.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                                <%--${cmd}--%>
                            <form action="manage/mainMenus/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <c:if test="${not empty menuItem}">
                                    <input type="hidden" name="id" value="${menuItem.id}">
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        描述
                                    </label>

                                    <div class="col-sm-9">
                                        <%--三元运算符--%>
                                        <input ${empty menuItem ? '' : 'readonly'} type="text"
                                                                                   id="code"
                                                                                   placeholder=""
                                                                                   name="description"
                                                                                   value="${menuItem.description}"
                                                                                   class="col-xs-10 col-sm-5">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="link">
                                        链接
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="link" name="link" placeholder=""
                                               value="${menuItem.link}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">
                                        名称
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="name" name="name" placeholder="名称"
                                               value="${menuItem.name}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="symbol">
                                        权限
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="symbol" name="symbol" placeholder="菜单描述"
                                               value="${mainMenu.description}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>

                                <sec:authorize access="hasAnyAuthority('OPT_MAINMENU_ADD', 'OPT_MAINMENU_EDIT')">
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

