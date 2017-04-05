<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>主菜单页面</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../jquery/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../jquery/js/mainmenu.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>
<body>
<%--引用公共界面--%>
<jsp:include page="commonNavBar.jsp"/>
<table width="100%" class="table table-bordered" border="1">
    <thead>
        <tr>
            <th>id</th>
            <th>代码</th>
            <th>名称</th>
            <th>描述</th>
            <th>子目录</th>
            <th>操作</th>
        </tr>
        </thead>
    <tbody>
    <c:forEach items="${mainmenus}" var="mainmenu">
        <tr>
            <td>${mainmenu.id}</td>
            <td>${mainmenu.code}</td>
            <td>${mainmenu.name}</td>
            <td>${mainmenu.description}</td>
            <td>
                <c:if test="${not empty mainmenu.menuItems}">
                    <c:forEach items="${mainmenu.menuItems}" var="menuitem">
                        <span>${menuitem.name}</span>
                    </c:forEach>
                </c:if>
            </td>
            <td>
                <button type="button" class="btn btn-info" data-toggle="modal" onclick="editInfo(${mainmenu.id})" data-target="#addOrUpdateMainMenuModal">编辑</button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-danger" onclick="deleteInfo(${mainmenu.id})">删除</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-sm" data-toggle="modal"  data-target="#addOrUpdateMainMenuModal" id="addMainMenu">
     添加主目录
</button>
<!-- 添加(修改)模态框示例实例 -->
<%--引用模态框的jsp页面--%>
<jsp:include page="mainmenumodal.jsp"></jsp:include>
</body>
</html>
