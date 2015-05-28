<%@ page import="dk.danthrane.TagLibUtils; dk.danthrane.twbs.FormTagLib.InputValidation" %>
<div class="form-group ${validationClass} ${clazz}">
    <label for='${id}'>${labelText}</label>
    <select class='form-control' name='${name}' ${multiple} id='${id}' ${disabled} ${raw(TagLibUtils.expandAttributes(attrs))}>
        <g:each in="${list}" var="item">
            <option value="${item.value}" ${item.selected}>${item.message}</option>
        </g:each>
    </select>
    <p class="help-block">
        ${raw(body())}
    </p>
</div>