<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="优惠券查询">
    <jsp:attribute name="css">
        <style type="text/css">
            #voucher-table .name{
                min-width: 150px;
            }
            #voucher-table .operate, #voucher-table .status{
                min-width: 80px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="applivoucherion/javascript">
            <sec:authorize access="hasAuthority('OPT_voucher_DELETE')">
            $(".voucher-ban-btn").click(function () {

                $("#id-of-voucher").val($(this).data("id"));
                $("#name-of-ban-voucher").html($(this).data("name"));
                $("#voucher-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#voucher-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var voucherId = $("#id-of-voucher").val();
                $.post("demo/vouchers/delete.do", {
                    "id": voucherId
                }, function (result) {
                    if (result) {
                        $("#tr-" + voucherId).remove();
                    }
                }, "json");
                $("#voucher-disable-confirm-modal").modal("hide");
            });
            </sec:authorize>
            $(function(){
                $("#voucher-table").DataTable({
                    "lengthMenu": [[10,20,50,-1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [1,4], "orderable": false}],
                    "order": [[0, "asc"]]
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <jsp:include page="component/navigations.jsp"/>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li>
                    优惠券管理
                </li>
                <li class="active">
                    优惠券表
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
                                优惠券查询表 <span class="inline help-block">(Javascript DataTable 翻页查询，当前这个不会和后台交互，全部在页面完成)</span>
                            </h3>
                            <table id="voucher-table" class="table table-hover">
                                <thead>
                                <tr>
                                    <th class="name">优惠券名称</th>
                                    <th>优惠券面值</th>
                                    <th>有效天数</th>
                                    <th>优惠券描述</th>
                                    <th>下发人</th>
                                    <th>下发时间</th>
                                    <th>优惠券详情</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${vouchers}" var="voucher">
                                    <tr id="tr-${voucher.id}">

                                        <td><c:out value="${voucher.name}" /></td>
                                        <td><c:out value="${voucher.description}"/></td>
                                        <td><c:out value="${voucher.saleStatus.name}"/></td>
                                        <td><c:out value="${voucher.status eq 'ENABLE' ? '存活' : '死亡'}"/></td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <sec:authorize access="hasAuthority('OPT_voucher_CREATE')">
                                                    <a href="manage/vouchers/detail.do?voucherId=${voucher.id}"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize access="hasAuthority('OPT_voucher_DELETE')">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a data-id="${voucher.id}"
                                                           data-name='<c:out value="${voucher.name}"/>'
                                                           class="btn btn-minier btn-danger voucher-ban-btn">
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
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <sec:authorize access="hasAuthority('OPT_voucher_DELETE')">
                    <input type="hidden" id="id-of-voucher">
                    <div id="voucher-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要杀<span
                                            id="name-of-ban-voucher"></span> ?
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
