<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<manage:page title="优惠卷发放">
    <jsp:body>
        <div class="ui main container">
            <h3 class="header" style="margin-top: 10px;">发放优惠券</h3>
            <form id="form" class="ui form">
                <div class="field">
                    <label>优惠券类型名</label>
                    <input type="hidden" name="voucherTypeId" value="${voucherTypeRo.id }"/>
                    <input type="text" name="voucherTypeName" readonly="readonly" value="${voucherTypeRo.name }"/>
                </div>
                <div class="field battle" id="byUseId">
                    <label>发放目标</label>
                    <select class="ui search dropdown" name=shopType>
                        <option value="">全部</option>
                        <c:forEach var="shopType" items="${shopTypes}">
                            <option value="${shopType.id }">${shopType.name }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="field">
                    <label>每人发放数量</label>
                    <input type="text" id="dispatchCount" name="dispatchCount" placeholder="发放数量"/>
                </div>
                <div class="field" style="display: block;text-align: center;margin-bottom: 60px;">
                    <input type="button" class="ui primary button" value="提交" onclick="dispatcherToUserSub()"/>
                    <input type="button" class="ui button" value="返回" onclick="returnPage()"/>
                </div>
            </form>
        </div>
        <c:import url="/WEB-INF/views/manage/common/footer.jsp"/>

        <script type="text/javascript"
                src="${pageContext.request.contextPath}/static-resources/layer/layer.js"></script>
        <script type="text/javascript">
            // 返回到优惠券类型列表页面
            function returnPage() {
                window.location.href = "${pageContext.request.contextPath}/manage/voucherType/list.do";
            }
            ;

            // 给某优惠券添加数量提交
            function dispatcherToUserSub() {
                var dispatchCount = $("#dispatchCount").val();
                if (Number(dispatchCount) > 0) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/manage/voucher/dispatcherSub.do',
                        data: $("#form").serialize(),
                        type: 'post',
                        success: function (data) {
                            if (data == "success") {
                                layer.msg("发放成功！");
                            } else {
                                layer.msg(data);
                            }
                        }, error: function () {
                            layer.msg("系统异常！");
                        }
                    });
                } else {
                    layer.msg('发放数量至少大于0');
                }
            }
            ;
        </script>
    </jsp:body>
</manage:page>
