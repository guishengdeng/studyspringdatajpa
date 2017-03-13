<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/13
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户页面</title>
</head>
<body>
<form  action="${pageContext.request.contextPath }/user/updatesubmit.action" method="post">
    <input type="hidden" name="id" value="${user.id }"/>

    <table  border="1" width="100%">
        <tr>
            <td>用户id：</td>
            <td>
                <input type="text" name="id" value="${user.id }"/>
            </td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" value="${user.username }"/>
            </td>
        </tr>

        <tr>
            <td>密码：</td>
            <td>
              <input type="text" name="password" value="${user.password }"/>
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input type="text" name="email" value="${user.email }"/>
            </td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td>
                <input type="text" name="age" value="${user.age }"/>
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="text" name="sex" value="${user.sex }"/>
            </td>
        </tr>
    </table>

    <input type="submit" value="提交"/>

</form>
</body>
</html>
