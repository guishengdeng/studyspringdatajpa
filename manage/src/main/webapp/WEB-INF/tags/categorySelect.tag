<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="true" type="java.lang.String" %>
<%@ attribute name="noneLabel" required="false" type="java.lang.String" %>
<%@ attribute name="id" required="false" type="java.lang.String" %>
<%@ attribute name="change" required="false" type="java.lang.String" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="categories" required="false" type="java.lang.Object" %>

<select name="${fieldName}" id="${id}" onchange="${change}">
    <c:if test="${withNone}">
        <option value="${noneLabel}" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <c:forEach items="${categories}" var="catetory" varStatus="varStatus">
         <%--如何在点击修改时,选中其对应的分类,并且将其设为只读--%>
        <option value="${catetory.id}" <c:if test="${selectedStatus == catetory.id}">selected </c:if>>${catetory.name} </option>
    </c:forEach>
</select>