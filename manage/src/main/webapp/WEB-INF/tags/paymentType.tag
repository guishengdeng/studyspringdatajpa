<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="excludePaymentIds" required="false" type="java.lang.Object" %>
<%@ attribute name="supportPaymentIds" required="false" type="java.lang.Object" %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<%@ attribute name="label" required="false" type="java.lang.String" %>
<%@ attribute name="labelComplement" required="false" type="java.lang.String" %>
<%@ attribute name="single" required="false" type="java.lang.Boolean" description="是否支持多选" %>
<div class="${empty fieldClasses ? 'form-group' : fieldClasses}">
    <label>${empty label ? '附加的支付方式' : label}
        <c:if test="${!empty labelComplement}">
            <span>${labelComplement}</span>
        </c:if>
    </label>
    <div class="col-sm-9">
	    <select name="${fieldName}" class="${single ? '' : 'multiple'}">
	        <option value="">请选择</option>
	        <depot:paymentType supportPaymentIds="${supportPaymentIds}" excludePaymentIds="${excludePaymentIds}" />
	    </select>
    </div>
</div>
<script type="application/javascript">
    $(function () {
        $(".paymentType-selector select").dropdown({
            fullTextSearch: true
        });
    })
</script>
