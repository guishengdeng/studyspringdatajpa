<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depot" uri="/WEB-INF/gbck.tld" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="provinceId" required="false" type="java.lang.Integer" %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<%@ attribute name="multipe" required="false" type="java.lang.Boolean" description="是否支持多选" %>
    <select name="${fieldName}" class="search" ${multipe ? 'multiple' : ''} >
        <option value="">请选择</option>
        <depot:province provinceId="${provinceId}"/>
    </select>
省
<script type="application/javascript">
    $(function () {
        $(".province-selector select").dropdown({
            fullTextSearch: true
        });
    })
</script>