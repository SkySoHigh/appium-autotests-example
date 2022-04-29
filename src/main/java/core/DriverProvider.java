package core;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;

public class DriverProvider {
    @Getter private static AppiumDriver driver;

    public DriverProvider(Driver driver) {
        DriverProvider.driver = driver.getDriver();
    }

}
