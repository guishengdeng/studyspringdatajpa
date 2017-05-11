<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<manage:page title="优惠券类型详情">
    <jsp:attribute name="css">
        <style type="text/css">
            .ui.form .field label span{
                font-weight: normal;
            }
        </style>
    </jsp:attribute>
<jsp:body>
<div class="ui main container">
    <h3 class="ui header">
         优惠券类型详情
    </h3>
    <div class="ui divider"></div>
    <form id="form" class="ui form">
        <div class="field">
            <label>优惠券类型名:
               <span>${voucherTypePo.name }</span> 
            </label>
        </div>
        <div class="field">
            <label>优惠券面值:
            	<span><fmt:formatNumber type="number" value="${voucherTypePo.faceValue/100 }" pattern="0" maxFractionDigits="2"/>元</span> 
            </label>
        </div>
        <div class="field" id="limitInp">
            <label>使用限额(订单金额需要达到多少钱才能使用)：
                <span>
                    <fmt:formatNumber type="number" value="${voucherTypePo.paymentLimit/100 }" pattern="0" maxFractionDigits="2"/>
                </span>
            </label>
        </div>
        <c:choose>
            <c:when test="${voucherTypePo.voucherLimitType eq 'BY_CATEGORY'}">
                <div class="field">
                    <label>优惠券适用的商品分类：
                        <span>${voucherTypePo.category.name}</span>
                    </label>
                </div>
            </c:when>
            <c:when test="${voucherTypePo.voucherLimitType eq 'BY_PRODUCTS'}">
                <div class="field">
                    <label>优惠券适用的商品：
	            <span>
		          <c:forEach var="product" items="${voucherTypePo.products}">
                      ${product.name }&nbsp;&nbsp;
                  </c:forEach>
	            </span>
                    </label>
                </div>
            </c:when>
            <c:otherwise>
                <div class="field">
                    <label>优惠券适用的商品分类和商品：
                        <span>无限制</span>
                    </label>
                </div>
            </c:otherwise>
        </c:choose>

        <manage:paymentType fieldName="paymentTypes" label="优惠券支持的付款方式" labelComplement="默认支持支付宝，微信"
                            supportPaymentIds="${voucherTypePo.paymentType}"/>

        <div class="field">
            <label>优惠券领取限定：
             <span>
            <c:if test="${voucherTypePo.fetchType ==0 }">只能领取一次</c:if>
            <c:if test="${voucherTypePo.fetchType ==1 }">可以领取多次</c:if>
            </span>
            </label>
        </div>
        <div class="field">
            <label>优惠券是否可以叠加使用：
                <span>${voucherTypePo.typeStatus.name}</span>
            </label>
        </div>
        <div class="field">
            <label>优惠可领取时间段：
	            <span>
		            <fmt:formatDate value="${voucherTypePo.startTime}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /> 至
		            <fmt:formatDate value="${voucherTypePo.expireTime}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
	            </span>
            </label>
        </div>

        <div class="inline fields">
            <label>过期类型: <span>${voucherTypePo.voucherExpireType.name}</span> </label>
        </div>

        <div class="field">
            <label>有效期（领取后X天内可用）：
	            <span>
	           	 	${voucherTypePo.periodDays }
	            </span>
            </label>
        </div>
        <div class="field">
            <label>创建数量：
	             <span>
                         ${voucherTypePo.issueCount }
                 </span>
            </label>
        </div>
        <div class="field">
            <label>优惠券池阀值（少于此数量时给通知人发邮件通知）：
	            <span>
                        ${voucherTypePo.minCountInPool}
                </span>
            </label>
        </div>
        <div class="field">
            <label>通知人邮箱：
	            <span>
	            	${voucherTypePo.mailTo }
	            </span>
            </label>
        </div>

        <div class="field">
            <label>使用说明：</label>
            ${voucherTypePo.description }
        </div>
        
        <div class="field" style="display: block;text-align: center;margin-bottom: 60px;">
            <input type="button" class="ui button" value="返回" onclick="history.go(-1)"/>
        </div>
     </form>
</div>
 </jsp:body>
</manage:page>
