package dk.danthrane.twbs

import static dk.danthrane.TagLibUtils.fail

class FormTagLib {
    static namespace = "twbs" // not sure if this is where it belongs? (Lars)

    static enum InputValidation {
        DEFAULT(icon: null),
        SUCCESS(icon: Icon.OK),
        WARNING(icon: Icon.ALERT),
        ERROR(icon: Icon.REMOVE)

        Icon icon
    }

    /**
     * Displays a Bootstrap input. It uses form group, which sets the width of the element to 100%. It comes with
     * a label.
     *
     * Body:        (Optional) Provides a help block for the input field.
     *
     * Attributes
     * name:        The 'name' attribute used on the input field.
     * labelText:   (Optional) The text for the label. Defaults to the name attribute
     * type:        (Optional) The 'type' attribute used on the input field. Defaults to "text"
     * id:          (Optional) The 'id' attribute used for the label and input. Will default to the name attribute
     * placeholder: (Optional) The 'placeholder' attribute used for the input. Will default to an empty string.
     * disabled:    (Optional) If the field should be disabled. Defaults to false.
     * validation:  (Optional) Of type {@link InputValidation}, defaults to {@link InputValidation#DEFAULT}.
     */
    def input = { attrs, body ->
        String name = attrs.name ?: fail(String, "name", "twbs:input")
        String id = attrs.id ?: name
        String labelText = attrs.labelText ?: name
        String placeholder = attrs.placeholder ? "placeholder=\"$attrs.placeholder\"" : ""
        String type = attrs.type ?: "text"
        boolean disabled = attrs.disabled ? Boolean.valueOf(attrs.disabled as String) : false
        String disabledAttr = disabled ? "disabled" : ""
        String value = attrs.value ?: ""
        String clazz = attrs.class ?: ""

        InputValidation validation = attrs.validation ?: InputValidation.DEFAULT
        String validationClass = "has-${validation.name().toLowerCase()} has-feedback"
        if (validation == InputValidation.DEFAULT) {
            validationClass = ""
        }

        out << """
        <div class="form-group $validationClass $clazz">
            <label for="$id">$labelText</label>
            <input type="$type" name="$name" value="${value}" class="form-control" id="$id" $placeholder $disabledAttr>
        """
        if (validation != InputValidation.DEFAULT) {
            out << twbs.icon(icon: validation.icon, class: "form-control-feedback")
            out << """
            <span class="sr-only">(${validation.name().toLowerCase()})</span>
            """
        }
        out << """
            <p class="help-block">
                ${body()}
            </p>
        </div>
        """
    }

    def textArea = { attrs, body ->
        String name = attrs.name ?: fail(String, "name", "twbs:input")
        String id = attrs.id ?: name
        String labelText = attrs.labelText ?: name
        String rows = attrs.rows ?: "3"
        String placeholder = attrs.placeholder ? "placeholder=\"$attrs.placeholder\"" : ""
        boolean disabled = attrs.disabled ? Boolean.valueOf(attrs.disabled as String) : false
        String disabledAttr = disabled ? "disabled" : ""
        String value = attrs.value ?: ""
        String clazz = attrs.class ?: ""

        InputValidation validation = attrs.validation ?: InputValidation.DEFAULT
        String validationClass = "has-${validation.name().toLowerCase()} has-feedback"
        if (validation == InputValidation.DEFAULT) {
            validationClass = ""
        }

        out << """
        <div class="form-group $validationClass $clazz">
            <label for="$id">$labelText</label>
            <textarea name="$name" rows="$rows" class="form-control" id="$id" $placeholder
                $disabledAttr>$value</textarea>
        """
        if (validation != InputValidation.DEFAULT) {
            out << twbs.icon(icon: validation.icon, class: "form-control-feedback")
            out << """
            <span class="sr-only">(${validation.name().toLowerCase()})</span>
            """
        }
        out << """
            <p class="help-block">
                ${body()}
            </p>
        </div>
        """
    }

    /**
     * Displays a checkbox.
     *
     * body:        The body will be shown as the text for the checkbox
     * validation:  (Optional) Of type {@link InputValidation}, defaults to {@link InputValidation#DEFAULT}. If this
     *              is not default, then the box will be colored appropriately to its validation state.
     */
    def checkbox = { attrs, body ->
        InputValidation validation = attrs.validation ?: InputValidation.DEFAULT
        String validationClass = "has-${validation.name().toLowerCase()}"
        if (validation == InputValidation.DEFAULT) {
            validationClass = ""
        }

        String name = attrs.name ?: fail(String, "name", "twbs:checkbox")
        if (validation != InputValidation.DEFAULT) {
            out << "<div class='$validationClass'>"
        }
        out << """
        <div class="checkbox">
            <label class="btn btn-link">
                <input type="checkbox" name="$name" id="$name"/>${body()}
            </label>
        </div>
        """
        if (validation != InputValidation.DEFAULT) {
            out << "</div>"
        }
    }

    /**
     * Displays a select.
     *
     * body:        This tag doesn't take any body.
     * list:        A list of items that will be shown in the select box.
     * multiple:    (Optional) If the select should allow multiple selection, defaults to false.
     * name:        (Optional) The name attribute used for the select, defaults to an empty string.
     * property:    (Optional) If the object passed in the list is a non primitive, then the value of that property
     *              will be passed into the 'value' attribute of the option tag. This will default to "id" (that is the
     *              tag will work out of the box for domain models).
     * renderAsCode:(Optional) If the message should be rendered as an i18n code. Defaults to false
     */
    def select = { attrs, body ->
        List list = attrs.list != null ? attrs.list : fail(List, "list", "twbs:select")
        String property = attrs.property ?: "id"
        boolean multiple = attrs.multiple ? Boolean.valueOf(attrs.multiple as String) : false
        boolean allowEmpty = attrs.allowEmpty ?: false
        boolean renderAsCode = attrs.renderAsCode ?: false
        String multipleAttr = multiple ? "multiple" : ""
        String name = attrs.name ?: ""
        String selectByValue = attrs.selectByValue ?: ""
        String labelText = attrs.labelText ?: name
        String id = attrs.id ?: name

        out << """<div class="form-group">"""
        out << "<label for='$id'>$labelText</label>"
        out << "<select class='form-control' name='$name' $multipleAttr id='$id'>"
        if (allowEmpty) {
            out << "<option value='null'>&mdash;</option>"
        }
        list.each {
            if (it.hasProperty(property)) {
                def value = it.properties[property]
                out << "<option value='${value}'"
                if (selectByValue as String == value as String) {
                    out << " selected"
                }
                String message = (renderAsCode) ? g.message(code: it.toString()) : it.toString()
                out << ">${message}</option>"
            } else {
                out << "<option"
                if (it as String == selectByValue) {
                    out << " selected"
                }
                String message = (renderAsCode) ? g.message(code: it.toString()) : it.toString()
                out << ">${message}</option>"
            }
        }
        out << "</select></div>"
    }

    def dualbox = { attrs, body ->
        String prefix = attrs.prefix ?: fail(String, "prefix", "twbs:dualbox")
        List available = attrs.available != null ? attrs.available : []
        List selected = attrs.selected != null ? attrs.selected : []
        out << render(template: "/twbs/dualbox", model: [prefix: prefix, available: available, selected: selected])
    }

    def requireDualboxAssets = { attrs, body ->
        out << asset.javascript(src: "components/dualbox.js")
    }

    def initDualbox = { attrs, body ->
        String prefix = attrs.prefix ?: fail(String, "prefix", "twbs:initDualbox")
        out << "dualbox.init(\"$prefix\");"
    }

}
