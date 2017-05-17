<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="depotnextdoor" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<depotnextdoor:page title="page.user.edit">
    <jsp:attribute name="css">

    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
          $('.audit-table').DataTable({
              paging: false,
              info: false,
              order: [[6, "asc"]],
              "columnDefs": [{"targets": [7], "orderable": false}],
          });

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
                    客户管理
                </li>
                <li class="active">
                    未审核商户
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                未审核商户
                            </h3>
                            <form action="/shops/auditList.do" method="get">
                                    商户ID <input name="id" type="text" placeholder="商户ID"  value="<c:out value="${vo.id}"/>" autocomplete="off"  style="width: 100px;height: 30px;">&nbsp;
                                    商户名称 <input name="name" type="text" placeholder="商户名称" value="<c:out value="${vo.name}"/>"  autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                    手机号码<input name="mobile" type="text" placeholder="手机号码" value="<c:out value="${vo.mobile}"/>" autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                     商户类型
                                <select name="shopType" style="width: 100px;height: 30px;">
                                    <option value="" selected >请选择</option>
                                    <c:forEach items="${shopTypes}" var="shopType">
                                    <option  <c:if test="${shopType.id == vo.shopType.id}">selected </c:if>
                                            value="<c:out value="${shopType.id}"/>"><c:out value="${shopType.name}"/></option>
                                    </c:forEach>
                                </select>
                                <div class="inline ">
                                    <button type="submit" class="btn btn-info btn-sm" style="width: 100px;height: 30px;">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="inline pull-right">
                                <button type="submit" class="btn btn-info btn-sm">
                                    <i class="ace-icon fa  fa-cloud-upload bigger-110"></i>导入商户
                                </button>
                            </div>
                            <div class="inline pull-right">
                                <a type="submit" class="btn btn-success btn-sm">
                                    <i class="ace-icon fa glyphicon-plus bigger-110"></i>新增商户
                                </a>
                            </div>
                            <div class="hr hr-18 dotted"></div>
                            <table class="table table-striped table-bordered table-hover audit-table">
                                <thead>
                                <tr>

                                    <th>商户ID</th>
                                    <th>商户名称</th>
                                    <th>商户类型</th>
                                    <th>商铺地址</th>
                                    <th>手机</th>
                                    <th>审核状态</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shopSearchResVoPage.content}" var="shopDetail">
                                    <tr id="tr-${shopDetail.id}">
                                        <td><c:out value="${shopDetail.shop.id}"/></td>
                                        <td><c:out value="${shopDetail.name}"/></td>
                                        <td><c:out value="${shopDetail.shopType.name}"/></td>
                                        <td><c:out value="${shopDetail.shopAddress}"/></td>
                                        <td><c:out value="${shopDetail.mobile}"/></td>
                                        <td>${shopDetail.auditStatus==25 || shopDetail.auditStatus==20?"待审核":null}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopDetail.createTime}"/></td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a href="/shops/audit.do?shopId=<c:out value="${shopDetail.shop.id}"/>"
                                                   class="btn btn-minier btn-info btn ">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    审核
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="/shops/auditList.do" springPage="${shopSearchResVoPage}"/>
                            <br><br><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>