<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
<depotnextdoor:page title="子菜单edit">

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
                    <a href="/manage/mainMenus/detail?id=${mainmenu_id}">
                        菜单管理
                    </a>
                </li>
                <li class="active">
                        子菜单管理
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
                                <a href="manage/mainMenus/detail.do?id=${mainmenu_id}" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回上一级
                                </a>
                            </span>
                            </h3>

                            <form action="manage/menuItems/addOrUpdate.do" method="post"
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
                                        <%--三元运算符 ${empty menuItem ? '' : 'readonly'}--%>
                                        <input  type="text"
                                                                                   id="code"
                                                                                   placeholder=""
                                                                                   name="description"
                                                                                   value="${menuItem.description}"
                                                                                   class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <%--这里插入这段代码的原因是：要维系子菜单和角色之间的关系,否则左边的选项卡会为空--%>
                                <c:if test="${not empty menuItem}">
                                            <c:if test="${not empty menuItem.roles}">
                                                <c:forEach items="${menuItem.roles}" var="role">
                                                    <input  type="hidden"   name="roles" id="roles" value="${role.id}" />
                                                </c:forEach>
                                            </c:if>
                                </c:if>
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
                                        <input type="text" id="symbol" name="symbol" placeholder=""
                                               value="${menuItem.symbol}" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <%--用于管理主菜单,因为主菜单和子菜单的关系是，主菜单是被维护段，只能被子菜单级联--%>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="mainMenu">
                                        主菜单
                                    </label>

                                    <div class="col-sm-9">
                                        <%--这是当前子菜单所属的父级菜单--%>
                                            <c:if test="${not empty menuItem.mainMenu}">
                                                <input type="checkbox" name="mainMenu" id="mainMenu" value="${menuItem.mainMenu.id}" checked="checked"/>
                                                <label for="mainMenu">${menuItem.mainMenu.name}</label>
                                            </c:if>
                                            <c:if test="${not empty mainMenus}">
                                                <c:forEach items="${mainMenus}" var="mainMenu"  varStatus="status">
                                                    <input type="checkbox" name="mainMenu" id="mainMenu" value="${mainMenu.id}"/>
                                                    <label for="mainMenu">${mainMenu.name}</label>
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

