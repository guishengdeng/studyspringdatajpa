<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <%@include file="/WEB-INF/views/manage/common/commonMeta.jsp" %>
    <title>编辑优惠券类型</title>
    <%@include file="/WEB-INF/views/manage/common/commonJs.jsp" %>
    <%@include file="/WEB-INF/views/manage/common/commonCss.jsp" %>
    <script>
    </script>
</head>
<body>
<c:import url="/WEB-INF/views/manage/common/header.jsp"/>
<div class="ui container">
    <form id="form" class="ui form" action="${pageContext.request.contextPath}/manage/voucherType/update.do" method="post">
        <h3 class="ui header" style="margin-top: 10px">
                   编辑优惠券类型
        </h3>
        <div class="ui divider"></div>
        <input type="hidden" name="id" value="${voucherType.id}"/>
        <div class="field">
            <label>优惠券类型名</label>
            <input type="text" id="name" name="name" value="${voucherType.name}">
        </div>
        <div class="field" style="display: block;text-align: center;margin-bottom: 60px;">
            <input type="botton" class="ui primary button" value="提交" onclick="subForm();"/>
            <input type="hidden" class="ui button" value="重置"/>
        </div>
    </form>
</div>
<c:import url="/WEB-INF/views/manage/common/footer.jsp"/>
</body>
</html>
