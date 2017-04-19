<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.list">
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
                    广告管理
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <li>
                                    启动页面广告管理
                                </li>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <%--<sec:authorize access="hasAuthority('OPT_USER_ADD')">--%>
                                    <a href="manage/advertisement/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                <%--</sec:authorize>--%>
                                </span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>图片链接</th>
                                    <th>点击链接</th>
                                    <th class="hidden-md hidden-sm hidden-xs">广告生效时间</th>
                                    <th class="hidden-md hidden-sm hidden-xs">广告过期时间</th>
                                    <th class="hidden-md hidden-sm hidden-xs">停留(毫秒)</th>
                                    <th class="hidden-md hidden-sm hidden-xs">优先级</th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${advertisements}" var="advertisement">

                                        <td>${advertisement.picturesLink}</td>
                                        <td>${advertisement.clickLink}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${advertisement.beginTimestamp}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${advertisement.endTimestamp}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${advertisement.residenceTime}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${advertisement.priority}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a href="manage/advertisement/edit?id=${advertisement.id}" class="btn btn-minier btn-info">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                </a>
                                            </div>
                                        </td>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                </div>
            </div>
        </div>
        <!-- PAGE CONTENT ENDS -->
        </div>
        <!-- /.col -->
        </div>
        <!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>