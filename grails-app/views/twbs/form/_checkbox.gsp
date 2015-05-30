<%@ page import="dk.danthrane.twbs.FormTagLib; dk.danthrane.TagLibUtils; dk.danthrane.twbs.Validation" %>

<g:if test="${validation != Validation.DEFAULT}">
    <div class='${validationClass}'>
</g:if>

<div class="checkbox ${clazz}">
    <label class="btn btn-link">
        <input type="checkbox" name="${name}" id="${id}" ${checked} ${disabled}
            ${raw(TagLibUtils.expandAttributes(attrs))} />
        ${labelText}
    </label>
</div>

<g:if test="${validation != Validation.DEFAULT}">
    </div>
</g:if>
