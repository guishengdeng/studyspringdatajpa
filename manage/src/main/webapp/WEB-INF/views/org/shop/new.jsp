<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="script">
        <script type="application/javascript">


            jQuery(function($) {
                var $overflow = '';
                var colorbox_params = {
                    rel: 'colorbox',
                    reposition:true,
                    scalePhotos:true,
                    scrolling:false,
                    previous:'<i class="ace-icon fa fa-arrow-left"></i>',
                    next:'<i class="ace-icon fa fa-arrow-right"></i>',
                    close:'&times;',
                    current:'{current} of {total}',
                    maxWidth:'100%',
                    maxHeight:'100%',
                    onOpen:function(){
                        $overflow = document.body.style.overflow;
                        document.body.style.overflow = 'hidden';
                    },
                    onClosed:function(){
                        document.body.style.overflow = $overflow;
                    },
                    onComplete:function(){
                        $.colorbox.resize();
                    }
                };

                $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
                $("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange fa-spin'></i>");

            })
        </script>
        <script src="/static-resource/ace/assets/js/jquery.colorbox.min.js"></script>
        <link rel="stylesheet" href="/static-resource/ace/assets/css/colorbox.min.css" />
        <link rel="stylesheet" href="/static-resource/ace/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
        <script>
            function showHidden(){
                var auditStatus=$("#auditStatus").val();
                if(auditStatus == "NORMAL"){
                    $("#hiddenDiv").addClass("hiddenDiv");
                }
                if(auditStatus == "AUDIT_FAILED"){
                    $("#hiddenDiv").removeClass("hiddenDiv");
                }else{
                    $("#hiddenDiv").addClass("hiddenDiv");
                }

            }
            var businessIdIsExist = false;

            function subForm() {
                var shopTypeId=$("select[name='shopTypeId']").val();
                if(shopTypeId == null || shopTypeId == undefined || shopTypeId == "" ){
                    $(".msgClass").html("请选择商户类型！");
                    $("#cat-disable-confirm-modal").modal();
                    return false;
                }
                var provinceId=$("select[name='provinceId']").val();
                var cityId=$("select[name='cityId']").val();
                var districtId=$("select[name='districtId']").val();
                if(provinceId == null || provinceId == undefined || provinceId == ""  ||
                        cityId == null || cityId == undefined || cityId == "" ||
                        districtId == null || districtId == undefined || districtId == ""

                ){
                    $(".msgClass").html("您正确选择省市县！");
                    $("#cat-disable-confirm-modal").modal();
                    return false;
                }
                if (businessIdIsExist) {
                    $(".msgClass").html("营业执照ID已经存在！");
                    $("#cat-disable-confirm-modal").modal();
                    return false;
                }
                    var auditStatus = $("#auditStatus").val();
                if(auditStatus == null || auditStatus == undefined || auditStatus == "" ){
                    $(".msgClass").html("请选择审核结果！");
                    $("#cat-disable-confirm-modal").modal();
                    return false;
                }else
                if(auditStatus == 'AUDIT_FAILED'){
                    if (!$("input[name='auditRejectReasons']:checked").val()) {
                        $(".msgClass").html("请选择审核失败原因！");
                        $("#cat-disable-confirm-modal").modal();
                        return false;
                    }
                }
                   $("#auditId").submit();
            }
            function showDiv(id) {

                $("#upgradeId").val(id);
            }
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });


            $("input[name='businessLicenceId']").blur(function () {
                var shopId=$().val();
                var businessLicenceId = $("input[name='businessLicenceId']").val();
                $.get("shops/isBusinessLicenceIdExist.do?businessLicenceId=" + businessLicenceId + "&shopId=", function (isExist) {
                    if (isExist) {
                        $("input[name='businessLicenceId']").val("")
                        $(".msgClass").html("营业执照ID已经存在！");
                        $("#cat-disable-confirm-modal").modal();
                    }
                    businessIdIsExist = isExist;
                });
            });

        </script>
    </jsp:attribute>
    <jsp:attribute name="css">
        <style>
            .hiddenDiv{
                display:none;
            }
        </style>
     </jsp:attribute>
    <jsp:body>
       <%-- <sec:authorize access="hasRole('OPT_SHOP_AUDIT')"><c:set var="canAudit"
                                                                 value="True"/></sec:authorize>--%>
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
                    新增商户
                </li>
            </ul>
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                新增商户
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                               <a href="/shops/auditList.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="" method="post"
                                  class="form-horizontal" role="form" id="auditId">
                                <div class="row">
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        <span>商铺名称:</span>
											<span class="input-icon">
												<input type="text" name="name">
											</span>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        <gbck:shopType fieldName="shopTypeId" fieldClasses="field" shopTypeClass="required text"
                                                         shopTypeId="${shopDetailResVo.shopTypeId}"/>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        <span>法人姓名:</span>
											<span class="input-icon">
												<input type="text"  name="corporateName">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        <span>商铺手机:</span>
											<span class="input-icon">
												<input type="text"  name="mobile">
											</span>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-3">
                                        <span>城市合伙人:</span>
											<span class="input-icon">
												<input type="text" name="partnerName">
											</span>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        <div class="box1 col-md-6">
                                            <span>注册地址:</span>
                                            <gbck:geo provinceFieldName="provinceId" cityFieldName="cityId"
                                                        districtFieldName="districtId"
                                                      provinceId=""
                                                      cityId=""
                                                      districtId=""/>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-sm-9">
                                        <span>详细地址:</span>
											<span class="input-icon">
												<input style="width: 1100px" class="form-control" type="text" name="deliveryAddress"
                                                        >
											</span>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <div class="ui raised segment">
                                    <span>商户资质:</span>
                                    <div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div>
                                                    <ul class="ace-thumbnails clearfix">
                                                        <li>
                                                            <a href="" title="Photo Title" data-rel="colorbox" name="shopPhoto">
                                                                <img width="150" height="150" alt="150x150" src="" />
                                                            </a>

                                                            <div class="tags">
                                                            <span class="label-holder">
                                                                <span class="label label-info">门头照片</span>
                                                            </span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <a href="" title="Photo Title" data-rel="colorbox" name="businessLicence">
                                                                <img width="150" height="150" alt="150x150" src="" />
                                                            </a>

                                                            <div class="tags">
                                                            <span class="label-holder">
                                                                <span class="label label-info">营业执照</span>
                                                            </span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <a href="" title="Photo Title" data-rel="colorbox" name="liquorSellLicence">
                                                                <img width="150" height="150" alt="150x150" src="" />
                                                            </a>

                                                            <div class="tags">
                                                            <span class="label-holder">
                                                                <span class="label label-info">酒类流通许可证</span>
                                                            </span>
                                                            </div>
                                                        </li>
                                                        <li>
                                                            <a href="" title="Photo Title" data-rel="colorbox" name="corporateIdPhoto">
                                                                <img width="150" height="150" alt="150x150" src="" />
                                                            </a>

                                                            <div class="tags">
                                                            <span class="label-holder">
                                                                <span class="label label-info">法人身份证</span>
                                                            </span>
                                                            </div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>营业执照名称:</label>
                                                <input type="text" name="businessLicenceName"
                                                      >
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>营业执照ID:</label>
                                                <input type="text" name="businessLicenceId"
                                                       >
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>酒类流通许可证ID:</label>
                                                <input type="text" name="liquorSellLicenceId"
                                                       >
                                            </div>
                                            <div class="col-xs-6 col-sm-4 col-md-3">
                                                <label>法人身份证ID:</label>
                                                <input type="text" name="corporateId"
                                                       >
                                            </div>
                                        <br>
                                    </div>
                                </div><br>

                                <div class="ui error message"></div>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" onclick="subForm()">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            保存
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <a class="btn" href="/shops/completeAuditList.do">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            取消
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <button type="button" class="bootbox-close-button close"
                                    data-dismiss="modal" aria-hidden="true">
                            </button>
                            <div class="bootbox-body"><span class="msgClass"></span><span
                                    id="name-of-ban-cat"></span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-cancel-ban btn-primary">
                                确认
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>
