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
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
           $('.confirm').bind('click',function(){

               $.ajax({
                   cache: true,
                   type: "POST",
                   url:'../user/updatesubmit.action',
                   data:$('#formid').serialize(),// 你的formid

                   error: function(request) {
                       alert("Connection error");
                   },
                   success: function(data) {
                       alert(data);
                       window.location.href="../user/userlist.action";

                   }
               });
            });

        });

    </script>

</head>
<body>
<form  id="formid">
   <%-- <input type="hidden" name="id" value="${user.id }"/>--%>

    <table  border="1" width="100%">
        <tr>
            <td>用户id：</td>
            <td>
                <input type="text" name="id" readonly="readonly" value="${user.id }"/>
            </td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>
                <input type="text" name="username" readonly="readonly" value="${user.username }"/>
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
                <input type="text" name="email"  value="${user.email }"/>
            </td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td>
                <input type="text" name="age"   value="${user.age }"/>
            </td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                <input type="text" name="sex"  value="${user.sex }"/>
            </td>
        </tr>
    </table>

    <input type="button" value="提交" class="confirm"/>

</form>
</body>
</html>
