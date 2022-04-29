package core;

import java.net.MalformedURLException;

public class BaseTest {

    protected BaseRouter baseRouter;
    protected PropertiesProvider properties;
    protected CapabilitiesProvider capabilities;

    public BaseTest() throws MalformedURLException {
        // TODO test with parallel run and optimize if necessary
        properties = new PropertiesProvider();
        capabilities = new CapabilitiesProvider(properties);
        baseRouter = new BaseRouter();


        // DriverProvider share initialized driver across all pages via static getter
        Driver driver = new Driver(properties, capabilities);
        new DriverProvider(driver);


    }
}
