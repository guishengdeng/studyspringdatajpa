<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="库存日志">
    <jsp:attribute name="css">
        <style type="text/css">
            #cat-table .name {
                min-width: 150px;
            }

            #cat-table .operate, #cat-table .status {
                min-width: 80px;
            }
        </style>
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
                    库存
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
                                库存日志
                            </h3>
                            <form action="goods/stock.do" method="get">
                                <div class="col-md-3 inline">
                                    <label>商品编码</label>
                                    <input name="productCode" value='<c:out value="${productShowVo.productCode}" />'
                                           type="text" placeholder="商品编码" autocomplete="off">
                                </div>
                                <div class="col-md-3 inline">
                                    <label>商品名称</label>
                                    <input name="name" value='<c:out value="${productShowVo.name}" />' type="text"
                                           placeholder="商品名称" autocomplete="off">
                                </div>
                                <div class="col-md-3 inline">
                                    <label>品牌</label>
                                    <input name="brandName" value='<c:out value="${productShowVo.brandName}" />'
                                           type="text" placeholder="品牌" autocomplete="off">
                                </div>
                                <div class="col-md-2 inline">
                                    <label>商品类别</label>
                                    <gbck:stockCategorySelect fieldName="categoryName"
                                                              selectedStatus="${productShowVo.categoryName}"
                                                              withNone="true" spiritLabel="白酒" berrLabel="啤酒"
                                                              redWineLabel="红酒" foreignWineLable="洋酒" elseLabel="其他"/>
                                </div>
                                <div class="inline">
                                    <button type="submit" class="btn btn-info btn-sm">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="hr hr-18 dotted"></div>

                            <table id="cat-table" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="name">商品编码</th>
                                    <th class="status">商品名称</th>
                                    <th class="status">商品类别</th>
                                    <th class="status">品牌</th>
                                    <th class="status">单位</th>
                                    <th class="status">库存数量</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${page.content}" var="stock">
                                    <tr id="tr-${stock.productId}">
                                        <td><c:out value="${stock.productCode}"/></td>
                                        <td><c:out value="${stock.name}"/></td>
                                        <td><c:out value="${stock.categoryName}"/></td>
                                        <td><c:out value="${stock.brandName}"/></td>
                                        <td><c:out value="${stock.standard}"/></td>
                                        <td><c:out value="${stock.quantity}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="goods/stock.do" springPage="${page}"/>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</gbck:page>
