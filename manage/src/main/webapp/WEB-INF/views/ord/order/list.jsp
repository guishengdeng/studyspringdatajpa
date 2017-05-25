<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="订单列表页" >
    <jsp:attribute name="css">
        <style type="text/css">
           /* #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }*/
        </style>
    </jsp:attribute>
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
                   var platForm = $('#platForm').val();
                   if(platForm=="xxx" || !platForm){
                       layer.msg("请手动选择平台公司");
                       return false;
                   }
                document.queryName.action = "orders.do";
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
                    订单列表
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
                                        订单列表
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
                                            <div class="col-md-3">
                                                <label>平台公司</label>
                                                <div class="row">
                                                    <div class="col-xs-8 col-sm-11">
                                                        <div class="input-group">
                                                            <%--<input name="platFormCompanyName" class="input-sm form-control" value='<c:out value="${orderReqVo.username}"/>' type="text" placeholder=""/>--%>
                                                                <%--js-example-basic-multiple --%>
                                                                <select id="platForm" class="js-example-basic-multiple" name="platFormId" onchange="changePlatForm(this.value)">
                                                                    <option value="xxx" >请选择平台公司</option>
                                                                    <c:forEach  items="${platFormRespVo}" var="platForm">
                                                                        <option value="${platForm.id}" >${platForm.platFormName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 inline">
                                                <label>城市合伙人</label>
                                                <div class="row">
                                                    <div class="col-xs-8 col-sm-11">
                                                        <%--input-daterange--%>
                                                        <div class="input-group">
                                                            <%--<input name="partnerName" class="input-sm form-control" value='<c:out value="${orderReqVo.username}"/>' type="text" placeholder=""/>--%>
                                                                <select id="partner" class="js-example-basic-multiple" name="partnerName">
                                                                    <option value="" >请选择城市合伙人</option>
                                                                        <%-- <c:forEach  items="${partnerRespVos}" var="partner">
                                                                        <option value="${partner.partnerName}" >${partner.partnerName}</option>
                                                                    </c:forEach>--%>
                                                                </select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="col-md-5 inline">
                                                     <div class="row">
                                                         <label>订单日期</label>
                                                         <div class="col-xs-8 col-sm-11">
                                                             <div class="input-daterange input-group">
                                                                 <input  id ="beginTimestamp" type="text" class="input-sm form-control" name="beginTimestamp">
                                                                 <span class="input-group-addon">
																		<i class="fa fa-exchange"></i>
																	</span>

                                                                 <input type="text" id="endTimestamp" class="input-sm form-control" name="endTimestamp">
                                                             </div>
                                                         </div>
                                                     </div>
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
                                    <th>平台公司</th>
                                    <th>城市合伙人</th>
                                    <th class="hidden-md hidden-sm hidden-xs">订单日期</th>
                                    <th class="hidden-md hidden-sm hidden-xs">订单号</th>
                                    <th class="hidden-md hidden-sm hidden-xs">金额</th>
                                    <th class="hidden-md hidden-sm hidden-xs">是否支付</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${orderPage.content}" var="order">
                                    <tr id="tr-${order.platFormCompanyName}">
                                        <td><c:out value="${order.platFormCompanyName}"/></td>
                                        <td><c:out value="${order.partnerName}"/></td>
                                        <td>
                                            <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </td>
                                        <td class="hidden-md hidden-sm hidden-xs"><c:out value="${order.orderCode}"/></td>
                                        <td class="hidden-md hidden-sm hidden-xs">
                                             <c:out value="${order.orderAmount}"/>
                                        </td>
                                        <td>
                                           <c:choose>
                                                <c:when test="${order.payStatus eq 'PAYED'}">
                                                    是
                                                </c:when>
                                               <c:when test="${order.payStatus eq 'UN_PAY'}">
                                                    否
                                               </c:when>
                                               <c:otherwise>
                                                   创建支付单
                                               </c:otherwise>
                                           </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <depotnextdoor:springPagePagination url="orders.do" springPage="${orderPage}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</depotnextdoor:page>
