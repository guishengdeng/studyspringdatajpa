<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/3/13
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
    <%--<style type="text/css">
         .welcome{
             margin-left:  1170px;
         }
    </style>--%>
    <script type="text/javascript" src="../jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../jquery/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../jquery/js/user.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>

<body>
<%--<div class="container" style="min-width: 1200px;">--%>
    <%--引用公共页面--%>
    <%--<a href="/person/profile.action" target="_blank">测试页面</a>--%>
    <jsp:include page="commonNavBar.jsp"/>
    <table class="table table-hover table-bordered" >
        <thead>
            <tr>
                <th>id</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>邮箱</th>
                <th>密码</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
        <!-- BEGIN list -->
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.age}</td>
                <td>${user.sex}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>
                    <c:if test="${not empty user.roles}">
                        <c:forEach items="${user.roles}" var="role">
                            <span>${role.name}</span>
                        </c:forEach>
                    </c:if>
                </td>
                <td>
                    <button type="button" class="btn btn-info" data-toggle="modal" onclick="editInfo(${user.id})" data-target="#addUserModal">编辑</button>
                    &nbsp;&nbsp;
                    <button type="button" class="btn btn-danger" onclick="deleteInfo(${user.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-sm" data-toggle="modal"  data-target="#addUserModal" id="addUser">
    添加用户
</button>
<!-- 添加(修改)模态框示例实例 -->
<form method="post" action="" class="form-horizontal" role="form" id="form_data"  style="margin: 20px;">
    <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        用户信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="userForm">
                        <div class="form-group">
                            <%--for="user_id"的属性值对应 id="user_id"的属性 --%>
                            <label for="user_id" class="col-sm-3 control-label">用户ID</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control"  name="id" value="" id="user_id"
                                       placeholder="请输入用户id">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="user_name" class="col-sm-3 control-label">用户名</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="username" value="" id="user_name"
                                       placeholder="请输入姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user_age" class="col-sm-3 control-label">年龄</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="age" value="" id="user_age"
                                       placeholder="年龄">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="user_sex" class="col-sm-3 control-label">性别</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="sex" value="" id="user_sex"
                                       placeholder="性别">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user_email" class="col-sm-3 control-label">邮箱</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="email" value="" id="user_email"
                                       placeholder="邮箱">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user_password" class="col-sm-3 control-label">密码</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" name="password" value="" id="user_password"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="user_roles" class="col-sm-3 control-label">角色</label>
                            <div class="col-sm-9" id="rolesList">
                                <c:if test="${not empty roles}">
                                    <c:forEach items="${roles}" var="role">
                                        <%--备注：name="name" 对应Role实体类的name属性--%>
                                        <input type="checkbox" class="form-control" id="user_roles" name="role_id" value="${role.role_id}">${role.name}
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                         <input type="hidden" id="act" value="add" name="act"/>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                   <%-- <button type="submit" class="btn btn-primary">
                        提交
                    </button>--%>
                    <button type="button" class="btn btn-primary" id="submitForm">
                        提交
                    </button><span id="tip"> </span>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>
<%--</div>--%>
</body>
</html>
