<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="微信">
    <jsp:attribute name="css">
        <style type="text/css">
            #cat-table .name{
                min-width: 150px;
            }
            #cat-table .operate, #cat-table .status{
                min-width: 50px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
            $(".wechatpay-ban-btn").click(function () {

                $("#id-of-wechatpay").val($(this).data("id"));
                $("#name-of-ban-wechatpay").html($(this).data("name"));
                $("#wechatpay-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#wechatpay-disable-confirm-modal").modal("hide");
            });
            $(".wechatpay-ban-btn").click(function () {
                var wechatpayId = $("#id-of-wechatpay").val();
               $.ajax({
                   methoed:"post",
                   data:{"id": wechatpayId},
                   url:"pay/wechatpaylog.do",
               }).done(function (result) {

                   $("#wechatpay-id").val(result.id);
                   $("#wechatpay-resultCode").val(result.resultCode);
                   $("#wechatpay-errCode").val(result.errCode);
                   $("#wechatpay-errCodeDes").val(result.errCodeDes);
                   $("#wechatpay-openid").val(result.openid);
                   $("#wechatpay-tradeType").val(result.tradeType);
                   $("#wechatpay-bankType").val(result.bankType);
                   $("#wechatpay-totalFee").val(result.totalFee);
                   $("#wechatpay-feeType").val(result.feeType);
                   $("#wechatpay-cashFee").val(result.cashFee);
                   $("#wechatpay-cashFeeType").val(result.cashFeeType);
                   $("#wechatpay-couponFee").val(result.couponFee);
                   $("#wechatpay-couponCount").val(result.couponCount);
                   $("#wechatpay-couponId").val(result.couponId);
                   $("#wechatpay-attach").val(result.attach);
                   $("#wechatpay-timeEnd").val(new Date(parseInt(result.timeEnd)).toLocaleString());

               })
            });
            </sec:authorize>
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
                    微信
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
                                微信支付
                            </h3>
                            <div class="hr hr-18 dotted"></div>

                            <form action="pay/wechatpay.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>银行账号</label>
                                    <input name="bankType" value='<c:out value="${wechatPaymentVo.bankType}" />' type="text" placeholder="支付宝账号"  autocomplete="off">
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <table id="cat-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="name">业务结果</th>
                                    <th class="status">用户身份标识</th>
                                    <th class="status">交易类型</th>
                                    <th class="status">付款银行</th>
                                    <th class="status">总金额</th>
                                    <th class="status">货币类型</th>
                                    <th class="status">现金支付金额</th>
                                    <th class="status">现金支付货币类型</th>
                                    <th class="status">代金券金额</th>
                                    <th class="status">代金券使用数量</th>
                                    <th class="status">代金券ID</th>
                                    <th class="status">支付完成时间</th>
                                    <th class="center operate"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${page.content}" var="wechatpay">
                                    <tr id="tr-${wechatpay.id}">
                                        <td><c:out value="${wechatpay.resultCode}" /></td>
                                        <td><c:out value="${wechatpay.openid}"/></td>
                                        <td><c:out value="${wechatpay.tradeType}"/></td>
                                        <td><c:out value="${wechatpay.bankType}"/></td>
                                        <td><c:out value="${wechatpay.totalFee}"/></td>
                                        <td><c:out value="${wechatpay.feeType}"/></td>
                                        <td><c:out value="${wechatpay.cashFee}"/></td>
                                        <td><c:out value="${wechatpay.cashFeeType}"/></td>
                                        <td><c:out value="${wechatpay.couponFee}"/></td>
                                        <td><c:out value="${wechatpay.couponCount}"/></td>
                                        <td><c:out value="${wechatpay.couponId}"/></td>
                                        <td><c:out value="${wechatpay.timeEnd}"/></td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${wechatpay.id}"
                                                           class="wechatpay-ban-btn">
                                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                        </a>

                                                    </c:if>
                                                </sec:authorize>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="wechatpay.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                        <input type="hidden" id="id-of-wechatpay">
                        <div id="wechatpay-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                            <div class="modal-dialog" style="width: 1200px">
                                <div class="modal-content">
                                    <div class="modal-body" id="alipay-modal-table" >

                                        <div class="modal-body">
                                            <div class="form-horizontal" role="form" >
                                                <div class="form-group">
                                                    <label  class="col-sm-3 control-label">ID</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control"  name="id" value="" id="wechatpay-id" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-horizontal" role="form" id="userForm">
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">业务结果</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control"  name="resultCode" value="" id="wechatpay-resultCode" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">错误代码</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="errCode" value="" id="wechatpay-errCode" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">错误代码描述</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="errCodeDes" value="" id="wechatpay-errCodeDes" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">用户身份标识</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="openid" value="" id="wechatpay-openid" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">交易类型</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="tradeType" value="" id="wechatpay-tradeType" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">付款银行</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="bankType" value="" id="wechatpay-bankType" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">总金额</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="totalFee" value="" id="wechatpay-totalFee" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">货币类型</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="feeType" value="" id="wechatpay-feeType" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">现金支付金额</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="cashFee" value="" id="wechatpay-cashFee" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">现金支付货币类型</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="cashFeeType" value="" id="wechatpay-cashFeeType" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">代金券金额</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="couponFee" value="" id="wechatpay-couponFee" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">代金券使用数量</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="couponCount" value="" id="wechatpay-couponCount" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">代金券ID</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="couponId" value="" id="wechatpay-couponId" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">商家数据包</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="attach" value="" id="wechatpay-attach" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">支付完成时间</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="timeEnd" value="" id="wechatpay-timeEnd" readonly>
                                                        </div>
                                                    </div>

                                                </div>
                                        <button type="button" class="bootbox-close-button close"
                                                data-dismiss="modal" aria-hidden="true">×
                                        </button>
                                        <div class="bootbox-body"> <span id="name-of-ban-wechatpay"></span>&nbsp;
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
