package locators.builder.models;

import lombok.Getter;
import org.openqa.selenium.By;

/**
 * The LocatorsToPlatforms class stores the widget's locators by platform and
 * provides an appropriate getters for all page classes.
 */
public class LocatorsToPlatforms {
    @Getter
    private final By android;
    @Getter
    private final By ios;

    public LocatorsToPlatforms(By androidLocator, By iosLocator) {
        this.android = androidLocator;
        this.ios = iosLocator;
    }

}
