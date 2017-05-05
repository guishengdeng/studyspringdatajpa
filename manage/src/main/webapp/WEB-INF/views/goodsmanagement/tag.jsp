<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<gbck:page title="添加标签">
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
                    商品管理
                </li>
                <li class="active">
                    标签管理
                </li>
            </ul>
            <a class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        <div class="page-content">
            <h3 class="header smaller lighter blue">
                添加标签
            </h3>
            <div class="row">
                <div class="col-xs-12">
                    <form id="contact-form" action="goodsmanagement/AddOrUpdate.do" method="post"
                          class="form-horizontal"
                          role="form">
                        <input type="hidden" name="id" id="id" value="${saleTag.id}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                标签名
                            </label>

                            <div class="col-sm-9">
                                <input id="name" type="text" name="name"
                                       placeholder="标签名"
                                       maxlength="50" minlength="1"
                                       value='<c:out value="${saleTag.name}"/>'
                                       class="required text col-xs-12 col-sm-12">

                            </div>
                                <%--class上加上required表示为必填，网页上在提交的时候会做验证--%>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                前台展示
                            </label>

                            <div class="col-sm-9">
                                <input id="showName" type="text" name="showName"
                                       placeholder="前台展示"
                                       maxlength="100" minlength="1"
                                       value='<c:out value="${saleTag.showName}"/>'
                                       class="required text col-xs-12 col-sm-12">

                            </div>
                                <%--class上加上required表示为必填，网页上在提交的时候会做验证--%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                标签
                            </label>

                            <div class="col-sm-9">
                                <input id="tag" type="text" name="tag"
                                       placeholder="标签"
                                       maxlength="50" minlength="1"
                                       value='<c:out value="${saleTag.tag}"/>'
                                       class="required text col-xs-12 col-sm-12">

                            </div>
                                <%--class上加上required表示为必填，网页上在提交的时候会做验证--%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name">
                                品牌排序
                            </label>

                            <div class="col-sm-9">
                                <input id="tag_table_num" type="number" name="idx"
                                       placeholder="标签名"
                                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                       minlength="1" min="0"
                                       value='<c:out value="${saleTag.idx}"/>'
                                       class="required text col-xs-12 col-sm-12">

                            </div>
                                <%--class上加上required表示为必填，网页上在提交的时候会做验证--%>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="description">
                                后台备注
                            </label>

                            <div class="col-sm-9">
                                <textarea id="description" type="text" name="description" placeholder="后台备注"
                                          maxlength="255" class="col-xs-12 col-sm-12"><c:out
                                        value="${saleTag.description}"/></textarea>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right">
                                是否启用
                            </label>
                            <div class="radio inline">
                                <label>
                                    <input name="saleStatus" type="radio" class="ace " value="ENABLE"
                                           <c:if test="${saleTag.saleStatus eq 'ENABLE'}">checked</c:if>>
                                    <span class="lbl">启用</span>
                                </label>
                            </div>

                            <div class="radio inline">
                                <label>
                                    <input name="saleStatus" type="radio" class="ace " value="DISABLE"
                                           <c:if test="${saleTag.saleStatus eq 'DISABLE'}">checked</c:if>>
                                    <span class="lbl">禁用</span>
                                </label>
                            </div>
                        </div>

                        <sec:authorize access="hasAuthority('OPT_SALETAG_CREATE')">
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
                        </sec:authorize>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</gbck:page>
