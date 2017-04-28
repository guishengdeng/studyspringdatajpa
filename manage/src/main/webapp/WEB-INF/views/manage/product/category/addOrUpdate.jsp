<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            var status = $("#statusChecked").val();
            if (status == "ENABLE") {
                $("#ENABLE").prop("checked", true);
            } else if (status == "DISABLE") {
                $("#DISABLE").prop("checked", true);
            }
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
                    <a href="manage/categories.do">
                        分类管理
                    </a>
                </li>
                <li class="active">
                    分类添加
                </li>
            </ul>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                分类添加
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/categories.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="manage/categories/${category.id eq null ? 'create' : 'update'}.do"
                                  method="post"
                                  class="form-horizontal" role="form">
                                <input type="hidden" name="id" id="id" value="${category.id}"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        分类名称
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="name"
                                               value="${category.name}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        分类图标
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="logo"
                                               value="${category.logo}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        是否启用
                                    </label>
                                    <div class="col-sm-9">
                                        <input id="statusChecked" type="hidden" value="${category.status}">
                                        <div class="col-sm-3">
                                            <label class="control-label">
                                                启用
                                            </label><input type="radio"
                                                           id="ENABLE"
                                                           name="status"
                                                           value="ENABLE">
                                        </div>
                                        <div class="col-sm-3">
                                            <label class="control-label">
                                                禁用
                                            </label><input type="radio"
                                                           id="DISABLE"
                                                           name="status"
                                                           value="DISABLE">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        SEO标题
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="seoTitle"
                                               value="${category.seoTitle}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        SEO关键字
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="seoKeywords"
                                               value="${category.seoKeywords}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        SEO描述信息
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="seoDescription"
                                               value="${category.seoDescription}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        父分类id
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text"
                                               name="parentCategoryId"
                                               value="${category.parent.id}"
                                               class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
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
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>