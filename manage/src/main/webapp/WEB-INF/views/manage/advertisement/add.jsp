<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script>
            $(function () {
                $(".datepicker").datepicker();
            });
        </script>
        <script type="application/javascript">
            $('body').on('change', 'input[type=file]', function () {
                var img = $(this).parent().parent().find('img.p-logo');
                var file = this.files[0];
                var reader = new FileReader();
                reader.onload = function (e) {
                    var base64stream = this.result;
                    var dataObj = new Object();
                    dataObj.productName = $("input[name='name']").val();
                    dataObj.fileName = file.name;
                    dataObj.base64stream = base64stream;
                    $.ajax({
                        type: "POST",
                        url: 'upload/streamUpload.do',
                        enctype: 'multipart/form-data',
                        data: dataObj
                    }).done(function (data) {
                        if (data.status != "success") {
                            alert("上传图片失败");
                        } else {
                            img.prop('src', data.imageUrl);
                            img.parent().find('form').remove();
                            img.removeClass('new');
                            layer.msg('图片上传成功');
                            syncImageVals();
                        }
                    });
                };
                reader.readAsDataURL(file);
            });
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
                    <a href="manage/advertisement/list">
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
                                启动页面广告管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/advertisement/list.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="manage/advertisement/saveOrUpdate.do" method="post"
                                  class="form-horizontal" role="form">
                                <div class="field adv-photo">
                                    <label>广告页图片</label>

                                    <div class="btn btn-primary" id="logo_button">选择图片</div>

                                    <c:if test="${advertisement.picturesLink!=null}">
                                        <depot:qiniu type="product" element="${advertisement.picturesLink}"/>
                                    </c:if>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="picturesLink">
                                        图片链接
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="picturesLink" placeholder="图片链接"
                                               name="picturesLink" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="clickLink">
                                        点击链接
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="clickLink" placeholder="点击链接"
                                               name="clickLink" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="beginTimestamp">
                                        广告生效时间
                                    </label>

                                    <div class="col-sm-9">
                                        <input class="date-picker" id="beginTimestamp"  name="beginTimestamp" type="text" data-date-format="yyyy-mm-dd"/>
                                    </div>
                                </div>
                                <%--<c:if test="${not empty admin.username}">--%>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="endTimestamp">
                                        广告过期时间
                                    </label>

                                    <div class="col-sm-9">
                                        <input class="date-picker" id="endTimestamp" name="endTimestamp" type="text" data-date-format="yyyy-mm-dd"/>
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
                                               name="clickLink" class="col-xs-10 col-sm-5">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="priority">
                                        优先级
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text" id="priority" placeholder="优先级"
                                               name="clickLink" class="col-xs-10 col-sm-5">
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
