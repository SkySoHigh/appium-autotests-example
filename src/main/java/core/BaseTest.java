package core;

import com.codeborne.selenide.Configuration;
import core.providers.CapabilitiesProvider;
import core.providers.DriverProvider;
import core.providers.PropertiesProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class BaseTest {
    protected BaseRouter baseRouter;
    protected CapabilitiesProvider capabilities;
    protected PropertiesProvider properties;

    public BaseTest() {
        properties = PropertiesProvider.getInstance();
        capabilities = CapabilitiesProvider.getInstance();
        baseRouter = new BaseRouter();
    }

    @BeforeMethod
    public void setUp() {
        closeWebDriver();
        Configuration.browserSize = null;
        Configuration.browserCapabilities = capabilities.getDesiredCapabilitiesForDevice("Test");
        Configuration.browser = DriverProvider.class.getName();
        open();
    }

    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }

}
