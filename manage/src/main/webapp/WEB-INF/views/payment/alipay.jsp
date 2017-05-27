<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="支付宝">
    <jsp:attribute name="css">
        <style type="text/css">
            #cat-table .name {
                min-width: 150px;
            }

            #cat-table .operate, #cat-table .status {
                min-width: 50px;
            }

            #alipay-table .name {
                min-width: 150px;
            }

            #alipay-table .operate, #alipay-table .status {
                min-width: 50px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
            $(".alipay-ban-btn").click(function () {

                $("#id-of-alipay").val($(this).data("id"));
                $("#name-of-ban-alipay").html($(this).data("name"));
                $("#alipay-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#alipay-disable-confirm-modal").modal("hide");
            });
            //向后台传送id 并 返回值
            $(".alipay-ban-btn").click(function () {
                var alipayId = $("#id-of-alipay").val();
                $.ajax({
                    method: "POST",
                    data: {"id": alipayId},
                    url: "pay/alipaylog.do",
                }).done(function (returnResult) {
                    $("#alipayment-id").html(returnResult.id)
                    $("#alipay-notifyId").html(returnResult.notifyId);
                    $("#alipay-buyerId").html(returnResult.buyerId);
                    $("#alipay-buyerEmail").html(returnResult.buyerEmail);
                    $("#alipay-useCoupon").html(returnResult.useCoupon)
                    $("#alipay-notifyTime").html(new Date(parseInt(returnResult.notifyTime)).toLocaleString())
                    $("#alipay-subject").html(returnResult.subject);
                    $("#alipay-isTotalFeeAjust").html(returnResult.isTotalFeeAjust);
                    $("#alipay-discount").html(returnResult.discount)
                    $("#alipay-tradeStatus").html(returnResult.tradeStatus);
                    $("#alipay-gmtCreate").html(new Date(parseInt(returnResult.gmtCreate)).toLocaleString());
                    $("#alipay-gmtPayment").html(new Date(parseInt(returnResult.gmtPayment)).toLocaleString())
                    $("#alipay-price").html(returnResult.price)
                    $("#alipay-totalFee").html(returnResult.totalFee);
                    $("#alipay-quantity").html(returnResult.quantity);
                    $("#alipay-paymentType").html(returnResult.paymentType)
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
                    支付宝
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
                            支付宝 <span class="inline help-block">(数据库翻页查询)</span>
                        </h3>
                        <div class="hr hr-18 dotted"></div>
                        <form action="pay/alipay.do" method="get">
                            <div class="col-md-3 inline">
                                <label>支付宝账号</label>
                                <input name="buyerEmail" value='<c:out value="${alipayPaymentVo.buyerEmail}" />'
                                       type="text" placeholder="支付宝账号" autocomplete="off">
                            </div>
                            <div class="inline">
                                <button type="submit" class="btn btn-info btn-sm">
                                    <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                </button>
                            </div>
                        </form>
                        <input type="hidden" id="alipay-id"/>
                        <table id="cat-table" class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="name" hidden>id</th>
                                <th class="name">通知编号</th>
                                <th class="status">买家支付宝账号</th>
                                <th class="status">是否使用红包</th>
                                <th class="status">更新时间</th>
                                <th class="status">主题</th>
                                <th class="status">是否调整总价</th>
                                <th class="status">折扣</th>
                                <th class="status">销交易状态</th>
                                <th class="status">交易创建时间</th>
                                <th class="status">交易付款时间</th>
                                <th class="status">商品单价</th>
                                <th class="status">交易金额</th>
                                <th class="status">数量</th>
                                <th class="center operate"></th>
                            </tr>
                            </thead>

                            <tbody>'
                            <c:forEach items="${page.content}" var="alipay">
                                <tr id="tr-${alipay.id}">
                                    <td><c:out value="${alipay. notifyId}"/></td>
                                    <td><c:out value="${alipay.buyerEmail}"/></td>
                                    <td><c:out value="${alipay.useCoupon}"/></td>
                                    <td><c:out value="${alipay.notifyTime}"/></td>
                                    <td><c:out value="${alipay.subject}"/></td>
                                    <td><c:out value="${alipay.isTotalFeeAjust}"/></td>
                                    <td><c:out value="${alipay.discount}"/></td>
                                    <td><c:out value="${alipay.tradeStatus}"/></td>
                                    <td><c:out value="${alipay.gmtCreate}"/></td>
                                    <td><c:out value="${alipay.gmtPayment}"/></td>
                                    <td><c:out value="${alipay.price}"/></td>
                                    <td><c:out value="${alipay.totalFee}"/></td>
                                    <td><c:out value="${alipay.quantity}"/></td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                                                <c:if test="${param.enabled != 'false'}">
                                                    <a data-id="${alipay.id}"
                                                       class="alipay-ban-btn">
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
                        <gbck:springPagePagination url="alipay.do" springPage="${page}"/>
                    </div><!-- /.span -->
                </div><!-- /.row -->
                <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                <input type="hidden" id="id-of-alipay">
                <div id="alipay-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body" id="alipay-modal-table">

                                <div class="modal-body">
                                    <div class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">ID&nbsp;&nbsp;:</label>
                                            <div class="col-sm-9">
                                                <label class="col-sm-3 control-label" style="text-align: left "
                                                       id="alipayment-id"></label>
                                            </div>
                                        </div>
                                        <div class="form-horizontal" role="form" id="userForm">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">通知编号&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-notifyId"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">买家支付宝用户号&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-buyerId"></label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">买家支付宝账号&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-buyerEmail"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">是否使用红包&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-useCoupon"></label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">更新时间&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-notifyTime"></label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">主题&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-subject"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">是否调整总价&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-isTotalFeeAjust"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">折扣&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label input class="col-sm-3 control-label"
                                                           style="text-align: left " id="alipay-discount"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">销交易状态&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-tradeStatus"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">交易创建时间&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-gmtCreate"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">交易付款时间&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-gmtPayment"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">商品单价&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-price"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">交易金额&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-totalFee"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">数量&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-quantity"></label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">支付类型&nbsp;&nbsp;:</label>
                                                <div class="col-sm-9">
                                                    <label class="col-sm-3 control-label" style="text-align: left "
                                                           id="alipay-paymentType"></label>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">
                                        <i class="ace-icon fa fa-times"></i>
                                    </button>
                                    <div class="bootbox-body"><span id="name-of-ban-alipay"></span>&nbsp;
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
