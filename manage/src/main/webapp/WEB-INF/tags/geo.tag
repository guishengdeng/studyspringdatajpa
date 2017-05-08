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
        $(".city-selector>.selection").remove();
        $(".district-selector>.selection").remove();
        $.get("/geo/listChildren.do?fieldName=${cityFieldName}&areaLevel=2&regionId=" + $(this).val(), function (data) {
            $(".city-selector").append(data);
            $(".city-selector select").dropdown({
                fullTextSearch: true
            });
        })
    });
    $("body").on("change", "select[name=${cityFieldName}]", function () {
        $(".district-selector>.selection").remove();
        $.get("/geo/listChildren.do?fieldName=${districtFieldName}&areaLevel=3&regionId=" + $(this).val(), function (data) {
            $(".district-selector").append(data);
            $(".district-selector select").dropdown({
                fullTextSearch: true
            });
        })
    });
</script>
