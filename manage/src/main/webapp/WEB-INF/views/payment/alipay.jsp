<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="猫">
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
                            <form action="alipay.do" method="get">
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

                            <table id="cat-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="name">通知编号</th>
                                    <th>买家支付宝用户号</th>
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
                                    <th class="status">支付类型</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${page.content}" var="alipay">
                                    <tr id="tr-${alipay.id}">
                                        <td><c:out value="${alipay. notifyId}" /></td>
                                        <td><c:out value="${alipay.buyerId}"/></td>
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
                                        <td><c:out value="${alipay.paymentType}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="alipay.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
