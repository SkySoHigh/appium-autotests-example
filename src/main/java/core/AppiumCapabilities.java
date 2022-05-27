package core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

/**
 * The AppiumCapabilities class used to parse capabilities and represent them as a POJO,
 * matching the received capabilities and device name.
 */
@Value
public class AppiumCapabilities {
    @NonNull String name;
    @NonNull Capabilities caps;


    /**
     * The Capabilities class contains all available appium capabilities.
     *
     * @see <a href=" https://appium.io/docs/en/writing-running-appium/caps/">Appiu caps doc</a>
     */
    @Value
    public static class Capabilities {
        // General
        @JsonProperty(required = true)
        String automationName;
        @JsonProperty(required = true)
        String platformName;
        @JsonProperty(required = true)
        String platformVersion;
        @JsonProperty(required = true)
        String deviceName;

        String app;

        String otherApps;
        String browserName;
        String newCommandTimeout;
        String language;
        String locale;
        String udid;
        String orientation;
        String autoWebview;
        String noReset;
        String fullReset;
        String eventTimings;
        String enablePerformanceLogging;
        String printPageSourceOnFindFailure;
        String clearSystemFiles;

        // Android (could be extended based on used automation engine)
        // Default is UiAutomator2 -> https://github.com/appium/appium-uiautomator2-driver#capabilities
        String appPackage;
        String appActivity;
        String autoGrantPermissions;
        String enforceAppInstall;


        // iOS (could be extended based on used automation engine)
        // Default is XCUITest -> https://github.com/appium/appium-xcuitest-driver#capabilities
        String bundleId;
        String autoAcceptAlerts;
        String autoDismissAlerts;


    }
}

