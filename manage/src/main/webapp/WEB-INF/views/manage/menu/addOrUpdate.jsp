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
                    <a href="manage/users">
                        菜单管理
                    </a>
                </li>
                <li class="active">
                    <c:out value="${cmd}"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                菜单管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/mainMenus.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <%--${cmd}--%>
                            <form action="manage/mainMenus/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <c:if test="${not empty mainMenu}">
                                    <input type="hidden" name="id" value="${mainMenu.id}">
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        代码
                                    </label>

                                    <div class="col-sm-9">
                                        <input ${empty mainMenu ? '' : 'readonly'} type="text"
                                                                                id="code"
                                                                                placeholder="代码"
                                                                                name="code"
                                                                                value="${mainMenu.code}"
                                                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="companyType">
                                        公司类型
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="companyType" name="companyType" placeholder="公司类型"
                                               value="${mainMenu.companyType}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">
                                        菜单名称
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="name" name="name" placeholder="菜单名称"
                                               value="${mainMenu.name}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="description">
                                        菜单描述
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="description" name="description" placeholder="菜单描述"
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

