<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<gbck:page title="猫维护">
    <jsp:attribute name="script">
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
                    示例管理
                </li>
                <li class="active">
                    猫维护
                </li>
            </ul>
            <a class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        <div class="page-content">
            <h3 class="header smaller lighter blue">
                猫数据
            </h3>
            <div class="row">
                <div class="col-xs-12">
                    <form id="contact-form" action="demo/cats/save.do" method="post" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="id" id="id" value="${cat.id}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                名字
                            </label>

                            <div class="col-sm-9">
                                <input id="name" type="text" name="name"
                                       placeholder="名字"
                                       maxlength="255" minlength="1"
                                       value="${cat.name}" class="required text col-xs-12 col-sm-12">

                            </div>
                                <%--class上加上required表示为必填，网页上在提交的时候会做验证--%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="description">
                                描述
                            </label>

                            <div class="col-sm-9">
                                <textarea id="description" type="text" name="description" placeholder="描述，你可以尝试着不填值"
                                          maxlength="255" class="col-xs-12 col-sm-12">${cat.description}</textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">
                                销售状态
                            </label>
                            <div class="col-sm-4">
                                <gbck:saleStatusRadio fieldName="saleStatus" selectedStatus="${cat.saleStatus}"
                                                      inline="true"/>
                            </div>
                            <label class="col-sm-2 control-label no-padding-right">
                                生命状态
                            </label>

                            <div class="col-sm-4">
                                <gbck:commonStatusRadio fieldName="status" selectedStatus="${cat.status}" inline="true"
                                                        enableLabel="存活" disableLabel="死亡"/>
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
