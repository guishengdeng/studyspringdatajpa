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

            /**
             * 验证版本号码是否存在
             */
            function verify() {
                var version = $("#version").val();
                var os= $("#os").val();
                if(version == null || version == undefined || version =="" ||
                        os == null || os == undefined || os ==""
                ){
                    return;
                }
                var myReg = /^(\d+[.]\d+[.]\d+)$/;
                if (!myReg.test(version)) {
                    $("#version").val("");
                    $(".msgClass").html("请按范例输入版本号码！");
                    $("#cat-disable-confirm-modal").modal();
                    return;
                }
                $.ajax({
                    url: '/upgrade/verify.do',
                    data: {"version": version, "os": os},
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if(data.data.model.verify == true){
                            $("#version").val("");
                            $(".msgClass").html("该版本号码已经存在！");
                            $("#cat-disable-confirm-modal").modal();
                        }
                    }, error: function () {
                        alert("系统异常！");
                    }
                });
            }

            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
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
                                           for="os">类型
                                    </label>
                                    <div class="col-sm-9">
                                        <select name="os"  id="os" class="required text col-xs-10 col-sm-5">
                                            <option value="ios" selected>ios</option>
                                            <option selected value="androId">android</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="version">版本号
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="version" id="version" maxlength="20"  pattern="\d+[.]\d+[.]\d+"
                                               placeholder="例：1.1.11" class="required text col-xs-10 col-sm-5 regExp" onblur="verify() "/>
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
                                        <input type="text" name="md5" id="md5" size="50" class="required text col-xs-10 col-sm-5 regExp" maxlength="32"
                                               pattern="^([a-fA-F0-9]{32,32})$" >
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
            <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <button type="button" class="bootbox-close-button close"
                                    data-dismiss="modal" aria-hidden="true">
                            </button>
                            <div class="bootbox-body"><span class="msgClass"></span><span
                                    id="name-of-ban-cat"></span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-cancel-ban btn-primary">
                                确认
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>
