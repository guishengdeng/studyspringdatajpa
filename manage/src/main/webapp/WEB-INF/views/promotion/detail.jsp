<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="">
                        升级配置
                    </a>
                </li>

                <li>
                    <a href="">
                        添加版本
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
                                添加升级配置
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/upgrade/list.do" class="btn btn-sm btn-primary"><i
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
                                               for="createTime">创建时间
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="createTime" id="createTime" maxlength="20"
                                                   placeholder="创建时间" class="col-xs-10 col-sm-5"
                                                   value="${promotion.createTime}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="id">id
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" name="id" id="id" maxlength="20"
                                                   placeholder="id" class="col-xs-10 col-sm-5"
                                                   value="<c:out value="${promotion.id}"/>"/>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="field">
                                    <label style="">创建人</label>
                                    <input type="text" name="adminId" placeholder="创建人" value="<c:out value="${promotion.adminId}"/>" maxlength="8">
                                </div>
                                <div class="field">
                                    <label style="">显示顺序</label>
                                    <input type="number" name="idx" placeholder="显示顺序" value="<c:out value="${promotion.idx}"/>" maxlength="3">
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="category">是否发布到客服端
                                    </label>
                                    <div class="col-sm-9">
                                        <select class="col-xs-10 col-sm-5" id="category" name="showInApp">
                                            <option value="true"
                                                    <c:if test="${promotion.showInApp == true}">selected</c:if> >是
                                            </option>
                                            <option value="false"
                                                    <c:if test="${promotion.showInApp == false}">selected</c:if> >否
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="field" style="display: block;text-align: center;margin-bottom: 60px;">
                                    <input type="submit" class="ui primary button" value="提交"/>
                                    <c:if test="${promotion.id == null}"> <input type="reset" class="ui button"
                                                                                 value="重置"/></c:if>
                                    <c:if test="${promotion.id != null}"> <a class="ui primary button"
                                                                             href="${pageContext.request.contextPath}/promotions/${promotion.id}.do">重置</a></c:if>
                                </div>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
