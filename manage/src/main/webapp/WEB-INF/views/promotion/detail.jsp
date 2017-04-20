<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <a href="">
                        活动发布
                    </a>
                </li>

                <li>
                    <a href="/promotions/new.do">
                        新增活动
                    </a>
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
                                    ${promotion.id == null?'新增活动':'修改活动'}
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/promotions.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form class="form-horizontal"
                                  <c:if test="${promotion.id == null}">action="/promotions.do"</c:if>
                                  <c:if test="${promotion.id != null}">action="/promotions/${promotion.id}.do"</c:if>
                                  method="post" style="display: block;margin-top: 5px;">
                                <c:if test="${promotion.id != null}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="id">id
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="id" id="id"  readonly
                                                   placeholder="id" class="col-xs-10 col-sm-5"
                                                   value="<c:out value="${promotion.id}"/>"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="createTime">创建时间
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="createTime" id="createTime"  readonly
                                                   placeholder="创建时间" class="col-xs-10 col-sm-5"
                                                   value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${promotion.createTime}"/>"/>
                                        </div>
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="adminId">活动标题
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="title" id="title" maxlength="20"
                                               placeholder="活动标题" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.title}"/>"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="url">活动链接
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="url" id="url" maxlength="100"
                                               placeholder="活动链接" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.url}"/>"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="logo">活动图片链接
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="logo" id="logo" maxlength="100"
                                               placeholder="活动图片链接" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.logo}"/>"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="adminId">创建人
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="adminId" id="adminId" maxlength="20"
                                               placeholder="创建人" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.adminId}"/>"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="idx">显示顺序
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="number" name="idx" id="idx" maxlength="20"
                                               placeholder="显示顺序" class="required text col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.idx}"/>"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="category">是否发布到客服端
                                    </label>
                                    <div class="col-sm-9">
                                        <select class="required text col-xs-10 col-sm-5" id="category" name="showInApp">
                                            <option value="false"
                                                    <c:if test="${promotion.showInApp == false}">selected</c:if> >否
                                            </option>
                                            <option value="true"
                                                    <c:if test="${promotion.showInApp == true}">selected</c:if> >是
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="submit">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                        &nbsp; &nbsp; &nbsp;
                                        <c:if test="${promotion.id == null}">
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </button>
                                        </c:if>
                                        <c:if test="${promotion.id != null}">
                                            <a class="btn" href="${pageContext.request.contextPath}/promotions/${promotion.id}.do">
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
