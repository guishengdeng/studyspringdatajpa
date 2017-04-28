<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<depotnextdoor:page title="App内容管理">
    <jsp:attribute name="script">
        <style>
            #logo_container {
                width: 340px;
            }

            #image {
                border: 5px;
                width: 336px;
                height: 200px;
            }

            #app_url {
                width: 350px;
                height: 280px;
            }
        </style>
   <script>
       $("#app_updata").click(function () {
           console.log(${pageContext.request.contextPath})
       });
       $("#appdelete_button").click(function () {
           $("#logo_container").val('');
           $("#image").hide();
       });
       /*
        * 修改图片
        */

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
           $("#image").show();
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
                    <a href="app">
                        App内容管理
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
                            <label>App内容管理</label>
                        </h3>
                        <form id="app_form" class="form-horizontal" action="/addOrUpdate" method="POST">
                            <input type="hidden" name="id" value="${appVo.id}">

                            <div class="form-group">
                                <label class="control-label col-xs-12 col-sm-3 no-padding-right"
                                       for="phone">400电话</label>

                                <div class="col-xs-12 col-sm-9">
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <i class="ace-icon fa fa-phone"></i>
                                        </span>
                                        <input type="tel" id="phone" name="tel" value="<c:out value="${appVo.tel}"/>"
                                               maxlength="15"
                                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                                               onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                               aria-required="true" aria-describedby="phone-error"
                                               placeholder="例：400 999 1919" class="valid required text"
                                               aria-invalid="false">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2"
                                       maxlength="100">
                                    搜索热词 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-2" name="hotKeyWord"
                                           value=" <c:out value='${appVo.hotKeyWord}'/>"
                                           maxlength="20"
                                           placeholder="例：茅台 五粮液 剑南春"
                                           class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>
                                <%--页遮罩图片URL--%>
                            <div class="field adv-photo row">
                                <label class="col-sm-3 control-label no-padding-right">
                                    首页遮罩图片URL
                                </label>
                                <div class="col-md-9" id="app_url">
                                    <img id="image" src=""/>
                                    <div class="btn btn-primary" id="logo_button">修改图片</div>
                                    <input type="file" id="logo_file" value=""/>
                                    <input name="pictureUrl" type="ime-mode:disabled" id="logo_container"
                                           value="<c:out value='${appVo.pictureUrl}'/>" class="col-xs-10 col-sm-5"
                                           readonly>
                                    <button id="appdelete_button" class="btn btn-primary" type="button">
                                        删除图片
                                    </button>
                                </div>
                            </div>
                                <%--页遮罩图片URL--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-4">
                                    首页遮罩跳转URL </label>

                                <div class="col-sm-9">
                                    <input type="text" id="form-field-4" name="url"
                                           onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           onafterpaste="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           value=" <c:out value='${appVo.url}'/>"
                                           maxlength="100" placeholder="首页遮罩跳转URL "
                                           class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-5"> 红包标题 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-5" name="title"
                                           value="<c:out value='${appVo.title}'/>"
                                           maxlength="100" placeholder="例：
gbcklogo144x144.png" class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-6">
                                    红包提示内容 </label>
                                <div class="col-sm-9">
                                    <input type="text" id="form-field-6" name="content"
                                           value="<c:out value='${appVo.content}'/>"
                                           maxlength="100" placeholder="提示内容" class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right required text"
                                       for="form-field-1-1"> 红包分享出去的图标 </label>

                                <div class="col-sm-9">
                                    <input type="text" id="form-field-1-1" name="icon"
                                           value="<c:out value='${appVo.icon}'/>"
                                           maxlength="100" placeholder="例：
gbcklogo144x144.png" class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-2">
                                    红包分享页url </label>

                                <div class="col-sm-9">
                                    <input type="text" id="form-field-1-2" name="shareUrl"
                                           onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           onafterpaste="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           value="<c:out value='${appVo.shareUrl}'/>"
                                           maxlength="100" placeholder="红包分享页url"
                                           class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-3">
                                    推荐有礼Url</label>

                                <div class="col-sm-9">
                                    <input type="text" id="form-field-1-3" name="recommedUrl"
                                           onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           onafterpaste="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           value="<c:out value='${appVo.recommedUrl}'/>"
                                           maxlength="100" placeholder="推荐有礼url"
                                           class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-4">
                                    app下载页面 </label>

                                <div class="col-sm-9">
                                    <input type="text" id="form-field-1-4" name="appDownloadUrl"
                                           onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           onafterpaste="this.value=this.value.replace(/[\u4e00-\u9fa5]{0,}/g,'')"
                                           maxlength="100" value=" <c:out value='${appVo.appDownloadUrl}'/>"
                                           placeholder="app下载页
面" class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-1-5"> 订单满足 多少（元）
                                    发券</label>
                                <div class="col-sm-9">
                                    <input type="number" min="0" id="form-field-1-5" name="money"
                                           maxlength="6"
                                           min="0"
                                           value="<c:out value='${appVo.money}'/>"
                                           placeholder="888" class="col-xs-10 col-sm-5 required text">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">首页搜索栏显示标签</label>

                                <div class="col-sm-9">
                                <span class="input-icon">
                                    <input name="tabOne" value="<c:out value='${appVo.tabOne}'/>" type="text"
                                           id="form-field-icon-1"
                                           maxlength="40" placeholder="显示标签" class="required text">
                                 </span>
                                    <span class="input-icon input-icon-right">
												<input name="tabTwo" value="<c:out value='${appVo.tabTwo}'/>"
                                                       type="text"
                                                       maxlength="40" id="form-field-icon-2" placeholder="搜索标签"
                                                       class="required text">
                                 </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">
                                    20倍会员下单购买数量 </label>
                                <div class="col-sm-4">
                                    <div class="control-group">

                                        <div class="radio inline">
                                            <label>
                                                <input name="type" type="radio" class="ace " value="受限"
                                                       <c:if test="${appVo.type eq '受限'}">checked</c:if>>
                                                <span class="lbl">受限</span>
                                            </label>
                                        </div>

                                        <div class="radio inline">
                                            <label>
                                                <input name="type" type="radio" class="ace" value="不受限"
                                                       <c:if test="${appVo.type eq '不受限'}">checked</c:if>>
                                                <span class="lbl">不受限</span>
                                            </label>
                                        </div>

                                    </div>
                                </div>

                                <div class="col-sm-9">
                                   <span class="input-icon input-icon-right">
												<input type="number" name="maxNum" min="0" style="text-align:left"
                                                       maxlength="5"
                                                       value="<c:out value='${appVo.maxNum}'/>"
                                                       id="form-field-icon-4" placeholder="最低起售数量倍数 ">
                                 </span>
                                    <span class="input-icon input-icon-right">
												<input type="number" name="maxNum" min="0"
                                                       maxlength="5"
                                                       value="<c:out value='${appVo.maxNum}'/>"
                                                       id="form-field-icon-3" placeholder="最大购买数量倍数">
                                 </span>
                                </div>
                            </div>

                            <div class="clearfix form-actions">
                                <div class="col-md-offset-3 col-md-9">
                                    <button id="app_updata" class="btn btn-info" type="submit">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        提交
                                    </button>
                                    &nbsp; &nbsp; &nbsp;
                                    <c:if test="${appVo.id == null}">
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            重置
                                        </button>
                                    </c:if>
                                    <c:if test="${appVo.id != null}">
                                        <a class="btn" href="${pageContext.request.contextPath}/app">
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
