<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depot" uri="/WEB-INF/gbck.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="shopTypeId" required="false" type="java.lang.Long" %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<%@ attribute name="multiple" required="false" type="java.lang.Boolean" description="是否支持多选" %>
<div class="shop-type-tag-selector ${empty fieldClasses ? 'five wide field' : fieldClasses}">
    商户类型
    <select name="${fieldName}" class="search" style="width: 170px">
        <option value="">请选择</option>
        <depot:shopType shopTypeId="${shopTypeId==null?-1:shopTypeId}"/>
    </select>
</div>
<script type="application/javascript">
    $(function () {
        $(".shop-type-tag-selector select").dropdown({
            fullTextSearch: true
        });
    })
</script>