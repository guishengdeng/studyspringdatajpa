<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">

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
                    商户中心
                </li>
                <li class="active">
                    <a href="/platform/platformList.do">
                        平台公司
                    </a>
                </li>
                <li class="active">
                    <a href="/platform/partnerList.do?platformId=<c:out value="${partner.platform.id}"/>">
                    合伙人
                     </a>
                </li>
                <li class="active">
                    合伙人详情
                </li>
            </ul>
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                城市合伙人详情
                                <a href="/platform/partnerList.do?platformId=<c:out value="${partner.platform.id}"/>"
                                   class="btn btn-sm btn-primary pull-right">
                                    <i class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="" method="post"
                                  class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="id">城市合伙人ID
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="id" id="id" maxlength="20" value="<c:out value="${partner.id}"/>"
                                               placeholder="城市合伙人ID" class="required text col-xs-10 col-sm-5 regExp"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="corporateName">联系人
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="corporateName" id="corporateName"  value="<c:out value="${partner.corporateName}"/>"
                                               placeholder="联系人" class="required text col-xs-10 col-sm-5 regExp"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="mobile">手机号
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="mobile" id="mobile" maxlength="20" value="<c:out value="${partner.mobile}"/>"
                                               placeholder="手机号" class="required text col-xs-10 col-sm-5 regExp"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="">详细地址
                                    </label>
                                    <div class="col-sm-9">
                                        <input type="text" name="" id="" maxlength="20"  value="<c:out value="${partner.name}"/>"
                                               placeholder="详细地址" class="required text col-xs-10 col-sm-5 regExp"/>
                                    </div>
                                </div>

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <a class="btn btn-info" type="submit" href="/platform/partnerList.do?platformId=<c:out value="${partner.platform.id}"/>">
                                            <i class="ace-icon fa fa-angle-left"></i>
                                            返回
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>
