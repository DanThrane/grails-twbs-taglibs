package dk.danthrane.twbs

import org.springframework.validation.Errors

import static dk.danthrane.TagLibUtils.expandOptionalAttribute
import static dk.danthrane.TagLibUtils.fail

class FormTagLib {
    static namespace = "twbs"

    static enum InputValidation {
        DEFAULT(icon: null),
        SUCCESS(icon: Icon.OK),
        WARNING(icon: Icon.ALERT),
        ERROR(icon: Icon.REMOVE)

        Icon icon
    }

    private void assistAutoComplete(... dummy) {
        // Unfortunately we cannot simply ask for the attributes map, as this will break IntelliJ's simple attribute
        // auto-complete. Rather have a bit of extra typing in this tag-lib, than having to remember every attribute
        // for every tag. So we call this function so that it can see that they are in use, even though this function
        // doesn't care at all for them. All the work is really done by prepareCommonInputAttributes

        // TODO Check if there are other ways of hinting these are needed
    }

    private String findFieldFromName(String name) {
        int idx = name.lastIndexOf('.')
        if (idx != -1 && idx != name.length() - 1) {
            return name.substring(idx + 1)
        }
        return name
    }

    private String getValidationClass(InputValidation validation) {
        String validationClass = "has-${validation.name().toLowerCase()} has-feedback"
        if (validation == InputValidation.DEFAULT) {
            validationClass = ""
        }
        return validationClass
    }

    private Map prepareCommonInputAttributes(String tagName, Map attrs) {
        // Common attributes
        String name = attrs.remove("name") ?: fail(String, "name", "twbs:$tagName")
        String id = attrs.remove("id") ?: name

        boolean disabled = attrs.disabled ? Boolean.valueOf(attrs.disabled as String) : false
        String disabledAttr = disabled ? "disabled" : ""

        // Styling
        String clazz = attrs.class ?: ""
        String placeholder = expandOptionalAttribute("placeholder", attrs.remove("placeholder"))

        String labelText = attrs.remove("labelText")
        if (labelText == null) {
            String labelCode = attrs.remove("labelCode")
            if (labelCode != null) {
                labelText = g.message(code: labelCode)
            } else {
                labelText = name
            }
        }

        // Validation
        InputValidation validation = attrs.remove("validation") ?: InputValidation.DEFAULT

        // Value
        String value = attrs.remove("value")

        // Value from object
        def bean = attrs.remove("bean")
        String beanField = attrs.remove("beanField") ?: findFieldFromName(name)
        if (value == null && bean != null) {
            if (bean.hasProperty(beanField)) {
                value = bean.properties[beanField]
            }

            // Validation from object
            if (bean.hasProperty("errors")) {
                Errors errors = bean.errors
                if (errors) {
                    if (errors.getFieldError(beanField)) {
                        validation = InputValidation.ERROR
                    }
                }
            }
        }

        [name: name, id: id, labelText: labelText, placeholder: placeholder, disabled: disabledAttr,
         clazz: clazz, validation: validation, validationClass: getValidationClass(validation), value: value,
         attrs: attrs]
    }

    /**
     * Displays a Bootstrap input.
     *
     * It uses form group, which sets the width of the element to 100%. It comes with a label.
     *
     * All core form attributes are accepted. Extra attributes are applied to the &lt;input&gt; element
     *
     * Body:        (Optional) Provides a help block for the input field.
     *
     * Additional attributes:
     * type:        (Optional) The 'type' attribute used on the input field. Defaults to "text"
     */
    def input = { attrs, body ->
        assistAutoComplete(attrs.name, attrs.id, attrs.labelText, attrs.labelCode, attrs.placeholder,
                attrs.placeholder, attrs.disabled, attrs.validation, attrs.value, attrs.bean, attrs.beanField)

        Map model = prepareCommonInputAttributes("input", attrs)
        String type = attrs.remove("type") ?: "text"
        model.type = type

        out << render([plugin: "twbs3", template: "/twbs/input", model: model], body)
    }

    /**
     * Displays a Bootstrap text area.
     *
     * It uses form group, which sets the width of the element to 100%. It comes with a label.
     *
     * All core form attributes are accepted. Extra attributes are applied to the &lt;textarea&gt; element
     *
     * Body:        (Optional) Provides a help block for the field.
     */
    def textArea = { attrs, body ->
        assistAutoComplete(attrs.name, attrs.id, attrs.labelText, attrs.labelCode, attrs.placeholder,
                attrs.placeholder, attrs.disabled, attrs.validation, attrs.value, attrs.bean, attrs.beanField)

        Map model = prepareCommonInputAttributes("textArea", attrs)

        out << render([plugin: "twbs3", template: "/twbs/textarea", model: model], body)
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
        boolean disabled = attrs.disabled ? Boolean.valueOf(attrs.disabled as String) : false
        String disabledAttr = disabled ? "disabled" : ""
        String multipleAttr = multiple ? "multiple" : ""
        String name = attrs.name ?: ""
        String selectByValue = attrs.selectByValue ?: ""
        String labelText = attrs.labelText ?: name
        String id = attrs.id ?: name

        out << """<div class="form-group">"""
        out << "<label for='$id'>$labelText</label>"
        out << "<select class='form-control' name='$name' $multipleAttr id='$id' $disabledAttr>"
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
