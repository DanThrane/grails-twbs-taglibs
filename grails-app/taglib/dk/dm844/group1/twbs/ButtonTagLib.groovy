package dk.dm844.group1.twbs

class ButtonTagLib {
    static namespace = "twbs"

    static enum ButtonSize {
        LARGE(clazz: "lg"),
        DEFAULT(clazz: null),
        SMALL(clazz: "sm"),
        XTRA_SMALL(clazz: "xs")

        String clazz
    }

    static enum ButtonStyle {
        DEFAULT(clazz: "default"),
        PRIMARY(clazz: "primary"),
        SUCCESS(clazz: "success"),
        INFO(clazz: "info"),
        WARNING(clazz: "warning"),
        DANGER(clazz: "danger"),
        LINK(clazz: "link")

        String clazz
    }

    def button = { attrs, body ->
        ButtonStyle style = attrs.btnstyle ?: ButtonStyle.DEFAULT
        String type = attrs.type ?: null
        String name = attrs.name ?: ""
        String id = attrs.id ? "id='$attrs.id'" : ""
        String formaction = attrs.formaction ?: name
        String sizeAttr = computeSizeAttribute(attrs.size as ButtonSize)

        out << "<button"
        if (type) {
            out << " type=\"$type\""
        }
        out << " class=\"btn btn-${style.clazz} $sizeAttr\" $id name=\"$name\" value=\"$formaction\">${body()}" +
                "</button>"
    }

    def linkButton = { attrs, body ->
        ButtonStyle style = attrs.btnstyle ?: ButtonStyle.DEFAULT
        String controller = attrs.controller ?: null
        String action = attrs.action ?: null
        String id = attrs.id ?: null
        String domId = attrs.domId ? "id='$attrs.domId'" : ""
        String sizeAttr = computeSizeAttribute(attrs.size as ButtonSize)
        String clazz = attrs.class ?: ""
        Map domAttrs = attrs.domAttrs ?: [:]

        out << "<a href=\"${computeLink(controller, action, id)}\" class=\"btn btn-" +
                "${style.clazz} $clazz $sizeAttr\" $domId "
        domAttrs.each { k, v -> out << "$k='$v' " }
        out << ">"
        out << body()
        out << "</a>"
    }

    String computeSizeAttribute(ButtonSize size) {
        return (size == null || size == ButtonSize.DEFAULT) ? "" : "btn-${size.clazz}"
    }

    String computeLink(String controller, String action, String id) {
        if (controller == null && action == null && id == null) {
            return "#"
        }
        return createLink(controller: controller, action: action, id: id)
    }

}
