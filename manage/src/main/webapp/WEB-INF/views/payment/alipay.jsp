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
            #cat-table .name{
                min-width: 150px;
            }
            #cat-table .operate, #cat-table .status{
                min-width: 50px;
            }
            #alipay-table .name{
                min-width: 150px;
            }
            #alipay-table .operate, #alipay-table .status{
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
                  method:"POST",
                  data:{"id":alipayId},
                  url:"pay/alipaylog.do",
              }).done(function (returnResult) {
                      $("#alipayment-id").val(returnResult.id)
                      $("#alipay-notifyId").val(returnResult.notifyId);
                      $("#alipay-buyerId").val(returnResult.buyerId);
                      $("#alipay-buyerEmail").val(returnResult.buyerEmail);
                      $("#alipay-useCoupon").val(returnResult.useCoupon)
                      $("#alipay-notifyTime").val(new Date(parseInt(returnResult.notifyTime)).toLocaleString())
                      $("#alipay-subject").val(returnResult.subject);
                      $("#alipay-isTotalFeeAjust").val(returnResult.isTotalFeeAjust);
                      $("#alipay-discount").val(returnResult.discount)
                      $("#alipay-tradeStatus").val(returnResult.tradeStatus);
                      $("#alipay-gmtCreate").val(new Date(parseInt(returnResult.gmtCreate)).toLocaleString());
                      $("#alipay-gmtPayment").val(new Date(parseInt(returnResult.gmtPayment)).toLocaleString())
                      $("#alipay-price").val(returnResult.price)
                      $("#alipay-totalFee").val(returnResult.totalFee);
                      $("#alipay-quantity").val(returnResult.quantity);
                      $("#alipay-paymentType").val(returnResult.paymentType)
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
                                    <input name="buyerEmail" value='<c:out value="${alipayPaymentVo.buyerEmail}" />' type="text" placeholder="支付宝账号"  autocomplete="off">
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <input type="hidden" id="alipay-id"   />
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
                                        <td><c:out value="${alipay. notifyId}" /></td>
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
                                        <td><div class="hidden-sm hidden-xs btn-group">
                                            <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                                                <c:if test="${param.enabled != 'false'}">
                                                    <a data-id="${alipay.id}"
                                                       class="alipay-ban-btn">
                                                        <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                    </a>

                                                </c:if>
                                            </sec:authorize>
                                        </div></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="alipay.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <sec:authorize access="hasAuthority('OPT_ALIPAY_LIST')">
                        <input type="hidden" id="id-of-alipay">
                        <div id="alipay-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                            <div class="modal-dialog" style="width: 1200px ">
                                <div class="modal-content">
                                    <div class="modal-body" id="alipay-modal-table" >

                                            <div class="modal-body">
                                                <div class="form-horizontal" role="form" >
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">ID</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control"  name="id" value="" id="alipayment-id" readonly>
                                                        </div>
                                                    </div>
                                                <div class="form-horizontal" role="form" id="userForm">
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">通知编号</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control"  name="notifyId" value="" id="alipay-notifyId" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">买家支付宝用户号</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="buyerId" value="" id="alipay-buyerId" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">买家支付宝账号</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="buyerEmail" value="" id="alipay-buyerEmail" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">是否使用红包</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="useCoupon" value="" id="alipay-useCoupon" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">更新时间</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" class="form-control" name="notifyTime" value="" id="alipay-notifyTime" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">主题</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="subject" value="" id="alipay-subject" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">是否调整总价</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="isTotalFeeAjust" value="" id="alipay-isTotalFeeAjust" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">折扣</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="discount" value="" id="alipay-discount" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">销交易状态</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="tradeStatus" value="" id="alipay-tradeStatus" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">交易创建时间</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="gmtCreate" value="" id="alipay-gmtCreate" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">交易付款时间</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="gmtPayment" value="" id="alipay-gmtPayment" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">商品单价</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="price" value="" id="alipay-price" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">交易金额</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="totalFee" value="" id="alipay-totalFee" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">数量</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="quantity" value="" id="alipay-quantity" readonly>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label  class="col-sm-3 control-label">支付类型</label>
                                                        <div class="col-sm-9">
                                                            <input type="" class="form-control" name="paymentType" value="" id="alipay-paymentType" readonly>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        <button type="button" class="bootbox-close-button close"
                                                data-dismiss="modal" aria-hidden="true">×
                                        </button>
                                        <div class="bootbox-body"> <span id="name-of-ban-alipay"></span>&nbsp;
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
