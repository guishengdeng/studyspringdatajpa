<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<manage:page title="优惠券批量发送管理">
    <jsp:attribute name="css">
        <style type="text/css">
            .apply-to-product .operate-column{
                min-width: 20em;
            }
            .apply-to-product .ui.button {
                padding: 0.4em 0.3em;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">

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
<!--         <div class="page-content"> -->
	               <div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">导入商户手机号</h3>
						</div>
						<div class="panel-body">
						<div class="row">
               <div class="col-xs-12">
							<form class="form-horizontal" method="post" enctype="multipart/form-data"
                      action="manage/voucher/upload.do">
                      <div class="success message">
                        <c:choose>
                            <c:when test="${param.status eq 'success'}">
                                <div class="ui visible success message">
                                    <div class="content">上传成功</div>
                                </div>
                            </c:when>
                            <c:when test="${param.status eq 'failed'}">
                                <div class="ui visible error message">
                                    <div class="content">上传失败</div>
                                </div>
                            </c:when>
                        </c:choose>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="inputfile">选择上传文件</label>
                            <div class="col-sm-9">
	                            <input type="file" name="data" accept=".xlsx"
	                                   id="inputfile"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" >选择优惠券类型</label>
                            <div class="col-sm-9">
	                            <select name="voucherTypeId" class="form-control">
	                                <c:forEach var="voucherType" items="${voucherTypes}">
	                                    <option value="${voucherType.id }">${voucherType.name }</option>
	                                </c:forEach>
	                            </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" >每人发送数量</label>
                            <div class="col-sm-9">
                            	<input type="text" name="dispatcherCnt" class="form-control"/>
                            </div>
                        </div>

                      <div class="clearfix form-actions">
	                       <div class="col-md-offset-3 col-md-9">
	                           <button class="btn btn-info" type="submit"
										id="btn_save">
	                               <i class="ace-icon fa fa-cloud-upload"></i>提交
	                           </button>
	                           &nbsp; &nbsp; &nbsp;
	                           <button class="btn" type="reset">
	                               <i class="ace-icon fa fa-undo"></i>
	                               	重置
	                           </button>
	                       </div>
                        </div><div class="form-group pull-right">
                                <br>
                                <span>下载模板:&nbsp;</span><span><a href="mobile.xlsx"><i class="file excel outline icon"></i>鼠标右键-》目标另存为</a></span>
                            </div>
                </form>
						</div>
<!-- 					</div> -->
            </div>
        </div>
      </div>
    </jsp:body>
</manage:page>