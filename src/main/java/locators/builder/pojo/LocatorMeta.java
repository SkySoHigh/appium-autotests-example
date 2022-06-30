package locators.builder.pojo;

import lombok.Getter;

/**
 * The LocatorMeta class stores information for element localization, provided by appropriate annotation.
 * <br/>
 * Method: localization strategy
 * <br/>
 * Value: search pattern for the given localization strategy
 * <br/>
 * Example: (xpath = "//*[@text='Login']") -> Method: xpath, Value: "//*[@text='Login']"
 */
public class LocatorMeta {

    @Getter
    private final String method;
    @Getter
    private final String value;

    public LocatorMeta(String method, String value) {
        this.method = method;
        this.value = value;
    }
}
