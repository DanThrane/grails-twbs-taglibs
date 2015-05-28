<%@ page import="dk.danthrane.TagLibUtils; dk.danthrane.twbs.FormTagLib.InputValidation" %>

<g:if test="${validation != InputValidation.DEFAULT}">
    <div class='${validationClass}'>
</g:if>

<div class="checkbox ${clazz}">
    <label class="btn btn-link">
        <input type="checkbox" name="${name}" id="${id}" ${checked} ${disabled}
            ${raw(TagLibUtils.expandAttributes(attrs))} />
        ${labelText}
    </label>
</div>

<g:if test="${validation != InputValidation.DEFAULT}">
    </div>
</g:if>
