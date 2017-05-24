<%@ tag import="com.biz.gbck.enums.order.RefundStatus" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%
    request.setAttribute("_refundStatusArray_", RefundStatus.values());
%>
<select name="${fieldName}">
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <c:forEach items="${_refundStatusArray_}" var="_refundStatus_" varStatus="varStatus">
        <option value="${_refundStatus_.name()}" ${selectedStatus eq _refundStatus_ ? 'selected' : ''} >${_refundStatus_.desc}</option>
    </c:forEach>
</select>