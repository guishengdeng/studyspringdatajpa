<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<manage:page title="新建优惠券类型">
    <jsp:attribute name="css">
        <link rel="stylesheet" href="static-resources/datetimepicker/jquery.datetimepicker.css"/>
        <style type="text/css">
            .xdsoft_timepicker {
                display: none !important;
            }

            .time-range-join {
                line-height: 1.2142em;
                display: inline-block;
                padding: 0.67861429em 1em;
            }
        </style>
    </jsp:attribute>
<jsp:attribute name="script">
    <script type="text/javascript"
            src="static-resources/datetimepicker/jquery.datetimepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static-resources/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $.datetimepicker.setLocale('ch');
        $('.datepicker').datetimepicker({
            format: 'Y-m-d'
        });
        $("select").dropdown({
            fullTextSearch: true
        });
        $('.ui.radio.checkbox').checkbox();
        $(".checkbox.limit-type-radio").click(checkVoucherLimitType);
        $(".checkbox.expire-type-radio").click(updateVoucherExpireType);
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
            $("#productTypeDiv.field").addClass("disabled");
            $(".productIds.field").addClass("disabled");
        } else if (limitTypeValue == 'BY_CATEGORY') {
            $("#productTypeDiv.field").removeClass("disabled");
            $(".productIds.field").addClass("disabled");
        } else {
            $("#productTypeDiv.field").addClass("disabled");
            $(".productIds.field").removeClass("disabled");
        }
    }

    function updateVoucherExpireType(){
        var expireTypeValue = $("input[name=voucherExpireType]:checked").val();
        if (expireTypeValue == 'EXPIRE_BY_DATE_RANGE') {
            $(".periodDays.field").addClass("disabled");
        } else {
            $(".periodDays.field").removeClass("disabled");
        }
    }

    $(".submit.button").click(function () {
        var datepickerIssue = $("#datepickerIssue").val();
        var datepickerExpire = $("#datepickerExpire").val();
        var $name = $("#name");
        var faceValue = $("#faceValue").val();
        var voucherExpireType = $("input[name=voucherLimitType]:checked").val();
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
        } else if (voucherExpireType == 'EXPIRE_BY_PERIOD' && periodDays == "") {
            layer.msg(voucherExpireType == 'EXPIRE_BY_PERIOD' && "有效期不能为空！");
        } else if (voucherExpireType == 'EXPIRE_BY_PERIOD' && !periodDays && Number(periodDays) < 1) {
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
        <div class="ui main container">
            <h3 class="ui header">新建优惠券类型</h3>

            <div class="ui divider"></div>
            <form id="form" class="ui form" action="manage/voucherType/save.do" method="post">
                <div class="field">
                    <label>优惠券类型名称</label>
                    <input type="text" id="name" name="name" placeholder="优惠券类型名称"
                           required="required">
                </div>
                <div class="field">
                    <label>优惠券面值</label>
                    <input type="number" min="1" id="faceValue" name="faceValue" placeholder="优惠券面值"
                           required="required">
                </div>
                <div class="field" id="limitInp">
                    <label>使用限额(订单金额需要达到多少钱才能使用)</label>
                    <input type="number" min="0" name="paymentLimit" id="paymentLimit" value="0"
                           placeholder="使用限额">
                </div>


                <div class="inline fields">
                    <label>使用限制:</label>

                    <div class="field">
                        <c:forEach var="voucherLimitType" items="${voucherLimitTypes}"
                                   varStatus="varStatus">
                            <div class="ui radio checkbox limit-type-radio">
                                <input type="radio" name="voucherLimitType"
                                       <c:if test="${varStatus.index eq 0}">checked</c:if>
                                       value="${voucherLimitType.name()}">
                                <label>${voucherLimitType.description}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="field disabled" id="productTypeDiv">
                    <label>优惠券适用的商品分类</label>
                    <select class="ui search dropdown" name="productType"
                            id="productTypeSel">
                        <option value="-1">选择商品分类</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <manage:product label="适用的商品" fieldName="productIds"
                                fieldClasses="productIds field disabled"/>

                <div class="field">
                    <label>优惠券是否可以叠加使用</label>
                    <select id="typeStatus" class="ui search dropdown" name="typeStatus">
                        <option value="">选择是否可以叠加使用</option>
                        <c:forEach items="${voucherTypeStatusArray}" var="voucherTypeStatus">
                            <option value="${voucherTypeStatus.name()}">${voucherTypeStatus.description}</option>
                        </c:forEach>
                    </select>
                </div>

                <manage:paymentType fieldName="paymentType" supportPaymentIds="1,21,22"/>

                <div class="field">
                    <label>优惠券领取限定</label>
                    <select class="ui search dropdown" name="fetchType" id="fetchTypeSel">
                        <option value="0">只能领取一次</option>
                        <option value="1">可以领取多次</option>
                    </select>
                </div>

                <div class="field">
                    <label>优惠有效时间段</label>
                    <input type="text" style="width: 150px;" name="startTime" class="datepicker"
                           id="datepickerIssue" placeholder="开始时间"/>
                    <span class="time-range-join">到</span>
                    <input type="text" style="width: 150px;" name="expireTime" class="datepicker"
                           id="datepickerExpire" placeholder="结束时间"/>
                </div>
                <div class="inline fields">
                    <label>过期类型:</label>
                    <div class="field">
                        <c:forEach var="voucherExpireType" items="${voucherExpireTypes}" varStatus="varStatus">
                            <div class="ui radio checkbox expire-type-radio">
                                <input type="radio" name="voucherExpireType" <c:if test="${varStatus.index eq 0}">checked</c:if>
                                       value="${voucherExpireType.name()}">
                                <label>${voucherExpireType.description}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="periodDays field disabled">
                    <label>有效期（领取后X天内可用）</label>
                    <input type="number" min="1" id="periodDays" name="periodDays"
                           placeholder="优惠券有效期">
                </div>
                <div class="field">
                    <label>创建数量</label>
                    <input type="number" min="0" id="issueCount" name="issueCount" placeholder="数量">
                </div>
                <div class="field">
                    <label>优惠券池阀值（少于此数量时给通知人发邮件通知）</label>
                    <input type="text" id="minCountInPool" name="minCountInPool"
                           placeholder="优惠券池阀值">
                </div>
                <div class="field">
                    <label>通知人</label>
                    <select name="mailTo" class="search">
                        <option value="">选择通知人</option>
                        <c:if test="${!empty listEnableAdmins }">
                            <c:forEach var="listEnableAdmin" items="${listEnableAdmins}">
                                <c:if test="${not empty listEnableAdmin.email}">
                                    <option value="${listEnableAdmin.email}">${listEnableAdmin.name }</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="field">
                    <label>使用说明</label>
                    <textarea rows="5" name="description"></textarea>
                </div>

                <div class="field">
                    <input type="button" class="ui primary button submit" value="提交"/>
                </div>
            </form>
        </div>
    </jsp:body>
</manage:page>
