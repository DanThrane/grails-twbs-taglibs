<%@ page import="dk.danthrane.twbs.FormTagLib; dk.danthrane.TagLibUtils; dk.danthrane.twbs.Validation" %>

<g:if test="${isHorizontal}">
    <div class="${horizontalStyle.size.getClassName(12 - horizontalStyle.columns)} ${horizontalStyle.size.getOffsetName(horizontalStyle.columns)}">
</g:if>

<g:if test="${validation != Validation.DEFAULT && !showLabel}">
    <div class='${validationClass}'>
</g:if>

<g:if test="${showLabel}">
    <div class="checkbox ${clazz}">
        <label class="btn btn-link">
            <input type="checkbox" name="${name}" id="${id}" ${checked} ${disabled}
                ${raw(TagLibUtils.expandAttributes(attrs))} />
            ${labelText}
        </label>
    </div>
</g:if>
<g:else>
    <input type="checkbox" name="${name}" id="${id}" ${checked} ${disabled}
        ${raw(TagLibUtils.expandAttributes(attrs))} />
</g:else>

<g:if test="${validation != Validation.DEFAULT && !showLabel}">
    </div>
</g:if>

<g:if test="${isHorizontal}">
    </div>
</g:if>