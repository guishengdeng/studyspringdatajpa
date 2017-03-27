<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/13
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
        <tr>
            <td>角色：</td>
            <td>
                 <%--这是用户点击修改时，其本身都具有的角色--%>
                 <c:if test="${not empty user.roles}">
                     <c:forEach items="${user.roles}" var="role">
                         <input type="checkbox" name="role_id" value="${role.role_id}" checked="checked"/>${role.name}
                     </c:forEach>
                 </c:if>
                 <%--数据来源于数据库 role表中的数据--%>
                  <%--备注：从form表单的复选框要想后台传值时，一定要在value="${}"--%>
                 <c:if test="${not empty roles}">
                         <c:forEach items="${roles}" var="role">
                             <input type="checkbox" name="role_id" value="${role.role_id}"/>${role.name}
                         </c:forEach>
                 </c:if>
            </td>
        </tr>
    </table>

    <input type="button" value="提交" class="confirm"/>

</form>
</body>
</html>
