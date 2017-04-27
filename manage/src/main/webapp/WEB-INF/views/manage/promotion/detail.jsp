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
                        url: "upload/uploadTest.do",
                        enctype: 'multipart/form-data',
                        data: dataObj
                    }).done(function (data) {
                        console.log(data);
                        if (data.status == 'success') {
                            layer.msg("上传图片成功");
                            hidden_input.val(data.name);
                            preview();
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

            $(function () {
                var spinner = $("#idx").spinner({
                    create: function (event, ui) {
                        //add custom classes and icons
                        $(this)
                            .next().addClass('btn btn-success').html('<i class="ace-icon fa fa-plus"></i>').css('right', '0')
                            .next().addClass('btn btn-danger').html('<i class="ace-icon fa fa-minus"></i>').css('right', '0').css('bottom', '0');

                        //larger buttons on touch devices
                        if ('touchstart' in document.documentElement)
                            $(this).closest('.ui-spinner').addClass('ui-spinner-touch');
                        $(this).closest('.ui-spinner').css('width', '335px');
                    }
                });

                $("#abc").on("click", function () {
                    console.log($(this).prop("checked"));
                });
            })
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
                    <a href="promotion/list">
                        活动管理
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
                                        <input type="text" name="id" id="id" readonly
                                               placeholder="id" class="col-xs-10 col-sm-5"
                                               value="<c:out value="${promotion.id}"/>"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="createTime">创建时间
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="createTime" id="createTime" readonly
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
                            <div class="field adv-photo row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="logo">
                                    活动图片
                                </label>

                                <div class="col-md-9">
                                    <img id="image" src="" width="100px" height="100px"/>
                                    <div class="btn btn-primary" id="logo_button">选择图片</div>
                                    <input type="file" id="logo_file" value=""/>
                                    <input name="logo" type="hidden" id="logo_container"
                                           value="${promotion.logo}" class="form-control required">
                                </div>
                            </div>
                            <c:if test="${!empty promotion.logo}">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="logo">
                                        活动图片链接
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="logo" placeholder="活动图片链接"
                                               name="picturesLink" class="col-xs-10 col-sm-5" value="${promotion.logo}">
                                    </div>
                                </div>
                            </c:if>

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
                                    <input id="idx" name="idx" type="text" style="margin:0;"
                                           class="col-sm-12 ui-spinner-input" autocomplete="off" role="spinbutton"
                                           aria-valuenow="10" value="<c:out value="${promotion.idx}"/>"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="category">是否发布到客服端
                                </label>
                                <div class="col-sm-9">
                                    <label>
                                        <input id="category" name="showInApp" class="ace ace-switch ace-switch-7"
                                               type="checkbox"
                                               <c:if test="${promotion.showInApp == true}">checked</c:if> />
                                        <span class="lbl"></span>
                                    </label>
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
                                        <a class="btn"
                                           href="${pageContext.request.contextPath}/promotions/${promotion.id}.do">
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
