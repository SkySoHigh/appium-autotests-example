package pages;


import locators.builder.WidgetsInitInjector;

public class BasePage {
    public BasePage() {
        WidgetsInitInjector.InitializeAllWidgets(this);
    }
}
