<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="enableLabel" required="false" type="java.lang.String" %>
<%@ attribute name="disableLabel" required="false" type="java.lang.String" %>
<select name="${fieldName}">
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <option value="NEW" ${selectedStatus eq 'NEW' ? 'selected' : ''} >${empty enableLabel ? '生成订单' : enableLabel}</option>
    <option value="PACKAGE" ${selectedStatus eq 'PACKAGE' ? 'selected' : ''} >${empty disableLabel ? '备货中' : disableLabel}</option>
    <option value="DELIVERED" ${selectedStatus eq 'DELIVERED' ? 'selected' : ''} >${empty disableLabel ? '已发货' : disableLabel}</option>
    <option value="SIGNED_PART" ${selectedStatus eq 'SIGNED_PART' ? 'selected' : ''} >${empty disableLabel ? '部分收货' : disableLabel}</option>
    <option value="SIGNED_ALL" ${selectedStatus eq 'SIGNED_ALL' ? 'selected' : ''} >${empty disableLabel ? '已收货' : disableLabel}</option>
</select>