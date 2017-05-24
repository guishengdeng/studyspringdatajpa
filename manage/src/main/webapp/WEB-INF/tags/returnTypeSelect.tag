
<%@ tag import="com.biz.gbck.enums.order.ReturnType" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%
    request.setAttribute("_returnTypeArray_", ReturnType.values());
%>
<select name="${fieldName}">
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <c:forEach items="${_returnTypeArray_}" var="_returnType_" varStatus="varStatus">
        <option value="${_returnType_.name()}" ${selectedStatus eq _returnType_ ? 'selected' : ''} >${_returnType_.desc}</option>
    </c:forEach>
</select>
