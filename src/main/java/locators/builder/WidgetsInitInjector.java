package locators.builder;

import locators.builder.annotations.FindByPlatform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * WidgetsInitInjector initializes all declared fields of the page with locators specified via the <a href="#{@link}">{@link FindByPlatform} annotation.
 * <br/>
 * All page classes should extend this class or call WidgetsInitInjector.InitializeAllWidgets(this) directly.
 */
public class WidgetsInitInjector {

    public static void InitializeAllWidgets(Object object) {
        for (Field filed : object.getClass().getDeclaredFields()) {
            if (filed.isAnnotationPresent(FindByPlatform.class)) {
                FindByPlatform annotation = filed.getAnnotation(FindByPlatform.class);
                filed.setAccessible(true);
                Class<?> cls = filed.getType();
                try {
                    filed.set(object, cls.getDeclaredConstructor(FindByPlatform.class).newInstance(annotation));
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                         InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
