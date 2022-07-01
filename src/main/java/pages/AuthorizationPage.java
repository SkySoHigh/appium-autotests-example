package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import locators.builder.annotations.FindByPlatform;
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