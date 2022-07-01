package locators.builder.annotations;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FindByPlatform {
    iOSXCUITFindBy ios() default @iOSXCUITFindBy();

    AndroidFindBy android() default @AndroidFindBy();
}
