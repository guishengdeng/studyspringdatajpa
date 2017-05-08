<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>

<depotnextdoor:page title="欢迎使用" hideHeader="true" bodyClasses="login-layout light-login">
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="widget-main">
                        <div id="fuelux-wizard-container" class="no-steps-container">
                            <div id="title-div">
                                <h2>欢迎加入1919隔壁仓库！</h2>
                            </div>
                            <div>
                                <ul class="steps" style="margin-left: 0">
                                    <li data-step="1" class="active">
                                        <span class="step">1</span>
                                    </li>

                                    <li data-step="2">
                                        <span class="step">2</span>
                                    </li>

                                    <li data-step="3">
                                        <span class="step">3</span>
                                    </li>

                                    <li data-step="4">
                                        <span class="step">4</span>
                                    </li>

                                    <li data-step="5">
                                        <span class="step">5</span>
                                    </li>
                                </ul>
                            </div>


                            <div class="step-content pos-rel">
                                <div class="step-pane active" data-step="1">
                                    <form class="form-horizontal" id="form-1">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <h3>填写账号密码</h3>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-12 col-sm-3 control-label no-padding-right"><b>*</b>账号</label>

                                            <div class="col-xs-12 col-sm-5">
																	<span class="block input-icon input-icon-right">
																		<input type="text" id="username"
                                                                               class="width-100" placeholder="邮箱/手机号"
                                                                               maxlength="20">
																	</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right"><b>*</b>登录密码</label>

                                            <div class="col-xs-12 col-sm-5">
																	<span class="block input-icon input-icon-right">
																		<input type="password" id="password"
                                                                               class="width-100" placeholder="密码不能小于6位"
                                                                               minlength="6" maxlength="20">
																	</span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-xs-12 col-sm-3 control-label no-padding-right"><b>*</b>确认密码</label>

                                            <div class="col-xs-12 col-sm-5">
																	<span class="block input-icon input-icon-right">
																		<input type="password" id="confirmPassword"
                                                                               class="width-100" placeholder="再次输入密码"
                                                                               minlength="6" maxlength="20">
																	</span>
                                            </div>
                                        </div>
                                    </form>

                                    <form class="form-horizontal hide" id="form-2">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <h3>基础信息</h3>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>公司名称</label>

                                            <div class="col-sm-16">
                                                <input type="text" id="name" placeholder="公司名称"
                                                       class="col-xs-10 col-sm-5" maxlength="20">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>联系人 </label>

                                            <div style="width:44%">
                                                <input type="text" id="corporateName" placeholder="联系人"
                                                       class="col-xs-5 col-sm-5" maxlength="20">
                                            </div>

                                            <label class="col-sm-1 control-label no-padding-right"><b>*</b>联系电话 </label>
                                            <div class="col-sm-4">
                                                <input type="text" id="mobile" placeholder="联系电话"
                                                       class="col-xs-10 col-sm-5" style="width:44%" maxlength="11">
                                                &nbsp;&nbsp;
                                                <button class="btn btn-xs" id="add-row">
                                                    <i class="ace-icon fa glyphicon-plus  bigger-110 icon-only"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="form-group" style="display: none;" id="spare-contact">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b> </label>

                                            <div style="width:44%">
                                                <input type="text" id="contactName" placeholder="联系人"
                                                       class="col-xs-5 col-sm-5" maxlength="20">
                                            </div>

                                            <label class="col-sm-1 control-label no-padding-right"><b>*</b> </label>
                                            <div class="col-sm-4">
                                                <input type="text" id="mobile2" placeholder="联系电话"
                                                       class="col-xs-10 col-sm-5" style="width:44%" maxlength="11">
                                                &nbsp;&nbsp;
                                                <button class="btn btn-xs" id="delete-row">
                                                    <i class="ace-icon fa glyphicon-minus  bigger-110 icon-only"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>经营品类 </label>
                                            <div style="width: 44%">
                                                <select id="category">
                                                    <option value="">===请选择===</option>
                                                    <option value="AL">Alabama</option>
                                                    <option value="AK">Alaska</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>注册资金</label>

                                            <div class="input-group" style="width: 10%">
                                                <input class="form-control input-mask-product col-xs-10 col-sm-5"
                                                       id="capital" name="capital" type="text">
                                                <span class="input-group-addon"><i class="ace-icon fa">万</i></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>经营规模</label>

                                            <div class="input-group" style="width: 16%">
                                                <input class="form-control input-mask-product col-xs-10 col-sm-5"
                                                       id="businessScale" name="businessScale" type="text">
                                                <span class="input-group-addon"><i class="ace-icon fa">万/年</i></span>
                                            </div>
                                        </div>
                                    </form>

                                    <form class="form-horizontal hide" id="form-3">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <h3>详细信息</h3>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>意向销售区域</label>

                                            <div class="col-sm-16">
                                                <input type="text" id="intentionSaleArea" placeholder="意向销售区域"
                                                       class="col-xs-10 col-sm-5" maxlength="100">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>目前销售区域</label>

                                            <div class="col-sm-16">
                                                <input type="text" id="currentSaleArea" placeholder="目前销售区域"
                                                       class="col-xs-10 col-sm-5" maxlength="100">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>团队人数</label>

                                            <div class="input-group" style="width: 16%">
                                                <input class="form-control input-mask-product col-xs-10 col-sm-5"
                                                       id="teamSize" name="businessScale" type="text">
                                                <span class="input-group-addon"><i class="ace-icon fa">人</i></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>车辆数量</label>

                                            <div class="input-group" style="width: 16%">
                                                <input class="form-control input-mask-product col-xs-10 col-sm-5"
                                                       id="vehicleSize" name="businessScale" type="text">
                                                <span class="input-group-addon"><i class="ace-icon fa">辆</i></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><b>*</b>仓库面积 </label>

                                            <div style="width:44%">
                                                <input type="text" id="storageSpace" placeholder="仓库面积"
                                                       class="col-xs-5 col-sm-5">
                                            </div>

                                            <label class="col-sm-1 control-label no-padding-right"><b>*</b>客户数量 </label>
                                            <div class="col-sm-4">
                                                <input type="text" id="customerNumber" placeholder="客户数量"
                                                       class="col-xs-10 col-sm-5" style="width:44%">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="checkbox">
                                                <label>
                                                    <input name="form-field-checkbox" type="checkbox" class="ace"
                                                           id="protocol-checkbox">
                                                    <span class="lbl">&nbsp;我已阅读并同意<a href="#">《使用协议》</a></span>
                                                </label>
                                            </div>
                                        </div>
                                    </form>

                                    <form class="form-horizontal hide" id="form-4" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <h3>上传营业执照和酒水流通许可证</h3>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <small><b>*</b>营业执照</small>
                                                </label>
                                                <div class="upload-pic-dev">
                                                    <input type="file" id="businessLicense" class="upload-input" accept="image/jpeg;"/>
                                                    <img id="businessLicense-img" src="" class="preview-img"/>
                                                    +
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <small><b>*</b>酒水通行证</small>
                                                </label>
                                                <div class="upload-pic-dev">
                                                    <input type="file" class="upload-input" id="winePermit" accept="image/jpeg;"/>
                                                    <img id="winePermit-img" src="" class="preview-img"/>
                                                    +
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                    <form class="form-horizontal hide" id="form-5">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <label>
                                                    <h3>完成</h3>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-xs-12 col-sm-5">
                                                <h4>注册信息提交成功，请耐心等待审核！</h4>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="wizard-actions" style="padding-right:33%" id="footer-dev">
                            <button class="btn btn-prev btn-white step-btn" disabled="disabled" id="pre-btn">
                                <i class="ace-icon fa fa-arrow-left"></i>
                                <c>上一步</c>
                            </button>

                            <button class="btn btn-success btn-white step-btn" data-last="Finish" id="next-btn">
                                <c>下一步</c>
                                <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                            </button>

                            <label>已有账号 <a href="" style="color:white">登录</a></label>
                        </div>
                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div>
    <!-- /.main-container -->

    <script type="text/javascript">
        $(function () {
            Partner.regiEvent();
        });

        var Partner = {
            stepIndex: 1,
            reqVo: {
                username: null,
                password: null,
                name: null,
                corporateName: null,
                mobile: null,
                contactName: null,
                mobile2: null,
                category: null,
                capital: null,
                businessScale: null,
                intentionSaleArea: null,
                currentSaleArea: null,
                teamSize: null,
                vehicleSize: null,
                storageSpace: null,
                customerNumber: null,
                businessLicense: null,
                winePermit: null
            },
            uploadPic: function () {
                Partner.reqVo.businessLicense = Common.mutipartFileUpload($("#businessLicense"), "正在上传营业执照");
                console.log(Partner.reqVo.businessLicense);
            },
            regiEvent: function () {
                $("#next-btn").click(function () {
                    var INDEX = Number(Partner.stepIndex);
                    if (!Partner.valid()) {
                        return false;
                    }
                    if (INDEX == 1) {
                        $("#pre-btn").removeAttr("disabled");
                    }
                    if (INDEX + 1 == 5) {
                        $("#footer-dev").remove();
                        Partner.uploadPic();
                    }
                    $(".steps").children().eq(INDEX).addClass("active");
                    $("#form-" + INDEX).addClass("hide");
                    Partner.stepIndex = INDEX + 1;
                    $("#form-" + Partner.stepIndex).removeClass("hide");
                    if (Partner.stepIndex == 4) {
                        $("#next-btn").children("c").html("提交");
                    }
                });

                $("#pre-btn").click(function () {
                    var INDEX = Number(Partner.stepIndex);
                    if (INDEX == 4) {
                        $("#next-btn").children("c").html("下一步");
                    }
                    $(".steps").children().eq(INDEX - 1).removeClass("active");
                    $("#form-" + INDEX).addClass("hide");
                    Partner.stepIndex = INDEX - 1;
                    $("#form-" + Partner.stepIndex).removeClass("hide");
                    if (Partner.stepIndex == 1) {
                        $("#pre-btn").attr("disabled", "disabled");
                    }
                });

                $(".upload-input").change(function (e) {
                    var obj = $(this);
                    var file = e.target.files[0];
                    var size = Math.round(file.size / 1024 / 1024);
                    if (size > 10) {
                        layer.msg('图片大小不得超过10M');
                        return;
                    }
                    var r = new FileReader();
                    r.readAsDataURL(file);
                    $(r).load(function () {
                        obj.next().attr("src", this.result);
                    })
                });

                $("#add-row").click(function() {
                    $("#spare-contact").css("display", "block");
                    return false;
                });
                $("#delete-row").click(function() {
                    $("#spare-contact").css("display", "none");
                    return false;
                });
            },

            valid: function () {
                var INDEX = Number(Partner.stepIndex);
                if (INDEX == 1) {
//                    return Partner.validAccountInfo();
                    return true;
                }
                if (INDEX == 2) {
//                    return Partner.validBasicInfo();
                    return true;
                }
                if (INDEX == 3) {
//                    return Partner.validDetailInfo();
                    return true;
                }
                return true;
            },
            validAccountInfo: function () {
                var username = $("#username").val();
                var password = $("#password").val();
                if (username === '') {
                    layer.msg("用户名不能为空");
                    return false;
                }
                if (password === '') {
                    layer.msg("登录密码不能为空");
                    return false;
                }
                if (password < 6) {
                    layer.msg("登录密码最小长度不能小于6");
                    return false;
                }
                if (password != $("#confirmPassword").val()) {
                    layer.msg("确认密码不匹配");
                    return false;
                }
                var isExist = false;
                $.ajax({
                    url: '/partner/validPartnerIsExist.do?username=' + username,
                    type: 'GET',
                    async: false,
                    success: function (data) {
                        isExist = data.data;
                    }
                });
                if(isExist === true) {
                    layer.msg("账户已存在");
                    return false;
                }
                Partner.reqVo.username = username;
                Partner.reqVo.password = password;
                return true;
            },
            validBasicInfo: function () {
                var name = $("#name").val();
                var corporateName = $("#corporateName").val();
                var mobile = $("#mobile").val();
                var category = $("#category").val();
                var capital = $("#capital").val();
                var businessScale = $("#businessScale").val();
                var contactName = $("#contactName").val();
                var mobile2 = $("#mobile2").val();

                if (name === '') {
                    layer.msg("公司名称不能为空");
                    return false;
                }
                if (corporateName === '') {
                    layer.msg("联系人不能为空");
                    return false;
                }
                if (!Partner.checkPhone(mobile)) {
                    return false;
                }
                if($("#spare-contact").css("display") === 'block') {
                    if (contactName === '') {
                        layer.msg("联系人不能为空");
                        return false;
                    }
                    if (!Partner.checkPhone(mobile2)) {
                        return false;
                    }
                }
                if (category === '') {
                    layer.msg("经营品类不能为空");
                    return false;
                }
                if (capital === '') {
                    layer.msg("注册资金不能为空");
                    return false;
                }
                if (businessScale === '') {
                    layer.msg("经营规模不能为空");
                    return false;
                }
                Partner.reqVo.name = name;
                Partner.reqVo.corporateName = corporateName;
                Partner.reqVo.mobile = mobile;
                Partner.reqVo.category = category;
                Partner.reqVo.capital = capital;
                Partner.reqVo.businessScale = businessScale;
                return true;
            },
            validDetailInfo: function () {
                if ($("#protocol-checkbox").is(":checked") === false) {
                    layer.msg("请认真阅读使用协议");
                    return false;
                }
                var intentionSaleArea = $("#intentionSaleArea").val();
                var currentSaleArea = $("#currentSaleArea").val();
                var teamSize = $("#teamSize").val();
                var vehicleSize = $("#vehicleSize").val();
                var storageSpace = $("#storageSpace").val();
                var customerNumber = $("#customerNumber").val();
                if (intentionSaleArea === '') {
                    layer.msg("意向销售区域不能为空");
                    return false;
                }
                if (currentSaleArea === '') {
                    layer.msg("目前销售区域不能为空");
                    return false;
                }
                if (teamSize === '') {
                    layer.msg("团队人数不能为空");
                    return false;
                }
                if (vehicleSize === '') {
                    layer.msg("车辆数量不能为空");
                    return false;
                }
                if (storageSpace === '') {
                    layer.msg("仓库面积不能为空");
                    return false;
                }
                if (customerNumber === '') {
                    layer.msg("客户数量不能为空");
                    return false;
                }
                Partner.reqVo.intentionSaleArea = intentionSaleArea;
                Partner.reqVo.currentSaleArea = currentSaleArea;
                Partner.reqVo.teamSize = teamSize;
                Partner.reqVo.vehicleSize = vehicleSize;
                Partner.reqVo.storageSpace = storageSpace;
                Partner.reqVo.customerNumber = customerNumber;
                console.log(Partner.reqVo);
                return true;
            },
            validForm4: function () {

            },
            checkPhone: function (phone) {
                if (!(/^1[34578]\d{9}$/.test(phone))) {
                    layer.msg("手机号码有误，请重填");
                    return false;
                }
                return true;
            }
        };

        var Common = {
            /**
             *
             * @param fileObj 文件框对象
             * @param msg 上传前提示
             * @returns {*}
             */
            mutipartFileUpload: function(fileObj, msg) {
                layer.msg(msg);
                var fileName;
                $.ajax({
                    url: '/upload/preview.do',
                    type: 'POST',
                    data: {"image_name": new FormData(fileObj)},
                    processData: false,
                    contentType: false,
                    async:false,
                    success: function (data) {
                        fileName = data.image_name;
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
                return fileName;
            }
        };
    </script>
</depotnextdoor:page>
<style type="text/css">
    body {
        background-color: #4977AA;
    }

    #title-div {
        margin-left: 34%;
        margin-bottom: 31px;
    }

    .upload-pic-dev {
        position: relative;
        border: 1px dashed;
        width: 64px;
        height: 64px;
        font-size: 43px;
        text-align: center;
        padding-bottom: 15px;
        cursor: pointer;
    }

    .upload-input {
        font-size: 14px;
        filter: alpha(opacity=0); /*IE滤镜，透明度50%*/
        -moz-opacity: 0; /*Firefox私有，透明度50%*/
        opacity: 0; /*其他，透明度50%*/
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        cursor: pointer;
    }

    .light-login {
        background-color: #4977AA;
        color: white;
    }

    .form-group > label[class*=col-] {
        margin-right: 5px;
    }

    b {
        color: red;
        padding-right: 6px;
    }

    .preview-img {
        width: 100%;
        height: 100%;
    }
</style>