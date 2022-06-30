package core.providers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverProvider implements WebDriverProvider {
    private final String appiumServerUrl = PropertiesProvider.getInstance().getAppiumServerUrl();

    @CheckReturnValue
    @Nonnull
    @Override
    public AppiumDriver createDriver(@Nonnull Capabilities capabilities) {
        try {
            return switch (capabilities.getPlatformName().name()) {

                case "ANDROID" -> new AndroidDriver(new URL(appiumServerUrl), capabilities);
                case "IOS" -> new IOSDriver(new URL(appiumServerUrl), capabilities);
                default -> throw new IllegalArgumentException("Unsupported platformName: "
                        + capabilities.getPlatformName().name() + "should be either Android or iOS");
            };
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
