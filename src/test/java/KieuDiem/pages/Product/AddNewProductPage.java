package KieuDiem.pages.Product;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddNewProductPage extends ProductPage {


    private String PAGE_URL = "https://cms.anhtester.com/admin/products/create";
    private String PAGE_TEXT = "Add New Product";

    By headerPage = By.xpath("//div[@class = 'aiz-titlebar text-left mt-2 mb-3']");
    public By inputProductName = By.xpath("//div[@class = 'col-md-8']//input[@placeholder = 'Product Name']");
    public By dropdownCategory = By.xpath("//div[@id = 'category']//button");
    public By searchCategory = By.xpath("//div[@id = 'category']//input");
    public By dropdownBrand = By.xpath("//div[@id = 'brand']//button");
    public By searchBrand = By.xpath("//div[@id = 'brand']//input");
    public By inputUnit = By.xpath("//input[@name='unit']");
    public By inputWeight = By.xpath("//input[@name = 'weight']");
    public By inputMiniumPurchase = By.xpath("//input[@name = 'min_qty']");
    public By inputTags = By.xpath("//label[contains(.,'Tags *')]//following-sibling::div//tags");
//    public By chooseGelleryImage = By.xpath("//label[text()='Gallery Images ']//following-sibling::div//div[normalize-space()='Choose File']");
//    public By GelleryImage = By.xpath("(//img[@class='img-fit'])[1]");
//    public By chooseThumbnailImage = By.xpath("//label[text()='Thumbnail Image ']//following-sibling::div//div[normalize-space()='Choose File']");
//    public By buttonAddImage = By.xpath("//button[normalize-space()='Add Files']");
//    public By ThumbnailImage = By.xpath("(//img[@class='img-fit'])[1]");
    public By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    public By inputDiscount = By.xpath("//input[@name='discount']");
    public By inputQuantity = By.xpath("(//input[@placeholder='Quantity'])");
//    public By buttonSaveandUnPublish = By.xpath("//button[value='publish']");
    public By buttonSaveandPublish = By.xpath("//button[normalize-space()='Save & Publish']");
    public By errorDiscount = By.xpath("//div[@class='alert alert-danger']//ul//li");

    private WebDriver driver;
    public AddNewProductPage(WebDriver _driver)
    {
        super(_driver);
        driver = _driver;
        new WebUI(driver);
    }


    public void verifyAddNewProductPage(){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "URL is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "URL is invalid");
    }
    public void selectCategoryField(String CATEGORY_KEYWORD){
        clickElement(dropdownCategory);
        driver.findElement(searchCategory).sendKeys(CATEGORY_KEYWORD, Keys.ENTER);
    }
    public void selectBrandFiel(String BAND_KEYWORD){
        clickElement(dropdownBrand);
        driver.findElement(searchBrand).sendKeys(BAND_KEYWORD, Keys.ENTER);
    }
    public void inputWeightField(int WEIGHT){
        driver.findElement(inputWeight).clear();
        driver.findElement(inputWeight).sendKeys(Integer.toString(WEIGHT));
    }
    public void inputMinumumQTYField(int QTY){
        driver.findElement(inputMiniumPurchase).clear();
        driver.findElement(inputMiniumPurchase).sendKeys(Integer.toString(QTY));
    }
    public void inputUnitPrice(int UNIT_PRICE){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(UNIT_PRICE));
    }
    public void inputQuantity(int QTT){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(QTT));
    }
    public void inputDiscount(int DISCOUNT){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(DISCOUNT));
    }
    public void verifyErrorDiscount(){
        scrollToElementwithJS(errorDiscount);
        Assert.assertTrue(driver.findElement(errorDiscount).isDisplayed(), "FAILED. The error message is not displayed");
        Assert.assertEquals(driver.findElement(errorDiscount).getText(), "The discount must be less than 1.", "FAILED. The error message is invalid");
    }

    public ProductPage AddProduct (String PRODUCT_NAME){
        driver.findElement(inputProductName).sendKeys(PRODUCT_NAME);
        selectCategoryField("Demo category 1");
        selectBrandFiel("Demo Brand");
        driver.findElement(inputUnit).sendKeys("Hộp");
        inputWeightField(50);
        inputMinumumQTYField(20);
        driver.findElement(inputTags).sendKeys("Chocopie");
        inputUnitPrice(12000);
        inputDiscount(2);
        inputQuantity(200);
        driver.findElement(buttonSaveandPublish).click();

        return new ProductPage(driver);
    }
}
