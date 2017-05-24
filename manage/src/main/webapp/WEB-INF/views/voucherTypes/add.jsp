<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags"%>
<manage:page title="新建优惠券类型">
	<jsp:attribute name="css">
	<link  rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-multiselect.min.css"/>
	<link rel="stylesheet" href="static-resource/ace/assets/css/select2.min.css" />
    </jsp:attribute>
	<jsp:attribute name="script">
	 <script type="text/javascript"
            src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>
     <script type="text/javascript"
            src="static-resource/ace/assets/js/select2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.datepicker').datetimepicker({
			format : 'Y-M-D'
		});
		$("select").dropdown({
            fullTextSearch: true
        });
		$(".checkbox-inline.limit-type-radio").click(checkVoucherLimitType);
        $(".checkbox-inline.expire-type-radio").click(updateVoucherExpireType);
        $("#sel_menu2").multiselect( {placeholder: "请选择",
                single: true});
	});

	// 时间段检测
	function checkDate() {
		var sDate = Date.parse($("#datepickerIssue").val());
		var eDate = Date.parse($("#datepickerExpire").val());
		if (sDate > eDate) {
			//console.info("结束日期不能小于开始日期");
			return false;
		}
		return true;
	}

	function checkVoucherLimitType() {
		var limitTypeValue = $("input[name=voucherLimitType]:checked").val();
		if (limitTypeValue == 'NONE') {
			document.getElementById("productTypeDiv").disabled=true;
			document.getElementById("productIdDiv").disabled=true;
		} else if (limitTypeValue == 'BY_CATEGORY') {
			document.getElementById("productTypeDiv").disabled=false;
			document.getElementById("productIdDiv").disabled=true;
		} else {
			document.getElementById("productTypeDiv").disabled=true;
			document.getElementById("productIdDiv").disabled=false;
		}
	}

	function updateVoucherExpireType() {
		var expireTypeValue = $("input[name=voucherExpireType]:checked").val();
		if (expireTypeValue == 'EXPIRE_BY_DATE_RANGE') {
			document.getElementById("periodDaysfieldset").disabled=true;
		} else {
			document.getElementById("periodDaysfieldset").disabled=false;
		}
	}

	$("#btn_save").click(
			function() {
				var datepickerIssue = $("#datepickerIssue").val();
				var datepickerExpire = $("#datepickerExpire").val();
				var $name = $("#name");
				var faceValue = $("#faceValue").val();
				var voucherExpireType = $(
						"input[name=voucherLimitType]:checked").val();
				var periodDays = $("#periodDays").val();
				var minCountInPool = $("#minCountInPool").val();
				var issueCount = $("#issueCount").val();
				var typeStatus = $("#typeStatus").val();
				var patternSel = $("#patternSel").val();
				var paymentLimit = $("#paymentLimit").val();

				if (!$name.val()) {
					layer.msg("名称不能为空！");
					$name.focus();
				} else if (faceValue == "") {
					layer.msg("面值不能为空！");
				} else if (Number(faceValue) <= 0) {
					layer.msg("面值不能小于0！");
				} else if (voucherExpireType == 'EXPIRE_BY_PERIOD'
						&& periodDays == "") {
					layer.msg(voucherExpireType == 'EXPIRE_BY_PERIOD'
							&& "有效期不能为空！");
				} else if (voucherExpireType == 'EXPIRE_BY_PERIOD'
						&& !periodDays && Number(periodDays) < 1) {
					layer.msg("有效期不能为空！");
				} else if (minCountInPool == "") {
					layer.msg("领优惠券池阀值不能为空！");
				} else if (Number(minCountInPool) <= 0) {
					layer.msg("领优惠券池阀值不能小于0！");
				} else if (issueCount == "") {
					layer.msg("创建数量不能为空！");
				} else if (Number(issueCount) <= 0) {
					layer.msg("创建数量不能小于0！");
				} else if (typeStatus == "") {
					layer.msg("请选择优惠券是否可以叠加使用！");
				} else if (datepickerExpire == "") {
					layer.msg("领取结束时间不能为空！");
				} else if (!checkDate()) {
					layer.msg("领取开始时间不能大于结束时间");
				} else if (!paymentLimit || Number(paymentLimit) < 0) {
					layer.msg("请填写正确的使用限额");
				} else {
					$("#form").submit();
				}
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
                <li><a href="${pageContext.request.contextPath}/manage/voucherType/list.do">
                    优惠券类型管理</a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        <div class="page-content">
            <h3 class="header smaller lighter blue">新建优惠券类型</h3>
           <div class="row">
               <div class="col-xs-12">
	            <form id="form" class="form-horizontal"
						action="manage/voucherType/save.do" method="post">
	                <div class="form-group">
	                    <label class="col-sm-2 control-label no-padding-right" for="name">优惠券类型名称</label>
	                    <div class="col-sm-9">
		                    <input type="text" id="name" name="name"
									placeholder="优惠券类型名称" required="required" class="form-control">
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right" for="faceValue">优惠券面值</label>
	                     <div class="col-sm-9">
		                    <input type="number" min="1" id="faceValue" class="form-control"
									name="faceValue" placeholder="优惠券面值" required="required">
		                 </div>
	                </div>
	                <div class="form-group" id="limitInp">
	                    <label
								class="col-sm-2 control-label no-padding-right"
								for="paymentLimit">使用限额(订单金额需要达到多少钱才能使用)</label>
	                    <div class="col-sm-9">
		                    <input type="number" min="0" name="paymentLimit" class="form-control"
									id="paymentLimit" value="0" placeholder="使用限额">
		                </div>
	                </div>
	
	
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right"
								for="voucherLimitType">使用限制:</label>
	                    	<div class="col-sm-9">
	                        <c:forEach var="voucherLimitType"
									items="${voucherLimitTypes}" varStatus="varStatus">
	                            <div class="checkbox-inline limit-type-radio">
	                                <input type="radio"
											name="voucherLimitType" 
											<c:if test="${varStatus.index eq 0}">checked</c:if>
											value="${voucherLimitType.name()}" class="ace">
	                                <label class="lbl" lbl>${voucherLimitType.description}</label>
	                            </div>
	                        </c:forEach>
	                    </div>
	                </div>
					<fieldset disabled id="productTypeDiv">
		                <div class="form-group">
		                    <label
									class="col-sm-2 control-label no-padding-right"
									for="productType">优惠券适用的商品分类</label>
		                     <div class="col-sm-9">
		                    <select class="form-control"
										name="productType" id="productTypeSel">
		                        <option value="-1">选择商品分类</option>
		                        <c:forEach var="category" items="${categories}">
		                            <option value="${category.id}">${category.name}</option>
		                        </c:forEach>
		                    </select>
		                    </div>
		                </div>
	                </fieldset>
	                
	                <fieldset id="productIdDiv" disabled>
		            <manage:product label="适用的商品" fieldName="productIds"
								fieldClasses="form-group" />
					</fieldset>			
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right"
								for="productType">优惠券是否可以叠加使用</label>
	                     <div class="col-sm-9">
	                    <select id="typeStatus" class="form-control"
									name="typeStatus">
	                        <option value="">选择是否可以叠加使用</option>
	                        <c:forEach items="${voucherTypeStatusArray}"
										var="voucherTypeStatus">
	                            <option value="${voucherTypeStatus.name()}">${voucherTypeStatus.description}</option>
	                        </c:forEach>
	                    </select>
	                    </div>
	                </div>
	                
	                <manage:paymentType fieldName="paymentType"
 							supportPaymentIds="1,21,22" /> 

	                <div class="form-group">
	                    <label class="col-sm-2 control-label"
								for="fetchType">优惠券领取限定</label>
	                     <div class="col-sm-9">
		                    <select class="form-control"
									name="fetchType" id="fetchTypeSel">
		                        <option value="0">只能领取一次</option>
		                        <option value="1">可以领取多次</option>
		                    </select>
	                    </div>
	                </div>
	
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right">优惠有效时间段</label>
	                    <div class="col-sm-9">
	                    	<div class="input-daterange input-group">
		                    <input type="text" style="width: 150px;" 
									name="startTime" class="datepicker" id="datepickerIssue"
									placeholder="开始时间" />
		                    <span class="group-addon">
		                    	<i class="fa fa-exchange"></i>
							</span>
		                    <input type="text" style="width: 150px;" 
									name="expireTime" class="datepicker" id="datepickerExpire"
									placeholder="结束时间" />
	                    </div></div>
	                </div>
	                 <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right">过期类型:</label>
	                   	<div class="col-sm-9">
	                        <c:forEach var="voucherExpireType"
									items="${voucherExpireTypes}" varStatus="varStatus">
	                             <div class="checkbox-inline expire-type-radio">
	                                <input type="radio"
											name="voucherExpireType" 
											<c:if test="${varStatus.index eq 0}">checked</c:if>
											value="${voucherExpireType.name()}" class="ace">
	                                <label class="lbl">${voucherExpireType.description}</label>
	                            </div>
	                        </c:forEach>
	                    </div>
	                </div>
	                
	                <fieldset disabled id="periodDaysfieldset">
	                <div class="form-group periodDays">
	                    <label
								class="col-sm-2 control-label no-padding-right">有效期（领取后X天内可用）</label>
	                    <div class="col-sm-9">
	                    <input type="number" min="1" id="periodDays" class="form-control "
									name="periodDays" placeholder="优惠券有效期">
	                           </div>
	                </div>
	                </fieldset>
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right" for="issueCount">创建数量</label>
	                     <div class="col-sm-9">
	                    	<input type="number" min="0" id="issueCount" class="form-control"
									name="issueCount" placeholder="数量">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right"
								for="minCountInPool">优惠券池阀值（少于此数量时给通知人发邮件通知）</label>
	                    <div class="col-sm-9">
	                    <input type="text" id="minCountInPool" class="form-control"
									name="minCountInPool" placeholder="优惠券池阀值">
	               		</div>
	                </div>
	                
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right" for="mailTo">通知人</label>
	                    <div class="col-sm-9">
		                    <select name="mailTo" class="form-control">
		                        <option value="">选择通知人</option>
		                        <c:if test="${!empty listEnableAdmins }">
		                            <c:forEach var="listEnableAdmin"
											items="${listEnableAdmins}">
<%-- 		                                <c:if test="${not empty listEnableAdmin.email}"> --%>
<%-- 		                                    <option value="${listEnableAdmin.email}">${listEnableAdmin.name }</option> --%>
<%-- 		                                </c:if> --%>
		                            </c:forEach>
		                        </c:if>
		                    </select>
	                    </div>
	                </div>
	
	                <div class="form-group">
	                    <label
								class="col-sm-2 control-label no-padding-right"
								for="description">使用说明</label>
	                    <div class="col-sm-9">
	                    <textarea rows="3" col="20" name="description" class="form-control"></textarea>
	                    </div>
	                </div>
	
	                <div class="clearfix form-actions">
                       <div class="col-md-offset-3 col-md-9">
                           <button class="btn btn-info" type="submit"
									id="btn_save">
                               <i
										class="ace-icon fa fa-check bigger-110"></i>
                               	保存
                           </button>
                           &nbsp; &nbsp; &nbsp;
                           <button class="btn" type="reset">
                               <i class="ace-icon fa fa-undo bigger-110"></i>
                               	重置
                           </button>
                       </div>
                   </div>
	            </form>
	          </div>
	       </div>
        </div>
    </jsp:body>
</manage:page>
