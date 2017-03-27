<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>修改角色页面</title>
</head>
<body>
<form  id="formid" action="/role/updateOrAddSubmit.action" method="post">
    <table  border="1" width="100%">
        <tr>
            <td>id：</td>
            <td>
                <input type="text" name="role_id" readonly="readonly" value="${role.role_id }"/>
            </td>
        </tr>
        <tr>
            <td>角色：</td>
            <td>
                <input type="text" name="name" readonly="readonly" value="${role.name }"/>
            </td>
        </tr>

        <tr>
            <td>描述：</td>
            <td>
                <input type="text" name="description" value="${role.description }"/>
            </td>
        </tr>
        <tr>
            <td>资源：</td>
            <td>
                     <%--这是该角色本身都所具有的资源--%>
                         <c:if test="${not empty role.resources}">
                           <c:forEach items="${role.resources}" var="resource">
                                 <input type="checkbox" name="id" value="${resource.id}" checked="checked"/>${resource.resourcename}
                           </c:forEach>
                         </c:if>
                     <%--这是从数据库取出来的，该角色选择资源--%>
                         <c:if test="${not empty resources}">
                             <c:forEach items="${resources}" var="resource">
                                 <input type="checkbox" name="id" value="${resource.id}" />${resource.resourcename}
                             </c:forEach>
                         </c:if>
            </td>
        </tr>

    </table>

    <input type="submit" value="提交" class="confirm"/>

</form>
</body>
</html>
