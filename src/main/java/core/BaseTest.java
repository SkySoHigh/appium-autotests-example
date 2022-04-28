package core;

import java.net.MalformedURLException;

public class BaseTest {

    public BaseTest() throws MalformedURLException {
        new PropertiesProvider();
        new CapabilitiesProvider();
    }
}
