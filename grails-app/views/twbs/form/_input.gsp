<%@ page import="dk.danthrane.twbs.Validation; dk.danthrane.TagLibUtils; dk.danthrane.twbs.FormTagLib" %>

<div class="form-group ${validationClass} ${clazz}">
    <label for="${id}">${labelText}</label>

    <input name="${name}" type="${type}" class="form-control" id="${id}" type="${type}" value="${value}" ${raw(placeholder)}
        ${disabled} ${raw(TagLibUtils.expandAttributes(attrs))}>

    <g:if test="${validation != Validation.DEFAULT}">
        <twbs:icon icon="${validation.icon}" class="form-control-feedback" />
        <span class="sr-only">(${validation.name().toLowerCase()})</span>
    </g:if>

    <p class="help-block">
        ${raw(body())}
    </p>
</div>