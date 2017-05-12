<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
<depotnextdoor:page title="主菜单edit">
    <jsp:attribute name="script">
         <script type="application/javascript">
             $('#confirm').on("click",function(){
                 var data = $('#mainMenu_form').serialize();
                 var name = $('#name').val();
                 var icon = $('#icon').val();
                 if(name == ""){
                     layer.msg("菜单名称不能为空");
                     return false;
                 }
                 if(icon == ""){
                     layer.msg("图标不能为空");
                     return false;
                 }
                 $.ajax({
                     method : "POST",
                     url : "manage/mainMenus/isExist.do",
                     data : data
                 }).done(function(returnResult){
                     if(returnResult){
                         document.mainMenuForm.action = "manage/mainMenus/addOrUpdate.do";
                         document.mainMenuForm.submit();
                     }else{
                         layer.msg("该菜单名称已存在,请重新输入");
                         return false;
                     }
                 });
             });
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
                    <a href="manage/mainMenu.do">
                        菜单管理
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
                                菜单管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/mainMenus.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <%--${cmd}--%>
                            <form action="" method="post"
                                  class="form-horizontal" role="form" id="mainMenu_form" name="mainMenuForm">
                                <c:if test="${not empty mainMenu}">
                                    <input type="hidden" name="id" value="${mainMenu.id}">
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">
                                        菜单名称
                                    </label>

                                    <div class="col-sm-9">
                                        <%--${not empty mainMenu ? 'readonly' : ''} --%>
                                        <input  type="text" id="name" name="name" placeholder="菜单名称"
                                               value="<c:out value='${mainMenu.name}'/>"
                                                class="required col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="code">
                                        显示顺序
                                    </label>

                                    <div class="col-sm-9">
                                        <input  type="text"
                                                id="code"
                                                placeholder=""
                                                name="code"
                                                value="<c:out value='${mainMenu.code}'/>"
                                                class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="icon">
                                        图标
                                    </label>
                                    <div class="col-sm-9">
                                        <input id="icon" type="text" name="icon"  placeholder="图标" value="<c:out value='${mainMenu.icon}'/>"
                                               maxlength="10" class="required col-xs-10 col-sm-5"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="description">
                                        描述
                                    </label>
                                    <div class="col-sm-9">
                                    <input id="description" type="text" name="description" value="<c:out value='${mainMenu.description}'/>"
                                          maxlength="10" class="col-xs-10 col-sm-5"/>
                                    </div>
                                </div>

                                <sec:authorize access="hasAnyAuthority('OPT_MAINMENU_ADD', 'OPT_MAINMENU_EDIT')">
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

