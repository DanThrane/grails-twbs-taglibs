<div class="${dropdownClass} ${clazz}" aria-expanded="${expanded}">
    <twbs:button active="${attrs.active}" block="${attrs.block}" class="dropdown-toggle" id="${id}"
                 disabled="${attrs.disabled}" size="${attrs.size}" style="${attrs.style}"
                 data-toggle="dropdown" >
        ${raw(body())}
        <g:if test="${!hideCaret}">
            <twbs:caret />
        </g:if>
    </twbs:button>
    <g:selectContent key="dropdown-menu"/>
</div>