<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>添加角色页面</title>
</head>
<body>
<form  action="/role/updateOrAddSubmit.action" method="post">

    <table  border="1" width="100%">
        <tr>
            <td>角色id：</td>
            <td>
                <input type="text" name="role_id" value=""/>
            </td>
        </tr>
        <tr>
            <td>角色名字：</td>
            <td>
                <input type="text" name="name" value=""/>
            </td>
        </tr>

        <tr>
            <td>描述：</td>
            <td>
                <input type="text" name="description" value=""/>
            </td>
        </tr>

        <tr>
            <td>资源：</td>
            <td>
                <c:if test="${not empty resources}">
                    <c:forEach items="${resources}" var="resource">
                        <input type="checkbox" name="id" value="${resource.id}"/>${resource.resourcename}
                    </c:forEach>
                </c:if>
            </td>
        </tr>
    </table>

    <input type="submit" value="提交"/>

</form>
</body>
</html>
