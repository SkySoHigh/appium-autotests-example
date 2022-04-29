package core;


import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class CapabilitiesProvider {

    @Getter private final DesiredCapabilities capabilities;

    CapabilitiesProvider(PropertiesProvider properties) {

        capabilities = new DesiredCapabilities();
        File app = new File(properties.getDeviceProperties().getAppPath());
        capabilities.setCapability("platformName", properties.getDeviceProperties().getPlatformSystem());
        capabilities.setCapability("deviceName", properties.getDeviceProperties().getDeviceName());
        capabilities.setCapability("app", app.getAbsolutePath());

        // Reset strategies -> https://appium.io/docs/en/writing-running-appium/other/reset-strategies/index.html
        if ( properties.getDeviceProperties().getFullReset()) {
            capabilities.setCapability("fullReset", properties.getDeviceProperties().getFullReset());
        } else {
            capabilities.setCapability("noReset", true);
        }

        switch (properties.getDeviceProperties().getPlatformSystem()) {
            case Android -> capabilities.setCapability("automationName", "UiAutomator2");
            case iOS -> capabilities.setCapability("automationName", "XCUITest");
        }
    }
}


