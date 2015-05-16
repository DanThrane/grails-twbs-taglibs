package dk.dm844.group1

import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException

class TagLibUtils {
    @Deprecated
    static <E> E getRequiredAttribute(attrs, String attr, String tagName) {
        def result = attrs[attr]
        if (result) {
            return result
        }
        throw new GrailsTagException("Tag $tagName requires attribute $attr!")
    }

    // Should replace getRequiredAttribute to assist the IDE auto-completion
    static <E> E fail(String tagName, String attr) {
        throw new GrailsTagException("Tag $tagName requires attribute $attr!")
    }

    @SuppressWarnings(["UnusedMethodParameter", "GroovyUnusedDeclaration"])
    static <E> E fail(Class<E> clazz, String attr, String tagName) {
        // class is supplied as a hint to the type checker, shouldn't be needed, but seems that IntelliJ
        // (at least) complains without it.
        throw new GrailsTagException("Tag $tagName requires attribute $attr!")
    }

}
