package widgets;


import locators.builder.LocatorBuilder;
import locators.builder.models.LocatorsToPlatforms;
import locators.builder.annotations.FindByPlatform;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;


public class BaseElement {
    @Getter
    @Setter
    private By locator;

    @Getter
    private final LocatorsToPlatforms locators;

    public BaseElement(FindByPlatform findByPlatform) {
        locators = LocatorBuilder.buildLocators(findByPlatform);
        locator = locators.getAndroid();
    }
}

