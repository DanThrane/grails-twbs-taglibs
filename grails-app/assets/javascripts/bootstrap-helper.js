var bootstrap = {};
bootstrap.VALIDATION_NONE    = { clazz: null };
bootstrap.VALIDATION_SUCCESS = { clazz: "has-success" };
bootstrap.VALIDATION_WARNING = { clazz: "has-warning" };
bootstrap.VALIDATION_ERROR   = { clazz: "has-error" };

bootstrap.addValidationClass = function(element, validationClass) {
    if (validationClass.clazz === null) {
        element.removeClass("has-feedback has-success has-warning has-error");
    } else {
        element.addClass("has-feedback " + validationClass.clazz);
    }
};

bootstrap.removeFormValidation = function() {
    $(".has-feedback").removeClass("has-feedback has-success has-warning has-error");
};
