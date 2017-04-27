<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<gbck:page title="欢迎">

    <jsp:body>
        <gbck:sidebar icon="fa fa-list">
            <gbck:singleMenu text="点我，我是猫，以后都照着我做" link="demo/cats.do" />
            <gbck:menuGroup text="这是个菜单组">
                <gbck:singleMenu text="菜单管理" link="manage/mainMenus.do" />
                <gbck:singleMenu text="角色管理" link="manage/roles.do" />
                <gbck:singleMenu text="二维码" link="qrcode/list.do" />
            </gbck:menuGroup>
            <gbck:singleMenu text="另一个菜单" link="manage/users.do" />
        </gbck:sidebar>
        <p>
            示例演示:猫管理，有创建及修改的前端及后台 <strong>数据验证</strong> 示例，列表页有页面通过js框架 <strong>dataTable分页</strong> 示例，也有 <strong>数据库分页</strong> 示例，请开发的时候做出相应的参考。
        </p>
    </jsp:body>
</gbck:page>
