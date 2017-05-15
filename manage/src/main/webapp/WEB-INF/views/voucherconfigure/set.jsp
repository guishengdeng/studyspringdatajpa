<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<c:set var="url"
       value="${pageContext.request.contextPath}/shops/page.do?mobile=${reqVo.mobile}&detailAuditStatus=${reqVo.detailAuditStatus}&provinceId=${reqVo.districtId}&cityId=${reqVo.cityId}&districtId=${reqVo.districtId}"/>
<manage:page title="优惠券配置设置 ">      <jsp:attribute name="script">
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
                                window.location.href = "/manage/voucherconfigure/toVoucherconfigure.do";
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
                    $.post("/manage/voucherconfigure/saveVoucherconfigure.do", {
                                voucherconfigure: vouchercfg,
                                voucherType: vouchertype,
                                isOpen: 0
                            },
                            function (data) {
                                if (data == "success") {
                                    alert("删除成功");
                                    window.location.href = "/manage/voucherconfigure/toVoucherconfigure.do";
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

        <div class="page-content">
            <form id="form" class="form-horizontal" action="" method="post">
                <h3 class="header smaller lighter blue" style="margin-top: 10px">
                    优惠券配置设置
                </h3>
                <div class="ui divider"></div>
                <div class="form-group">
                    <label>选择营销类型</label>
                    <select name="voucherconfigure" id="voucherconfigure">
                        <option value="">请选择营销类型</option>
                        <c:if test="${!empty voucherConfigures }">
                            <c:forEach var="voucherConfigure" items="${voucherConfigures}">
                                <option value="${voucherConfigure.name()}">${voucherConfigure.title }</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <label>优惠券类型</label>
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
                <div class="form-group">
                    <label>数量</label>
                    <input type="number" id="quantity" name="quantity" value="1">
                </div>
                <div class="clearfix form-actions"
                     style="display: block;text-align: center;margin-bottom: 60px;">
                    <input type="button" onclick="saveSub()" class="btn btn-info"
                           value="提交"/>
                    <input type="button" class="btn" value="返回" onclick="history.go(-1)"/>
                </div>
            </form>
            <c:if test="${not empty vouchercfglist}">
                <div class="ui raised segment">
                    <a class="ui teal ribbon label">现有优惠券配置</a>
                    <table class="ui table celled">
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
                                    <a class="btn-delete-vouchercfg delete-btn"
                                       data-config="${vouchercfg.voucherconfigure}"
                                       data-type="${vouchercfg.voucherType}">
                                        <i class="trash icon"></i><span>删除</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </jsp:body>
</manage:page>

