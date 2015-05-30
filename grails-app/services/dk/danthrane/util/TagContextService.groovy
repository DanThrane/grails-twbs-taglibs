package dk.danthrane.util

/**
 * @author Dan Thrane
 */
class TagContextService {
    RequestStoreService requestStoreService

    private void enterContext(String context) {
        List contexts = requestStoreService.contexts
        if (!contexts) {
            contexts = requestStoreService.contexts = []
        }
        contexts.add(context)
    }

    private void leaveContext() {
        requestStoreService.contexts.pop()
    }

    boolean isInContext(String context) {
        requestStoreService.contexts.contains(context)
    }

    void context(String context, Closure closure) {
        enterContext(context)
        closure()
        leaveContext()
    }

}
