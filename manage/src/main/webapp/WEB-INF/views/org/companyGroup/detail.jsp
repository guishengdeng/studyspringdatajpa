<%--
  Created by IntelliJ IDEA.
  User: liubin
  Date: 5/2/17
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
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
                    价格管理
                </li>
                <li class="active">
                    <a href="companyGroup/list.do">
                        客户组列表
                    </a>
                </li>
                <li>
                        ${companyGroup.id == null?'新增客户组':'修改客户组'}
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
                                ${companyGroup.id == null?'新增客户组':'修改客户组'}
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="companyGroup/list.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                        </h3>
                        <form class="form-horizontal"
                              <c:if test="${companyGroup.id == null}">action="${pageContext.request.contextPath}/companyGroup/save.do"</c:if>
                              <c:if test="${companyGroup.id != null}">action="${pageContext.request.contextPath}/companyGroup/${companyGroup.id}.do"</c:if>
                              method="post" style="display: block;margin-top: 5px;">
                            <c:if test="${companyGroup.id != null}">
                                <div class="field" style="display:none;">
                                    <label style="">id</label>
                                    <input type="text" name="id" readonly placeholder="id" value="${companyGroup.id}">
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="name">客户组名称
                                </label>
                                <div class="col-sm-9">
                                    <input type="text" name="name" id="name" maxlength="20"
                                           placeholder="客户组名称" class="required text col-xs-10 col-sm-5"
                                           value="<c:out value="${companyGroup.name}"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="name">客户组编码
                                </label>
                                <div class="col-sm-9">
                                    <input type="text" name="code" id="code" maxlength="20"
                                           placeholder="客户组编码" class="required text col-xs-10 col-sm-5"
                                           value="<c:out value="${companyGroup.code}"/>"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="description">备注
                                </label>
                                <div class="col-sm-9">
                                    <textarea id="description" type="text" name="description" placeholder="描述"
                                              maxlength="255" class="col-xs-12 col-sm-12"><c:out value="${companyGroup.description}"/></textarea>
                                        <%--<textarea name="description" id="description" rows="5" cols="20"--%>
                                        <%--maxlength="40" class="required text col-xs-10 col-sm-5">--%>
                                        <%--<c:out value="${companyGroup.description}"/>--%>
                                    </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="companyLevel">客户组级别
                                </label>
                                <div class="col-sm-9">
                                    <select class="form-control required" id="companyLevel">
                                        <option value="">平台公司</option>
                                        <option value="">合作者</option>
                                    </select>
                                </div>
                            </div>

                            <div class="hr hr-16 hr-dotted"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-top" for="duallist"> Dual listbox </label>
                                <div class="col-sm-8">
                                    <select multiple="multiple" size="10" name="duallistbox_demo1[]" id="duallist">
                                        <option value="option1">Option 1</option>
                                        <option value="option2">Option 2</option>
                                        <option value="option3" selected="selected">Option 3</option>
                                        <option value="option4">Option 4</option>
                                        <option value="option5">Option 5</option>
                                        <option value="option6" selected="selected">Option 6</option>
                                        <option value="option7">Option 7</option>
                                        <option value="option8">Option 8</option>
                                        <option value="option9">Option 9</option>
                                        <option value="option0">Option 10</option>
                                    </select>

                                    <div class="hr hr-16 hr-dotted"></div>
                                </div>

                            </div>



                                <%--<div class="clearfix form-actions">--%>
                                <%--<div class="col-md-offset-3 col-md-9">--%>
                                <%--<button class="btn btn-info" type="submit">--%>
                                <%--<i class="ace-icon fa fa-check bigger-110"></i>--%>
                                <%--提交--%>
                                <%--</button>--%>
                                <%--&nbsp; &nbsp; &nbsp;--%>
                                <%--<c:if test="${companyGroup.id == null}">--%>
                                <%--<button class="btn" type="reset">--%>
                                <%--<i class="ace-icon fa fa-undo bigger-110"></i>--%>
                                <%--重置--%>
                                <%--</button>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${companyGroup.id != null}">--%>
                                <%--<a class="btn" href="/companyGroup/${companyGroup.id}">--%>
                                <%--<i class="ace-icon fa fa-undo bigger-110"></i>--%>
                                <%--重置--%>
                                <%--</a>--%>
                                <%--</c:if>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>

