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
            #cat-table .name {
                min-width: 150px;
            }

            #cat-table .operate, #cat-table .status {
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
                    methoed: "post",
                    data: {"id": wechatpayId},
                    url: "pay/wechatpaylog.do",
                }).done(function (result) {

                    $("#wechatpay-id").html(result.id);
                    $("#wechatpay-resultCode").html(result.resultCode);
                    $("#wechatpay-errCode").html(result.errCode);
                    $("#wechatpay-errCodeDes").html(result.errCodeDes);
                    $("#wechatpay-openid").html(result.openid);
                    $("#wechatpay-tradeType").html(result.tradeType);
                    $("#wechatpay-bankType").html(result.bankType);
                    $("#wechatpay-totalFee").html(result.totalFee);
                    $("#wechatpay-feeType").html(result.feeType);
                    $("#wechatpay-cashFee").html(result.cashFee);
                    $("#wechatpay-cashFeeType").html(result.cashFeeType);
                    $("#wechatpay-couponFee").html(result.couponFee);
                    $("#wechatpay-couponCount").html(result.couponCount);
                    $("#wechatpay-couponId").html(result.couponId);
                    $("#wechatpay-attach").html(result.attach);
                    $("#wechatpay-timeEnd").html(new Date(parseInt(result.timeEnd)).toLocaleString());

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
                            <input name="bankType" value='<c:out value="${wechatPaymentVo.bankType}" />' type="text"
                                   placeholder="支付宝账号" autocomplete="off">
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
                                <td><c:out value="${wechatpay.resultCode}"/></td>
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
                    <gbck:springPagePagination url="wechatpay.do" springPage="${page}"/>
                </div><!-- /.span -->
            </div><!-- /.row -->
            <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
            <input type="hidden" id="id-of-wechatpay">
            <div id="wechatpay-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body" id="alipay-modal-table">

                            <div class="modal-body">
                                <div class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">ID&nbsp;&nbsp;:</label>
                                        <div class="col-sm-9">
                                            <label type="text" class="col-sm-3 control-label" style="text-align: left"
                                                   id="wechatpay-id"></label>
                                        </div>
                                    </div>
                                    <div class="form-horizontal" role="form" id="userForm">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">业务结果&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-resultCode"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">错误代码&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-errCode"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">错误代码描述&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-errCodeDes"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">用户身份标识&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-openid"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">交易类型&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-tradeType"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">付款银行&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-bankType"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">总金额&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-totalFee"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">货币类型&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-feeType"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">现金支付金额&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-cashFee"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">现金支付货币类型&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-cashFeeType"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">代金券金额&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-couponFee"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">代金券使用数量&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-couponCount"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">代金券ID&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-couponId"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">商家数据包&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-attach"></label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">支付完成时间&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left"
                                                       id="wechatpay-timeEnd"></label>
                                            </div>
                                        </div>

                                    </div>
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">
                                        <i class="ace-icon fa fa-times"></i>
                                    </button>
                                    <div class="bootbox-body"><span id="name-of-ban-wechatpay"></span>&nbsp;
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
