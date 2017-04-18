<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<gbck:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
        </script>
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="component/navigations.jsp"/>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    示例管理
                </li>
                <li class="active">
                    猫
                </li>
            </ul>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form id="contact-form" action="demo/cats.do" method="post" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="id" id="id" value="${cat.id}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                名字
                            </label>

                            <div class="col-sm-4">
                                <input id="name" type="text" name="name"
                                       placeholder="名字"
                                       maxlength="255" minlength="1"
                                       value="${cat.name}" class="required text col-xs-12 col-sm-12">

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="description">
                                描述
                            </label>

                            <div class="col-sm-4">
                                <textarea id="description" type="text" name="description" placeholder="名字"
                                       maxlength="255" class="col-xs-12 col-sm-12">${cat.description}</textarea>

                            </div>
                        </div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit" id="btn_save">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</gbck:page>
