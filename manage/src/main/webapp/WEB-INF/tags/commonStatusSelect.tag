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
    <option value="ENABLE" ${selectedStatus eq 'ENABLE' ? 'selected' : ''} >${empty enableLabel ? '启用' : enableLabel}</option>
    <option value="DISABLE" ${selectedStatus eq 'DISABLE' ? 'selected' : ''} >${empty disableLabel ? '禁用' : disableLabel}</option>
</select>