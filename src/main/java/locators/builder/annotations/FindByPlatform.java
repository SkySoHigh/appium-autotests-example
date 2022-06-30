package locators.builder.annotations;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.junit.Test;

import javax.annotation.Nullable;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FindByPlatform {
    iOSXCUITFindBy ios() default @iOSXCUITFindBy();

    AndroidFindBy android() default @AndroidFindBy();
}
