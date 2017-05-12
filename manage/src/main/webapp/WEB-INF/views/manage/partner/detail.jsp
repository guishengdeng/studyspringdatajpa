<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="合伙人">
    <jsp:attribute name="css">
        <style type="text/css">
            #title-logo {
                width: 5%;
            }

            #title-label {
                font-size: 19px;
            }

            .custom-div {
                border: 1px solid #CCCCCC;
            }

            .float-left-div {
                float: left;
            }

            .left-div {
                width: 34%;
            }

            .padding-left-td {
                padding-left: 2%;
            }

            .padding-left-td2 {
                padding-left: 7%;
            }

            .row-xs1-div, .row-xs1-div div {
                height: 110px;
            }

            .row-xs2-div, .row-xs2-div div {
                height: 200px;
            }

            .row-xs2-div > div > div:first-child {
                padding-top: 4%;
            }

            .custom-div table {
                width: 46%;
            }

            .custom-div tr {
                height: 35px;
            }

            .one-col-dev {
                background-color: #6389B2;
            }

            .one-col-dev h2 {
                color: white;
                margin-top: 9%;
            }

            .license-img {
                width: 50%;
            }
            .option-btn {
                margin-left: 10%;
                margin-top:2%;
            }
            .back-div {
                margin-right: 3%;
            }
            .enlarge-img {
                width: 17%;
                z-index: 112;
                margin-left: -16%;
                margin-top: 20%;
                cursor: pointer;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {

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
                <li>
                    合伙人管理
                </li>
                <li class="active">
                    审核合伙人
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <img src="/static-resource/common/image/partner.png" id="title-logo"/>
                            <label id="title-label">城市合伙人审核</label>
                            <div class="hr hr-18 dotted"></div>
                            <div class="row-xs1-div">
                                <div class="custom-div float-left-div left-div one-col-dev">
                                    <div class="text-center">
                                        <h2>基础信息</h2>
                                    </div>
                                </div>
                                <div class="custom-div">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>公司名称: ${partner.name}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>经营规模: ${partner.businessScale}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>联系人: ${partner.corporateName}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>电话: ${partner.mobile}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>经营品类: ${partner.category}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>注册资金: ${partner.capital}</label>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="row-xs1-div">
                                <div class="custom-div float-left-div left-div one-col-dev">
                                    <div class="text-center">
                                        <h2>详细信息</h2>
                                    </div>
                                </div>
                                <div class="custom-div">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>意向销售区域: ${partner.intentionSaleArea}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>团队人数: ${partner.teamSize}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>目前销售区域: ${partner.currentSaleArea}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>仓库面积: ${partner.storageSpace}</label>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="padding-left-td">
                                                <label>车辆数量: ${partner.vehicleSize}</label>
                                            </td>
                                            <td class="padding-left-td2">
                                                <label>客户数量: ${partner.customerNumber}</label>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row-xs2-div">
                                <div>
                                    <div class="custom-div float-left-div left-div one-col-dev">
                                        <div class="text-center">
                                            <h2>营业执照与酒水通行证</h2>
                                        </div>
                                    </div>
                                    <div class="custom-div">
                                        <table>
                                            <tbody>
                                            <tr>
                                                <td class="padding-left-td">
                                                    <label>营业执照</label>
                                                </td>
                                                <td class="padding-left-td2">
                                                    <label>酒水通行证</label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="padding-left-td">
                                                    <img src="/static-resource/common/image/partner.png"
                                                         class="license-img"/>
                                                    <img src="/static-resource/common/image/fangda.png" class="enlarge-img"/>
                                                </td>
                                                <td class="padding-left-td2">
                                                    <img src="/static-resource/common/image/partner.png"
                                                         class="license-img"/>
                                                    <img src="/static-resource/common/image/fangda.png" class="enlarge-img"/>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="row-xs2-div">
                                <div>
                                    <div class="custom-div float-left-div left-div one-col-dev">
                                        <div class="text-center">
                                            <h2>审核</h2>
                                        </div>
                                    </div>
                                    <div class="custom-div">
                                        <table>
                                            <tbody>
                                            <tr>
                                                <td class="padding-left-td">
                                                    <label>审核意见:${partner.auditOpinion}</label>
                                                </td>
                                                <td>
                                                    <label></label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="padding-left-td">
                                                    <label>
                                                        审核状态:
                                                        <c:if test="${partner.approvalStatus eq 'UNDER_REVIEW'}">
                                                            审核中
                                                        </c:if>
                                                        <c:if test="${partner.approvalStatus eq 'PASS'}">
                                                            通过
                                                        </c:if>
                                                        <c:if test="${partner.approvalStatus eq 'VETO'}">
                                                            否决
                                                        </c:if>
                                                    </label>
                                                </td>
                                                <td>
                                                    <label></label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="padding-left-td">
                                                    <label>审核时间:${partner.auditTime}</label>
                                                </td>
                                                <td>
                                                    <label></label>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="text-right back-div">
                                            <button type="button" class="btn btn-primary option-btn">
                                                <i class="ace-icon fa fa-reply"></i><a href="/partner/list.do" style="color:white">返回</a></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.span -->
                </div><!-- /.row -->
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div>
        <!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
