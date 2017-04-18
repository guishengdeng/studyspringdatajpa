<%@ tag import="com.biz.gbck.dao.mysql.po.enums.CommonStatusEnum" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depotnextdoorTag" uri="http://com.depotnextdoor/tag/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="selectedStatus" required="true" type="java.lang.Integer" %>

<%
    request.setAttribute("_commonStatusArray_", CommonStatusEnum.values());
%>
<c:forEach items="${_commonStatusArray_}" var="commonStatus">

</c:forEach>