<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>资源列表</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../jquery/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../jquery/js/resource.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>
<body>
<%--引用公共界面--%>
<jsp:include page="commonNavBar.jsp"/>
    <table class="table table-bordered" width="100%" border="1">
        <tr>
            <th>id</th>
            <th>资源名</th>
            <th>描述</th>
            <th>链接地址</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${resources}" var="resource">
            <tr>
                <td>${resource.id}</td>
                <td>${resource.resourcename}</td>
                <td>${resource.description}</td>
                <td>${resource.linkedaddress}</td>
                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal" onclick="editInfo(${resource.id})" data-target="#addOrUpdateRosourceModal">编辑</button>
                    &nbsp;&nbsp;
                    <button type="button" class="btn btn-danger" onclick="deleteInfo(${resource.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<div>${data}</div>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-sm" data-toggle="modal"  data-target="#addOrUpdateRosourceModal">
    添加资源
</button>
<%--引用资源模态框的jsp页面--%>
<jsp:include page="resourcemodal.jsp"/>
</body>
</html>
