package dk.dm844.group1.twbs

import static dk.dm844.group1.TagLibUtils.fail

class GlyphIconTagLib {
    static namespace = "twbs"

    def icon = { attrs, body ->
        String clazz = attrs.class ?: ""
        String domId = attrs.domId ? "id='$attrs.domId'" : ""
        Icon icon = attrs.icon ?: fail(Icon, "icon", "twbs:icon")
        out << """<span class="glyphicon glyphicon-$icon.name $clazz" $domId></span>"""
    }
}
