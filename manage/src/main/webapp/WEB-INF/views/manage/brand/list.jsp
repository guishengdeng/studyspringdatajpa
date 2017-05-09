<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="gbck" tagdir="/WEB-INF/tags" %>
<gbck:page title="商品品牌管理">
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
    <jsp:attribute name="script">
        <script type="application/javascript">
            <%--<sec:authorize access="hasRole('OPT_CAT_DELETE')">--%>
            $(".cat-ban-btn").click(function () {

                $("#id-of-cat").val($(this).data("id"));
                $("#name-of-ban-cat").html($(this).data("name"));
                $("#cat-disable-confirm-modal").modal();
            });
            $(".btn-cancel-ban").click(function () {
                $("#cat-disable-confirm-modal").modal("hide");
            });
            $(".btn-confirm-ban").click(function () {
                var catId = $("#id-of-cat").val();
                $.post("demo/cats/delete.do", {
                    "id": catId
                }, function (result) {
                    if (result) {
                        $("#tr-" + catId).remove();
                    }
                }, "json");
                $("#cat-disable-confirm-modal").modal("hide");
            });
            <%--</sec:authorize>--%>
            $(function () {
                $("#cat-table").DataTable({
                    "lengthMenu": [[10, 20, 50, -1], [10, 20, 50, "所有"]],
                    "columnDefs": [{"targets": [1, 4], "orderable": false}],
                    "order": [[0, "asc"]]
                });
            });
            <sec:authorize access="hasRole('OPT_CAT_EDIT')">
            </sec:authorize>
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
                    商品品牌管理
                </li>
                <li class="active">
                    商品品牌列表
                </li>
            </ul>
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-cat">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                商品品牌管理
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                    <a href="product/brand/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        添加
                                    </a>
                                </span>
                            </h3>
                            <div>
                                <select class="chosen-select form-control" id="form-field-select-3" data-placeholder="Choose a State...">
                                    <option value="">  </option>
                                    <option value="AL">Alabama</option>
                                    <option value="AK">Alaska</option>
                                    <option value="AZ">Arizona</option>
                                    <option value="AR">Arkansas</option>
                                    <option value="CA">California</option>
                                    <option value="CO">Colorado</option>
                                    <option value="CT">Connecticut</option>
                                    <option value="DE">Delaware</option>
                                    <option value="FL">Florida</option>
                                    <option value="GA">Georgia</option>
                                    <option value="HI">Hawaii</option>
                                    <option value="ID">Idaho</option>
                                    <option value="IL">Illinois</option>
                                    <option value="IN">Indiana</option>
                                    <option value="IA">Iowa</option>
                                    <option value="KS">Kansas</option>
                                    <option value="KY">Kentucky</option>
                                    <option value="LA">Louisiana</option>
                                    <option value="ME">Maine</option>
                                    <option value="MD">Maryland</option>
                                    <option value="MA">Massachusetts</option>
                                    <option value="MI">Michigan</option>
                                    <option value="MN">Minnesota</option>
                                    <option value="MS">Mississippi</option>
                                    <option value="MO">Missouri</option>
                                    <option value="MT">Montana</option>
                                    <option value="NE">Nebraska</option>
                                    <option value="NV">Nevada</option>
                                    <option value="NH">New Hampshire</option>
                                    <option value="NJ">New Jersey</option>
                                    <option value="NM">New Mexico</option>
                                    <option value="NY">New York</option>
                                    <option value="NC">North Carolina</option>
                                    <option value="ND">North Dakota</option>
                                    <option value="OH">Ohio</option>
                                    <option value="OK">Oklahoma</option>
                                    <option value="OR">Oregon</option>
                                    <option value="PA">Pennsylvania</option>
                                    <option value="RI">Rhode Island</option>
                                    <option value="SC">South Carolina</option>
                                    <option value="SD">South Dakota</option>
                                    <option value="TN">Tennessee</option>
                                    <option value="TX">Texas</option>
                                    <option value="UT">Utah</option>
                                    <option value="VT">Vermont</option>
                                    <option value="VA">Virginia</option>
                                    <option value="WA">Washington</option>
                                    <option value="WV">West Virginia</option>
                                    <option value="WI">Wisconsin</option>
                                    <option value="WY">Wyoming</option>
                                </select>
                            </div>

                            <table id="cat-table" class="table table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th class="name">品牌名称</th>
                                    <th class="name">分类名称</th>
                                    <th class="status">排序</th>
                                    <th>状态</th>
                                    <th class="center operate"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${brands}" var="brand">
                                    <tr>
                                        <td>${brand.id}</td>
                                        <td>${brand.name}</td>
                                        <td>${brand.categoryName}</td>
                                        <td>${brand.idx}</td>
                                        <td>${brand.status}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                    <%--<sec:authorize access="hasAuthority('OPT_CAT_EDIT')">--%>
                                                <a href="product/brand/add?brandId=${brand.id}.do"
                                                   class="btn btn-minier btn-info">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                </a>
                                                    <%--</sec:authorize>--%>
                                                    <%--<sec:authorize access="hasAuthority('OPT_CAT_DELETE')">--%>
                                                <c:if test="${param.enabled != 'false'}">
                                                    <a data-id="${brand.id}"
                                                       data-name="${brand.name}"
                                                       class="btn btn-minier btn-danger cat-ban-btn">
                                                        <i class="ace-icon fa fa-ban bigger-120"></i>
                                                    </a>
                                                </c:if>
                                                    <%--</sec:authorize>--%>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <div id="cat-disable-confirm-modal" role="dialog" class="modal" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <button type="button" class="bootbox-close-button close"
                                            data-dismiss="modal" aria-hidden="true">×
                                    </button>
                                    <div class="bootbox-body">您确定要杀死猫<span
                                            id="name-of-ban-cat"></span> ?
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
