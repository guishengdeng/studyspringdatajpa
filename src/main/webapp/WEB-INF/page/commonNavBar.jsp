<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/26
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>公共导航条</title>

</head>
<body>
<a href="/user/userlist.action">用户列表</a>
<a href="/role/rolelist.action">角色列表</a>
<a href="/resource/resourcelist.action">资源列表</a>
    <div class="welcome">
        欢迎您:<span style="color:red;">${sessionScope.username}</span>
        <button type="button" class="btn btn-default btn-xs">
            <span class="glyphicon glyphicon-user"></span><a href="../user/loginout.action">退出</a>
        </button>

    </div>

    <c:if test="${not empty roleSet}">
        <c:forEach items="${roleSet}" var="roles">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle"
                        data-toggle="dropdown">
                    ${roles.name} <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <c:if test="${not empty roles.resources}">
                          <c:forEach items="${roles.resources}" var="resource">
                              <li><a href="/user${resource.linkedaddress}?action=${resource.id}">${resource.description}</a></li>
                              <li class="divider"></li>
                          </c:forEach>
                    </c:if>
                </ul>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>
