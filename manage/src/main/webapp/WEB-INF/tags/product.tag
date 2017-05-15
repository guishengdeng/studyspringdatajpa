<%@ tag import="org.codelogger.utils.ValueUtils" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <label>${empty label ? '商品选择' : label}</label>
    <input type="hidden" name="${fieldName}" value="${selectedProducts}" readonly>

    <div class="search selection ui dropdown multiple"></div>
</div>
<div class="ui modal product-select ${product_tag_seq}">
    <div class="header">
        商品选择
    </div>
    <div class="content">
        <table class="ui celled table product-select-table${product_tag_seq}">
            <thead>
            <tr>
                <th class="p-checkbox">
                    <span class="pointer select-all">全选</span>|<span
                        class="pointer select-inverted">反选</span>
                </th>
                <th>中台id</th>
                <th>名称</th>
                <th>净含量(ml)</th>
                <th>箱规</th>
                <th>最小起售量</th>
                <th>仓库价(元)</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${product_tag_data}" var="product">
                <tr>
                    <td class="p-checkbox">
                        <input type="checkbox" value="${product.id}" data-name="${product.name}"
                               data-net-content="${product.netContent}">
                    </td>
                    <td class="p-centerId">${product.centerId}</td>
                    <td class="p-name">${product.name}</td>
                    <td>${product.netContent}</td>
                    <td>${product.standard}</td>
                    <td>${product.minQuantity}</td>
                    <td>${product.salesPrice/100}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="actions">
        <div class="ui deny button">
            取消
        </div>
        <div class="ui positive right labeled icon button product-select-confirm${product_tag_seq}">
            我选好了
            <i class="checkmark icon"></i>
        </div>
    </div>
</div>
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
            $('.ui.modal.product-select.${product_tag_seq}').modal('show');
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
                productDisplayElements += '<a class="ui label transition visible" style="display: inline-block !important;">' + $this.data("name") + "(" + $this.data("net-content") + "ml)" + '</a>';
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