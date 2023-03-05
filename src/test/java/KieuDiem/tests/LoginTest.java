package KieuDiem.tests;

import KieuDiem.common.BaseTest;
import KieuDiem.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    @Test
    public void Test1_LoginSuccess(){
        loginPage = new LoginPage(driver);

        loginPage.loginCMSsuccess("admin@example.com", "123456");
    }
    @Test
    public void Test2_LoginFailwithinvalidEmail(){
        loginPage = new LoginPage(driver);

        loginPage.loginCMSsuccess("admin@exam", "123456");
    }
    @Test
    public void Test3_LoginFailwithinvalidPassword(){
        loginPage = new LoginPage(driver);

        loginPage.loginCMSsuccess("admin@exam", "1256");
    }
}
