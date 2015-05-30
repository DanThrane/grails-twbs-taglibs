package dk.danthrane.util

import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

import static dk.danthrane.TagLibUtils.*

/**
 * @author Dan Thrane
 */
class CaptureTagLib {
    TagCaptureService tagCaptureService

    def content = { attrs, body ->
        String key = attrs.key ?: fail(String, "key", "g:content")
        tagCaptureService.captureTag(key, body)
    }

    def selectContent = { attrs, body ->
        String key = attrs.key ?: fail(String, "key", "g:content")
        Closure tag = tagCaptureService.retrieveTag(key)

        if (tag != null) {
            out << tag()
        } else {
            throw new GrailsTagException("Unable to find content with key='$key'")
        }
    }

}
