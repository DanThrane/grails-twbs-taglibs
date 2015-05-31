package dk.danthrane.twbs

import static dk.danthrane.TagLibUtils.*

/**
 * @author Dan Thrane
 */
class NavTagLib {
    static namespace = "twbs"

    def nav = { attrs, body ->
        NavStyle style = attrs.remove("style") ?: fail(NavStyle, "style", "twbs:nav")
        boolean justified = optionalBoolean(attrs.remove("justified"))
        boolean stacked = optionalBoolean(attrs.remove("stacked"))
        String clazz = attrs.remove("class") ?: ""
        List classes = [clazz, "nav-$style.baseName"]

        if (justified) {
            classes += "nav-justified"
        }
        if (stacked) {
            classes += "nav-stacked"
        }

        Map model = [classes: classes.join(" "), attrs: attrs]
        out << render([plugin: "twbs3", template: "/twbs/nav/nav", model: model], body)
    }

    def navItem = { attrs, body ->
        assistAutoComplete(attrs.size, attrs.style, attrs.class, attrs.block, attrs.active, attrs.disabled,
                attrs.absolute, attrs.action, attrs.base, attrs.controller, attrs.event, attrs.fragment, attrs.id,
                attrs.mapping, attrs.params, attrs.uri, attrs.url)

        boolean disabled = optionalBoolean(attrs.remove("disabled"))
        boolean active = optionalBoolean(attrs.remove("active"))

        List classes = []
        if (disabled) {
            classes += "disabled"
        }
        if (active) {
            classes += "active"
        }

        String link = g.link(attrs, body)
        Map model = [link: link, classes: classes.join(" ")]
        out << render([plugin: "twbs3", template: "/twbs/nav/item", model: model], body)
    }
}
