<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>子菜单页面</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../jquery/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../jquery/js/menuitem.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>
<body>
<%--引用公共界面--%>
<jsp:include page="commonNavBar.jsp"/>
<table width="100%" class="table table-bordered" border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>名称</th>
        <th>链接</th>
        <th>描述</th>
        <th>符号</th>
        <th>父级目录(多对一)</th>
        <%-- @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="menuItem")
        private List<Resource> resources;mappedBy="menuItem"可以看出MenuItem和Resource之间的维护级联关系
        Resource是维护级联关系的维护者，MenuItem是被被维护者 所以这里不写资源
        --%>
        <th>角色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${menuItems}" var="menuitem">
        <tr>
            <td>${menuitem.menuitem_id}</td>
            <td>${menuitem.name}</td>
            <td>${menuitem.link}</td>
            <td>${menuitem.description}</td>
            <td>${menuitem.symbol}</td>
            <td>
                <c:if test="${not empty menuitem.mainMenu}">
                    <span>${menuitem.mainMenu.name}</span>
                </c:if>
            </td>
            <td>
                <c:if test="${not empty menuitem.roles}">
                     <c:forEach items="${menuitem.roles}" var="role">
                         <span>${role.name}</span>
                     </c:forEach>
                </c:if>
            </td>
            <td>
                <button type="button" class="btn btn-info" data-toggle="modal" onclick="editInfo(${menuitem.menuitem_id})" data-target="#addOrUpdateMenuItemModal">编辑</button>
                &nbsp;&nbsp;
                <button type="button" class="btn btn-danger" onclick="deleteInfo(${menuitem.menuitem_id})">删除</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-sm" data-toggle="modal"  data-target="#addOrUpdateMenuItemModal" id="addMenuItem">
    添加子目录
</button>
<!-- 添加(修改)模态框示例实例 -->
<%--引用模态框的jsp页面--%>
<jsp:include page="menuitemmodal.jsp"></jsp:include>
</body>
</html>
