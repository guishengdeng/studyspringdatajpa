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
        <style>
            .col_red{
                color: red;
            }
            .col_green{
                color: green;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">
            $('.audit-table').DataTable({
                paging: false,
                info: false,
                order: [[9, "asc"]],
                "columnDefs": [{"targets": [0,4,8], "orderable": false}],
            });

            function updateShopStatus(shopId) {
                if(shopId ==null || shopId == undefined || shopId== ""){
                    alert("没有商户id我怎么禁用启用");
                    return;
                }
                $.ajax({
                    url: '/shops/toggleShopStatus.do',
                    data: {"shopId": shopId},
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if(data==true){
                            var status=$("#span_"+shopId+"").html();
                            if(status=="启用"){
                                $("#span_"+shopId+"").html("禁用");
                                $("#butName_"+shopId+"").html("启用");
                                $("#shop_"+shopId+"").addClass("col_red").removeClass("col_green");
                                $("#but_"+shopId+"").addClass("fa-unlock").removeClass("fa-lock");
                                $("#a_"+shopId+"").addClass("btn-success").removeClass("btn-danger");
                            }else{
                                $("#span_"+shopId+"").html("启用");
                                $("#butName_"+shopId+"").html("禁用");
                                $("#shop_"+shopId+"").addClass("col_green").removeClass("col_red");
                                $("#but_"+shopId+"").addClass("fa-lock").removeClass("fa-unlock");
                                $("#a_"+shopId+"").addClass("btn-danger").removeClass("btn-success");
                            }
                        }
                    }, error: function () {
                        alert("系统异常！");
                    }
                });
            }

            /**
             * 选择id集合
             * @returns {*}
             */
            function selectIds() {
                var chk_value = new Array();
                $('input[name="shop_ids"]:checked').each(function () {
                    chk_value.push($(this).val());
                });
                if (chk_value.length == 0) {
                    alert('你还没有选择任何内容！');
                    return false;
                }
                return chk_value;
            }

            /**
             * 多个修改上下架
             */
            function updateSelectStatus(status) {
                var shopIds = selectIds();
                if (shopIds == null || shopIds.length == null) {
                    return false;
                }
                $.ajax({
                    url: '/shops/toggleShopsStatus.do',
                    type: 'POST',
                    data: {"shopIds": '' + shopIds + '', "status": status},
                    dataType: 'json',
                    success: function (data) {
                        if (data==true) {
                            alert("修改成功");
                            window.location.href = "/shops/completeAuditList.do";
                        } else {
                            alert("修改失败")
                        }
                    },
                    error: function () {
                        alert("系统异常！");
                    }
                });

            }

            $("span.select-inverted").click(function () {
                $("input[type='checkbox']").each(function () {
                    this.checked = this.checked ? false : true;
                })
            });
            $("span.select-all").click(function () {
                $("input[type='checkbox']").each(function () {
                    this.checked = true;
                })
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
                    已审核商户
                </li>
            </ul>
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                已审核商户
                            </h3>
                            <form action="/shops/completeAuditList.do" method="get">
                                审核状态
                                <select name="auditStatus" style="width: 100px;height: 30px;">
                                    <option value="" selected >全部</option>
                                    <option <c:if test="${vo.auditStatus==30 && vo.auditStatusTwo == null}">selected</c:if>
                                            value="30" >审核通过</option>
                                    <option  <c:if test="${vo.auditStatus==0 && vo.auditStatusTwo == null}">selected</c:if>
                                            value="0"  >审核未通过</option>
                                </select>
                                    商户ID <input name="id" type="text" placeholder="商户ID"  value="<c:out value="${vo.id}"/>" autocomplete="off"  style="width: 100px;height: 30px;">&nbsp;
                                    商户名称 <input name="name" type="text" placeholder="商户名称" value="<c:out value="${vo.name}"/>"  autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                    手机号码<input name="mobile" type="text" placeholder="手机号码" value="<c:out value="${vo.mobile}"/>" autocomplete="off" style="width: 100px;height: 30px;">&nbsp;
                                <gbck:shopType fieldName="shopTypeId" fieldClasses="field"
                                                 shopTypeId="${vo.shopTypeId}"/>
                                <div class="inline "><div class="inline pull-right"><i style="color:white;">_</i></div>
                                    <button type="submit" class="btn btn-info btn-sm" style="height: 30px;">
                                        <i class="ace-icon fa fa-search bigger-110"></i>搜索
                                    </button>
                                </div>
                            </form>
                            <div class="inline pull-right">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="updateSelectStatus('DISABLE')">
                                    <i class="ace-icon fa   fa-lock bigger-110"></i>一键禁用
                                </button>
                            </div>
                            <div class="inline pull-right">
                                <button type="submit" class="btn btn-info btn-sm" onclick="updateSelectStatus('ENABLE')">
                                    <i class="ace-icon fa  fa-unlock bigger-110"></i>一键启用
                                </button>
                            </div><div class="inline pull-right"><i style="color:white;">_</i></div>
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
                                    <th style="width:80px" class="p-checkbox"><span class="pointer select-all">全选</span>/
                                        <span class="pointer select-inverted">反选</span></th>
                                    <th>商户ID</th>
                                    <th>商户名称</th>
                                    <th>商户类型</th>
                                    <th>商铺地址</th>
                                    <th>手机</th>
                                    <th>商户状态</th>
                                    <th>审核状态</th>
                                    <th>操作</th>
                                    <th style="display: none">排序</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${shopSearchResVoPage.content}" var="shopDetail">
                                    <tr id="tr_${shopDetail.id}">
                                        <td style="width:80px">
                                            <input  name="shop_ids" class="ace" type="checkbox" value="<c:out value='${shopDetail.shop.id}'/>"/>
                                            <span class="lbl"></span>
                                        </td>
                                        <td><c:out value="${shopDetail.shop.id}"/></td>
                                        <td><c:out value="${shopDetail.name}"/></td>
                                        <td><c:out value="${shopDetail.shopType.name}"/></td>
                                        <td><c:out value="${shopDetail.shopAddress}"/></td>
                                        <td><c:out value="${shopDetail.mobile}"/></td>
                                        <td class="${shopDetail.shop.status eq "ENABLE"?"col_green":"col_red"}" id="shop_${shopDetail.shop.id}"><span id="span_${shopDetail.shop.id}">${shopDetail.shop.status eq "ENABLE"?"启用":"禁用"}</span></td>
                                        <td style="color:${shopDetail.auditStatus==30?"green":"red"}">${shopDetail.auditStatus==30?"审核通过":"审核未通过"}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <a class="btn btn-xs btn-info" href="#">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i><span>编辑</span>
                                                </a>&nbsp;
                                                <a class="btn btn-xs btn-danger" id="a_${shopDetail.shop.id}" onclick="updateShopStatus('${shopDetail.shop.id}')">
                                                    <i id="but_${shopDetail.shop.id}" class="ace-icon fa fa-lock bigger-120"></i>
                                                    <span id="butName_${shopDetail.shop.id}">禁用</span>
                                                </a>
                                            </div>
                                        </td>
                                        <td style="display: none"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${shopDetail.createTime}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <gbck:springPagePagination url="/shops/completeAuditList.do" springPage="${shopSearchResVoPage}"/>
                            <br><br><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</depotnextdoor:page>
