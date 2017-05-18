<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="depot" uri="/WEB-INF/gbck.tld" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ attribute name="provinceFieldName" required="true" type="java.lang.String" %>
<%@ attribute name="cityFieldName" required="true" type="java.lang.String" %>
<%@ attribute name="districtFieldName" required="true" type="java.lang.String" %>
<%@ attribute name="provinceId" required="false" type="java.lang.Integer" %>
<%@ attribute name="cityId" required="false" type="java.lang.Integer" %>
<%@ attribute name="districtId" required="false" type="java.lang.Integer" %>
<%@ attribute name="fieldClasses" required="false" type="java.lang.String" %>
<div class="three fields">
    <manage:geoProvince fieldName="${provinceFieldName}" provinceId="${provinceId}"/>
    <manage:geoCity fieldName="${cityFieldName}" provinceId="${provinceId}" cityId="${cityId}"/>
    <manage:geoDistrict fieldName="${districtFieldName}" cityId="${cityId}"
                        districtId="${districtId}"/>
</div>
<script type="application/javascript">
    $("body").on("change", "select[name=${provinceFieldName}]", function () {
        $.get("/geo/listChildren.do?fieldName=${cityFieldName}&areaLevel=2&regionId=" + $(this).val(), function (data) {
            $(".city-selector option").remove();
            $(".district-selector option").remove();
            var values=data.data;
            $(".city-selector").append("<option value='' >请选择</option>");
            for(var i in values){
                $(".city-selector").append("<option value='"+ values[i].id+"' >"+values[i].name+"</option>");
            }
        })
    });
    $("body").on("change", "select[name=${cityFieldName}]", function () {
        $.get("/geo/listChildren.do?fieldName=${districtFieldName}&areaLevel=3&regionId=" + $(this).val(), function (data) {
            $(".district-selector option").remove();
            var values=data.data;
            $(".district-selector").append("<option value='' >请选择</option>");
            for(var i in values){
                $(".district-selector").append("<option value='"+ values[i].id+"' >"+ values[i].name+"</option>");
            }
        })
    });
</script>
