<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="退换货单列表">
    <jsp:attribute name="css">
        <style type="text/css">
            #orderReturn-table .name {
                min-width: 150px;

            }

            #orderReturn-table .operate, #orderReturn-table .status {
                min-width: 80px;
            }

        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {
                $("#orderReturn-table").DataTable({
                    "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [1, 4], "orderable": false}],
                    "order": [[0, "asc"]],
                    "ajax": "${pageContext.request.contextPath}"
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
                    退货审核
                </li>
                <li class="active">
                    退货单列表
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
                                退货单列表 <span class="inline help-block"></span>
                            </h3>
                            <div class="advance-search">
                                <table>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">退货单号:</label>
                                        <div><input type="text"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">原销售单:</label>
                                        <div><input type="text"></div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">退款单状态:</label>
                                        <div><input type="text" width="20"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 inline">退货类型:</label>
                                        <gbck:saleStatusSelect fieldName="saleStatus" selectedStatus="${reqVo.saleStatus}" withNone="true"/>                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">退货申请时间:</label>
                                        <div class="input-daterange input-group">
                                            <input type="text" class=" input-sm form-control" name="date-range-picker">
                                            <span class="input-group-addon">
																		<i class="fa fa-exchange"></i>
																	</span>

                                            <input type="text" class=" input-sm form-control" name="date-range-picker">
                                        </div>
                                    </div>
                                </table>
                            </div>
                            <table id="orderReturn-table" class="table center table-hover">
                                <thead>
                                <tr>
                                    <th>退货单号</th>
                                    <th>原销售单</th>
                                    <th>退货金额</th>
                                    <th>退货申请时间</th>
                                    <th>退货类型</th>
                                    <th>退货单状态</th>
                                    <th>退款单状态</th>
                                    <th class="center operate">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${orderReturns.content}" var="orderReturn">
                                    <tr id="tr-${orderReturn.id}" role="row">
                                        <td><a href="${pageContext.request.contextPath}/orderReturn/detail?orderReturn=${orderReturn.returnCode}"> <c:out value="${orderReturn.returnCode}"/></a></td>
                                        <td><c:out value="${orderReturn.order.orderCode}"/></td>
                                        <td><c:out value="${orderReturn.returnAmount}"/></td>
                                        <td><fmt:formatDate type="both" value="${orderReturn.createTimestamp}"/></td>
                                        <td><c:out value="${orderReturn.returnType.desc}"/></td>
                                        <td><c:out value="${orderReturn.status.desc}"/></td>
                                        <td><c:out value="${orderReturn.refundStatus.desc}"/></td>
                                        <td><a href="#"> 审核 </a></td>
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
    </jsp:body>
</gbck:page>
