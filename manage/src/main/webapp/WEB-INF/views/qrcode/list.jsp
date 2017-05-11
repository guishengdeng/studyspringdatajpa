<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.biz.gbck.dao.mysql.po.qrcode.enums.BusinessStatusEnum" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%
    request.setAttribute("_businessStatusArray_", BusinessStatusEnum.values());
%>
<gbck:page title="二维码信息">
    <jsp:attribute name="css">
        <style type="text/css">
            #qrcode-table .name{
                min-width: 100px;
            }
            #qrcode-table .operate, #qrcode-table .status{
                min-width: 80px;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            <%--<sec:authorize access="hasRole('OPT_CAT_DELETE')">--%>
            $(".qrcode-ban-btn").click(function () {
                $("#id-of-qrcode").val($(this).data("id"));
                $("#name-of-ban-qrcode").html($(this).data("name"));
                $("#qrcode-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#qrcode-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var codeId = $("#id-of-qrcode").val();
                $.post("qrcode/delete.do", {
                    "id": codeId
                }, function (result) {
                    if (result) {
                        $("#tr-" + codeId).remove();
                    }
                }, "json");
                $("#qrcode-disable-confirm-modal").modal("hide");
            });
            <%--</sec:authorize>--%>
            /* $(function(){
                $("#qrcode-table").DataTable({
                    "lengthMenu": [[10,20,50,-1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [5,5], "orderable": false}],
                    "order": [[0, "asc"]]
                });
            }); */

            <sec:authorize access="hasRole('OPT_CAT_EDIT')">
            </sec:authorize>
        </script>
    </jsp:attribute>
    <jsp:body>
        <%-- <jsp:include page="component/navigations.jsp"/> --%>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        首页
                    </a>
                </li>
                <li class="active">
                    二维码列表
                </li>
            </ul>
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-qrcode">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <form action="qrcode/list.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>二维码</label>
                                    <input name="bcno" value='<c:out value="${reqVo.bcno}" />' type="text" placeholder="二维码"  autocomplete="off">
                                </div>
                                <div class="col-md-2 inline">
                                    <label>业务状态</label>
                                    <select name="busStatus">
									    <option value="" ${empty reqVo.busStatus ? 'selected' : ''} >全部</option>
									    <c:forEach items="${_businessStatusArray_}" var="_businessStatusArray_" varStatus="varStatus">
									        <option value="${_businessStatusArray_}" ${reqVo.busStatus eq _businessStatusArray_ ? 'selected' : ''} >${_businessStatusArray_.description}</option>
									    </c:forEach>
									</select>
                                </div>
                                <div class="col-md-2 inline">
                                    <label>是否删除</label>
                                    <select name="status">
                                        <option value="" ${empty reqVo.status ? 'selected' : ''} >全部</option>
                                        <option value="DISABLE" ${reqVo.status eq 'DISABLE' ? 'selected' : ''} >是</option>
                                        <option value="ENABLE" ${reqVo.status eq 'ENABLE' ? 'selected' : ''} >否</option>
                                    </select>
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="hr hr-18 dotted"></div>
                            <table id="qrcode-table" class="table table-hover">
                                <thead>
                                <tr>
                                    <th class="name">瓶码</th>
                                    <th class="name">箱码</th>
                                    <th class="name">商品编码</th>
                                    <th class="name">业务状态</th>
                                    <th class="name">是否删除</th>
                                    <th class="center operate" hidden>操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${qrcodes.content}" var="qrcode">
                                    <tr id="tr-${qrcode.bcno}">
                                        <td>${qrcode.bcno}</td>
                                        <td>${qrcode.boxno}</td>
                                        <td>${qrcode.litm}</td>
                                        <td>${qrcode.businessStatus}</td>
                                        <td>${qrcode.status eq 'ENABLE' ? '否' : '是'}</td>
                                        <td hidden>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <%--<sec:authorize access="hasAuthority('OPT_CAT_EDIT')">--%>
                                                    <%-- <a href="demo/cats/${qrcode.bcno}.do" --%>
                                                    <!-- <a href="#"
                                                       class="btn btn-minier btn-info">
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </a> -->
                                                <%--</sec:authorize>--%>
                                                <%--<sec:authorize access="hasAuthority('OPT_CAT_DELETE')">--%>
                                                    <%-- <c:if test="${qrcode.status eq 'ENABLE'}">
                                                        <a data-id="${qrcode.bcno}" title="删除"
                                                           data-name="${qrcode.bcno}"
                                                           class="btn btn-minier btn-danger qrcode-ban-btn">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if> --%>
                                                <%--</sec:authorize>--%>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="qrcode/list.do" springPage="${qrcodes}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <div id="qrcode-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要删除二维码<span
                                            id="name-of-ban-qrcode"></span> ?
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
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
