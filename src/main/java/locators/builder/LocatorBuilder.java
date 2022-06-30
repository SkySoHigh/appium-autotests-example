package locators.builder;

import locators.builder.annotations.FindByPlatform;
import locators.builder.pojo.LocatorMeta;
import locators.builder.pojo.LocatorsToPlatforms;
import org.openqa.selenium.By;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The LocatorBuilder class is used to create element locators (<a href="#{@link}">{@link By}) based on page class field annotations (<a href="#{@link}">{@link FindByPlatform}) and used element search strategies (<a href="#{@link}">{@link ByStrategies}).
 */
public class LocatorBuilder {
    protected static final Class<?>[] DEFAULT_ANNOTATION_METHOD_ARGUMENTS = new Class<?>[]{};
    private static final List<String> BUILT_IN_METHOD_TO_BE_EXCLUDED = new ArrayList<>() {
        {
            Stream.of(Object.class, Annotation.class, Proxy.class)
                    .map(Class::getDeclaredMethods)
                    .map(LocatorBuilder::getMethodNames)
                    .flatMap(List::stream)
                    .forEach(this::add);
        }
    };
    private static final List<String> FIND_BY_METHODS_TO_BE_EXCLUDED = new ArrayList<>(List.of("priority"));

    /**
     * Creates <a href="#{@link}">{@link LocatorMeta} based on provided element annotation.
     * <br/>
     * NOTE: If annotation specifies multiple search strategies (id = "", xpath = ""), the first matching one will be used.
     *
     * @param elementAnnotation Annotation containing information about the element localization strategy.
     *                          <br/>
     *                          Could be any of FindBy annotations or its heirs:
     *                          <br/>
     *                          <a href="#{@link}">{@link io.appium.java_client.pagefactory.AndroidFindBy}
     *                          <br/>
     *                          <a href="#{@link}">{@link io.appium.java_client.pagefactory.iOSXCUITFindBy}
     * @return LocatorMeta object
     */
    private static LocatorMeta getLocatorMeta(Annotation elementAnnotation) {
        for (Method m : prepareAnnotationMethods(elementAnnotation.getClass())) {
            try {
                Object strategyParameter = m.invoke(elementAnnotation);
                if (strategyParameter != null && !String.valueOf(strategyParameter).isEmpty()) {
                    return new LocatorMeta(m.getName(), strategyParameter.toString());
                }
            } catch (IllegalAccessException | IllegalArgumentException
                     | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return new LocatorMeta(null, null);
    }

    /**
     * Returns names of all available object methods as list of strings
     *
     * @param methods Array of methods
     * @return List of all method names (including builtin)
     */
    private static List<String> getMethodNames(Method[] methods) {
        return Stream.of(methods).map(Method::getName).collect(Collectors.toList());
    }

    /**
     * Creates list of declared elementAnnotation methods without:
     * <br/>
     * - builtin methods (toSting, equals, etc);
     * <br/>
     * - unsupported find strategies (priority)
     *
     * @param elementAnnotation Annotation containing information about the element localization strategy.
     *                          <br/>
     *                          Could be any of FindBy annotations or its heirs:
     *                          <br/>
     *                          <a href="#{@link}">{@link io.appium.java_client.pagefactory.AndroidFindBy}
     *                          <br/>
     *                          <a href="#{@link}">{@link io.appium.java_client.pagefactory.iOSXCUITFindBy}
     * @return List of methods representing element localization strategies
     */
    private static Method[] prepareAnnotationMethods(Class<? extends Annotation> elementAnnotation) {
        List<String> targetAnnotationMethodNamesList = getMethodNames(elementAnnotation.getDeclaredMethods());
        targetAnnotationMethodNamesList.removeAll(BUILT_IN_METHOD_TO_BE_EXCLUDED);
        targetAnnotationMethodNamesList.removeAll(FIND_BY_METHODS_TO_BE_EXCLUDED);
        targetAnnotationMethodNamesList.remove("query");
        return targetAnnotationMethodNamesList.stream()
                .map((methodName) -> {
                    try {
                        return elementAnnotation.getMethod(methodName, DEFAULT_ANNOTATION_METHOD_ARGUMENTS);
                    } catch (NoSuchMethodException | SecurityException e) {
                        throw new RuntimeException(e);
                    }
                }).toArray(Method[]::new);
    }

    /**
     * Creates <a href="#{@link}">{@link By} object, used to localize an element.
     * <br/>
     * Note: If <a href="#{@link}">{@link ByStrategies} enum does not contain requested strategy,
     * then IllegalArgumentException exception is thrown
     *
     * @param locatorMeta Locator meta information from annotation.
     *                    <br/>
     *                    Contains localization strategy (method) and search pattern (value).
     * @return Element locator
     */
    private static By getBy(LocatorMeta locatorMeta) {
        return Stream.of(ByStrategies.values())
                .filter((strategy) -> strategy.returnValueName().equals(locatorMeta.getMethod()))
                .findFirst()
                .map((strategy) -> strategy.getBy(locatorMeta))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("There is an unknown strategy: %s with value %s | ByStrategies: %s",
                                locatorMeta.getMethod(), locatorMeta.getValue(), Arrays.toString(ByStrategies.values()))
                ));
    }

    /**
     * Builds <a href="#{@link}">{@link LocatorsToPlatforms} object based on provided annotation.
     *
     * @param annotation FindByPlatform annotation
     * @return LocatorsToPlatforms object
     */
    public static LocatorsToPlatforms buildLocators(FindByPlatform annotation) {

        LocatorMeta androidLocator = getLocatorMeta(annotation.android());
        LocatorMeta iosLocator = getLocatorMeta(annotation.ios());

        return new LocatorsToPlatforms(
                androidLocator.getMethod() != null ? LocatorBuilder.getBy(androidLocator) : null,
                iosLocator.getMethod() != null ? LocatorBuilder.getBy(iosLocator) : null
        );
    }

}
