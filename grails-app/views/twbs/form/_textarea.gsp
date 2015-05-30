<%@ page import="dk.danthrane.twbs.FormTagLib; dk.danthrane.TagLibUtils; dk.danthrane.twbs.Validation" %>
<div class="form-group ${validationClass} ${clazz}">
    <label for="${id}">${labelText}</label>
    <textarea name="${name}" class="form-control" id="${id}" ${raw(placeholder)}
        ${disabled} ${raw(TagLibUtils.expandAttributes(attrs))}>${value}</textarea>

    <g:if test="${validation != Validation.DEFAULT}">
        <twbs:icon icon="${validation.icon}" class="form-control-feedback" />
        <span class="sr-only">(${validation.name().toLowerCase()})</span>
    </g:if>

    <p class="help-block">
        ${raw(body())}
    </p>
</div>
