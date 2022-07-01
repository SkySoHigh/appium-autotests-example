package core;

import pages.AuthorizationPage;

public class BaseRouter {
    public AuthorizationPage authorizationPage() {
        return new AuthorizationPage();
    }
}
