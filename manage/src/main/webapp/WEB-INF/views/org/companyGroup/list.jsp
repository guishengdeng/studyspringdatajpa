<%--
  Created by IntelliJ IDEA.
  User: liubin
  Date: 5/2/17
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="客户组">
    <jsp:attribute name="script">
        <script type="application/javascript">

        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    价格管理
                </li>
                <li class="active">
                    客户组
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
                                客户组管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="companyGroup/new.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增客户组
                                </a>
                            </span>
                            </h3>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>客户组编码</th>
                                    <th>客户组名称</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <c:forEach var="companyGroup" items="${companyGroups}">
                                    <tr>
                                        <td>${companyGroup.code}</td>
                                        <td>${companyGroup.name}</td>
                                        <td>${companyGroup.description}</td>
                                        <td>
                                            <a class="ui primary find-btn" href="companyGroup/edit/${companyGroup.id}.do">
                                                <i class="ui icon legal"></i><span>修改</span></a>&nbsp;
                                            <a class="ui delete-btn" onclick="deleteById(${companyGroup.id})">
                                                <i class="minus circle icon"></i>
                                                <span>禁用</span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
        <input type="hidden" id="promotionsId">
    </jsp:body>
</depotnextdoor:page>

