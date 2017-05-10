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
                    商户中心
                </li>
                <li class="active">
                    <a href="/platform/platformList.do">
                    平台公司
                    </a>
                </li>
                <li class="active">
                    合伙人
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                合伙人
                                <a href="/platform/platformList.do" class="btn btn-sm btn-primary pull-right"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    返回
                                </a>
                            </h3>
                            <form action="/platform/partnerList.do" method="get">
                                <input type="hidden" value="<c:out value="${vo.platformId}"/>" />
                                    合伙人ID<input name="id" type="text" placeholder="平台公司ID"  value="<c:out value="${vo.id}"/>" autocomplete="off"  style="width: 100px;height: 30px;">&nbsp;
                                    合伙人姓名<input name="corporateName" type="text" placeholder="法人姓名" value="<c:out value="${vo.corporateName}"/>"  autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                    法人手机<input name="mobile" type="text" placeholder="手机号码" value="<c:out value="${vo.mobile}"/>" autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                <div class="inline "><div class="inline pull-right"><i style="color:white;">_</i></div>
                                    <button type="submit" class="btn btn-info btn-sm" style="height: 30px;">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                           <div class="inline pull-right"><i style="color:white;">_</i></div>
                            <div class="inline pull-right">
                                <from id="downDate">
                                    <button type="submit" class="btn btn-success btn-sm">
                                        <i class="ace-icon fa fa-download bigger-110"></i>导出
                                    </button>
                                </from>
                            </div><div class="inline pull-right"><i style="color:white;">_</i></div>
                            <div class="inline pull-right">
                                <select name="shopType" style="width: 70px">
                                    <option value="" selected >请选择</option>
                                    <option value="" selected >全部</option>
                                    <option value="" selected >勾选项</option>
                                    <option value="" selected >当前页</option>
                                </select>
                            </div>
                            <div class="hr hr-18 dotted"></div>
                            <table class="table table-striped table-bordered table-hover audit-table">
                                <thead>
                                <tr>
                                    <th class="p-checkbox"><span class="pointer select-all">全选</span>/
                                        <span class="pointer select-inverted">反选</span></th>
                                    <th>城市合伙人ID</th>
                                    <th>城市合伙人名称</th>
                                    <th>省份</th>
                                    <th>城市</th>
                                    <th>状态</th>
                                    <th>上级平台</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr >
                                    <td class="p-checkbox" style="width:65px">
                                        <input type="checkbox" class="ui checkbox" name="group" value="${partner.id}"/>
                                    </td>
                                    <td><c:out value="H13542"/></td>
                                    <td><c:out value="合伙人店铺"/></td>
                                    <td><c:out value="四川"/></td>
                                    <td><c:out value="成都"/></td>
                                    <td style="color:red" id="id_${partner.id}">禁用</td>
                                    <td><c:out value="凤凰隔"/></td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <a class="btn btn-xs btn-info" href="/platform/partnerEdit.do?id=<c:out value="${partner.id}"/>">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i><span>详情</span>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                                <c:forEach items="${partnerSearchResVoPage.content}" var="partner">
                                    <tr >
                                        <td class="p-checkbox" style="width:65px">
                                            <input type="checkbox" class="ui checkbox" name="group" value="${partner.id}"/>
                                        </td>
                                        <td><c:out value="${partner.id}"/></td>
                                        <td><c:out value="${partner.name}"/></td>
                                        <td><c:out value="${partner.province.name}"/></td>
                                        <td><c:out value="${partner.city.name}"/></td>
                                        <td style="color:${partner.status eq "ENABLE"?"green":"red"}" id="id_${partner.id}">${partner.status eq "ENABLE"?"启用":"禁用"}</td>
                                        <td><c:out value="${partner.platform.name}"/></td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a class="btn btn-xs btn-info" href="/platform/partnerEdit.do?id=<c:out value="${partner.id}"/>">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i><span>详情</span>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="/platform/partnerList.do" springPage="${partnerSearchResVoPage}"/>
                            <br><br><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>
