package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private final Duration impTimeout = Duration.ofSeconds(5);
    private final Duration pageLoadTimeout = Duration.ofSeconds(10);

    @Getter private final AppiumDriver driver;

    public Driver(PropertiesProvider properties, CapabilitiesProvider capabilities) throws MalformedURLException {

        URL url = new URL(properties.getCommonProperties().getAppiumServerUrl());

        switch (properties.getDeviceProperties().getPlatformSystem()) {
            case iOS -> driver = new IOSDriver(url, capabilities.getCapabilities());
            case Android -> driver = new AndroidDriver(url, capabilities.getCapabilities());
            default -> throw new IllegalArgumentException("Unsupported value: " +
                    properties.getDeviceProperties().getPlatformSystem() + " for " +
                    properties.getDeviceProperties().getPlatformSystem().name());
        }
        driver.manage().timeouts().implicitlyWait(impTimeout);
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout);
    }

}