<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select name="${param.fieldName}">
    <option value="0">不限</option>
    <c:forEach var="address" items="${regions}">
        <option value="${address.id}" >${address.name}</option>
    </c:forEach>
</select>
