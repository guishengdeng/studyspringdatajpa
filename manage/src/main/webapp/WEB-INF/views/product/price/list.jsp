<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="价格管理">
    <jsp:attribute name="css">
        <style type="text/css">
            #page-table .name{
                min-width: 150px;
            }
            #page-table .check-th{
                min-width: 80px;
                cursor:pointer;
            }
            #page-table .operate, #page-table .status{
                min-width: 80px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            //全选事件
	        var active_class = 'active';
	        $("#page-table > thead > tr > th input[type=checkbox]").eq(0).on('click', function(){
	        	var th_checked = this.checked;
	        	$(this).closest('table').find('tbody > tr').each(function(){
	                var row = this;
	                if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
	                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
	            });
	        });
	        //勾选事件
	        $(".table-ck").on('click',function(){
	        	var tr_checked = this.checked;
	        	if(tr_checked){
	        		$(this).closest("tr").addClass(active_class);
	        	}else{
	        		$(this).closest("tr").removeClass(active_class);
	        	}
	        });
	        
	        //modal update
	        $("#btn-multi-update").click(function () {
	        	//获取选中项
	        	var num=$("#page-table > tbody > tr input[type=checkbox]:checked").length;
	        	console.log(num);
	        	if(num==0){
	        		layer.msg("请选择需要修改的商品信息");
	        		return;
	        	}
	        	$("#box-field-percentum").val(100);
	        	$("#box-field-increment,#box-field-fixed").val(0);
                $("#page-disable-update-modal").modal();
            });
	        $(".btn-cancel-update-ban").click(function () {
                $("#page-disable-update-modal").modal("hide");
            });
	        
	        $("#box-field-percentum").on("focus",function(){
	        	$("#box-field-fixed").val(0);
	        });
	        
	        $("#box-field-increment").on("focus",function(){
	        	$("#box-field-fixed").val(0);
            });
	        
	        $("#box-field-fixed").on("focus",function(){
                $("#box-field-percentum").val(100);
                $("#box-field-increment").val(0);
            });
	        
	        //模态框确定事件
            $(".btn-update-ban").click(function () {
            	var percentum1=$("#box-field-percentum").val();
            	var increment1=$("#box-field-increment").val();
            	var fixed1=$("#box-field-fixed").val();
            	
            	if(percentum1==''&&increment1==''&&fixed1==''){
            		layer.msg("请输入价格调整信息",{time:1000});
           		    return;
            	}
                if(percentum1!=''&&parseFloat(percentum1)<0){
                	layer.msg("请输入大于0的百分比");
                    return;
                }

                multisetprice();
            	
                $("#page-disable-update-modal").modal("hide");
            });
	        
	        //批量处理
	        function multisetprice(){
	        	var bfb=parseFloat($("#box-field-percentum").val());
	        	var add_num=parseFloat($("#box-field-increment").val());
	        	var lock_value=parseFloat($("#box-field-fixed").val());
	        	if(isNaN(bfb))bfb=100;
	        	if(isNaN(add_num))add_num=0;
	        	if(isNaN(lock_value))lock_value=0;
	        	bfb=bfb/100;
	        	$("#page-table > tbody > tr input[type=checkbox]:checked").each(function(){
                    var idstr=$(this).attr("data-id");
                    var price=parseFloat($("#price_"+idstr).val());
                    if(isNaN(price))price=0;
                    if(lock_value>0){
                    	price=lock_value;
                    }else{
                    	price=price*bfb+add_num;
                    }
                    if(price<=0)price=0;
                    console.log(price);
                    $("#price_"+idstr).val(price.toFixed(2));
                });
	        }
            
            //更新按钮
            $(".btn-single-update").on("click",function(){
            	var obj=$(this);
            	layer.confirm('您是否确定更新商品价格信息？', {
           		  btn: ['是','否'] //按钮
           		}, function(){
           			$(this).prop("disabled",true);
           			update(singleupdatedatas(obj));
           			$(this).prop("disabled",false);
           		}, function(){
           		});
            });
            
            //提交按钮
            $("#btn-submit").on("click",function(){
            	var num=$("#page-table > tbody > tr input[type=checkbox]:checked").length;
                console.log(num);
                if(num==0){
                    layer.msg("没有修改记录，无法执行该操作",{time:1000});
                    return;
                }
                
                layer.confirm('您是否确定更新商品价格信息？', {
                    btn: ['是','否'] //按钮
                  }, function(){
                	  $(this).prop("disabled",true);
                      update(multiupdatedatas());
                      $(this).prop("disabled",false);
                  }, function(){
                 });
            });
            
            
            //ajax
            function update(updateinfos){
            	console.log(updateinfos);
            	$.ajax({
                    cache : true,
                    type : "POST",
                    url : "product/price/update.do",
                    data : { data: JSON.stringify(updateinfos) },
                    async : false,
                    dataType : "json",
                    error : function(request) {
                    	layer.msg("操作错误！");
                    },
                    success : function(data) {
                        if (data==true) {
                            layer.msg("操作成功");
                        }
                    }
                });
            }
            
            //单个更新数据信息
            function singleupdatedatas(obj){
            	var idstr=$(obj).attr("data-id");
            	var price=parseFloat($("#price_"+idstr).val());
            	if(isNaN(price))price=0;
            	
            	var updateinfos=new Array();
            	var info={
            		id:idstr,
            		price:parseInt(price*100)
            	};
            	updateinfos.push(info);
            	console.log(updateinfos.length);
            	return updateinfos;
            }
            
            //批量更新数据信息
            function multiupdatedatas(){
            	var updateinfos=new Array();
            	//获取选中项
                $("#page-table > tbody > tr input[type=checkbox]:checked").each(function(){
                	var idstr=$(this).attr("data-id");
                    var price=parseFloat($("#price_"+idstr).val());
                    if(isNaN(price))price=0;

                    var info={
                       id:idstr,
                       price:parseInt(price*100)
                    };
                    updateinfos.push(info);
                });
                console.log(updateinfos.length);
                return updateinfos;
            }
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
                <li class="active">
                    价格管理列表
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">价格管理列表</span>
                            </h3>
                            <div class="inline">
                                <button id="btn-multi-update" data-id="" data-name="批量修改" type="button" class="btn btn-info btn-sm">
                                    <i class="ace-icon fa fa-pencil bigger-110"></i>调整显示价格
                                </button>
                                <button id="btn-submit" type="button" class="btn btn-info btn-sm">
                                    <i class="ace-icon fa fa-check bigger-110"></i>保存选中价格
                                </button>
                            </div>
                            
                            <div class="hr hr-18 dotted"></div>

                            <table id="page-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" />
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th class="status">商品编码</th>
                                    <th class="name">商品名称</th>
                                    <th class="status">采购价</th>
                                    <th class="status">销售价</th>
                                    <th class="center operate">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${page.content}" var="page">
                                    <tr id="tr-${page.id}">
                                        <td class="center">
                                            <label class="pos-rel">
                                                <input type="checkbox" data-id='<c:out value="${page.id}"/>' class="ace table-ck" />
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td><c:out value="${page.productCode}" /></td>
                                        <td><c:out value="${page.productName}"/></td>
                                        <td><c:out value="${page.purchasePrice}"/></td>
                                        <td>
                                            <input type="number" 
                                            id='price_<c:out value="${page.id}"/>' 
                                            value='<c:out value="${page.purchasePrice}"/>' /></td>
                                        <td class="center">
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button type="button" data-id='<c:out value="${page.id}"/>' class="btn btn-info btn-sm btn-single-update">
			                                        <i class="ace-icon"></i>更新价格
			                                    </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="product/price/list.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <%-- <sec:authorize access="hasAuthority('OPT_CAT_DELETE')"> --%>
                    <input type="hidden" id="id-of-page">
                    <div id="page-disable-update-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
					                <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
					                <h5 class="modal-title" id="myModalLabel">调整显示价格</h5>
					            </div>
					            <div class="modal-body">
					               <div class="form-horizontal">
					                   <div class="form-group">
					                           <label class="col-sm-2 control-label no-padding-right" style="padding-top:0px;"
                                                  for="box-field-percentum">在仓库价的基础上乘以</label>
                                               <div class="col-sm-10">
                                                   <input type="number" id="box-field-percentum" value="100" 
                                                   class="col-xs-10 col-sm-5" />
                                                   <h5>&nbsp;&nbsp;&nbsp;&nbsp;%</h5>
                                               </div>
				                       </div>
				                       <div class="form-group">
                                               <label class="col-sm-2 control-label no-padding-right" 
                                                  for="box-field-increment">并整体加</label>
                                               <div class="col-sm-10">
                                                   <input type="number" value="0" id="box-field-increment"
                                                    class="col-xs-10 col-sm-5" />
                                                    <h5>&nbsp;&nbsp;&nbsp;&nbsp;元</h5>
                                               </div>
                                       </div>
                                       <div class="form-group">    
                                               <label class="col-sm-2 control-label no-padding-right" 
                                                  for="box-field-fixed">锁定价格</label>
                                               <div class="col-sm-10">
                                                   <input type="number" value="0" id="box-field-fixed" class="col-xs-10 col-sm-5" />
                                                   <h5>&nbsp;&nbsp;&nbsp;&nbsp;元</h5>
                                               </div>
					                   </div>
					                   <div class="form-group" style="padding-left:10px;">    
                                           <p style="color:red;">提示：</p>
                                           <p style="color:red;">1:调整显示价格不会同步到数据库，勾选后点击确认价格后才会更改。</p>
                                           <p style="color:red;">2:确认勾选价格，只读取统一调整价格，单个输入价格修改无效。</p>
                                           <p style="color:red;">3:所有加架基础价格都以仓库价格为标准。</p>
                                           <p style="color:red;">4:点击锁定价格，其他选项不生效</p> 
                                       </div>
					               </div>
					            </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-cancel-update-ban btn-default">
                                        返回
                                    </button>
                                    <button type="button" class="btn btn-update-ban btn-primary">
                                       调整显示价格
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- </sec:authorize> --%>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
