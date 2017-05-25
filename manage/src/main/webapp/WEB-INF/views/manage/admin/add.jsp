<%@page import="com.biz.gbck.enums.org.CompanyLevel" %>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    request.setAttribute("_companyLevelArray_", CompanyLevel.values());
%>
<depotnextdoor:page title="用户列表edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function(){
                $('#companyLevelSons').hide();
            });
            $('#confirm').on("click",function(){
                 var cmd = $('#cmd').val();
                 var username = $('#username').val().trim();

                  if(cmd == "add"){
                      var password = $('#password').val().trim();
                      if(!username){
                          layer.msg("用户名不能为空");
                          return false;
                      }
                      if(!password){
                          layer.msg("密码不能为空");
                          return false;
                      }
                      if(password.length<6 ||password.length>20){
                          layer.msg("密码长度只能是6-20的字符");
                          return false;
                      }
                  }

                 $.ajax({
                     method : "POST",
                     url : "manage/users/isExist.do",
                     data : {"username":username,"cmd":cmd}
                 }).done(function(returnResult){
                     if(returnResult){

                         document.adminForm.action = "manage/users/save_"+cmd+".do";
                         document.adminForm.submit();
                     }else{
                         layer.msg("该用户已注册");
                         return false;
                     }
                 });
             });
             function changeCompanyLevel(value){
                 if(value == "" || !value){
                     layer.msg("请手动选择后台账号级别");
                     return false;
                 }
                 if(value == "ORG_PLATFORM"){
                     $.ajax({
                         method : "POST",
                         url : "platform/findByCompanyLevel.do",
                         data : {"companyLevel":value}
                     }).done(function(result){
                         $('#companyLevelSons').show();
                         $('#companyLabel').html("公司");
                         $('#companySelect').empty();
                         for(var index=0;index<result.length;index++){
                             $('#companySelect').show();
                              $('#companySelect').append('<option value="'+result[index].id+'">'+result[index].platFormName+'</option>');
                         }
                     });
                 }
                 if(value == "ORG_PARTNER"){
                     $.ajax({
                         method : "POST",
                         url : "partner/findByCompanyLevel.do",
                         data : {"companyLevel":value}
                     }).done(function(result){
                         $('#companyLevelSons').show();
                         $('#companyLabel').html("合伙人");
                         $('#companySelect').empty();
                         for(var index=0;index<result.length;index++){
                             $('#companySelect').show();
                             $('#companySelect').append('<option value="'+result[index].id+'">'+result[index].partnerName+'</option>');
                         }
                     });
                 }
                 if(value == "ORG_WAREHOUSE"){
                     $.ajax({
                         method : "POST",
                         url : "wareHouse/findByCompanyLevel.do",
                         data : {"companyLevel":value}
                     }).done(function(result){
                         $('#companyLevelSons').show();
                         $('#companyLabel').html("隔壁仓库");
                         $('#companySelect').empty();
                         for(var index=0;index<result.length;index++){
                             $('#companySelect').show();
                             $('#companySelect').append('<option value="'+result[index].id+'">'+result[index].name+'</option>');
                         }
                     });
                 }
             }
        </script>
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
                            <form action="" method="post" id="admin_form" name="adminForm"
                                                 class="form-horizontal" role="form">
                                         <input type="hidden" id="cmd" value="<c:out value="${cmd}"/>"/>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="username">
                                                用户名
                                            </label>

                                            <div class="col-sm-9">
                                                <input ${empty admin ? '' : 'readonly'}
                                                        class="col-xs-10 col-sm-5"
                                                        type="text"
                                                        id="username"
                                                        placeholder=""
                                                        name="username"
                                                        value="${admin.username}"/>
                                                <p class="help-block">用户名一旦注册,便不能修改</p>
                                            </div>
                                      </div>
                                <c:if test="${empty admin}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="password">
                                            密码
                                        </label>

                                        <div class="col-sm-9">
                                            <input type="password" id="password" placeholder="密码"   value="<c:out value='${admin.password}'/> "
                                                   name="password" class="required col-xs-10 col-sm-5" maxlength="20" minlength="6">

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
                                        <input type="text" id="name" name="name" placeholder=""
                                               value="<c:out value='${admin.name}'/>"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">
                                        后台账号级别
                                    </label>
                                    <div class="col-sm-9">
                                        <select name="companyLevel" onchange="changeCompanyLevel(this.value)">
                                            <option value="">请选择</option>
                                            <c:forEach items="${_companyLevelArray_}" var="_companyLevel_" varStatus="status" begin="0" end="2" step="1"><%--${fn:length(_companyLevelArray_) - 1}--%>
                                                <option value="${_companyLevel_.name()}">${_companyLevel_.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="companyLevelSons">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name" id="companyLabel">

                                    </label>
                                    <div class="col-sm-9">
                                        <select name="company" id="companySelect" style="display: none;">
                                               <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <c:if test="${not empty admin.username}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="">
                                            启用状态
                                        </label>
                                        <div class="col-sm-9">
                                                <depotnextdoor:commonStatusRadio fieldName="status" selectedStatus="${admin.status}" inline="true" enableLabel="启用" disableLabel="禁用"/>

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
                                            <button class="btn btn-info" type="button" id="confirm">
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
