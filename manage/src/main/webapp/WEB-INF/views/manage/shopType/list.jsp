<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
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
                    商户中心
                </li>
                <li class="active">
                    商户列表
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
                                商户类型管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="shopTypes/new.do" class="btn btn-sm btn-primary"><i
                                             class="ace-icon glyphicon glyphicon-plus"></i>
                                        新增商户类型
                                </a>
                            </span>
                            </h3>
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>类型名称</th>
                                    <th>显示顺序</th>
                                    <th>备注</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <c:forEach var="shopTypes" items="${shopTypes}">
                                    <tr>
                                        <td>${shopTypes.name}</td>
                                        <td>${shopTypes.idx}</td>
                                        <td>${shopTypes.description}</td>
                                        <td>
                                            <a class="ui primary find-btn" href="shopTypes/${shopTypes.id}.do">
                                                <i class="ui icon legal"></i><span>修改</span></a>&nbsp;
                                            <a class="ui delete-btn" onclick="deleteById(${shopTypes.id})">
                                                <i class="minus circle icon"></i>
                                                <span>禁用</span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <button type="button" class="bootbox-close-button close"
                                                    data-dismiss="modal" aria-hidden="true">×
                                            </button>
                                            <div class="bootbox-body">您确定要禁用活动<span
                                                    id="name-of-ban-cat"></span> ?
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-cancel-ban btn-default">
                                                取消
                                            </button>
                                            <button type="button" class="btn btn-confirm-ban btn-primary">
                                                确认
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
        <input type="hidden" id="promotionsId">
    </jsp:body>
</depotnextdoor:page>
