<%@ page import="dk.danthrane.twbs.ButtonTagLib; dk.dm844.group1.util.FaIcon; dk.danthrane.twbs.ButtonTagLib.ButtonStyle; dk.danthrane.twbs.ButtonTagLib.ButtonSize; dk.danthrane.twbs.Icon; dk.danthrane.twbs.Icon" %>
<twbs:row class="dualbox">
    <twbs:column offset="1" cols="4" type="XS">
        <twbs:select multiple="true" list="${selected}" labelText="${g.message(code: "twbs.dualbox.selected")}"
                     name="${prefix}Selected"/>
    </twbs:column>
    <twbs:column cols="2" class="dualbox-buttons" type="XS">
        <twbs:linkButton btnstyle="${ButtonStyle.PRIMARY}" domId="${prefix}MoveLeft">
            &laquo;
        </twbs:linkButton>
        <br/>
        <br />
        <twbs:linkButton btnstyle="${ButtonStyle.PRIMARY}" domId="${prefix}MoveRight">
            &raquo;
        </twbs:linkButton>
    </twbs:column>
    <twbs:column cols="4" type="XS">
        <twbs:select multiple="true" list="${available}" labelText="${g.message(code: "twbs.dualbox.available")}"
                     name="${prefix}Available"/>
    </twbs:column>
</twbs:row>