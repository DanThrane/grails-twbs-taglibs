package dk.dm844.group1.twbs

import static dk.dm844.group1.TagLibUtils.fail

class LabelTagLib {
    static namespace = "twbs"

    def label = { attrs, body ->
        LabelType type = attrs.type ?: fail(LabelType, "type", "twbs:label")
        out << """
        <span class="label ${computeLabelClass(type)}">${body()}<span>
        """
    }

    private String computeLabelClass(LabelType type) {
        return (type == LabelType.DEFAULT) ? "" : "label-${type.clazz}"
    }
}
