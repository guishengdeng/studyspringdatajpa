<%@ tag import="com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%
    request.setAttribute("_saleStatusArray_", SaleStatusEnum.values());
%>
<select name="${fieldName}">
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <c:forEach items="${_saleStatusArray_}" var="_saleStatus_" varStatus="varStatus">
        <option value="${_saleStatus_.name()}" ${selectedStatus eq _saleStatus_ ? 'selected' : ''} >${_saleStatus_.name}</option>
    </c:forEach>
</select>