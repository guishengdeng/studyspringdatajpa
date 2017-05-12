<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="属性值列表">
    <jsp:attribute name="script">
         <script type="application/javascript">
             function saveSort() {
                  var $productExtendId = $('#curr_productExtend_id').val();
                  $.ajax({
                      method : "POST",
                      data : $('#extendProperty_sort_form').serialize(),
                      url : "product/extendProperty/saveSort.do"

                  }).done(function(){
                      window.location.href = "product/extendProperty/detail/"+$productExtendId+".do"
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
                    <a href="manage/categories.do">
                        商品管理
                    </a>
                </li>
                <li>
                    <a href="product/extendProperty/detail/${productExtendId}.do">
                        属性列表
                    </a>
                </li>
                <li class="active">
                    属性值列表
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
           <%-- <div class="profile-info-row">
                <div class="profile-info-name">状态</div>

                <div class="profile-info-value">

                    <span class="editable" id="status">${productExtend.status eq 'ENABLE' ? '启用' : '禁用'}</span>

                </div>
            </div>--%>

        </div>
        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                属性值列表
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <sec:authorize access="hasAuthority('OPT_EXTENDPROPERTY_ADD')">
                                    <a  href="product/extendProperty/add/${productExtendId}.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                    <button onclick="saveSort();"  class="btn btn-sm btn-primary">
                                        保存排序
                                    </button>
                                </sec:authorize>
                               </span>
                            </h3>
                            <div class="col-md-12">
                                <p>特别说明：数字越小越靠前,点击'保存排序'之后才生效</p>
                            </div>
                            <div class="col-xs-12 widget-container-col ui-sortable" id="widget-container-col-10">
                                <form id="extendProperty_sort_form" action="">
                                    <input type="hidden" name="productExtendId" id="curr_productExtend_id" value="${productExtendId}"/>
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
                                            <th class="center">属性值</th>
                                            <th class="center">排序</th>
                                            <th class="center">状态</th>

                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${extendProperties}" var="extendProperty" varStatus="status" >
                                            <tr>
                                                <td class="center">
                                                    <label class="pos-rel">
                                                        <input type="checkbox" class="ace">
                                                        <span class="lbl"></span>
                                                    </label>
                                                </td>
                                                <td class="center"><c:out value="${extendProperty.id}"/></td>
                                                <td class="center"><c:out value="${extendProperty.value}"/></td>
                                                <td class="center">
                                                    <input type="hidden" name="extendPropertySortListVos[${status.index}].id" value="${extendProperty.id}">
                                                    <input type="number" style="width:40px" name="extendPropertySortListVos[${status.index}].idx" value="<c:out value='${extendProperty.idx}'/>">

                                                </td>
                                                <td class="center"><c:out value="${extendProperty.status eq 'ENABLE' ? '启用': '禁用'}"/></td>

                                                <td class="center">
                                                    <sec:authorize access="hasAuthority('OPT_EXTENDPROPERTY_EDIT')">
                                                        <a href="product/extendProperty/edit/${extendProperty.id}/${productExtendId}.do"<%--onclick="editProperty(${productExtend.id});"--%>
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
