<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    商户中心
                </li>
                <li class="active">
                    <a href="shopTypes.do">
                    商户列表
                    </a>
                </li>
                <li>
                        ${shopType.id == null?'新增商户类型':'修改商户类型'}
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
                                    ${shopType.id == null?'新增商户类型':'修改商户类型'}
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="shopTypes.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form class="form-horizontal"
                                  <c:if test="${shopType.id == null}">action="${pageContext.request.contextPath}/shopTypes.do"</c:if>
                                  <c:if test="${shopType.id != null}">action="${pageContext.request.contextPath}/shopTypes/${shopType.id}.do"</c:if>
                                  method="post" style="display: block;margin-top: 5px;">
                                <c:if test="${shopType.id != null}">
                                    <div class="field" style="display:none;">
                                        <label style="">id</label>
                                        <input type="text" name="id" readonly placeholder="id" value="${shopType.id}">
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name">类型名称
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="name" id="name" maxlength="20"
                                               placeholder="类型名称" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${shopType.name}"/>"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="idx">显示顺序
                                    </label>
                                    <div class="col-sm-9">
                                        <input id="idx" name="idx" type="text" style="margin:0;" class="col-sm-12 ui-spinner-input"
                                               autocomplete="off" role="spinbutton" aria-valuenow="10" value="<c:out value="${shopType.idx}"/>" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="description">备注
                                    </label>
                                    <div class="col-sm-9">
                                        <textarea name="description" id="description" rows="5" cols="20"
                                                  maxlength="40" class="required text col-xs-10 col-sm-5">
                                            <c:out value="${shopType.description}"/>
                                        </textarea>
                                    </div>
                                </div>

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="submit">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                        &nbsp; &nbsp; &nbsp;
                                        <c:if test="${shopType.id == null}">
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </button>
                                        </c:if>
                                        <c:if test="${shopType.id != null}">
                                            <a class="btn" href="/shopTypes/${shopType.id}">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
    </jsp:body>
</depotnextdoor:page>
