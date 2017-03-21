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
    <script type="text/javascript">
          function deleteUser(){

              return false;//设置为false目的是为了不让点击超链接时，让其跳转到目标连接
          }
         function queryUser(){
              document.userForm.action = "${pageContext.request.contextPath }/user/userlist.action";
              document.userForm.submit();

          }
    </script>
</head>

<body>

       <%-- <c:if test="${empty username}">
            欢迎您:<span style="color:red;">${username}</span>
        </c:if>
        <c:if test="${not empty username}">
            <a href="/user/loginout.action" style="color: blue">退出</a>
        </c:if>--%>
       欢迎您:<span style="color:red;">${username}</span> <a href="/user/loginout.action" style="color: blue">退出</a>
        <form name="userForm" action="${pageContext.request.contextPath}/user/userlist.action" method="post">

           <%-- <table width="100%" border=1>
                <tr>
                    <td>查询条件：<input name="username" placeholder="请输入用户名" /></td>
                    <td>
                        <input type="button" value="查询" onclick="queryUser()" />

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
                    <th>角色</th>
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
                            <c:if test="${not empty user.roles}">
                                  <c:forEach items="${user.roles}" var="role">
                                       <span>${role.name}</span>
                                  </c:forEach>
                            </c:if>
                        </td>
                        <td>
                            <a href="/user/update.action?id=${user.id}" name="${user.id}" class="update">修改</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/delete.action?id=${user.id}"
                               onclick="deleteUser()">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <a href="/user/add.action">添加</a>

</body>
</html>
