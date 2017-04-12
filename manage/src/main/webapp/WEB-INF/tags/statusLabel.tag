<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depotnextdoorTag" uri="http://com.depotnextdoor/tag/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="selectedStatus" required="true" type="java.lang.Integer" %>

<c:set var="labelClasses"
       value="${selectedStatus eq 0 ? 'label label-lg label-purple arrowed' : 'label label-lg label-primary arrowed-right'}"/>

<c:choose>
    <c:when test="${selectedStatus eq 0}">
        禁用
    </c:when>
    <c:otherwise>
        启用
    </c:otherwise>
</c:choose>
