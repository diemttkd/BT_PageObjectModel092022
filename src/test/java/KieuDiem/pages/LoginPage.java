package KieuDiem.pages;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    private String PAGE_TEXT = "Welcome to Active eCommerce CMS";
    private String PAGE_URL = "https://cms.anhtester.com/login";

    By headerPage = By.xpath("//h1");
    By inputEmail = By.id("email");
    By inputPassword = By.id("password");
    By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    By toastErrorMessage = By.xpath("//span[@data-notify = 'message']");

    private WebDriver driver;
    public LoginPage(WebDriver _driver){
        driver = _driver;
        new WebUI(driver);
    }

    //Verify login page
    public void verifyLoginPage(){
        Assert.assertEquals(getCurrentURL(), PAGE_URL,"Current URL is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "Header page is invalid");
    }
    //Verify error message
    public void verifyErrorMessage(){
        Assert.assertTrue(driver.findElement(toastErrorMessage).isDisplayed(), "The toast error message is not displayed");
        Assert.assertEquals(getTextElement(toastErrorMessage), "Invalid login credentials", "Error message is invalid");
    }
    //Điền email
    public void enterEmail(String email){
        setText(inputEmail, email);
    }
    //Điền password
    public void enterPassword(String password){
        setText(inputPassword, password);
    }
    //Click button
    public void clickonLogin(){
        clickElement(buttonLogin);
    }
    //Xử lý login thành công
    public DashboardPage loginCMSsuccess(String email, String password){
        openURL(PAGE_URL);
        verifyLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickonLogin();

        return new DashboardPage(driver);
    }
    //Xử lý login thất bại
    public void loginCMSfail(String email, String password){
        openURL(PAGE_URL);
        verifyLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickonLogin();
        verifyErrorMessage();
    }
}
