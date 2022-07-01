package core;

import com.fasterxml.jackson.annotation.JsonAlias;
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
        @JsonAlias({"appium:automationName"})
        String automationName;

        @JsonProperty(required = true)
        String platformName;

        @JsonProperty(required = true)
        @JsonAlias({"appium:platformVersion"})
        String platformVersion;

        @JsonProperty(required = true)
        @JsonAlias({"appium:deviceName"})
        String deviceName;

        @JsonAlias({"appium:app"})
        String app;

        @JsonAlias({"appium:otherApps"})
        String otherApps;

        @JsonAlias({"appium:browserName"})
        String browserName;

        @JsonAlias({"appium:newCommandTimeout"})
        String newCommandTimeout;

        @JsonAlias({"appium:language"})
        String language;

        @JsonAlias({"appium:locale"})
        String locale;

        @JsonAlias({"appium:udid"})
        String udid;

        @JsonAlias({"appium:orientation"})
        String orientation;

        @JsonAlias({"appium:autoWebview"})
        String autoWebview;

        @JsonAlias({"appium:noReset"})
        String noReset;

        @JsonAlias({"appium:fullReset"})
        String fullReset;

        @JsonAlias({"appium:eventTimings"})
        String eventTimings;

        @JsonAlias({"appium:enablePerformanceLogging"})
        String enablePerformanceLogging;

        @JsonAlias({"appium:printPageSourceOnFindFailure"})
        String printPageSourceOnFindFailure;

        @JsonAlias({"appium:clearSystemFiles"})
        String clearSystemFiles;

        // Android (could be extended based on used automation engine)
        // Default is UiAutomator2 -> https://github.com/appium/appium-uiautomator2-driver#capabilities
        @JsonAlias({"appium:appPackage"})
        String appPackage;

        @JsonAlias({"appium:appActivity"})
        String appActivity;

        @JsonAlias({"appium:autoGrantPermissions"})
        String autoGrantPermissions;

        @JsonAlias({"appium:enforceAppInstall"})
        String enforceAppInstall;


        // iOS (could be extended based on used automation engine)
        // Default is XCUITest -> https://github.com/appium/appium-xcuitest-driver#capabilities
        @JsonAlias({"appium:bundleId"})
        String bundleId;

        @JsonAlias({"appium:autoAcceptAlerts"})
        String autoAcceptAlerts;

        @JsonAlias({"appium:autoDismissAlerts"})
        String autoDismissAlerts;
    }
}

