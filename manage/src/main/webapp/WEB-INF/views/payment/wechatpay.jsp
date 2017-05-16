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

                            <form action="wechatpay.do" method="get">
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
                                    <th class="name">返回码</th>
                                    <th>错误码</th>
                                    <th class="status">错误码描述</th>
                                    <th class="status">openid</th>
                                    <th class="status">交易类型</th>
                                    <th class="status">银行账号类型</th>
                                    <th class="status">总费用</th>
                                    <th class="status">费用类型</th>
                                    <th class="status">现金费用</th>
                                    <th class="status">现金费用类型</th>
                                    <th class="status">优惠券费用</th>
                                    <th class="status">优惠券数量</th>
                                    <th class="status">优惠券id</th>
                                    <th class="status">attach</th>
                                    <th class="status">结束时间</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${page.content}" var="wechatpay">
                                    <tr id="tr-${wechatpay.id}">
                                        <td><c:out value="${wechatpay.resultCode}" /></td>
                                        <td><c:out value="${wechatpay.errCode}"/></td>
                                        <td><c:out value="${wechatpay.errCodeDes}"/></td>
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
                                        <td><c:out value="${wechatpay.attach}"/></td>
                                        <td><c:out value="${wechatpay.timeEnd}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="wechatpay.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <sec:authorize access="hasAuthority('OPT_CAT_DELETE')">
                        <input type="hidden" id="id-of-cat">
                        <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <button type="button" class="bootbox-close-button close"
                                                data-dismiss="modal" aria-hidden="true">×
                                        </button>
                                        <div class="bootbox-body">您确定要杀死猫<span
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
                    </sec:authorize>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
