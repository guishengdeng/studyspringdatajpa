<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
<depotnextdoor:page title="扩展属性edit">
    <jsp:attribute name="script">
         <script type="application/javascript">
             $('#curr').hide();
             $('#addOrUpdate').on('click',function(){
                 /*var value = $('#productExtendValue').val();
                 var id = $('#id').val();
                 var productExtendValue = $ ('#productExtendValue').val();
                 var curr_productExtend = $('#productExtend').html();
                 var selectedCategoryId = $('#selectedCategoryId').val();
                 var clickedCategoryId = $('#clickedCategoryId').val();
                 var requestFormData = $('#productExtend_form').serialize();
                 curr_productExtend = JSON.parse(curr_productExtend);
                 if(selectedCategoryId == clickedCategoryId){
                     for (var index = 0;index < curr_productExtend.length ;index++){
                         var  productExtends = curr_productExtend[index].name;
                         var  curr_id = curr_productExtend[index].id;
                         if (value == productExtends ){
                             if(curr_id == id){
                                 commonAjaxReq(requestFormData,selectedCategoryId);
                                 return true; //放行
                             }
                             layer.msg("您输入的值已存在,请重新输入");
                             return false;//不让用户提交
                         }
                     }
                 }
                 if(selectedCategoryId == "不限"){
                     layer.msg("请选择分类");
                     return false;
                 }
                 if( productExtendValue =="" ){
                     layer.msg("请输入属性名");
                     return false;
                 }
                 change(value);
                 //满足条件后,放行,让用户提交数据
                 commonAjaxReq(requestFormData,selectedCategoryId)*/

                 change();
                 var data = $('#productExtend_form').serialize();
                 var selectedCategoryId = $('#selectedCategoryId').val();
                 var productExtendValue = $ ('#productExtendValue').val();
                 if(selectedCategoryId == "不限"){
                     layer.msg("请选择分类");
                     return false;
                 }
                 if( productExtendValue =="" ){
                     layer.msg("请输入属性名");
                     return false;
                 }
                 $.ajax({
                     method : "POST",
                     url : "product/categoryProperty/addOrUpdate.do",
                     data : data,
                     dataType : "json"
                 }).done(function(result){
                     if(result){
                         //layer.msg("输入的值不存在,可以输入");
                         document.productExtendForm.action = "product/categoryProperty/again.do";
                         document.productExtendForm.submit();
                         //window.location.href = "/product/categories/"+selectedCategoryId+".do"
                     }else{
                         layer.msg("你输入的属性名已存在");
                         return false;
                     }
                 });
             });
             function commonAjaxReq(data,categoryId){
                 $.ajax({
                     method : "POST",
                     url : "product/categoryProperty/addOrUpdate.do",
                     data : data,
                     dataType : "json"
                 }).done(function(result){
                     if(result){
                         layer.msg("what you do is successful");
                         window.location.href = "/product/categories/"+categoryId+".do"
                     }else{
                         layer.msg("what you do is failed");
                     }
                 });
             }
             function change(){
                 //点击下拉列表,获取说选中的分类id
                 var selectedCategoryId = $('#selectedCategoryId').val();
                 if(selectedCategoryId == "不限"){
                     layer.msg("请手动选择分类,谢谢。")
                     return false;
                 }
                 $.ajax({
                     method : "POST",
                     url : "product/categoryProperty/change.do",
                     data : {
                               "id": selectedCategoryId
                         },
                     dataType : "json"
                 }).done(function(data){
                     $("#list").empty();
                     $('#curr').show();
                     for(var i=0;i<data.currJson.length;i++){
                         var name = data.currJson[i].name;
                         $("#list").append('<input type="text" readonly="readonly"  value="'+name+'"/>');
                         console.log(data.currJson);
                     }
                 });
             }
         </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome">
                        首页
                    </a>
                </li>

                <li>
                    <a href="manage/users">
                        扩展属性
                    </a>
                </li>
                <li class="active">
                    <c:out value="${cmd}"/>
                </li>
            </ul>
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                扩展属性
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="product/categories/${categoryId}.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <form action="" method="post" name="productExtendForm"
                                  class="form-horizontal" role="form" id="productExtend_form">
                                <div  id="productExtend" style="display: none">${currJson}</div>
                                <div  id="categories" style="display: none">${categories}</div>
                                <input type="hidden"  id="clickedCategoryId" value="${categoryId}"/>
                                <input type="hidden" name="id" id="id" value="${property.id}"/>
                                <input type="hidden" name="idx" value="${property.idx}"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        分类
                                    </label>

                                    <div class="col-md-6">
                                        <depotnextdoor:categorySelect selectedStatus="${categoryId}" fieldName="categoryId"  categories="${categories}" id="selectedCategoryId" change="change()" withNone="true" noneLabel="不限"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" id="curr">
                                        当前分类所属的扩展属性
                                    </label>

                                    <div class="col-md-6" id="list">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="productExtendValue">
                                        属性名
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text"  name="name" id="productExtendValue" placeholder="属性名称：容量,体积等" class="required col-xs-10 col-sm-5" value="<c:out value='${property.name}'/>">
                                    </div>
                                </div>

                                <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right"
                                               >
                                            是否启用
                                        </label>
                                        <div class="col-sm-9">
                                            <%--<label class="checkbox-inline">
                                                <input type="radio" id="isEnable"
                                                       name="status" ${property.status.value == 1 ? 'checked' :''}
                                                       value="ENABLE"> 是
                                            </label>
                                            <label class="checkbox-inline">
                                                <input type="radio"
                                                       name="status" ${property.status.value == 0 ? '' :'checked'}
                                                       value="DISABLE"> 否
                                            </label>--%>
                                                <depotnextdoor:commonStatusRadio fieldName="status" selectedStatus="${property.status}" inline="true" enableLabel="是" disableLabel="否"/>
                                        </div>
                                    </div>
                                <sec:authorize access="hasAnyAuthority('OPT_PRODUCTEXTEND_ADD', 'OPT_PRODUCT_EDIT')">
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button class="btn btn-info" type="button" id="addOrUpdate">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                提交
                                            </button>

                                            &nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                重置
                                            </button>
                                        </div>
                                    </div>
                                </sec:authorize>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>

