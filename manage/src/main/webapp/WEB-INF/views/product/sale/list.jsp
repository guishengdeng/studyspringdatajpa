<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="订单列表页" >

    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {
                $('#beginTimestamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss',

                }).on("dp.change", function (e) {
                    $('#endTimestamp').data("DateTimePicker").minDate(e.date);
                });
                $('#endTimestamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss',
                    useCurrent: false,

                }).on("dp.change", function (e) {
                    $('#beginTimestamp').data("DateTimePicker").maxDate(e.date);
                });
                $('#deliveryStartTimeStamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss',
                    useCurrent: false,

                }).on("dp.change", function (e) {
                    $('#beginTimestamp').data("DateTimePicker").maxDate(e.date);
                });
                $('#deliveryEndTimeStamp').datetimepicker({
                    format: 'YYYY/MM/DD H:mm:ss',
                    useCurrent: false,

                }).on("dp.change", function (e) {
                    $('#beginTimestamp').data("DateTimePicker").maxDate(e.date);
                });

                $('.js-example-basic-multiple').select2({
                     width:"200px",
                });

            });
            function changePlatForm(id){
                $('#partner').empty();
                $.ajax({
                    method : "POST",
                    url : "orders/findPartners.do",
                    data : {platFormId:id}
                }).done(function(result){
                    $('#partner').empty();
                      for(var index=0;index<result.length;index++){
                           $('#partner').append('<option value="'+result[index].partnerName+'">'+result[index].partnerName+'</option>');
                      }
                });
            }
            $('#query').on('click',function(){
                  /* var platForm = $('#platForm').val();
                   if(platForm=="xxx" || !platForm){
                       layer.msg("请手动选择平台公司");
                       return false;
                   }*/
                document.queryName.action = "saleDetail.do";
                document.queryName.submit();
            });
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
                <li class="active">
                    销售详情
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                                    <h3 class="header smaller lighter blue">
                                        销售详情列表
                                        <span class="hidden-sm hidden-xs btn-group pull-right">
                                        <%--<sec:authorize access="hasAuthority('OPT_ORDER_LIST')">
                                            <a href="orders.do" class="btn btn-sm btn-primary">
                                                返回
                                            </a>
                                        </sec:authorize>--%>
                                       </span>
                                    </h3>
                                    <%--用作查询的表单框--%>
                                <form action="" method="get" class="form-group" name="queryName">
                                    <div class="col-md-3 inline">
                                        <label>订单号</label>
                                        <input name="orderCode" value='<c:out value="${vo.orderCode}" />' type="text" placeholder=""  autocomplete="off">
                                    </div>
                                    <div class="col-md-4 inline">
                                            <label>制单日期</label>
                                                <div class="input-daterange input-group">
                                                    <input  id ="beginTimestamp" type="text" class="input-sm form-control" name="startTimeStamp">
                                                    <span class="input-group-addon">
                                                                            <i class="fa fa-exchange"></i>
                                                                        </span>

                                                    <input type="text" id="endTimestamp" class="input-sm form-control" name="endTimeStamp">
                                                </div>
                                    </div>
                                    <div class="col-md-4 inline">
                                        <div class="row">
                                            <label>出库日期</label>
                                            <div class="col-xs-8 col-sm-11">
                                                <div class="input-daterange input-group">
                                                    <input  id ="deliveryStartTimeStamp" type="text" class="input-sm form-control" name="deliveryStartTimeStamp">
                                                    <span class="input-group-addon">
                                                                                <i class="fa fa-exchange"></i>
                                                                            </span>

                                                    <input type="text" id="deliveryEndTimeStamp" class="input-sm form-control" name="deliveryEndTimeStamp">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3 inline">
                                        <label>商品编号</label>
                                        <input name="productCode" value='<c:out value="${vo.productCode}" />' type="text" placeholder=""  autocomplete="off">
                                    </div>
                                    <div class="col-md-3 inline">
                                        <label>商品名称</label>
                                        <input name="productName" value='<c:out value="${vo.productName}" />' type="text" placeholder=""  autocomplete="off">
                                    </div>
                                    <div class="col-md-2 inline">
                                        <label>订单状态</label>
                                        <depotnextdoor:orderStatus fieldName="status" selectedStatus="${vo.status}" withNone="true"/>
                                    </div>
                                    <div class="col-md-1 inline">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-11">
                                                <div class="input-daterange input-group">
                                                    <button type="button" class="btn btn-info btn-sm" id="query">
                                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                              <div class="hr hr-18 dotted"></div>
                            <table id="order-table" class="table  table-bordered table-hover">
                                <thead>
                                        <tr>
                                            <th>订单号</th>
                                            <th>订单状态</th>
                                            <th class="hidden-md hidden-sm hidden-xs">制单日期</th>
                                            <th class="hidden-md hidden-sm hidden-xs">出库单号</th>
                                            <th class="hidden-md hidden-sm hidden-xs">出库日期</th>
                                            <th class="hidden-md hidden-sm hidden-xs">商品编号</th>
                                            <th class="hidden-md hidden-sm hidden-xs">商品名称</th>
                                            <th class="hidden-md hidden-sm hidden-xs">单位</th>
                                            <th class="hidden-md hidden-sm hidden-xs">订购数量</th>
                                            <th class="hidden-md hidden-sm hidden-xs">发货数量</th>
                                            <th class="hidden-md hidden-sm hidden-xs">实收数量</th>
                                            <th class="hidden-md hidden-sm hidden-xs">单价</th>
                                            <th class="hidden-md hidden-sm hidden-xs">金额</th>
                                        </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${productPage.content}" var="product">
                                    <tr id="tr-${product.orderCode}">
                                        <td><c:out value="${product.orderCode}"/></td>
                                        <td>
                                            <c:if test="${product.status eq 'NEW'}">
                                                生成订单
                                            </c:if>
                                            <c:if test="${product.status eq 'PACKAGE'}">
                                                备货中
                                            </c:if>
                                            <c:if test="${product.status eq 'DELIVERED'}">
                                                已发货
                                            </c:if>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${product.createTimeStamp}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td><c:out value="${product.sn}"/></td>
                                        <td>
                                            <fmt:formatDate value="${product.deliveryTime}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs"><c:out value="${product.productCode}"/></td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                             <c:out value="${product.name}"/>
                                        </td>
                                        <td>
                                           <c:if test="${product.unit eq 'BOTTLE'}">
                                               瓶
                                           </c:if>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <c:out value="${product.quantity}"/>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">

                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">

                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <c:out value="${product.price}"/>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                            <fmt:formatNumber value="${product.price*product.quantity}" type="currency" pattern="￥.00"/>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <depotnextdoor:springPagePagination url="saleDetail.do" springPage="${productPage}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
