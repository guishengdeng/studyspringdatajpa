<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<gbck:page title="退换货单列表">
    <jsp:attribute name="css">
        <style type="text/css">
            #orderReturn-table .name {
                min-width: 150px;

            }

            #orderReturn-table .operate, #orderReturn-table .status {
                min-width: 80px;
            }

            .profile-info-value {
                width: 180px;
            }

            textarea {
                margin: 0px;
                width: 400px;
                height: 70px;
            }

            button{
                height: 40px;
                width: 80px;
            }

        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
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
                    退货审核
                </li>
                <li class="active">
                    退货单审核
                </li>
            </ul>
        </div>

        <c:set value="${orderReturn}" var="orderReturn"/>
        <c:set value="${orderReturn.order}" var="order"/>

        <div class="page-content">
        <div class="table-detail">
            <div class="row">
                <div class="col-xs-12 col-sm-2">
                    <div class="text-center">
                        <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                            <div class="inline position-relative">
                                <span class="white">退换货单信息</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 col-sm-7">
                    <div class="space visible-xs"></div>

                    <div class="profile-user-info profile-user-info-striped">
                        <div class="profile-info-row">
                            <div class="profile-info-name"> 退货单号</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.returnCode}</span>
                            </div>
                        </div>

                        <div class="profile-info-row">


                            <div class="profile-info-name"> 用户</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.name}</span>
                            </div>

                            <div class="profile-info-name"> 电话</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.mobile}</span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name"> 退货金额</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.returnAmount}</span>
                            </div>

                            <div class="profile-info-name"> 原单金额</div>

                            <div class="profile-info-value">
                                <span>${order.orderAmount}</span>
                            </div>

                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name"> 退货类型</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.returnType.desc}</span>
                            </div>

                            <div class="profile-info-name">退货申请时间</div>

                            <div class="profile-info-value">
                                <span><fmt:formatDate type="both" value="${orderReturn.createTimestamp}"/></span>
                            </div>
                        </div>

                        <div class="profile-info-row">
                            <div class="profile-info-name"> 退换货说明</div>

                            <div class="profile-info-value">
                                <span>${orderReturn.description}</span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <hr>
            <div class="row">
                <div class="col-xs-12 col-sm-2">
                    <div class="text-center">
                        <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                            <div class="inline position-relative">
                                <span class="white">图片信息</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="profile-info-value">
                    <div class="profile-info-row">
                        <c:forEach items="${orderReturn.images}" var="image">
                            <div class="profile-info-value">
                                <img height="150" class="thumbnail inline no-margin-bottom" alt="Domain Owner's Avatar"
                                     src="${image}">
                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>
            <hr>
            <div class="row">
                <div class="col-xs-12 col-sm-2">
                    <div class="text-center">
                        <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                            <div class="inline position-relative">
                                <span class="white">商品明细</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 col-sm-7">
                    <div class="profile-user-info profile-user-info-striped" style="border: none;">
                        <div class="profile-info-row">
                            <table id="order-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>编码</th>
                                    <th>品名</th>
                                    <th>数量</th>
                                    <th>单价</th>
                                    <th>金额</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orderReturn.items}" var="orderReturnitem">
                                    <c:set var="item" value="${orderReturnitem.item}"/>
                                    <tr role="row">
                                        <td><c:out value="${item.productCode}"/></td>
                                        <td><c:out value="${item.name}"/></td>
                                        <td><c:out value="${item.quantity}"/></td>
                                        <td><c:out value="${item.price}"/></td>
                                        <td><c:out value="${item.quantity*item.price}"/></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>

            <hr>
            <div class="row">
                <div class="col-xs-12 col-sm-2">
                    <div class="text-center">
                        <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                            <div class="inline position-relative">
                                <span class="white">审核</span>
                            </div>
                        </div>
                    </div>
                </div>

                <%--<sec:authorize access="hasAuthority('OPT_ORDERRETURN_AUDIT')" >--%>
                    <div class="col-xs-12 col-sm-7">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <form action="" method="post" name="form">
                                    <div class="col-md-15 inline">
                                        <label style="padding-top: 5px">审核说明：</label>
                                        <div style="padding-left: 65px;">
                                            <textarea name="auditContent" ></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-15 inline">
                                        <label style="padding-top: 5px">拒绝理由：</label>
                                        <div style="padding-left: 65px;">
                                            <textarea name="rejectReason" placeholder="审核通过可不填"></textarea>
                                        </div>
                                    </div>
                                    <div>
                                        <c:if test="${orderReturn.returnType eq 'RETURN'}">
                                            <label>是否立即退款：</label>
                                            <select name="isRefundNow">
                                                <option value="REFUND_NOW" selected>立即退款</option>
                                                <option value="NOT_REFUND_NOW">不立即退款</option>
                                            </select>
                                        </c:if>
                                    </div>
                                    <input name="id" value="${orderReturn.id}" hidden/>
                                    <br>
                                    <div class="col-md-8 inline" style="padding-left: 65px;">
                                        <button class="btn btn-primary no-border"
                                                onclick="form.action='${pageContext.request.contextPath}/orderReturn/audit?auditStatus=PASS';form.submit()"><i class="icon-beaker align-top bigger-125"></i>通过</button>
                                        <button class="btn btn-danger no-border"
                                                onclick="form.action='${pageContext.request.contextPath}/orderReturn/audit?auditStatus=REJECT';form.submit()">否决</button>
                                        <button onclick="javascript:history.go(-1)">取消</button>
                                    </div>
                                </form>
                            </div>
                            <hr>
                        </div>
                    </div>
                <%--</sec:authorize>--%>

            </div>
        </div>
    </jsp:body>
</gbck:page>
