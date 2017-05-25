<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<manage:page title="优惠卷发放">
<jsp:attribute name="script">
<script type="text/javascript">
            // 返回到优惠券类型列表页面
            function returnPage() {
                window.location.href = "${pageContext.request.contextPath}/manage/voucherType/list.do";
            };
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
            };
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
                <li><a href="/manage/voucherType/list.do">
                    优惠券类型管理</a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary history-back">
                <i class="ace-icon fa fa-angle-left"></i>
                返回
            </a>
        </div>
        
        <div class="page-content">
            <h3 class="header smaller lighter blue" style="margin-top: 10px;">发放优惠券</h3>
            <form id="form" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right">优惠券类型名</label>
                    <div class="col-sm-9">
	                    <input type="hidden" name="voucherTypeId" value="${voucherTypeRo.id }"/>
	                    <input type="text" name="voucherTypeName" readonly="readonly" value="${voucherTypeRo.name }"/>
                    </div>
                </div>
                <div class="form-group battle" id="byUseId">
                    <label class="col-sm-2 control-label no-padding-right">发放目标</label>
                    <div class="col-sm-9">
	                    <select class="ui search dropdown" name=shopType>
	                        <option value="">全部</option>
	                        <c:forEach var="shopType" items="${shopTypes}">
	                            <option value="${shopType.id }">${shopType.name }</option>
	                        </c:forEach>
	                    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right">每人发放数量</label>
                    <div class="col-sm-9">
                   	 	<input type="text" id="dispatchCount" name="dispatchCount" placeholder="发放数量"/>
                    </div>
                </div>
                 <div class="clearfix form-actions">
                       <div class="col-md-offset-3 col-md-9">
	                       <button class="btn btn-info" type="button" onclick="dispatcherToUserSub()">
							 <i class="ace-icon fa fa-check bigger-110"></i>
								 提交
						 	</button>
							<button class="btn" type="button" onclick="returnPage()">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								返回
							</button>			
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</manage:page>
