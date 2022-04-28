package core;


import lombok.Getter;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class CapabilitiesProvider {

    @Getter private static DesiredCapabilities capabilities;

    CapabilitiesProvider() {

        capabilities = new DesiredCapabilities();
        File app = new File(PropertiesProvider.getDeviceProperties().getAppPath());
        capabilities.setCapability("platformName", PropertiesProvider.getDeviceProperties().getPlatformSystem());
        capabilities.setCapability("deviceName", PropertiesProvider.getDeviceProperties().getDeviceName());
        capabilities.setCapability("app", app.getAbsolutePath());

        // Reset strategies -> https://appium.io/docs/en/writing-running-appium/other/reset-strategies/index.html
        if ( PropertiesProvider.getDeviceProperties().getFullReset()) {
            capabilities.setCapability("fullReset", PropertiesProvider.getDeviceProperties().getFullReset());
        } else {
            capabilities.setCapability("noReset", true);
        }

        switch ( PropertiesProvider.getDeviceProperties().getPlatformSystem() ) {
            case Android:
                capabilities.setCapability("automationName", "UiAutomator2");
                break;
            case iOS:
                capabilities.setCapability("automationName", "XCUITest");
                break;
        }

    }
}


