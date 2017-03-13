<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/13
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <%--<script type="text/javascript">
        $(function () {
            alert("hello world");
        });
    </script>--%>
    <script type="text/javascript">
          function deleteUser(){
              return false;
          }
          function add() {
              window.location.href="adduser.jap";
          }
    </script>
</head>

<body>

        <form name="userForm" action="${pageContext.request.contextPath}/user/userlist.action" method="post">

            <%--<table width="100%" border=1>
                <tr>
                    <td>查询条件：<input name="itemsCustomer.name" id="condition" /></td>
                    <td>
                        <input type="button" value="查询" onclick="queryItems()" />

                    </td>
                </tr>
            </table>--%>
            <table width="100%" border="1">
                <tr>
                    <th>id</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>密码</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.age}</td>
                        <td>${user.sex}</td>
                        <td>${user.email}</td>
                        <td>${user.password}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/update.action?id=${user.id}">修改</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/delete.action?id=${user.id}"
                               onclick="deleteUser()">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <a href="${pageContext.request.contextPath}/user/add.action">添加</a>

</body>
</html>
