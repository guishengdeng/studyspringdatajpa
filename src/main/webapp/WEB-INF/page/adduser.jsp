<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/13
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<form  action="${pageContext.request.contextPath }/user/adduser.action" method="post">

    <table  border="1" width="100%">
        <tr>
            <td>用户id：</td>
            <td>
                <input type="text" name="id" value=""/>
            </td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" value=""/>
            </td>
        </tr>

        <tr>
            <td>密码：</td>
            <td>
                <input type="text" name="password" value=""/>
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input type="text" name="email" value=""/>
            </td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td>
                <input type="text" name="age" value=""/>
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="text" name="sex" value=""/>
            </td>
        </tr>
        <tr>
            <td>角色：</td>
            <td>
                    <c:if test="${not empty roles}">
                         <c:forEach items="${roles}" var="rolename">
                              <%--备注：name="name" 对应Role实体类的name属性--%>
                              <input type="checkbox" name="name" value="${rolename}"/>${rolename}

                         </c:forEach>
                    </c:if>
            </td>
        </tr>
    </table>

    <input type="submit" value="提交"/>

</form>
</body>
</html>
