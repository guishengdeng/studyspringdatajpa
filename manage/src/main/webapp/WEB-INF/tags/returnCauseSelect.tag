<%@ tag import="com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum" %>
<%@ tag import="com.biz.gbck.enums.order.ReturnCause" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%
    request.setAttribute("_returnStatusArray_", ReturnCause.values());
%>
<select name="${fieldName}">
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <c:forEach items="${_returnStatusArray_}" var="_returnStatus_" varStatus="varStatus">
        <option value="${_returnStatus_.name()}" ${selectedStatus eq _returnStatus_ ? 'selected' : ''} >${_returnStatus_.name}</option>
    </c:forEach>
</select>