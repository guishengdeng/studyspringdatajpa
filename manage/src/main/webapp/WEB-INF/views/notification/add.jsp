<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<gbck:page title="猫维护">
    <jsp:attribute name="css">
        <style type="text/css">
            .sho{
                display: none;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script>
            $("#notifyType1").click(function () {
                $("#userSelector").removeClass("sho");
                $("#selectType").addClass("sho");
                $("#shopTypeId").val(null);
                $("select[name='shopTypeId']").removeAttr("shopTypeClass","required text");
            });
            $("#notifyType2").click(function () {
                $("#userSelector").addClass("sho");
                $("#selectType").removeClass("sho");
                $("#mobile").val(null);
                $("select[name='shopTypeId']").attr("shopTypeClass","required text");
            });
            $("#notifyType3").click(function () {
                $("#userSelector").addClass("sho");
                $("#selectType").addClass("sho");
                $("#shopTypeId").val(null);
                $("#mobile").val(null);
                $("select[name='shopTypeId']").removeAttr("shopTypeClass","required text");
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
                <li class="active">
                    消息推送
                </li>
            </ul>
        </div>
        <div class="page-content">
            <h3 class="header smaller lighter blue">
                消息推送
            </h3>
            <div class="row">
                <div class="col-xs-12">
                    <form id="contact-form" action="/manage/notification/save.do"
                          method="post" class="form-horizontal"
                          role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">
                                选择推送方式
                            </label>
                            <div class="col-sm-4">
                                <div class="control-group">
                                    <div class="radio inline">
                                        <label>
                                            <input name="notifyType" type="radio" class="ace" value="0"
                                                   checked=checked  id="notifyType1">
                                            <span class="lbl">推送给指定商户</span>
                                        </label>
                                    </div>
                                    <div class="radio inline">
                                        <label>
                                            <input name="notifyType" type="radio" class="ace" value="1" id="notifyType2">
                                            <span class="lbl">推送给一类商户</span>
                                        </label>
                                    </div>
                                    <div class="radio inline">
                                        <label>
                                            <input name="notifyType" type="radio" class="ace" value="3" id="notifyType3">
                                            <span class="lbl">所有审核过的商户</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="title">
                                消息标题
                            </label>
                            <div class="col-sm-9">
                                <input id="title" type="text" name="title"
                                       placeholder="消息标题"
                                       maxlength="15" minlength="1"
                                        class="required text col-xs-12 col-sm-12 ">

                            </div>
                        </div>
                        <div class="form-group" id="userSelector">
                            <label class="col-sm-2 control-label no-padding-right" for="mobile">
                                户手机号码
                            </label>
                            <div class="col-sm-9">
                                <input id="mobile" type="text" name="mobile"  pattern="^(((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0,6-8])|(14[57]))+\d{8})$"
                                       placeholder="户手机号码"
                                       maxlength="13" minlength="1"
                                       class="required text col-xs-12 col-sm-12 regExp">

                            </div>
                        </div>
                        <div class="form-group shopTypeSelector sho" id="selectType">
                            <div class="col-sm-9">
                                <label class="col-sm-2 control-label no-padding-right" for="mobile">
                                </label>
                            <gbck:shopType fieldName="shopTypeId" fieldClasses="field" shopTypeClass="required text"
                                           shopTypeId="${shopDetailResVo.shopTypeId}"/>
                                </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="notifyContent">
                                推送内容
                            </label>
                            <div class="col-sm-9">
                                <textarea id="notifyContent" type="text" name="notifyContent"
                                       placeholder="推送内容"
                                       maxlength="50" minlength="1"
                                        class="required text col-xs-12 col-sm-12"></textarea>

                            </div>
                        </div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit" id="btn_save">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    推送
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset" id="buttonReset">
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
