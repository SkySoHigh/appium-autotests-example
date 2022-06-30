package core;

import pages.AuthorizationPage;

import static com.codeborne.selenide.appium.ScreenObject.screen;

public class BaseRouter {

    public AuthorizationPage authorizationPage() {
        return screen(AuthorizationPage.class);
    }


}
