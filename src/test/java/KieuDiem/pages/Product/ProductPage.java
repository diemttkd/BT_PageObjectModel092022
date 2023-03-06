package KieuDiem.pages.Product;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ProductPage {

    private String PAGE_URL = "https://cms.anhtester.com/admin/products/all";
    private String PAGE_TEXT = "All Product";

    public By headerPage = By.xpath("//h5");
    public By tableData = By.xpath("//div[@class='card-body']");
    public By inputSearchProduct = By.xpath("//input[@id='search']");
    public By tdRecord = By.xpath("//tbody//tr[1]//td[2]//span");

    private WebDriver driver;
    public ProductPage(WebDriver _driver){
        driver = _driver;
        new WebUI(driver);
    }

    //Verify Product Page
    public void verifyProductPage(){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "FAILED. The URL page is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "FAILED. The page text is invalid");
    }
    //Search product vừa thêm mới
    public void searchProduct(String productName){
        waitforPageLoaded();
        getElement(inputSearchProduct).sendKeys(productName, Keys.ENTER);
        sleep(5);

    }
    //Kiểm tra kết quả tìm kiếm product
    public void checkProductName(String productName){
        Assert.assertEquals(getTextElement(tdRecord), productName, "Product name does not match");
    }
}
