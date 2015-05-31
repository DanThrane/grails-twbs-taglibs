<%@ page import="dk.danthrane.TagLibUtils" %>
<ul class="dropdown-menu ${clazz}" role="${role}" ${raw(labelledBy)} ${raw(TagLibUtils.expandAttributes(attrs))}>
    ${raw(body())}
</ul>