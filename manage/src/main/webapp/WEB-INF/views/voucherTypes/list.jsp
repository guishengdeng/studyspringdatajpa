<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<manage:page title="优惠券类型列表">
<jsp:attribute name="script">
<script type="text/javascript">
    // 给某优惠券添加数量提交
	function addIssueCountSub(){
    	var id = $("#typeId").val();
    	var addIssueCount = $("#addIssueCount").val();
    	if(Number(addIssueCount)>0){
    	    $.ajax({
    	        url: '${pageContext.request.contextPath}/manage/voucherType/addIssueCount.do',
    	        data: {"id":id,"addIssueCount":addIssueCount},
    	        type: 'post',
    	        success: function (data) {
    	            if (data == "success") {
    	            	//layer.msg("提交成功！");
    	            	window.location.href = window.location.href;
    	            } else {
    	            	layer.msg("提交失败！");
    	            }
    	        }, error: function () {
    	        	layer.msg("系统异常！");
    	        }
    	    });	    		
    	}else{
    		layer.msg('数量至少大于0');
    	}
	};

    // 给某优惠券添加数量
    function addIssueCount(typeId,name){
    	var html = '<div style="padding:5px;">';
    	html+='<input type="hidden" id="typeId" name="typeId" value="'+typeId+'"/>';
    	html+='数量：<input id="addIssueCount" name="addIssueCount"/>&nbsp;&nbsp;&nbsp;';
    	html+='<button type="button" onclick="addIssueCountSub()">提交</button>';
    	html+='</div>';
    	
    	layer.open({
    		  type: 1, //page层
    		  area: ['300px', '100px'],
    		  title: '给'+name+'添加数量',
    		  shade: 0.6, //遮罩透明度
    		  moveType: 1, //拖拽风格，0是默认，1是传统拖动
    		  shift: 1, //0-6的动画形式，-1不开启
    		  content: html
    	});   	
    };
    
    $(document).ready(function() {
        $('td.time').each(function() {
            var date = new Date(new Number($(this).text()));
            $(this).text(date.Format("yyyy-MM-dd hh:mm:ss"));
        });
    });
</script>
</jsp:attribute>
<jsp:body>
<div class="ui container" style="display: block;margin-top: 5px;">
    <br>
    <div class="ui breadcrumb">
        <a class="active section">优惠券类型管理</a>
    </div>
    <div class="pull-right">
        <a href="${pageContext.request.contextPath}/manage/voucherconfigure/toVoucherconfigure.do" class="ui tiny primary button">
            <i class="ui icon plus"></i>
            <span>优惠券运营配置</span>
        </a>
        <a href="${pageContext.request.contextPath}/manage/voucherType/add.do" class="ui tiny primary button">
            <i class="ui icon plus"></i>
            <span>新建优惠券类型</span>
        </a>
    </div>
    <table class="ui striped table">
        <thead>
        <tr>
            <th>
                <h4>优惠券类型名称</h4>
            </th>
            <th>
                <h4>优惠券面值</h4>
            </th>
            <th>
                <h4>剩余数量</h4>
            </th>
            <th>
                <h4>有效期(天)</h4>
            </th>
            <th>
                <h4>优惠券描述</h4>
            </th>
            <th style="text-align: center;">
                <h4>操作</h4>
            </th>
        </tr>
        </thead>
        
        <c:forEach var="voucherType" items="${voucherTypes}">
            <tr>
                <td>
                <c:set var="name" value="${voucherType.name}" /> 
				<c:choose>  
				    <c:when test="${fn:length(name) > 10}">  
				        <c:out value="${fn:substring(name, 0, 10)}..." />  
				    </c:when>  
				   <c:otherwise>  
				      <c:out value="${name}" />  
				    </c:otherwise>  
				</c:choose>                 
                </td>
                <td><fmt:formatNumber type="number" value="${voucherType.faceValue/100}" pattern="0" maxFractionDigits="2"/></td>
                <td>${voucherType.issueCount}</td>
                <td>${voucherType.periodDays}</td>
                <td>
                <c:set var="description" value="${voucherType.description}" /> 
				<c:choose>  
				    <c:when test="${fn:length(description) > 10}">  
				        <c:out value="${fn:substring(description, 0, 10)}..." />  
				    </c:when>  
				   <c:otherwise>  
				      <c:out value="${description}" />  
				    </c:otherwise>  
				</c:choose> 
                </td>
                <td>
                    <a class="ui button teal" href="${pageContext.request.contextPath}/manage/voucher/dispatcherToTypeUser.do?id=${voucherType.id}">发放给某类型下用户</a>
                    <a class="ui primary button" href="${pageContext.request.contextPath}/manage/voucherType/edit.do?id=${voucherType.id}">修改</a>
                    <a class="ui primary button" href="${pageContext.request.contextPath}/manage/voucherType/details.do?id=${voucherType.id}">详情</a>
                    <a class="ui primary button" href="javascript:void(0)" onclick="addIssueCount('${voucherType.id}','${voucherType.name}')">添加数量</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</jsp:body>
</manage:page>