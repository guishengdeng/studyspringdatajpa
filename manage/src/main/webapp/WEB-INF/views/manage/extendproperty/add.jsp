<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--这是用户--%>
<depotnextdoor:page title="扩展属性值edit">
    <jsp:attribute name="script">
         <script type="application/javascript">
                $('#addOrUpdate').on('click',function(){
                     var value = $('#extendPropertyValue').val();
                     var id = $('#id').val();
                     var curr_extendProperties = $('#extendProperties').html();
                     var productExtendId = $('#productExtendId').val();

                     curr_extendProperties = JSON.parse(curr_extendProperties);
                     for (var index = 0;index < curr_extendProperties.length ;index++){
                          var  extendProperties = curr_extendProperties[index].value;
                          var  curr_id = curr_extendProperties[index].id;
                         if (value == extendProperties ){
                               if(curr_id == id){
                                   commonAjaxReq($('#extendProperty_form').serialize(),productExtendId);
                                   return true; //放行
                               }
                             layer.msg("您输入的值已存在,请重新输入");
                             return false;//不让用户提交
                         }
                     }
                        commonAjaxReq($('#extendProperty_form').serialize(),productExtendId);

                });
                function commonAjaxReq(data,productExtendId){
                    $.ajax({
                        method : "POST",
                        url : "product/extendProperty/addOrUpdate.do",
                        data : data,
                        dataType : "json"
                    }).done(function(result){
                        alert(result);
                        if(result){
                            //layer.msg("what you do is successful");
                            window.location.href = "product/extendProperty/detail/"+productExtendId+".do"
                        }else{
                            //layer.msg("what you do is failed");
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
                    <a href="product/productExtend/detail/${productExtendId}.do">
                        扩展属性
                    </a>
                </li>
                <li class="active">
                        扩展属性值
                </li>
                <li class="active">
                    <c:out value="${cmd}"/>
                </li>
            </ul>
        </div>
        <div class="profile-user-info profile-user-info-striped">
            <div class="profile-info-row">
                <div class="profile-info-name">分类</div>

                <div class="profile-info-value">

                    <span class="editable" id="category">${category.name}</span>

                </div>
            </div>
            <div class="profile-info-row">
                <div class="profile-info-name"> 属性名 </div>

                <div class="profile-info-value">
                    <span class="editable" id="name">${productExtend.name}</span>
                </div>
            </div>
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
                                <a href="product/extendProperty/detail/${productExtendId}.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </span>
                            </h3>
                            <%--product/extendProperty/addOrUpdate.do--%>
                            <form action="" method="post"
                                  class="form-horizontal" role="form" id="extendProperty_form">
                                <input type="hidden" name="productExtendId" id="productExtendId" value="${productExtendId}"/>
                                <input type="hidden" name="id" id="id" value="${extendProperty.id}"/>
                                <input type="hidden" name="idx" value="${extendProperty.idx}"/>
                                <input type="hidden" id="cmd" value="${cmd}"/>
                                <div  id="extendProperties" style="display: none">${currJson}</div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="extendPropertyValue">
                                        属性值
                                    </label>

                                    <div class="col-sm-9">
                                        <input type="text"  name="value" id="extendPropertyValue" placeholder="属性值：容量,体积等" class="required col-xs-10 col-sm-5" value="<c:out value='${extendProperty.value}'/>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           >
                                        是否启用
                                    </label>
                                    <div class="col-sm-9">
                                       <%-- <label class="checkbox-inline">
                                            <input type="radio" id="isEnable"
                                                   name="status" ${extendProperty.status.value == 1 ? 'checked' :''}
                                            value="ENABLE"> 是
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="radio"
                                                   name="status" ${extendProperty.status.value == 1 ? 'checked' :''}
                                            value="DISABLE"> 否
                                        </label>--%>

                                               <depotnextdoor:commonStatusRadio fieldName="status" selectedStatus="${extendProperty.status}" inline="true" enableLabel="是" disableLabel="否"/>
                                    </div>
                                </div>
                                <sec:authorize access="hasAnyAuthority('OPT_EXTENDPROPERTY_ADD', 'OPT_EXTENDPROPERTY_EDIT')">
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

