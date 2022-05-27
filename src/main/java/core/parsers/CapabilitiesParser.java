package core.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.AppiumCapabilities;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The class CapabilitiesParser is used to parse the settings and convert them to HasMap, where the device name will be the key,
 * and the POJO value will be an object based on <a href="#{@link}">{@link AppiumCapabilities}.
 */
public class CapabilitiesParser {

    private static final String fileName = "capabilities.json";

    /**
     * Reads the capabilities.json config and matches the device name with the capabilities specified for it.
     *
     * @return HashMap  where device name is mapped to <a href="#{@link}">{@link AppiumCapabilities.Capabilities} object.
     * @throws IOException if InputStream can't be acquired with getCapabilities method.
     */
    public static @NotNull HashMap<String, AppiumCapabilities.Capabilities> mapCapabilitiesToDevices() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        AppiumCapabilities[] capsList = mapper.readValue(ResourceParser.readFile(fileName), AppiumCapabilities[].class);

        HashMap<String, AppiumCapabilities.Capabilities> mappedCaps = new HashMap<>();
        for (AppiumCapabilities cp : capsList) {
            mappedCaps.put(cp.getName(), cp.getCaps());
        }
        return mappedCaps;
    }

    /**
     * Converts <a href="#{@link}">{@link AppiumCapabilities.Capabilities} object to the DesiredCapabilities used for driver creation.
     *
     * @param capabilities POJO based on <a href="#{@link}">{@link AppiumCapabilities.Capabilities}.
     * @return new DesiredCapabilities based on <a href="#{@link}">{@link AppiumCapabilities.Capabilities} map.
     * @see DesiredCapabilities
     */
    public static @NotNull DesiredCapabilities generateDesiredCaps(AppiumCapabilities.Capabilities capabilities) {
        ObjectMapper mapper = new ObjectMapper();
        return new DesiredCapabilities(mapper.convertValue(capabilities, new TypeReference<Map<String, Object>>() {
        }));
    }
}