<%@ tag import="com.biz.gbck.dao.mysql.po.stock.StockCategoryEnum" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="spiritLabel" required="false" type="java.lang.String" %>
<%@ attribute name="berrLabel" required="false" type="java.lang.String" %>
<%@ attribute name="redWineLabel" required="false" type="java.lang.String" %>
<%@ attribute name="foreignWineLable" required="false" type="java.lang.String" %>
<%@ attribute name="elseLabel" required="false" type="java.lang.String" %>
<%
    request.setAttribute("_categoryArray_", StockCategoryEnum.values());
%>
    <select name="${fieldName}">
        <c:if test="${withNone}">
            <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
        </c:if>
        <c:forEach items="${_categoryArray_}" var="_categoryStatus_" varStatus="varStatus">
            <option value="${_categoryStatus_.name()}" ${selectedStatus eq _categoryStatus_ ? 'selected' : ''} >${_categoryStatus_.name}</option>
        </c:forEach>
    </select>
</select>