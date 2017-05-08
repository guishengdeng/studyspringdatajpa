<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:sidebar icon="fa fa-list" fixed="true">
    <%-- <gbck:singleMenu text="新增" link="qrcode/new.do" icon="fa fa-plus" /> --%>
    <gbck:menuGroup text="是否删除" icon="fa fa-list">
        <gbck:singleMenu text="否" link="qrcode/list.do"/>
        <gbck:singleMenu text="是" link="qrcode/list.do?status=DISABLE"/>
    </gbck:menuGroup>
</gbck:sidebar>