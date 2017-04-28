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
                    <a href="manage/mainMenus/detail?id=${mainmenu_id}">
                        菜单管理
                    </a>
                </li>
                <li>
                    <a href="manage/menuItems/detail.do?id=${menuitem_id}">
                        子菜单管理
                    </a>
                </li>
                <li class="active">
                        操作管理
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
                                    返回
                                </a>
                            </span>
                            </h3>

                            <form action="manage/resources/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                    <%--menuitem_id该参数来自于MenuItemController的detail方法--%>
                                    <input type="hidden" name="menuItem.id" value="${menuitem_id}">
                                    <input type="hidden" name="id" value="${resource.id}">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="link">
                                        名称
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="link" name="name" placeholder=""
                                               value="<c:out value='${resource.name}'/>" class="required col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="symbol">
                                        权限
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="symbol" name="symbol" placeholder=""
                                               value="<c:out value='${resource.symbol}'/>" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="description">
                                        备注
                                    </label>

                                    <div class="col-sm-9">
                                        <input  type="text"
                                                id="description"
                                                name="description"
                                                value="<c:out value='${resource.description}'/> "
                                                class="col-xs-10 col-sm-5">
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



