<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="合伙人">
    <jsp:attribute name="css">
      <link rel="stylesheet" href="/static-resource/common/css/partner/detail.css"/>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {
                $(".license-img").click(function() {
                    $("#warpper-img").prop("src", $(this).prop("src"));
                    var $tong = $('#tong');
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-rim', //加上边框
                        area: ['80%', '100%'], //宽高
                        content: $tong
                    });
                    $tong.removeClass('hide');
                })
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
                <div id="tong" class="hide layui-layer-wrap" style="display: block;"><img src="" id="warpper-img"></div>
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
                                                    <img src="${partner.businessLicense}"
                                                         class="license-img"/>
                                                </td>
                                                <td class="padding-left-td2">
                                                    <img src="${partner.winePermit}"
                                                         class="license-img"/>
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
                                                    <label>审核时间:
                                                        <fmt:formatDate value="${partner.auditTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                    </label>
                                                </td>
                                                <td>
                                                    <label></label>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div class="text-right back-div">
                                            <button type="button" class="btn btn-primary option-btn" onclick="javascript:location='/partner/list.do'">
                                                <i class="ace-icon fa fa-reply"></i>返回</button>
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
