<%@ page import="dk.danthrane.TagLibUtils; dk.danthrane.twbs.FormTagLib" %>

<div class="form-group ${validationClass} ${clazz}">
    <label for="${id}">${labelText}</label>

    <input name="${name}" type="${type}" class="form-control" id="${id}" type="${type}" value="${value}" ${placeholder}
        ${disabled} ${raw(TagLibUtils.expandAttributes(attrs))}> %{-- The last attributes are already escaped --}%

    <g:if test="${validation != FormTagLib.InputValidation.DEFAULT}">
        <twbs:icon icon="${validation.icon}" class="form-control-feedback" />
        <span class="sr-only">(${validation.name().toLowerCase()})</span>
    </g:if>

    <p class="help-block">
        ${raw(body())}
    </p>
</div>