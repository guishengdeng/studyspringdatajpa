<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="退换货审核">
    <jsp:attribute name="css">
        <style type="text/css">
            #cat-table .name {
                min-width: 150px;
            }

            #cat-table .operate, #cat-table .status {
                min-width: 80px;
            }
            .search{
            }
            .search div{
                margin-bottom:10px;

            }

            .input-daterange{
                width: auto;
            }
            input, select {
                height: 30px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <%--<script src="${pageContext.request.contextPath}/static-resource/ace/assets/js/daterangepicker.min.js"/>--%>
        <script type="application/javascript">
            $('.input-daterange').datepicker({autoclose: true, format: 'yyyy-mm-dd'});
            $('.date-picker').datepicker({
                autoclose: true,
                todayHighlight: true
            })
            // show datepicker when clicking on the icon
            next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            $(".cat-ban-btn").click(function () {

                $("#id-of-cat").val($(this).data("id"));
                $("#name-of-ban-cat").html($(this).data("name"));
                $("#cat-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var catId = $("#id-of-cat").val();
                $.post("demo/cats/delete.do", {
                    "id": catId
                }, function (result) {
                    if (result) {
                        $("#tr-" + catId).remove();
                    }
                }, "json");
                $("#cat-disable-confirm-modal").modal("hide");
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
                    退货单管理
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
                                退货单列表
                            </h3>
                            <div class="search">
                                <form action="${pageContext.request.contextPath}/orderReturn" method="get">
                                    <div>
                                        <div class="col-md-3 inline">
                                            <label>退货单号:</label>
                                            <input name="returnCode" type="text" placeholder="输入退货单号模糊查询" autocomplete="on" value="${reqVo.returnCode}">
                                        </div>
                                        <div class="col-md-3 inline">
                                            <label>原销售单:</label>
                                            <input name="orderCode" type="text" placeholder="输入原销售单号模糊查询" autocomplete="off" value="${reqVo.orderCode}">
                                        </div>
                                        <div class="col-md-2 inline">
                                            <label>退货单状态:</label>
                                            <gbck:returnStatusSelect fieldName="status"
                                                                     selectedStatus="${reqVo.status}" withNone="true"/>
                                        </div>
                                        <div class="col-md-2 inline">
                                            <label>退款单状态:</label>
                                            <gbck:refundStatusSelect fieldName="refundStatus"
                                                                     selectedStatus="${reqVo.refundStatus}" withNone="true"/>
                                        </div>
                                        <div class="col-md-2 inline">
                                            <label>退货类型:</label>
                                            <gbck:returnTypeSelect fieldName="returnType" selectedStatus="${reqVo.returnType}"
                                                                   withNone="true"/></div>
                                    </div>
                                    <div style="margin-top: 5px">
                                        <div class="col-md-8 inline">
                                            <div style="float: left">
                                                <label style="padding-top: 5px;padding-right: 3px">退货单申请时间:</label>
                                            </div>
                                            <div class="input-daterange input-group" style="float: left">
                                                <input type="text" class="input-sm form-control" name="startTime" value="${reqVo.startTime}">
                                                <span class="input-group-addon"><i class="fa fa-exchange"></i></span>
                                                <input type="text" class="input-sm form-control" name="endTime" value="${reqVo.endTime}">
                                            </div>
                                        </div>
                                        <div class="inline" style="float: right; margin-right: 72px;">
                                            <button type="submit" class="btn btn-info btn-sm">
                                                <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                            </button>
                                        </div>
                                    </div>

                                </form>
                            </div>

                            <div class="hr hr-18 dotted"></div>

                            <table id="cat-table" class="table table-bordered table-hover">
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
                                        <td>
                                            <a href="${pageContext.request.contextPath}/orderReturn/detail?id=${orderReturn.id}">
                                                <c:out value="${orderReturn.returnCode}"/></a></td>
                                        <td><c:out value="${orderReturn.order.orderCode}"/></td>
                                        <td><c:out value="${orderReturn.returnAmount}"/></td>
                                        <td><fmt:formatDate type="both" value="${orderReturn.createTimestamp}"/></td>
                                        <td><c:out value="${orderReturn.returnType.desc}"/></td>
                                        <td><c:out value="${orderReturn.status.desc}"/></td>
                                        <td><c:out value="${orderReturn.refundStatus.desc}"/></td>
                                        <td><c:choose>
                                            <c:when test="${orderReturn.status.desc eq '已审核'}">
                                                <a href="${pageContext.request.contextPath}/orderReturn/detail?id=${orderReturn.id}">查看</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/orderReturn/audit?id=${orderReturn.id}">
                                                    审核 </a>
                                            </c:otherwise>
                                        </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="orderReturn" springPage="${orderReturns}"/>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
