<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="css">
        <style type="text/css">
            table tr th{
                padding: 10px !important;
            }
        </style>
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
                                    返回
                                </a>
                            </span>
                            </h3>

                            <form action="manage/roles/addOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                 <input type="hidden" name="id" id="id" value="${role.id}"/>
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
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        菜单&操作
                                    </label>
                                    <div class="col-sm-9">
                                        <table class="infotable" id="simple-table">
                                            <tr>
                                                <td>
                                                    <table border=0 align="center">
                                                        <tr>
                                                            <c:set var="menuItemIndex" value="0"/>
                                                            <c:set var="resourceIndex" value="0"/>
                                                            <c:forEach items="${mainmenus}" var="mainMenu" varStatus="status">
                                                                <td valign="top" class="no">
                                                                    <table border=0 align="center">
                                                                        <tr>
                                                                            <th style="padding:5px 20px !important;"><strong>${mainMenu.name}</strong></th>
                                                                        </tr>
                                                                        <c:forEach items="${mainMenu.menuItems}" var="menuItem"
                                                                                   varStatus="menuItemStatus">
                                                                            <c:set var="menuItemIndex" value="${menuItemIndex+1}"/>
                                                                            <tr>
                                                                                <td class="no" style="padding:0px 20px !important;">
                                                                                    <table border=0 align="left">
                                                                                        <tr>
                                                                                            <td class="no" colspan="2" nowrap>
                                                                                                <input type="checkbox" name="menuItems"
                                                                                                       id="menuItemId_${menuItem.id}"
                                                                                                       value="${menuItem.id}"/>
                                                                                                <label for="menuItemId_${menuItem.id}">
                                                                                                        ${menuItem.name}
                                                                                                </label>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="no" width="30"></td>
                                                                                            <td class="no">
                                                                                                <c:forEach items="${menuItem.resources}"
                                                                                                           var="resource"
                                                                                                           varStatus="resourceStatus">
                                                                                                    <c:set var="resourceIndex"
                                                                                                           value="${resourceIndex+1}"/>
                                                                                                    <input type="checkbox" name="resources"
                                                                                                           id="resourceId_${resource.id}"
                                                                                                           value="${resource.id}"/><label
                                                                                                        for="resourceId_${resource.id}">${resource.name}</label><br>
                                                                                                </c:forEach>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </table>
                                                                </td>
                                                            </c:forEach>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
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
                            <SCRIPT LANGUAGE="JavaScript">
                                $().ready(function () {
                                    $("#contentform").validate();
                                })

                                <c:forEach items="${role.menuItems}" var="menuItem" varStatus="status">
                                var obj${status.count} = document.getElementById('menuItemId_${menuItem.id}');
                                if (obj${status.count})
                                    obj${status.count}.checked = true;
                                </c:forEach>

                                <c:forEach items="${role.resources}" var="resource" varStatus="status">
                                var obj${status.count} = document.getElementById('resourceId_${resource.id}');
                                if (obj${status.count})
                                    obj${status.count}.checked = true;
                                </c:forEach>
                            </SCRIPT>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>

