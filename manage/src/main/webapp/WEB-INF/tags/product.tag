<%@ tag import="org.codelogger.utils.ValueUtils" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="depot" uri="/WEB-INF/gbck.tld" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="label" required="false" type="java.lang.String" %>
<%@ attribute name="selectedProducts" required="false" type="java.lang.String " %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<%
    request.setAttribute("product_tag_seq",
            ValueUtils.getValue((Integer) request.getAttribute("product_tag_seq")) + 1);
%>
<depot:product/>
<div class="${empty fieldClasses ? 'field' : fieldClasses} product-selector-${product_tag_seq}">
    <label class="col-sm-2 control-label no-padding-right">${empty label ? '商品选择' : label}</label>
    <div class="col-sm-9">
	    <input type="hidden" name="${fieldName}" value="${selectedProducts}" readonly>
	    <div class="search selection dropdown multiple form-control"></div>
    </div>
</div>

<div class="modal fade product-select ${product_tag_seq}" tabindex="-1">
   <div class="modal-dialog">
		<div class="modal-content">
		    <div class="modal-header no-padding">
				<div class="table-header">
					商品选择
				</div
		    <div class="modal-body no-padding">
<!-- 		    <div class="row"> -->
<!-- 				<div class="col-xs-12"> -->
		        <table class="table table-striped table-bordered table-hover table-responsive product-select-table${product_tag_seq}">
		            <thead>
		            <tr>
		                <th class="sorting_disabled center">
		                    <span class="pointer select-all">全选</span>|<span
		                        class="pointer select-inverted">反选</span>
		                </th>
		                <th>中台id</th>
		                <th class="hidden-480">名称</th>
		                <th>净含量(ml)</th>
		                <th>箱规</th>
		                <th>最小起售量</th>
		                <th>仓库价(元)</th>
		            </tr>
		            </thead>
		            <tbody>
		            <c:if test="${product_tag_data != null}">
			            <c:forEach items="${product_tag_data}" var="product">
			                <tr>
			                    <td class="checkbox">
			                        <input type="checkbox" value="${product.id}" data-name="${product.name}">
<%-- 			                               data-net-content="${product.netContent}"> --%>
			                    </td>
			                    <td>1</td>
			                    <td class="hidden-480" style="width:300;">2</td>
			                    <td>2</td>
								<td>3</td>
			                    <td>4</td>
			                    <td>5</td>
			                </tr>
			            </c:forEach>
		            </c:if>
		            </tbody>
		        </table>
		    </div>
		    <div class="modal-footer no-margin-top">
		        <button type="button" class="btn btn-default" data-dismiss="modal">
		            取消
		        </button>
		        <button type="button" class="right btn btn-success product-select-confirm${product_tag_seq}" data-dismiss="modal">
		            我选好了 <i class=" fa fa-check"></i>
		        </button>
<!-- 		    </div></div> -->
		    </div><!-- body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- .modal -->

<script type="application/javascript">
    function initProductTableSelectStatus(){
        var productIds = $(".product-selector-${product_tag_seq} input[name='${fieldName}']").val();
        if (productIds) {
            productIds = "," + productIds;
            $(".product-select-table${product_tag_seq} input").each(function () {
                this.checked = productIds.indexOf("," + $(this).val()) != -1;
            });
        }
    }
    $(function () {
        $(".product-selector-${product_tag_seq} .selection").click(function () {
            initProductTableSelectStatus();
            $('.modal.product-select.${product_tag_seq}').modal('show');
        });
        $('.product-select-table${product_tag_seq}').DataTable({
            "lengthMenu": [[-1], ["所有"]],
            "columnDefs": [{"targets": [0], "orderable": false}],
            "order": [[1, "asc"]]
        });
        $(".product-select-table${product_tag_seq} span.select-all").click(function () {
            $(".product-select-table${product_tag_seq} input[type='checkbox']").each(function () {
                this.checked = true;
            })
        });
        $(".product-select-table${product_tag_seq} span.select-inverted").click(function () {
            $(".product-select-table${product_tag_seq} input[type='checkbox']").each(function () {
                this.checked = this.checked ? false : true;
            })
        });
        $(".product-select-table${product_tag_seq} tr").click(function () {
            var checkBox = $(this).find("input[type='checkbox']").get(0);
            if (checkBox) {
                checkBox.checked = checkBox.checked ? false : true;
            }
        });
        $(".product-select-confirm${product_tag_seq}").click(function () {
            var productIds = "";
            var productDisplayElements = "";
            $(".product-select-table${product_tag_seq} input:checked").each(function () {
                var $this = $(this);
                productIds += "," + $this.val();
                productDisplayElements += '<div class="label" style="display: inline-block !important;">' + $this.data("name") + "(" + $this.data("net-content") + "ml)" + '</div> ';
            });
            $(".product-selector-${product_tag_seq} input[name='${fieldName}']").val(productIds.substr(1));
            $(".product-selector-${product_tag_seq} .selection").html(productDisplayElements);
        });
        $(function(){
            initProductTableSelectStatus();
            $(".product-select-confirm${product_tag_seq}").click();
        })
    });
</script>