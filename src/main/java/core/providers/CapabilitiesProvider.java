package core.providers;

import core.AppiumCapabilities;
import core.parsers.CapabilitiesParser;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;


/**
 * The CapabilitiesProvider class provides access to the appium device capabilities.
 * <br>
 * Singleton which Implements Initialization-on-demand holder idiom.
 */
public class CapabilitiesProvider {

    /**
     * Stores HashMap with mapped device name to provided Capabilities.
     */
    private final HashMap<String, AppiumCapabilities.Capabilities> mappedCapsToDevices;

    private CapabilitiesProvider() {
        try {
            this.mappedCapsToDevices = Objects.requireNonNull(CapabilitiesParser.mapCapabilitiesToDevices());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CapabilitiesProvider getInstance() {
        return CapsHolder.instance;
    }

    /**
     * Gets <a href="#{@link}">{@link AppiumCapabilities.Capabilities} from for specified device as POJO.
     * Could be used for modification or further customization.
     *
     * @param deviceName testing device name
     */
    public AppiumCapabilities.Capabilities getCapabilitiesForDevice(String deviceName) {
        AppiumCapabilities.Capabilities caps = mappedCapsToDevices.get(deviceName);
        if (caps == null) {
            throw new IllegalArgumentException("No capabilities found for device with name: " + deviceName);
        }
        return caps;
    }

    /**
     * Generates <a href="#{@link}">{@link DesiredCapabilities} based on {@link AppiumCapabilities.Capabilities}
     * for the specified device.
     *
     * @param deviceName testing device name
     */
    public DesiredCapabilities getDesiredCapabilitiesForDevice(String deviceName) {
        return CapabilitiesParser.generateDesiredCaps(getCapabilitiesForDevice(deviceName));
    }

    private static class CapsHolder {
        public static final CapabilitiesProvider instance = new CapabilitiesProvider();
    }

}
