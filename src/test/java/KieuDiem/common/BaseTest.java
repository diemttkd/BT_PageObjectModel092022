package KieuDiem.common;

import KieuDiem.keyword.WebUI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Start Chrome browser from BaseTest...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @AfterMethod
    public void closeBrowser(){
        WebUI.sleep(2);
        System.out.println("Close Browser from BaseTest...");
        driver.quit();
    }
}
