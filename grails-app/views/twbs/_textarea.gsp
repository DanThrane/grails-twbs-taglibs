<%@ page import="dk.danthrane.TagLibUtils; dk.danthrane.twbs.FormTagLib.InputValidation" %>
<div class="form-group ${validationClass} ${clazz}">
    <label for="${id}">${labelText}</label>
    <textarea name="${name}" class="form-control" id="${id}" ${placeholder}
        ${disabled} ${raw(TagLibUtils.expandAttributes(attrs))}>${value}</textarea>

    <g:if test="${validation != InputValidation.DEFAULT}">
        <twbs:icon icon="${validation.icon}" class="form-control-feedback" />
        <span class="sr-only">(${validation.name().toLowerCase()})</span>
    </g:if>

    <p class="help-block">
        ${raw(body())}
    </p>
</div>
