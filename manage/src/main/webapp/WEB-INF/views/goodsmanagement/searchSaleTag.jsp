<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="猫">
    <jsp:attribute name="css">
        <style type="text/css">
            #cat-table .name{
                min-width: 150px;
            }
            #cat-table .operate, #cat-table .status{
                min-width: 80px;
            }
            #saleTageAdd{
                position:absolute;
                top: 20px;
                right: 12px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <sec:authorize access="hasAuthority('OPT_CAT_DELETE')">
            $(".cat-ban-btn").click(function () {

                $("#id-of-cat").val($(this).data("id"));
                $("#name-of-ban-cat").html($(this).data("name"));
                $("#cat-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var saleTagId = $("#id-of-cat").val();
                $.post("goodsmanagement/delete.do", {
                    "id": saleTagId
                }, function (result) {
                    if (result) {
                        $("#tr-" + saleTagId).remove();
                    }
                }, "json");
                $("#cat-disable-confirm-modal").modal("hide");
            });
            </sec:authorize>
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
                <li>
                    示例管理
                </li>
                <li class="active">
                    猫列表
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
                                销售标签 <span class="inline help-block">(数据库翻页查询)</span>
                            </h3>
                            <form action="goodsmanagement/search.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>名字</label>
                                    <input name="name" value='<c:out value="${saleTagSearch.name}" />' type="text" placeholder="名字"  autocomplete="off">
                                </div>
                                <div class="col-md-2 inline">
                                    <label>启用状态</label>
                                    <gbck:commonStatusSelect fieldName="saleStatus" selectedStatus="${saleTagSearch.saleStatus}" withNone="true" enableLabel="启用" disableLabel="禁用"/>
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                                <div>
                                <a  class="btn btn-info btn-sm" id="saleTageAdd" href="goodsmanagement/tag.do">
                                    添加标签
                                </a>
                                </div>
                            </form>
                            <div class="hr hr-18 dotted"></div>

                            <table id="cat-table" class="table table-hover">
                                <thead>
                                <tr>
                                    <th class="name">编号</th>
                                    <th>标签名</th>
                                    <th class="status">后台备注</th>
                                    <th class="status">是否启用</th>
                                    <th class="status">排序</th>
                                    <th class="center operate"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${page.content}" var="saleTag">
                                    <tr id="tr-${saleTag.id}">

                                        <td><c:out value="${saleTag.id}"/></td>
                                        <td><c:out value="${saleTag.name}"/></td>
                                        <td><c:out value="${saleTag.description}"/></td>
                                        <td><c:out value="${saleTag.saleStatus eq 'ENABLE' ? '启用':'禁用'}"/></td>
                                        <td><c:out value="${saleTag.idx}"/></td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_SALETAG_CREATE')">
                                                    <a href="goodsmanagement/find?id=${saleTag.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize access="hasAuthority('OPT_SALETAG_DELETE')">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${saleTag.id}"
                                                           data-name='<c:out value="${saleTag.name}"/>'
                                                           class="btn btn-minier btn-danger cat-ban-btn">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </sec:authorize>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="goodsmanagement/search.do" springPage="${page}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <sec:authorize access="hasAuthority('OPT_CAT_DELETE')">
                        <input type="hidden" id="id-of-cat">
                        <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <button type="button" class="bootbox-close-button close"
                                                data-dismiss="modal" aria-hidden="true">×
                                        </button>
                                        <div class="bootbox-body">您确定要杀死猫<span
                                                id="name-of-ban-cat"></span> ?
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-cancel-ban btn-default">
                                            取消
                                        </button>
                                        <button type="button" class="btn btn-confirm-ban btn-primary">
                                            确认
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
