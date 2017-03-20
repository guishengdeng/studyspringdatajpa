<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/18
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>403页面</title>
    <style type="text/css">
         body{
             background-color: #DEDEDE;
        }
          div{
               margin:300px 400px;
               color: red;
               font-size: 20px;
          }

    </style>
</head>
<body>
       <div>Dear: <strong>${user}</strong> 你无权进行此操作，请联系管理员。</div>
       <a href="<c:url value="/page/loginout.action" />"></a>
</body>
</html>
