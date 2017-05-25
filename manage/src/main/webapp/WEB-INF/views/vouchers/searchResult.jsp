<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="优惠券查询">
    <jsp:attribute name="css">
        <style type="text/css">
            #voucher-table .name{
                min-width: 150px;
            }
            #voucher-table .operate, #voucher-table .status{
                min-width: 80px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
        $('.datepicker').datetimepicker({
			format : 'Y-M-D H:m:s'
		});
            $(".voucher-ban-btn").click(function () {

                $("#id-of-voucher").val($(this).data("id"));
                $("#name-of-ban-voucher").html($(this).data("name"));
                $("#voucher-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#voucher-disable-confirm-modal").modal("hide");
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
                    优惠券查询管理
                </li>
                <li class="active">
                    优惠券列表
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
                                 优惠券列表 <span class="inline help-block"></span>
                            </h3>
                            <form action="${pageContext.request.contextPath}/manage/voucher/voucherList.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>名称：</label>
                                    <input name="name" value='<c:out value="${reqVo.name}" />' type="text" placeholder="名称"  autocomplete="off">
                                </div>
                                <div class="col-md-3 inline">
                                    <label>下发人：</label>
                                    <input name="issuerName" type="text" value="${reqVo.issuerName}"/>
                                </div>
                                <div class="col-md-3 inline">
                                    <label>下发时间：</label>
                                    <input name="startTime" type="text" value="${reqVo.startTime}" class="datepicker" id="datepickerIssue"/>
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="hr hr-18 dotted"></div>

                            <table id="voucher-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <tr>
                                    <th class="name">优惠券名称</th>
                                    <th>优惠券面值</th>
                                    <th>有效天数</th>
                                    <th>优惠券描述</th>
                                    <th>下发人</th>
                                    <th>下发时间</th>
                                    <th>优惠券详情</th>
                                </tr>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${vouchers.content}" var="voucher">
                                    <tr id="tr-${voucher.id}">
                                        <td>${voucher.name }"</td>
                                        <td>${voucher.faceValue/100 }</td>
                                        <td>${voucher.periodDays }</td>
                                        <td>${voucher.description }</td>
                                        <td>${voucher.issuerName }</td>
                                        <td>${fn:substring(voucher.startTime, 0, 19)}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                    <a href="manage/vouchers/detail.do?voucherId=${voucher.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>查看
                                                    </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="/manage/voucher/voucherList.do" springPage="${vouchers}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
