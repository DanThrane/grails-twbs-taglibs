package dk.danthrane.twbs

/**
 * @author Dan Thrane
 */
class MiscTagLib {
    static namespace = "twbs"

    def caret = { attrs, body ->
        out << "<span class='caret'></span>"
    }

}
