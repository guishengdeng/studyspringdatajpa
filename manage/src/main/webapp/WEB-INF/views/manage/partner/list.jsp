<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="合伙人">
    <jsp:attribute name="css">
        <style type="text/css">
            #partner-table .name {
                min-width: 150px;
            }

            #partner-table .operate, #partner-table .status {
                min-width: 80px;
            }

            table td {
                border: 1px solid #DDDDDD;
            }

            table th {
                border: 1px solid #DDDDDD;
            }

            #condition-dev {
                height: 148px;
            }

            .row-dev {
                margin-top: 1%;
                height: 66px;
                margin-bottom: -2%;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {
                $("#partner-table").DataTable({
                    "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [0, 8], "orderable": false}],
                    "ording": false
                });
                $(".date-picker").datetimepicker({
                    format: 'YYYY-MM-DD H:mm:ss'
                });

            });
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
                    示例管理
                </li>
                <li class="active">
                    合伙人列表
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="hr hr-18 dotted"></div>
                            <div id="condition-dev">
                                <form action="/partner/list" method="POST">
                                    <div class="row-dev">
                                        <div class="col-md-3 inline">
                                            <label>审核状态</label>
                                            <select name="approvalStatus" class="valid" aria-invalid="false">
                                                <option value="" selected>全部</option>
                                                <option value="UNDER_REVIEW">审核中</option>
                                                <option value="PASS">通过</option>
                                                <option value="VETO">否决</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>公司名称</label>
                                            <input name="name" value="${condition.name}" type="text" placeholder="公司名称"
                                                   autocomplete="off"
                                                   class="valid" aria-invalid="false">
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>联系人</label>
                                            <input name="corporateName" value="${condition.corporateName}" type="text" placeholder="联系人" autocomplete="off"
                                                   class="valid" aria-invalid="false">
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>操作人</label>
                                            <input name="operator" value="${condition.operator}" type="text" placeholder="操作人" autocomplete="off"
                                                   class="valid" aria-invalid="false">
                                        </div>
                                    </div>
                                    <div class="row-dev">
                                        <div class="col-md-3 inline">
                                            <label>申请开始时间</label>
                                            <div class="input-group">
                                                <input type="text" name="submitStartTime" value="${condition.submitStartTime}" class="form-control date-picker">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-clock-o bigger-110"></i>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>申请结束时间</label>
                                            <div class="input-group">
                                                <input type="text" name="submitEndTime" value="${condition.submitStartTime}" class="form-control date-picker">
                                                <span class="input-group-addon">
                                            <i class="fa fa-clock-o bigger-110"></i>
                                        </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>操作开始时间</label>
                                            <div class="input-group">
                                                <input type="text" name="optionStartTime" value="${condition.optionStartTime}" class="form-control date-picker">
                                                <span class="input-group-addon">
                                            <i class="fa fa-clock-o bigger-110"></i>
                                        </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>操作结束时间</label>
                                            <div class="input-group">
                                                <input name="optionEndTime" type="text" value="${condition.optionEndTime}" class="form-control date-picker">
                                                <span class="input-group-addon">
                                            <i class="fa fa-clock-o bigger-110"></i>
                                        </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 inline" style="margin-top: 22px">
                                        <button type="submit" class="btn btn-info btn-sm">
                                            <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <div class="hr hr-18 dotted"></div>
                            <table id="partner-table" class="table table-hover">
                                <thead>
                                <tr>
                                    <th class="center operate">公司名称</th>
                                    <th class="center operate">主要联系人</th>
                                    <th class="center operate">联系电话</th>
                                    <th class="center operate">经营品类</th>
                                    <th class="center operate">申请时间</th>
                                    <th class="center operate">审核状态</th>
                                    <th class="center operate">操作时间</th>
                                    <th class="center operate">操作人</th>
                                    <th class="center operate">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${partners}" var="partner">
                                    <tr id="tr-1">
                                        <td class="center operate"><c:out value="${partner.name}"/></td>
                                        <td class="center operate"><c:out value="${partner.corporateName}"/></td>
                                        <td class="center operate"><c:out value="${partner.mobile}"/></td>
                                        <td class="center operate"><c:out value="${partner.category}"/></td>
                                        <td class="center operate"><c:out value="${partner.createTimestamp}"/></td>
                                        <td class="center operate">
                                            <c:if test="${partner.approvalStatus eq 'UNDER_REVIEW'}">
                                                审核中
                                            </c:if>
                                            <c:if test="${partner.approvalStatus eq 'PASS'}">
                                                通过
                                            </c:if>
                                            <c:if test="${partner.approvalStatus eq 'VETO'}">
                                                否决
                                            </c:if>
                                        </td>
                                        <td class="center operate"><c:out value="${partner.auditTime}"/></td>
                                        <td class="center operate"><c:out value="${partner.operator}"/></td>
                                        <td class="center operate">
                                            <c:choose>
                                                <c:when test="${partner.approvalStatus eq 'UNDER_REVIEW'}">
                                                    <a role="button" href="/partner/edit/${partner.id}">审核</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a role="button" href="/partner/detail/${partner.id}">详情</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
