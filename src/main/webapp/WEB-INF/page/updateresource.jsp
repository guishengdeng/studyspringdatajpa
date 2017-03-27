<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改资源页面</title>
</head>
<body>
<form  id="formid" action="/resource/updateOrAddSubmit.action" method="post">
    <table  border="1" width="100%">
        <tr>
            <td>id：</td>
            <td>
                <input type="text" name="id" readonly="readonly" value="${resource.id }"/>
            </td>
        </tr>
        <tr>
            <td>资源名：</td>
            <td>
                <input type="text" name="resourcename" value="${resource.resourcename }"/>
            </td>
        </tr>

        <tr>
            <td>描述：</td>
            <td>
                <input type="text" name="description" value="${resource.description}"/>
            </td>
        </tr>


    </table>

    <input type="submit" value="提交" class="confirm"/>

</form>
</body>
</html>
