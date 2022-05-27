package core.providers;

import core.parsers.PropertiesParser;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

/**
 * The PropertiesProvider class provides access to test properties/settings.
 * <br>
 * Singleton which Implements Initialization-on-demand holder idiom.
 */
public class PropertiesProvider {

    @Getter
    private final String appiumServerUrl;
    @Getter
    private final String testRailUrl;
    @Getter
    private final String testRailToken;
    @Getter
    private final String youTrackUrl;
    @Getter
    private final String youTrackToken;


    private PropertiesProvider() {
        try {
            Properties properties = PropertiesParser.getAllProperties();
            this.appiumServerUrl = PropertiesParser.getProperty(properties, "appiumServerUrl", "http://127.0.0.1:4723/wd/hub");
            this.testRailUrl = PropertiesParser.getProperty(properties, "testRailUrl", "");
            this.testRailToken = PropertiesParser.getProperty(properties, "testRailToken", "");
            this.youTrackUrl = PropertiesParser.getProperty(properties, "youTrackUrl", "");
            this.youTrackToken = PropertiesParser.getProperty(properties, "youTrackToken", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static PropertiesProvider getInstance() {
        return PropsHolder.instance;
    }


    private static class PropsHolder {
        public static final PropertiesProvider instance = new PropertiesProvider();
    }


}


