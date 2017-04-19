<%@ tag import="com.biz.gbck.dao.mysql.po.demo.SaleStatusEnum" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%@ attribute name="inline" required="false" type="java.lang.Boolean" %>
<%
    request.setAttribute("_saleStatusArray_", SaleStatusEnum.values());
%>
<div class="control-group">
<c:forEach items="${_saleStatusArray_}" var="_saleStatus_" varStatus="varStatus">
    <div class="radio ${inline ? 'inline' : ''}">
        <label>
            <input name="${fieldName}" type="radio" class="ace" value="${_saleStatus_.name()}"
            ${empty selectedStatus and varStatus.index == 0 ? 'checked' : ''} ${selectedStatus eq _saleStatus_ ? 'checked' : ''}>
            <span class="lbl">${_saleStatus_.name}</span>
        </label>
    </div>
</c:forEach>
</div>