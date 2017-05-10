<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">
            <c:forEach items="${admin.roles}" var="role" varStatus="status">
            var obj${status.count} = document.getElementById('roleId_${role.id}');
            if (obj${status.count}) obj${status.count}.checked = true;
            </c:forEach>
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
                    客户管理
                </li>
                <li class="active">
                    <a href="/shops/auditList.do">
                    未审核商户
                    </a>
                </li>
                <li class="active">
                    待审核用户详情
                </li>
            </ul>
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                待审核用户详情
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="/shops/auditList.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <c:set var="shopDetail" value="${auditData.shopDetail}"/>
                            <form action="shops/audit.do?shopId=${shopDetail.shop.id}" method="post"
                                  class="form-horizontal" role="form">
                                <input name="shopId" type="hidden" value="${shopDetail.shop.id}">
                                <div class="row">
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        商铺名称:
											<span class="input-icon">
												<input type="text" value="${shopDetail.name}" readonly="readonly">
											</span>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        商户类型:
											<span class="input-icon">
												<input type="text" value="${shopDetail.shopType.name}" >
											</span>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        法人姓名:
											<span class="input-icon">
												<input type="text" value="${shopDetail.corporateName}" readonly="readonly">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        商铺手机:
											<span class="input-icon">
												<input type="text" value="${shopDetail.mobile}" readonly="readonly">
											</span>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        上级城市合伙人:
											<span class="input-icon">
												<input type="text" value="这是个死参数" readonly="readonly">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        <div class="box1 col-md-6">
                                            注册地址:
                                            <manage:geo provinceFieldName="provinceId" cityFieldName="cityId"
                                                        districtFieldName="districtId"
                                                        provinceId="${shopDetail.province.id}"
                                                        cityId="${shopDetail.city.id}"
                                                        districtId="${shopDetail.district.id}"/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        详细地址:
											<span class="input-icon">
												<input style="width: 1100px" class="form-control" type="text"
                                                       value="${shopDetail.deliveryAddress}" readonly="readonly">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        修改原因:
											<span class="input-icon">
												<input style="width: 1100px" class="form-control" type="text"
                                                       value="${shopDetail.reason}" readonly="readonly">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        详情提交审核时间:
												<input style="width: 500px" class="form-control" type="text" readonly="readonly"
                                                       value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopDetail.createTime}"/>">
                                    </div>
                                </div>
                                <br>
                                <c:set var="shopQualification" value="${auditData.shopQualification}"/>
                                    <%--<c:if test="${shopQualification != null && shopQualification.auditStatus != 30}">--%>
                                <div class="ui raised segment">
                                    <span class="label label-success arrowed " >商户资质</span>
                                    <br><br>
                                    <div>
                                        <input type="hidden" name="shopQualificationId"
                                               value="${shopQualification.id}"/>

                                        <div class="field">
                                            <label>门头照片</label>
                                            <div class="field">
                                                <img width="150" height="150" alt="150x150"
                                                     src="/static-resource/ace/assets/images/gallery/thumb-2.jpg">
                                            </div>
                                            <div class="p-div"></div>
                                        </div><br>
                                        <div class="field">
                                            <label>营业执照</label>
                                            <div class="field">
                                                <img width="150" height="150" alt="150x150"
                                                     src="/static-resource/ace/assets/images/gallery/thumb-2.jpg">
                                            </div>
                                            <div class="field p-div"></div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>营业执照名称:</label>
                                                <input type="text" name="businessLicenceName"
                                                       value="${shopQualification.businessLicenceName}">
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>营业执照ID:</label>
                                                <input type="text" name="businessLicenceId"
                                                       value="${shopQualification.businessLicenceId}">
                                            </div>
                                        </div>
                                        <br>
                                        <div class="ui divider"></div>
                                        <div class="field liquorSellLicence-container">
                                            <label>酒类流通许可证</label>
                                            <div class="field">
                                                <img width="150" height="150" alt="150x150"
                                                     src="/static-resource/ace/assets/images/gallery/thumb-2.jpg">
                                            </div>
                                            <div class="p-div"></div>
                                        </div>
                                        <div class="field">
                                            <label>酒类流通许可证ID:</label>
                                            <input type="text" name="liquorSellLicenceId"
                                                   value="${shopQualification.liquorSellLicenceId}">
                                        </div><br>
                                        <div class="ui divider"></div>
                                        <div class="field corporate-container">
                                            <label>法人身份证</label>
                                            <div class="field">
                                                <img width="150" height="150" alt="150x150"
                                                     src="/static-resource/ace/assets/images/gallery/thumb-2.jpg">
                                            </div>
                                            <div class="field p-div"></div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>法人身份证ID:</label>
                                                <input type="text" name="corporateId"
                                                       value="${shopQualification.corporateId}">
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>提交审核时间:</label>
                                                <input type="text" readonly="readonly" id="qualificationCreateTime"
                                                       value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopQualification.createTime}"/>">
                                            </div>
                                        </div>
                                        <br>
                                    </div>
                                </div>
                                审核结果:
                                <div class="row ">
                                    <select name="auditStatus" class="search audit-select col-xs-6 col-sm-4 col-md-3">
                                        <option value="">请选择</option>
                                        <c:forEach var="currentAuditStatus"
                                                   items="${auditStatusList}">
                                            <option value="${currentAuditStatus.name()}" ${shopQualification.auditStatus == currentAuditStatus.value ? "selected" :""}>${currentAuditStatus.description}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="inline fields reject-reason-container">
                                    <label>未通过原因:</label>
                                    <c:forEach var="auditRejectReason"
                                               items="${auditRejectReasons}">
                                        <div class="field">
                                            <div class="ui checkbox">
                                                <input type="checkbox"
                                                       name="auditRejectReasons"
                                                       value="${auditRejectReason.value}"
                                                       tabindex="0"
                                                       class="hidden">
                                                <label>${auditRejectReason.description}</label>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="ui error message"></div>
                                    <%--  <c:if test="${canAudit}">--%>
                                <div class="clearfix form-actions">
                                    <button class="btn btn-info pull-left " type="submit">
                                        <i class="ace-icon fa fa-check bigger-110 pull-left"></i>
                                        提交
                                    </button>
                                </div>
                                    <%-- </c:if>--%>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
