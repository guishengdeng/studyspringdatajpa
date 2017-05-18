<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags"%>
<manage:page title="优惠券类型详情">
<jsp:attribute name="script">
<script type="text/javascript">
	function returnPage() {
                window.location.href = "${pageContext.request.contextPath}/manage/voucherType/list.do";
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
            <h3 class="header smaller lighter blue">编辑优惠券类型</h3>
           <div class="row">
               <div class="col-xs-12">
				    <form id="form" class="form-horizontal"
						action="${pageContext.request.contextPath}/manage/voucherType/update.do"
						method="post">
					        <input type="hidden" name="id" value="${voucherType.id}" />
			        <div class="form-group">
			            <label class="col-sm-2 control-label no-padding-right">优惠券类型名</label>
			            <div class="col-sm-9">
			            	<input type="text" id="name" name="name"
							value="${voucherType.name}">
			        	</div>
			        </div>
					        
					<div class="clearfix form-actions">
                       <div class="col-md-offset-3 col-md-9">
                           <button class="btn btn-info" type="submit"
									id="btn_save">
                               <i class="ace-icon fa fa-check bigger-110"></i>
                               	保存
                           </button>
                           &nbsp; &nbsp; &nbsp;
                           <button class="btn" type="button" onclick="returnPage()">
                               <i class="ace-icon fa fa-undo bigger-110"></i>
                               	返回
                           </button>
                       </div>
                   </div>
		    	</form>
		    </div>
		</div>
	</div>
</jsp:body>
</manage:page>