<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/24
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加资源页面</title>
</head>
<body>
<form  action="/resource/updateOrAddSubmit.action" method="post">

    <table  border="1" width="100%">
        <tr>
            <td>id：</td>
            <td>
                <input type="text" name="id" value=""/>
            </td>
        </tr>
        <tr>
            <td>资源名字：</td>
            <td>
                <input type="text" name="resourcename" value=""/>
            </td>
        </tr>

        <tr>
            <td>描述：</td>
            <td>
                <input type="text" name="description" value=""/>
            </td>
        </tr>
    </table>

    <input type="submit" value="提交"/>

</form>
</body>
</html>
