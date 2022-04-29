package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import locators.Locators;
import widgets.Button;


public class PermissionsPage {

    @AndroidFindBy(id = Locators.PermissionsLocators.ALLOW_WHILE_USING_APP_BTN)
    public Button allow = new Button();

    @AndroidFindBy(id = Locators.PermissionsLocators.ALLOW_ONE_TIME_BTN)
    public Button allowOnce = new Button();

    @AndroidFindBy(id = Locators.PermissionsLocators.REJECT_BTN)
    public Button reject = new Button();

}
