<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<manage:page title="优惠卷发放">
    <jsp:body>
        <div class="page-content">
            <div class="ui ribbon blue label">发放优惠券</div>
            <div class="ui pull-right">
                <a href="${pageContext.request.contextPath}/shops/goSearch.do">
                    <i class="reply all icon"></i>返回查询列表&nbsp;</a>
            </div>
            <div class="ui main container">
                <form id="form" class="ui form">
                    <div class="field">
                        <!-- <label>优惠券类型名</label>
                <input type="hidden" name="voucherTypeId" value="${voucherTypeRo.id }" />
                <input type="text" name="voucherTypeName" readonly="readonly" value="${voucherTypeRo.name }" /> -->
                        <label>选择优惠券类型</label>
                        <select class="ui search dropdown" name="voucherTypeId">
                            <c:forEach var="voucherType" items="${voucherTypes}">
                                <option value="${voucherType.id }">${voucherType.name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- <div class="field battle" id="byUseId">
                <label>发放目标</label>
                <select class="ui search dropdown" name="dispatcherto">
	                <c:forEach var="user" items="${users}">
		               <option value="${user.id }">${user.name }</option>
	                </c:forEach>
                </select>
            </div> -->
                    <input type="hidden" name="dispatcherto" value="${userId}"/>
                    <div class="field">
                        <label>发放数量</label>
                        <input type="number" id="dispatchCount" name="dispatchCount" placeholder="发放数量"/>
                    </div>
                    <div class="field" style="display: block;text-align: center;margin-bottom: 60px;">
                        <c:if test="${err != 90000}">
                            <input type="button" class="ui primary button" onclick="dispatcherToUserSub()" value="提交"/>
                        </c:if>
                        <input type="button" class="ui button" value="返回" onclick="history.go(-1)"/>
                    </div>
                    <span style="color: red">${msg}</span>
                </form>
            </div>
        </form>
        <div class="ui raised segment">
            <div class="ui ribbon blue label">用户优惠券</div>
            <table class="ui celled product table">
                <thead>
                <tr>
                    <th>类型</th>
                    <th>发放时间</th>
                    <th>发放人</th>
                    <th>使用时间</th>
                    <th>抵扣金额</th>
                    <th>绑定订单</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${vouchers}" var="voucher">
                    <tr>
                        <td>${voucher.voucherType.name}</td>
                        <td>${voucher.bindingTime}</td>
                        <td>${voucher.createBy}</td>
                        <td>${voucher.useTime}</td>
                        <td>${voucher.useAmount/100}</td>
                        <td>${voucher.orderPo.id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
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
                        url: '${pageContext.request.contextPath}/manage/voucher/sendVoucherByUserId.do',
                        data: $("#form").serialize(),
                        type: 'post',
                        success: function (data) {
                            if (data == "success") {
                                alert("发放成功！返回查询页面")
                                window.location.href = "${pageContext.request.contextPath}/shops/goSearch.do";
                            } else {
                                alert(data);
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
