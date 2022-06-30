package locators.builder;

import io.appium.java_client.AppiumBy;
import locators.builder.pojo.LocatorMeta;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The ByStrategies class provides access to locator initialization methods depending on the search strategy being used.
 * <br/>
 * Strategies for <a href="#{@link}">{@link io.appium.java_client.pagefactory.AndroidFindBy} and <a href="#{@link}">{@link io.appium.java_client.pagefactory.iOSXCUITFindBy} are implemented by default.
 * All new strategies should override method getBy by exact implementation.
 */
public enum ByStrategies {

    BYACCESSABILITY("accessibility") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.accessibilityId(locatorMeta.getValue());
        }
    },
    BYCLASSNAME("className") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.className(locatorMeta.getValue());
        }
    },
    BYID("id") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.id(locatorMeta.getValue());
        }
    },
    BYTAG("tagName") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return By.tagName(locatorMeta.getValue());
        }
    },
    BYNAME("name") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.name(locatorMeta.getValue());
        }
    },
    BYXPATH("xpath") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return By.xpath(locatorMeta.getValue());
        }
    },
    BY_DATA_MATCHER("androidDataMatcher") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.androidDataMatcher(locatorMeta.getValue());
        }
    },
    BY_VIEW_MATCHER("androidViewMatcher") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.androidViewMatcher(locatorMeta.getValue());
        }
    },
    BY_NS_PREDICATE("iOSNsPredicate") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.iOSNsPredicateString(locatorMeta.getValue());
        }
    },
    BY_CLASS_CHAIN("iOSClassChain") {
        @Override
        public By getBy(LocatorMeta locatorMeta) {
            return AppiumBy.iOSClassChain(locatorMeta.getValue());
        }
    };

    private final String valueName;

    ByStrategies(String valueName) {
        this.valueName = valueName;
    }

    public static List<String> strategiesNames() {
        return Stream.of(values()).map((s) -> s.valueName).collect(Collectors.toList());
    }


    public String returnValueName() {
        return valueName;
    }

    public By getBy(LocatorMeta locatorMeta) {
        return null;
    }
}
