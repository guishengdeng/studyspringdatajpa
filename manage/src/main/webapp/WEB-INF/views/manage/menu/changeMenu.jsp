<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="子菜单列表">
     <jsp:attribute name="script">
         <script type="application/javascript">
            function changeMenuItemParent(currMainMenuId,changedMainMenuId,menuItemId){

                currMainMenuId != changedMainMenuId ? $('#menuItem_'+menuItemId).val(changedMainMenuId) : $('#menuItem_'+menuItemId).val("");

            }

         </script>
     </jsp:attribute>
     <jsp:attribute name="css">
          <style type="text/css">
              #simple-table .name, #simple-table .empty, #simple-table .link, #simple-table .description{
                  min-width: 80px;
              }
              #simple-table .menuChange{
                  min-width:80px;
              }
              #simple-table .symbol{
                  width: 200px;
                  word-break:break-all;
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
                <li class="active">
                    <a href="/manage/mainMenus.do">
                        菜单管理
                    </a>
                </li>
                <li class="active">
                    更改菜单
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <input type="hidden" id="id-of-menuitem">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                更改菜单列表列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_MAINMENU_ADD')">
                                    <a href="manage/menuItems/add.do?mainMenu_id=${mainmenu_id}" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        返回
                                    </a>
                                </sec:authorize>
                            </h3>
                        <form action="manage/menuItems/updateItemMenuParentMenu.do"  method="post">
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="name">名称</th>
                                    <th class="empty"></th>
                                    <th class="hidden-md hidden-sm hidden-xs">序号</th>
                                    <th class="link">链接</th>
                                    <th class="symbol">需要权限</th>
                                    <th class="description">备注</th>
                                    <th class="menuChange">菜单更改</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${mainMenus}" var="currMainMenu" varStatus="status">
                                    <tr id="tr-${currMainMenu.id}">
                                        <td class="hidden-md hidden-sm hidden-xs">${currMainMenu.name}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">子菜单名称</td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="symbol"></td>
                                        <td class="hidden-md hidden-sm hidden-xs"></td>
                                        <td class="hidden-md hidden-sm hidden-xs">

                                        </td>
                                    </tr>

                                    <c:forEach items="${currMainMenu.menuItems}" var="menuItem" varStatus="status">
                                        <tr id="tr-${menuItem.id}">
                                            <td class="hidden-md hidden-sm hidden-xs"></td>
                                            <td class="hidden-md hidden-sm hidden-xs">${menuItem.name}</td>
                                            <td class="hidden-md hidden-sm hidden-xs">${menuItem.code}</td>
                                            <td class="hidden-md hidden-sm hidden-xs">${menuItem.link}</td>
                                            <td class="symbol">${menuItem.symbol}</td>
                                            <td class="hidden-md hidden-sm hidden-xs">${menuItem.description}</td>
                                            <td class="hidden-md hidden-sm hidden-xs">

                                                <select name="mainMenu" id="id" onchange="changeMenuItemParent(${currMainMenu.id},this.value,${menuItem.id})" style="width: 120px;">
                                                   <%-- <c:if test="${withNone}">
                                                        <option value="${noneLabel}" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
                                                    </c:if>--%>
                                                    <c:forEach items="${mainMenus}" var="mainMenu" varStatus="varStatus">
                                                        <option value="${mainMenu.id}" <c:if test="${currMainMenu.id == mainMenu.id}">selected </c:if> >${mainMenu.name} </option>
                                                    </c:forEach>
                                                       <input type="hidden" value="${menuItem.id}" name="menuItemIds">
                                                       <input type="hidden" id="menuItem_${menuItem.id}" value="" name="menuItemParentIds">
                                                </select>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                                <tr>
                                    <td class="hidden-md hidden-sm hidden-xs"></td>
                                    <td class="hidden-md hidden-sm hidden-xs"></td>
                                    <td class="hidden-md hidden-sm hidden-xs"></td>
                                    <td class="hidden-md hidden-sm hidden-xs"></td>
                                    <td class="symbol"></td>
                                    <td class="hidden-md hidden-sm hidden-xs"></td>
                                    <td class="hidden-md hidden-sm hidden-xs">
                                        <%--<sec:authorize access="hasAnyAuthority('OPT_MENUITEM_EDIT')">
                                            <div class="clearfix form-actions">
                                                <div class="col-md-offset-3 col-md-9">--%>
                                                    <button class="btn btn-info" type="submit">
                                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                                        保存
                                                    </button>
                                           <%--     </div>
                                            </div>
                                        </sec:authorize>--%>
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                         </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>



