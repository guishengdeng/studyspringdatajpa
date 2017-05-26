<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<c:set var="url"
       value="${pageContext.request.contextPath}/shops/page.do?mobile=${reqVo.mobile}&detailAuditStatus=${reqVo.detailAuditStatus}&provinceId=${reqVo.districtId}&cityId=${reqVo.cityId}&districtId=${reqVo.districtId}"/>
<manage:page title="优惠券配置设置 ">      
<jsp:attribute name="script">
        <script type="text/javascript">
            $("select").dropdown({
                fullTextSearch: true
            });
            // 选择是否开启事件处理
            function isOpenChange() {
                var isOpen = $("#isOpen").val();
                if (isOpen != "1") {
                    $("#voucherType").val("");
                }
            }
            ;

            // 提交表单之前的验证
            function savaSubCheck() {
                var voucherconfigure = $("#voucherconfigure").val();
                var voucherType = $("#voucherType").val();
                var isOpen = $("#isOpen").val();
                if (voucherconfigure == "") {
                    layer.msg("请选择一种营销类型！");
                    return false;
                } else if (isOpen == "") {
                    layer.msg("请选择是否开启！");
                    return false;
                } else if (isOpen == "1" && voucherType == "") {// 如果是开启就必须选择
                    layer.msg("必须选择一种优惠券类型才能开启，请选择优惠券类型！");
                    return false;
                }
                return true;
            }
            ;

            // 给某优惠券添加数量提交
            function saveSub() {
                if (savaSubCheck()) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/manage/voucherconfigure/saveVoucherconfigure.do',
                        data: $("#form").serialize(),
                        type: 'post',
                        success: function (data) {
                            if (data == "success") {
                                layer.msg("提交成功！");
                                window.location.href = "${pageContext.request.contextPath}/manage/voucherconfigure/toVoucherconfigure.do";
                            } else {
                                layer.msg("提交失败！");
                            }
                        }, error: function () {
                            layer.msg("系统异常！");
                        }
                    });
                }
            }
            ;
            $("body").on("click", ".btn-delete-vouchercfg", function () {
                if (confirm("你确定要删除该运营配置吗?")) {
                    var vouchercfg = $(this).data("config");
                    var vouchertype = $(this).data("type");
                    $.post("${pageContext.request.contextPath}/manage/voucherconfigure/saveVoucherconfigure.do", {
                                voucherconfigure: vouchercfg,
                                voucherType: vouchertype,
                                isOpen: 0
                            },
                            function (data) {
                                if (data == "success") {
                                    alert("删除成功");
                                    window.location.href = "${pageContext.request.contextPath}/manage/voucherconfigure/toVoucherconfigure.do";
                                } else {
                                    alert("删除失败");
                                }
                            }
                    )
                    ;
                }
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
                <li><a href="${pageContext.request.contextPath}/manage/voucherType/list.do">
                    优惠券类型管理</a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        <div class="page-content">
        <div class="row">
	       	<div class="col-xs-12">
            <form id="form" class="form-horizontal" action="" method="post">
                <h3 class="header smaller lighter blue" style="margin-top: 10px">优惠券配置设置</h3>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right">选择营销类型</label>
                    <div class="col-sm-9">
                    <select name="voucherconfigure" id="voucherconfigure">
                        <option value="">请选择营销类型</option>
                        <c:if test="${!empty voucherConfigures }">
                            <c:forEach var="voucherConfigure" items="${voucherConfigures}">
                                <option value="${voucherConfigure.name()}">${voucherConfigure.title }</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right">优惠券类型</label>
                    <div class="col-sm-9">
                    <select id="voucherType" name="voucherType">
                        <option value="">选择优惠券类型</option>
                        <c:if test="${!empty voucherTypeRos }">
                            <c:forEach var="voucherTypeRo" items="${voucherTypeRos}">
                                <c:if test="${!voucherTypeRo.isExpire()}">
                                    <option value="${voucherTypeRo.id }">${voucherTypeRo.name }</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right">数量</label>
                    <div class="col-sm-9">
                    <input type="number" id="quantity" name="quantity" value="1">
                    </div>
                </div>
                <div class="clearfix form-actions"
                     style="display: block;text-align: center;margin-bottom: 60px;">
                    <input type="button" onclick="saveSub()" class="btn btn-info"
                           value="提交"/>
                    <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
                </div>
            </form></div></div>
            <c:if test="${not empty vouchercfglist}">
            <div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">现有优惠券配置</h3>
				</div>
				<div class="panel-body">
					<table class="table  table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>优惠券营销类型</th>
                            <th>优惠券类型</th>
                            <th>数量</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="vouchercfg" items="${vouchercfglist}">
                            <tr>
                                <td>${vouchercfg.voucherconfigurename}</td>
                                <td>${vouchercfg.voucherTypename}</td>
                                <td>${vouchercfg.quantity}</td>
                                <td>
                                    <a class="btn btn-xs btn-info btn-delete-vouchercfg"
                                       data-config="${vouchercfg.voucherconfigure}"
                                       data-type="${vouchercfg.voucherType}">
                                        <i class="ace-icon fa fa-trash-o bigger-80"></i><span> 删除</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
				</div>
			</div>
            </c:if>
        </div>
    </jsp:body>
</manage:page>

