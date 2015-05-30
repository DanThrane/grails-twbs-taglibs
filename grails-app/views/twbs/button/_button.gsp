<%@ page import="dk.danthrane.TagLibUtils" %>
<button type="button" class="${classes}" ${raw(disabledAttribute)} ${raw(TagLibUtils.expandAttributes(attrs))}>
    ${raw(body())}
</button>