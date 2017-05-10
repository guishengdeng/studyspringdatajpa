<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
<depotnextdoor:page title="用户列表edit">

    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count})
                obj${status.count}.checked = true;
            </c:forEach>
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome">
                        首页
                    </a>
                </li>

                <li>
                    <a href="manage/users">
                        用户管理
                    </a>
                </li>
                <li class="active">
                    <c:out value="${cmd}"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                用户管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/users.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="manage/users/save_${cmd}.do" method="post"
                                                 class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="account">
                                                用户名
                                            </label>

                                            <div class="col-sm-9">
                                                <input ${empty admin ? '' : 'readonly'}
                                                        class="required col-xs-10 col-sm-5"
                                                        type="text"
                                                        id="account"
                                                        placeholder="用户名"
                                                        name="username"
                                                        value="${admin.username}"/>

                                            </div>
                                      </div>
                                <c:if test="${empty admin}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="password">
                                            密码
                                        </label>

                                        <div class="col-sm-9">
                                            <input type="password" id="password" placeholder="密码" value="<c:out value='${admin.password}'/> "
                                                   name="password" class="required col-xs-10 col-sm-5">
                                        </div>
                                    </div>
                                </c:if>
                                <%--由于sql语句要求一定要传入密码参数，所以，这里需要给定一个隐藏的input标签--%>
                                <%--<input type="hidden" value="${admin.password}"
                                       name="password" class="col-xs-10 col-sm-5">--%>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">
                                        描述
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" id="name" name="name" placeholder="姓名"
                                               value="<c:out value='${admin.name}'/>"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <c:if test="${not empty admin.username}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="">
                                            启用状态
                                        </label>
                                        <div class="col-sm-9">
                                            <label class="checkbox-inline">
                                                <input type="radio"
                                                       name="status" ${admin.status.value == 1 ? 'checked' :''}
                                                       value="ENABLE"> 启用
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="radio"
                                                       name="status" ${admin.status.value == 0 ? '' :'checked'}
                                                       value="DISABLE"> 禁用
                                            </label>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="roles">
                                        角色
                                    </label>

                                    <div id="roles" class="col-sm-9">
                                        <c:forEach items="${roles}" var="role" varStatus="status">
                                            <input type="checkbox" name="roles"
                                                   id="roleId_${role.id}" value="${role.id}"> <label
                                                for="roleId_${role.id}"><c:out
                                                value="${role.name}"/>[${role.description}]</label><br>
                                        </c:forEach>
                                    </div>
                                </div>

                                <sec:authorize access="hasAnyAuthority('OPT_USER_ADD', 'OPT_USER_EDIT')">
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button class="btn btn-info" type="submit">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                提交
                                            </button>

                                            &nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </button>
                                        </div>
                                    </div>
                                </sec:authorize>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
