package org.selenium.tests;

import org.selenium.api.actions.SignUpApi;
import org.selenium.base.BaseTest;
import org.selenium.pages.AccountPage;
import org.selenium.pojo.Login;
import org.selenium.util.ConfigLoader;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    @Test (description = "Registered User Order History Validation")
    public void registeredUserOrderHistory() throws InterruptedException {
        Login login = new Login(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.loginApp(login);
        AccountPage accountPage = new AccountPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        accountPage.load();
        accountPage.clickOrdersLink();
        Thread.sleep(5000);
        accountPage.getOrderTableHeading();
        accountPage.clickViewButton();
        Thread.sleep(5000);
        System.out.println(accountPage.getNoticeHeading());
        System.out.println(accountPage.getAddress());

    }
}
