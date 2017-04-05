<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>角色列表</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../jquery/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../jquery/js/role.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>
<body>
    <%--引用公共界面--%>
    <jsp:include page="commonNavBar.jsp"/>
    <table width="100%" class="table table-bordered" border="1">
        <thead>
            <tr>
                <th>id</th>
                <th>角色</th>
                <th>描述</th>
                <th>资源</th>
                <th>目录(子目录)</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${roles}" var="role">
                <tr>
                    <td>${role.role_id}</td>
                    <td>${role.name}</td>
                    <td>${role.description}</td>
                    <td>
                        <c:if test="${not empty role.resources}">
                            <c:forEach items="${role.resources}" var="resource">
                                <span>${resource.resourcename}</span>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${not empty role.menuItems}">
                            <c:forEach items="${role.menuItems}" var="menuitem">
                                <span>${menuitem.name}</span>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td>
                        <button type="button" class="btn btn-info" data-toggle="modal" onclick="editInfo(${role.role_id})" data-target="#addOrUpdateRoleModal">编辑</button>
                        &nbsp;&nbsp;
                        <button type="button" class="btn btn-danger" onclick="deleteInfo(${role.role_id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-sm" data-toggle="modal"  data-target="#addOrUpdateRoleModal" id="addRole">
    添加角色
</button>
<!-- 添加(修改)模态框示例实例 -->
<%--引用模态框的jsp页面--%>
<jsp:include page="rolemodal.jsp"></jsp:include>
</body>
</html>
