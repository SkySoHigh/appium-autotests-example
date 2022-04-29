package widgets;

import core.DriverProvider;
import io.appium.java_client.AppiumDriver;

public class BaseElement {

    protected AppiumDriver driver;

    public BaseElement() {
        driver = DriverProvider.getDriver();
        // !TODO set locator based on reflection on annotation
    }
}
