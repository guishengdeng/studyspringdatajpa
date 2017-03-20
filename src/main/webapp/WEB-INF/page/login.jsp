<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统登陆页面</title>
     <style type="text/css">
          /*.formDiv{
              margin: 200px 50px;
              border: 1px solid red;
              padding: 0px 400px;
          }*/
     </style>
</head>
<body>
      <div class="formDiv">
          <form action="/login" method="post">
              用户名：<input type="text" name="username" /><strong style="color:red;">${result}</strong> <br/>
              密码：<input type="password" name="password" /> <br/>
              <input type="submit" value="登陆"/><br/>
          </form>
      </div>

</body>
</html>