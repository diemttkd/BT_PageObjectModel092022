package KieuDiem.keyword;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.List;

import static org.testng.Assert.fail;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LOADED_TIMEOUT = 30;

    private static WebDriver driver;
    public WebUI(WebDriver _driver){
        driver = _driver;
    }

    public static void sleep(double second){
        try {
            Thread.sleep((long) (1000 * second));
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    public static WebElement getElement(By by){
        return driver.findElement(by);
    }
    public static void openURL(String URL){
        driver.get(URL);
        waitforPageLoaded();
    }
    public static String getCurrentURL(){
        waitforPageLoaded();
        return driver.getCurrentUrl();
    }
    public static void clickElement(By by){
        waitforElementVisible(by);
        getElement(by).click();
    }
    public static void clearTextElement(By by){
        waitforElementVisible(by);
        getElement(by).clear();
    }
    public static void setText(By by, String value){
        waitforElementVisible(by);
        getElement(by).sendKeys(value);
    }
    public static String getTextElement(By by){
        waitforElementVisible(by);
        return getElement(by).getText();
    }
    public static String getAttributeElement(By by, String attributeName){
        waitforElementVisible(by);
        return getElement(by).getAttribute(attributeName);
    }
    public static void scrollToElementwithJS(By by){
        waitforElementPresent(by);
        //Dùng actions class

        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(by));
    }
    public static String getHTML5ValidationMessage(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String)js.executeScript("return arguments[0].validationMessage;", element);
    }
    public static String getErrorInRequiredField(By by){
        return getHTML5ValidationMessage(getElement(by));
    }
    public static void waitforElementPresent(By by, int second){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void waitforElementPresent(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void waitforElementVisible(By by, int second){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis(500));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void waitforElementVisible(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void waitforElementClickable(By by, int second){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis(500));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public static boolean verifyElementVisible(By by, int second){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis((500)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean verifyElementNOTVisible(By by, int second){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second), Duration.ofMillis((500)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean checkElementExist(By by){
        List<WebElement> listElement = driver.findElements(by);

        if (listElement.size() > 0){
            System.out.println("Element"  + by + " existing.");
            return true;
        }else
        {
            System.out.println("Element" + by + " NOT Exist.");
            return false;
        }

    }
    public static boolean checkElementExist(String xpath){
        List<WebElement> listElement = driver.findElements(By.xpath(xpath));

        if (listElement.size() > 0){
            System.out.println("Element" + xpath + " existing");
            return true;
        }else
        {
            System.out.println("Element" + xpath + " not existing");
            return false;
        }
    }

    public static void waitforPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_PAGE_LOADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                fail("FAILED. Timeout waiting for page load.");
            }
        }
    }
}
