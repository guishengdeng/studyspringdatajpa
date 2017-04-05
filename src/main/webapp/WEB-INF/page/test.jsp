<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/28
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面,专门用来学习SpringMVC的不常用的注解,以加深了解</title>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
         $(function(){
             $("#profile").click(function() {

                 profile();
             });
             $("#login").click(function() {
                 login();
             });
         });
         function profile(){
             var query = $('#id').val() + '/' + $('#name').val() + '/'
                 + $('#status').val();
             var url = '../person/profiles/'+query+'.action';
             alert(url);
             $.get(url, function(data) {
                 //{"personVoTest":[{"id":1,"name":"张三","status":false},{"id":2,"name":"李思","status":true}]}
                      alert(data.id+"----"+data.name+"----"+data.status);
             },"json");
         }

             function login() {
                 alert($('#form_data').serialize());
                 $.ajax({
                     type : 'POST',
                  /*   contentType : 'application/json',*/
                     url :'../person/login.action',
                     dataType : 'json',
                     data : $('#form_data').serialize(),
                     success : function(data){
                         alert("id: " + data.id + "\nname: " + data.name + "\nstatus: "
                             + data.status);
                     },
                     error : function() {
                         alert('Err...');
                     }
                 });
              }

    </script>
</head>
<body>
      <form action="" id="form_data" method="post">
            <table>
                <tr>
                    <td>id</td>
                    <td><input id="id" name="id" value="100" /></td>
                </tr>
                <tr>
                    <td>name</td>
                    <td><input id="name" name ="name" value="snowolf" /></td>
                </tr>
                <tr>
                    <td>status</td>
                    <td><input id="status" name ="status" value="true" /></td>
                </tr>
                <tr>
                    <td><input type="button" id="profile" value="Profile——GET" /></td>
                    <td><input type="button" id="login" value="Login——POST" /></td>
                </tr>
            </table>
      </form>
</body>
</html>
