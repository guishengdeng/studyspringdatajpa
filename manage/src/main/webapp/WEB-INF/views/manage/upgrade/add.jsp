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
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    APP配置
                </li>
                <li>
                    <a href="/upgrade/list.do">
                    升级配置列表
                    </a>
                </li>
                <li>
                    新增升级配置
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
                            <form action="/upgrade/save_add.do" method="post"
                                  class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="version">版本号
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="version" id="version" maxlength="20"
                                               placeholder="版本号" class="required text col-xs-10 col-sm-5"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="os">类型
                                    </label>
                                    <div class="col-sm-9">
                                        <select name="os"  id="os" class="required text col-xs-10 col-sm-5">
                                            <option value="ios" selected>ios</option>
                                            <option value="android">android</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="inhourse">是否抢版
                                    </label>
                                    <div class="col-sm-9">
                                        <select name="inhourse" id="inhourse"  class="required text col-xs-10 col-sm-5">
                                            <option value="false" selected>否</option>
                                            <option value="true">是</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="force">是否强制升级
                                    </label>
                                    <div class="col-sm-9">
                                        <select name="force" id="force"  class="required text col-xs-10 col-sm-5">
                                            <option value="false" selected>否</option>
                                            <option value="true">是</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="url">URL
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="url" id="url" size="50" pattern="https?://[-a-zA-Z0-9:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?" class="required regExp text col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="md5">MD5
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="md5" id="md5" size="50" class="required text col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="info">升级说明
                                    </label>
                                    <div class="col-sm-9">
                                        <textarea name="info" id="info" rows="5" cols="20"  class="required text col-xs-10 col-sm-5"></textarea>
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
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
