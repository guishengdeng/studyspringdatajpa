<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<gbck:page title="商品品牌">
    <jsp:attribute name="script">
        <script type="application/javascript">
            $('#logo_file').hide();


            if ($('#logo_container').val() != '') {
                preview();
            }

            $('#logo_button').on('click', function () {
                var logo_file = $(this).next().next();
                logo_file.click();
            });

            $('#del_logo_button').on('click', function () {
                $('#image').attr("src", "");
                $('#logo_container').attr("value", "");
            });


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

            function preview() {
                var image_container = $('#logo_container');
                $.ajax({
                    method: 'post',
                    data: {image_name: image_container.val()},
                    url: "upload/preview.do"
                }).done(function (data) {
                    console.log(data);
                    $('#image').attr('src', data.uri);
                });
            }
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
                    <a href="product/brand">
                        商品品牌管理
                    </a>
                </li>
            </ul>
            <a href="product/brand.do" class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        <div class="page-content">
            <h3 class="header smaller lighter blue">
                商品品牌
            </h3>
            <div class="row">
                <div class="col-xs-12">
                    <form id="contact-form"
                          <%--<c:if test="${brand.id == null}">action="/product/brand/save.do"</c:if>--%>
                          <%--<c:if test="${brand.id != null}">action="/product/brand/edit.do"</c:if>--%>
                          method="post" class="form-horizontal" role="form">
                        <input type="hidden" name="id" id="id" value="${brand.id}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                品牌中文名
                            </label>

                            <div class="col-sm-9">
                                <input id="name" type="text" name="name"
                                       placeholder="品牌中文名"
                                       maxlength="255" minlength="1"
                                       value="${brand.name}" class="required text col-xs-12 col-sm-12">
                            </div>
                        </div>

                        <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="nameEn">
                            品牌英文名
                        </label>

                        <div class="col-sm-9">
                            <input id="nameEn" type="text" name="nameEn"
                                   placeholder="品牌英文名"
                                   maxlength="255" minlength="1"
                                   value="${brand.nameEn}" class="required text col-xs-12 col-sm-12">
                        </div>
                    </div>

                        <div class="form-group">
                            <label class="col-md-2 control-label">品牌Logo:</label>
                            <div class="col-md-5">
                                <img id="image" src="" width="100px" height="100px"/>
                                <div class="btn btn-info" id="logo_button">选择图片</div>
                                <div class="btn btn-info" id="del_logo_button">删除图片</div>
                                <input type="file" id="logo_file"/>
                                <input name="logo" type="hidden" id="logo_container"
                                       value="${brand.logo}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="description">
                                品牌描述
                            </label>

                            <div class="col-sm-9">
                                <textarea id="description" type="text" name="description" placeholder="品牌描述"
                                          maxlength="255" class="col-xs-12 col-sm-12">${brand.description}</textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="seoTitle">
                                SEO 标题
                            </label>

                            <div class="col-sm-9">
                                <input id="seoTitle" type="text" name="seoTitle"
                                       placeholder="SEO 标题"
                                       maxlength="255" minlength="1"
                                       value="${brand.seoTitle}" class="required text col-xs-12 col-sm-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="seoKeywords">
                                SEO 关键字
                            </label>

                            <div class="col-sm-9">
                                <input id="seoKeywords" type="text" name="seoKeywords"
                                       placeholder="SEO 关键字"
                                       maxlength="255" minlength="1"
                                       value="${brand.seoKeywords}" class="required text col-xs-12 col-sm-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="seoDescription">
                                SEO 描述信息
                            </label>

                            <div class="col-sm-9">
                                <input id="seoDescription" type="text" name="seoDescription"
                                       placeholder="SEO 描述信息"
                                       maxlength="255" minlength="1"
                                       value="${brand.seoDescription}" class="required text col-xs-12 col-sm-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   for="category">是否启用
                            </label>
                            <div class="col-sm-9">
                                <label>
                                    <input id="category" name="showInApp" class="ace ace-switch ace-switch-7"
                                           type="checkbox"
                                           <c:if test="${brand.status == 0}">checked</c:if> />
                                    <span class="lbl"></span>
                                </label>
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
