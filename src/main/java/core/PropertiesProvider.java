package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import lombok.Getter;

public class PropertiesProvider {
    private static final String propertiesFileName = "src/main/resources/test.properties";

    @Getter private static DeviceProperties deviceProperties;
    @Getter private static CommonProperties commonProperties;
    @Getter private static IntegrationProperties integrationProperties;


    PropertiesProvider() {
        try (FileInputStream fis = new FileInputStream(propertiesFileName)) {
            Properties property = new Properties();
            property.load(fis);

            deviceProperties = new DeviceProperties(property);
            commonProperties = new CommonProperties(property);
            integrationProperties = new IntegrationProperties(property);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


class DeviceProperties extends PropertiesParser {

    @Getter private final supportedOS platformSystem;
    @Getter private final String platformVersion;
    @Getter private final String appPath;
    @Getter private final String deviceName;
    @Getter private final String uuid;
    @Getter private final Boolean fullReset;
    @Getter private final Boolean autoAcceptAlert;

    public DeviceProperties(Properties property) {
        platformSystem = getFromEnum(supportedOS.class, getProperty(property, "platformSystem"));
        platformVersion = getProperty(property, "platformVersion");
        appPath = getProperty(property, "appPath");
        deviceName = getProperty(property, "deviceName");
        uuid = getProperty(property, "uuid", "");
        fullReset = Boolean.parseBoolean(getProperty(property, "fullReset", "false"));
        autoAcceptAlert = Boolean.parseBoolean(getProperty(property, "autoAcceptPerms", "false"));
    }

    public enum supportedOS {
        Android,
        iOS,
    }

}


class CommonProperties extends PropertiesParser {
    @Getter private final String appiumServerUrl;

    public CommonProperties(Properties property) {
        appiumServerUrl = getProperty(property, "appiumServerUrl", "http://127.0.0.1:4723/wd/hub");
    }

}

class IntegrationProperties extends PropertiesParser {
    @Getter private final String testRailUrl;
    @Getter private final String testRailToken;
    @Getter private final String youTrackUrl;
    @Getter private final String youTrackToken;

    public IntegrationProperties(Properties property) {
        testRailUrl = getProperty(property, "testRailUrl", "");
        testRailToken = getProperty(property, "testRailToken", "");
        youTrackUrl = getProperty(property, "youTrackUrl", "");
        youTrackToken = getProperty(property, "youTrackToken", "");
    }

}
