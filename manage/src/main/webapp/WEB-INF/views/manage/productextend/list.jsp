<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="属性列表">
    <jsp:attribute name="script">
           <script type="application/javascript">

             /*  function addProperty() {
                  // var categoryId = $('#curr_category_id').val();
                  /!* if(categoryId!=null && categoryId !=''){*!///?categoryId="+categoryId
                       window.location.href="product/categoryProperty/add.do
                 /!*  }else{*!/
                      // layer.msg("请选择分类后再添加");
                  /!* }*!/
               }*/
               /* function editProperty(id){
                var categoryId = $('#curr_category_id').val();
                window.location.href="product/categoryProperty/edit.do?categoryId="+categoryId+"&id="+id;
                }*/
               /*保存排序功能*/
               $('#save_sort').on('click',function(){
                   var categoryId = $('#curr_category_id').val();
                   $.ajax({
                       type: "POST",
                       dataType:"text",
                       data:$('#category_sort_form').serialize(),
                       url:'product/categoryProperty/save_sort.do'
                   }).done(function(){
                       window.location.href='/manage/categories/'+categoryId+".do"
                   });
               });
              /* var category=document.getElementById("category");
               var option=category.options;
               for(var i=0;i<option.length;i++){
                   option[i].onclick=function(){
                       alert(this.text);//获取下拉选项的文本值
                       alert(this.value);//获取下拉选项的value值
                   }
               }*/

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
                    <a href="#">
                        商品管理
                    </a>
                </li>
                <li class="active">
                    属性列表
                </li>
            </ul>
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                属性列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_PRODUCTEXTEND_ADD')">
                                   <%--onclick="addProperty();"--%>
                                    <a  href="product/categoryProperty/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                    <button  id="save_sort" class="btn btn-sm btn-primary">
                                        保存排序
                                    </button>
                                </sec:authorize>
                               </span>
                            </h3>
                            <div class="col-md-6">
                                <span>特别说明：数字越小越靠前,点击'保存排序'之后才生效</span>
                            </div>
                           <%-- <div class="col-md-6">
                                <label>状态</label>
                                <depotnextdoor:categorySelect fieldName="name"  categories="${categories}" id="category"/>
                            </div>--%>


                            <div class="col-xs-12 widget-container-col ui-sortable" id="widget-container-col-10">
                                <div class="widget-box ui-sortable-handle" id="widget-box-10">
                                    <div class="widget-header widget-header-small">
                                        <h5 class="widget-title smaller"></h5>
                                        <div class="widget-toolbar no-border">
                                            <ul class="nav nav-tabs" id="myTab">
                                                <c:forEach items="${categories}" var="category" varStatus="status">
                                                    <li class="<c:if test="${category.id==categoryId}">active</c:if>">
                                                        <a  href="manage/categories/${category.id}.do">
                                                                ${category.name}
                                                        </a>
                                                    </li>
                                                </c:forEach>

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <form id="category_sort_form" action="">
                                    <input type="hidden" name="categoryId" id="curr_category_id" value="${categoryId}"/>
                                    <table id="category_sort_table" class="table  table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="center">
                                                <label class="pos-rel">
                                                    <input type="checkbox" class="ace">
                                                    <span class="lbl"></span>
                                                </label>
                                            </th>

                                            <th class="center">属性ID</th>
                                            <th class="center">属性名</th>
                                            <th class="center">排序</th>
                                            <th class="center">状态</th>
                                            <th class="center">链接</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${currentProductExtend}" var="productExtend" varStatus="status" >
                                            <tr>
                                                <td class="center">
                                                    <label class="pos-rel">
                                                        <input type="checkbox" class="ace">
                                                        <span class="lbl"></span>
                                                    </label>
                                                </td>
                                                <td class="center"><c:out value="${productExtend.id}"/></td>
                                                <td class="center"><c:out value="${productExtend.name}"/></td>
                                                <td class="center">
                                                    <input type="hidden" name="propertySortListVos[${status.index}].id" value="${productExtend.id}">
                                                    <input type="number" style="width:40px" name="propertySortListVos[${status.index}].idx" value="<c:out value='${productExtend.idx}'/>">

                                                </td>
                                                <td class="center"><c:out value="${productExtend.status eq 'ENABLE' ? '启用': '禁用'}"/></td>
                                                <td class="center">
                                                    <a href = "product/extendProperty/detail/${productExtend.id}.do" class="btn btn-xs btn-primary">
                                                        属性值
                                                    </a>
                                                </td>
                                                <td class="center">
                                                    <sec:authorize access="hasAuthority('OPT_PRODUCTEXTEND_EDIT')">
                                                        <a href="product/categoryProperty/edit/${productExtend.id}/${categoryId}.do"<%--onclick="editProperty(${productExtend.id});"--%>
                                                           class="btn btn-minier btn-info">
                                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                        </a>
                                                    </sec:authorize>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
