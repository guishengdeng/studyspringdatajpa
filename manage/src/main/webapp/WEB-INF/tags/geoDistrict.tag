<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depot" uri="/WEB-INF/gbck.tld" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="cityId" required="false" type="java.lang.Integer" %>
<%@ attribute name="districtId" required="false" type="java.lang.Integer" %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<%@ attribute name="multipe" required="false" type="java.lang.Boolean" description="是否支持多选" %>
    <select name="${fieldName}" class="search district-selector dept_select" ${multipe ? 'multiple' : ''} >
        <option value="">请选择</option>
        <depot:district cityId="${cityId}" districtId="${districtId}"/>
    </select>
区/县
<script type="application/javascript">
    $(function () {
        $(".district-selector select").dropdown({
            fullTextSearch: true
        });
    })
   /* $(function(){
        $('.dept_select').chosen();
    });*/
</script>