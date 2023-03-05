package KieuDiem.pages;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;

import KieuDiem.pages.Product.AddNewProductPage;
import KieuDiem.pages.Product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage {

    private String PAGE_URL = "https://cms.anhtester.com/admin";

    By inputSearchMenu = By.id("menu-search");
    By menuOption = By.xpath("//div[@class = 'aiz-side-nav-wrap']");
    By optionProductMenu = By.xpath("//span[normalize-space()='Products']");
    By optionAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");
    By optionCategory = By.xpath("//span[normalize-space()='Category']");
    By resultSearchMenu = By.xpath("//ul[@id = 'search-menu']//li[1]");
    By optionAllProduct = By.xpath("//span[normalize-space()='All products']");

    private WebDriver driver;
    public DashboardPage(WebDriver _driver) {
        driver = _driver;
        new WebUI(driver);
    }

    public void verifyDashboardPage(){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "URL is invalid");
        Assert.assertTrue(checkElementExist(menuOption), "Menu is not exist");
    }
    public ProductPage openProductPage(){
        waitforPageLoaded();
        clickElement(optionAllProduct);

        return  new ProductPage(driver);
    }
    public AddNewProductPage openAddNewProductPage(){
        waitforPageLoaded();
        clickElement(optionProductMenu);
        clickElement(optionAddNewProduct);

        return new AddNewProductPage(driver);
    }
    public CategoryPage openCategoryPage(){
        waitforPageLoaded();
        clickElement(optionProductMenu);
        waitforElementVisible(optionCategory);
        clickElement(optionCategory);

        return new CategoryPage(driver);
    }

}
