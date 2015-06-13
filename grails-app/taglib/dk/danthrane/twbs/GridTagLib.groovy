package dk.danthrane.twbs

import dk.danthrane.TagLibUtils

class GridTagLib {
    static namespace = "twbs"

    def container = { attrs, body ->
        boolean fluid = attrs.fluid ?: false
        String clazz = attrs.class ?: ""
        String id = attrs.id ? "id='$attrs.id'" : ""
        out << "<div class=\"container${fluid ? "-fluid" : ""} $clazz\" $id>"
        out << body()
        out << '</div>'
    }

    def row = { attrs, body ->
        String clazz = attrs.remove("class") ?: ""
        out << "<div class='row $clazz' ${TagLibUtils.expandAttributes(attrs)}>"
        out << body()
        out << '</div>'
    }

    def column = { attrs, body ->
        GridSize type = attrs.type ?: GridSize.MEDIUM
        String columns = attrs.cols ?: "12"
        String clazz = attrs.class ?: ""
        String offset = attrs.offset ? "col-${type.columnName}-offset-$attrs.offset" : ""
        out << "<div class=\"col-${type.columnName}-$columns $clazz $offset\">"
        out << body()
        out << "</div>"
    }

}
