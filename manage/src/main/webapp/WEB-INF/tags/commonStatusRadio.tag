<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedStatus" required="false" type="java.lang.Object" %>
<%@ attribute name="enableLabel" required="false" type="java.lang.String" %>
<%@ attribute name="disableLabel" required="false" type="java.lang.String" %>
<%@ attribute name="inline" required="false" type="java.lang.Boolean" %>
<div class="control-group">
    <div class="radio ${inline ? 'inline' : ''}">
        <label>
            <input name="${fieldName}" type="radio" class="ace" value="ENABLE"
            ${empty selectedStatus ? 'checked' : ''} ${selectedStatus eq 'ENABLE' ? 'checked' : ''}>
            <span class="lbl">${empty enableLabel ? '启用' : enableLabel}</span>
        </label>
    </div>
    <div class="radio ${inline ? 'inline' : ''}">
        <label>
            <input name="${fieldName}" type="radio" class="ace" value="DISABLE"
            ${selectedStatus eq 'DISABLE' ? 'checked' : ''}>
            <span class="lbl">${empty disableLabel ? '启用' : disableLabel}</span>
        </label>
    </div>
</div>