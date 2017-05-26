<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="广告">
    <jsp:attribute name="script">
        <script>
            $(function () {
                $('#beginTimestamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss'
                }).on("dp.change", function (e) {
                    $('#endTimestamp').data("DateTimePicker").minDate(e.date);
                });
                $('#endTimestamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss',
                    useCurrent: false
                }).on("dp.change", function (e) {
                    $('#beginTimestamp').data("DateTimePicker").maxDate(e.date);
                });
            });
        </script>
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
            /** -----------------------------》图片上传《-------------------------------- */
            //隐藏文件控件
            $('#logo_file').hide();

            //如果之前有上传过图片，将图片展示出来
            if ($('#logo_container').val() != '') {
                preview();
            }

            //点击上传图片，弹出选择框
            $('#logo_button').on('click', function () {
                var logo_file = $(this).next();
                logo_file.click();
            });
            /**
             * 上传图片
             */
            $('#logo_file').on('change', function () {
                var hidden_input = $(this).next();
                var file = this.files[0];
                var reader = new FileReader();
                reader.onload = function (e) {
                    var base64stream = this.result;
                    var dataObj = {
                        base64stream: base64stream
                    };
                    $.ajax({
                        type: "POST",
                        url: "upload/uploadProduct.do",
                        enctype: 'multipart/form-data',
                        data: dataObj
                    }).done(function (data) {
                        console.log(data);
                        if (data.status == 'success') {
                            layer.msg("上传图片成功");
                            hidden_input.val(data.name);
                            $('#image').attr('src', data.uri);
                        } else {
                            layer.msg("上传图片失败");
                        }
                    });
                };
                reader.readAsDataURL(file);
            });

            /**
             * 预览图片
             */
            function preview() {
                var image_container = $('#logo_container');
                $.ajax({
                    method: 'post',
                    data: {image_name: image_container.val()},
                    url: "upload/preview.do"
                }).done(function (data) {
                    $('#image').attr('src', data.uri);
                });
            }

            /**
             * 为时间控件绑定change时间，用于判断时间先后
             */

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
                    <a href="advertisement/list">
                        广告管理
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
                                    ${advertisement.id == null?'新增广告':'修改广告'}
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="advertisement/list.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon fa fa-angle-left"></i>
                                        返回
                                    </a>
                                </span>
                            </h3>
                            <form action="advertisement/saveOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <input type="hidden" name="id" id="id" value="${advertisement.id}"/>
                                <div class="field adv-photo row">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="picturesLink">
                                        广告图片
                                    </label>

                                    <div class="col-md-9">
                                        <img id="image" src="" width="100px" height="100px"/>
                                        <div class="btn btn-primary" id="logo_button">选择图片</div>
                                        <input type="file" id="logo_file" value=""/>
                                        <input name="picturesLink" type="hidden" id="logo_container"
                                               value="${advertisement.picturesLink}" class="form-control required">
                                    </div>
                                </div>
                                <c:if test="${!empty advertisement.picturesLink}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               for="picturesLink">
                                            图片链接
                                        </label>

                                        <div class="col-sm-9">
                                            <input type="text" id="picturesLink" placeholder="图片链接"
                                                   name="picturesLink" class="col-xs-10 col-sm-5"
                                                   value="${advertisement.picturesLink}">
                                        </div>
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="clickLink">
                                        点击链接
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="clickLink" placeholder="点击链接"
                                               name="clickLink" class="required text col-xs-10 col-sm-5"
                                               value="${advertisement.clickLink}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="beginTimestamp">
                                        广告生效时间
                                    </label>

                                    <div class="col-sm-9">
                                        <div class="input-group input-group-sm col-sm-5">
                                            <input id="beginTimestamp" type="text" required="required"
                                                   class="form-control required date" name="beginTimestamp"
                                                   value="<fmt:formatDate value="${advertisement.beginTimestamp}" pattern="yyyy-MM-dd" />"/>
                                            <span class="input-group-addon">
                                                    <i class="fa fa-clock-o"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                    <%--<c:if test="${not empty admin.username}">--%>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="endTimestamp">
                                        广告过期时间
                                    </label>

                                    <div class="col-md-9">
                                        <div class="input-group input-group-sm col-sm-5">
                                            <input id="endTimestamp" type="text" required="required"
                                                   class="form-control required date" name="endTimestamp"
                                                   value="<fmt:formatDate value="${advertisement.endTimestamp}" pattern="yyyy-MM-dd"/>"/>
                                            <span class="input-group-addon">
                                                    <i class="fa fa-clock-o"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                    <%--</c:if>--%>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="residenceTime">
                                        停留(毫秒)
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="residenceTime" placeholder="停留(毫秒)"
                                               name="residenceTime" class="col-xs-10 col-sm-5"
                                               value="<c:if test="${advertisement.residenceTime != null}">${advertisement.residenceTime}</c:if><c:if test="${advertisement.residenceTime == null}">3000</c:if>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="priority">
                                        优先级
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="priority" placeholder="优先级"
                                               name="priority" class="col-xs-10 col-sm-5"
                                               value="<c:if test="${advertisement.priority != null}">${advertisement.priority}</c:if><c:if test="${advertisement.priority == null}">3</c:if>">
                                    </div>
                                </div>

                                    <%--<sec:authorize access="hasAnyAuthority('OPT_USER_ADD', 'OPT_USER_EDIT')">--%>
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
                                    <%--</sec:authorize>--%>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
