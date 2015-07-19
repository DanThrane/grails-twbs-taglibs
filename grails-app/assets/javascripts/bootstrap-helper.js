var bootstrap = {};
bootstrap.VALIDATION_NONE    = { clazz: null };
bootstrap.VALIDATION_SUCCESS = { clazz: "has-success" };
bootstrap.VALIDATION_WARNING = { clazz: "has-warning" };
bootstrap.VALIDATION_ERROR   = { clazz: "has-error" };

bootstrap.addValidationClass = function(element, validationClass) {
    var parent = element.closest(".form-group");
    if (validationClass.clazz === null) {
        parent.removeClass("has-feedback has-success has-warning has-error");
    } else {
        parent.addClass("has-feedback " + validationClass.clazz);
    }
};

bootstrap.removeFormValidation = function(formElement) {
    formElement.find(".has-feedback").removeClass("has-feedback has-success has-warning has-error");
};
