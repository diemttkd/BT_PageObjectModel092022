package KieuDiem.pages.Product;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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
    public By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    public By inputDiscount = By.xpath("//input[@name='discount']");
    public By inputQuantity = By.xpath("(//input[@placeholder='Quantity'])");
    public By buttonSaveandPublish = By.xpath("//button[normalize-space()='Save & Publish']");

    private WebDriver driver;
    public AddNewProductPage(WebDriver _driver)
    {
        super(_driver);
        driver = _driver;
        new WebUI(driver);
    }

    //Verify Add New Product Page
    public void verifyAddNewProductPage(){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "URL is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "URL is invalid");
    }
    //Xử lý chọn Category field
    public void selectCategoryField(String CATEGORY_NAME){
        clickElement(dropdownCategory);
        driver.findElement(searchCategory).sendKeys(CATEGORY_NAME, Keys.ENTER);
    }
    //Xử lý chọn Brand field
    public void selectBrandFiel(String BAND_KEYWORD){
        clickElement(dropdownBrand);
        driver.findElement(searchBrand).sendKeys(BAND_KEYWORD, Keys.ENTER);
    }
    //Input WEIGHT
    public void inputWeightField(int WEIGHT){
        driver.findElement(inputWeight).clear();
        driver.findElement(inputWeight).sendKeys(Integer.toString(WEIGHT));
    }
    //Input MiniumQTY
    public void inputMinumumQTYField(int QTY){
        driver.findElement(inputMiniumPurchase).clear();
        driver.findElement(inputMiniumPurchase).sendKeys(Integer.toString(QTY));
    }
    //Input UNIT_PRICE
    public void inputUnitPrice(int UNIT_PRICE){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(UNIT_PRICE));
    }
    //Input Quantity
    public void inputQuantity(int QTT){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(QTT));
    }
    //Input Discount
    public void inputDiscount(int DISCOUNT){
        driver.findElement(inputUnitPrice).clear();
        driver.findElement(inputUnitPrice).sendKeys(Integer.toString(DISCOUNT));
    }
    //Thêm mới Product
    public ProductPage AddProductSuccess (String PRODUCT_NAME){
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
    public void AddProductFailWithEmptyProductName (){
        driver.findElement(inputProductName).sendKeys("");
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
        System.out.println(getErrorInRequiredField(inputProductName));
        Assert.assertEquals(getErrorInRequiredField(inputProductName), "Please fill out this field." , "FAILED, The error validation is not displayed");
    }
}
