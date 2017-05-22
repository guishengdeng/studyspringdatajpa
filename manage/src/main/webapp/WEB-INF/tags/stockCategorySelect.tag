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
<select name="${fieldName}"><%--字段名--%>
    <c:if test="${withNone}">
        <option value="" ${empty selectedStatus ? 'selected' : ''} >${empty noneLabel ? '不限' : noneLabel}</option>
    </c:if>
    <option value="白酒" ${selectedStatus eq '白酒' ? 'selected' : ''} >${empty spiritLabel ? '白酒' : spiritLabel}</option>
    <option value="啤酒" ${selectedStatus eq '啤酒' ? 'selected' : ''} >${empty berrLabel ? '啤酒' : berrLabel}</option>
    <option value="红酒" ${selectedStatus eq '红酒' ? 'selected' : ''} >${empty redWineLabel ? '红酒' : redWineLabel}</option>
    <option value="洋酒" ${selectedStatus eq '洋酒' ? 'selected' : ''} >${empty foreignWineLable ? '洋酒' : foreignWineLable}</option>
    <option value="其他" ${selectedStatus eq '其他' ? 'selected' : ''} >${empty elseLabel ? '其他' : elseLabel}</option>
</select>