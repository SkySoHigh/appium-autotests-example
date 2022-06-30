package pages;

import locators.AndroidLocators;
import locators.builder.annotations.FindByPlatform;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import widgets.InputText;


public class AuthorizationPage extends BasePage {
    @FindByPlatform(android = @AndroidFindBy(id = "example"))
    @Getter
    public InputText inputLogin;

    @FindByPlatform(ios = @iOSXCUITFindBy(id = "example"), android = @AndroidFindBy(xpath = "//([@text='example']"))
    @Getter
    public InputText inputPassword;

}


