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
<gbck:page title="二维码履历信息">
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
                    二维码履历列表
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
                                    <label>瓶码</label>
                                    <input name="bcno" value='<c:out value="${reqVo.bcno}" />' type="text" placeholder="瓶码"  autocomplete="off">
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
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${qrcodeResume.content}" var="item">
                                    <tr id="tr-${item.id}">
                                        <td>${item.bcno}</td>
                                        <td>${item.boxno}</td>
                                        <td>${item.litm}</td>
                                        <td>${item.businessStatus}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="qrcode/resume/list.do" springPage="${qrcodeResume}" />
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
