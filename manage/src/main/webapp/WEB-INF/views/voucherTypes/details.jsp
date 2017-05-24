<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<manage:page title="优惠券类型详情">
	<jsp:attribute name="css">
	<link rel="stylesheet"
			href="static-resource/ace/assets/css/bootstrap-multiselect.min.css" />
	<link rel="stylesheet"
			href="static-resource/ace/assets/css/select2.min.css" />
    </jsp:attribute>
	<jsp:attribute name="script">
	 <script type="text/javascript"
			src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>
     <script type="text/javascript"
			src="static-resource/ace/assets/js/select2.min.js"></script>
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
         <li><a href="/manage/voucherType/list.do">
             优惠券类型管理</a>
         </li>
     </ul>
     <a class="btn btn-xs btn-primary history-back">
         <i class="ace-icon fa fa-angle-left"></i>
         返回
     </a>
</div>
 <div class="page-content">
    <h3 class="header smaller lighter blue">
         优惠券类型详情
    </h3>
    <div class="row">
    <form id="form" class="form-horizontal">
        <div class="form-group">
            <label><b>优惠券类型名:</b>
               <span>${voucherTypePo.name }</span> 
            </label>
        </div>
        
        <div class="form-group">
            <label><b>优惠券面值:</b>
            	<span><fmt:formatNumber type="number"
										value="${voucherTypePo.faceValue/100 }" pattern="0"
										maxFractionDigits="2" />元</span> 
            </label>
        </div>
        
        <div class="form-group" id="limitInp">
            <label><b>使用限额(订单金额需要达到多少钱才能使用)：</b>
                <span>
                    <fmt:formatNumber type="number"
										value="${voucherTypePo.paymentLimit/100 }" pattern="0"
										maxFractionDigits="2" />
                </span>
            </label>
        </div>
        
        <c:choose>
            <c:when
								test="${voucherTypePo.voucherLimitType eq 'BY_CATEGORY'}">
                <div class="form-group">
                    <label><b>优惠券适用的商品分类：</b>
                        <span>${voucherTypePo.category.name}</span>
                    </label>
                </div>
            </c:when>
            <c:when
								test="${voucherTypePo.voucherLimitType eq 'BY_PRODUCTS'}">
                <div class="form-group">
                    <label><b>优惠券适用的商品：</b>
	            <span>
		          <c:forEach var="product" items="${voucherTypePo.products}">
                      ${product.name }&nbsp;&nbsp;
                  </c:forEach>
	            </span>
                    </label>
                </div>
            </c:when>
            <c:otherwise>
                <div class="form-group">
                    <label><b>优惠券适用的商品分类和商品：</b>
                        <span>无限制</span>
                    </label>
                </div>
            </c:otherwise>
        </c:choose>

        <manage:paymentType fieldName="paymentTypes"
							label="<b>优惠券支持的付款方式" labelComplement="(默认支持支付宝，微信)：</b>"
							supportPaymentIds="${voucherTypePo.paymentType}" />
        <div class="form-group">
            <label><b>优惠券领取限定：</b>
             <span>
            <c:if test="${voucherTypePo.fetchType ==0 }">只能领取一次</c:if>
            <c:if test="${voucherTypePo.fetchType ==1 }">可以领取多次</c:if>
            </span>
            </label>
        </div>
        
        <div class="form-group">
            <label><b>优惠券是否可以叠加使用：</b>
                <span>${voucherTypePo.typeStatus.name}</span>
            </label>
        </div>
        <div class="form-group">
            <label><b>优惠可领取时间段：</b>
	            <span>
		            <fmt:formatDate value="${voucherTypePo.startTime}"
										type="both" dateStyle="long" pattern="yyyy-MM-dd" /> 至
		            <fmt:formatDate value="${voucherTypePo.expireTime}"
										type="both" dateStyle="long" pattern="yyyy-MM-dd" />
	            </span>
            </label>
        </div>

        <div class="form-group">
            <label><b>过期类型:</b>
             <span>${voucherTypePo.voucherExpireType.name}</span>
            </label>
        </div>

        <div class="form-group">
            <label><b>有效期（领取后X天内可用）：</b>
	            <span>
	           	 	${voucherTypePo.periodDays }
	            </span>
            </label>
        </div>
        <div class="form-group">
            <label><b>创建数量：</b>
	             <span>
                         ${voucherTypePo.issueCount }
                 </span>
            </label>
        </div>
        <div class="form-group">
            <label><b>优惠券池阀值（少于此数量时给通知人发邮件通知）：</b>
	            <span>
                        ${voucherTypePo.minCountInPool}
                </span>
            </label>
        </div>
        <div class="form-group">
            <label><b>通知人邮箱：</b>
	            <span>
	            	${voucherTypePo.mailTo }
	            </span>
            </label>
        </div>

        <div class="form-group">
            <label><b>使用说明：</b></label>
            ${voucherTypePo.description }
        </div>
        
        <div class="form-group"
							style="display: block; text-align: center; margin-bottom: 60px;">
            <input type="button" class="btn btn-info" value="返回"
								onclick="history.go(-1)" />
     </form>
     </div>
    </div>
</div>
 </jsp:body>
</manage:page>
