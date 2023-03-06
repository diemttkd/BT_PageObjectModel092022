package KieuDiem.pages.Category;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;

import KieuDiem.pages.DashboardPage;
import KieuDiem.pages.Product.AddNewProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CategoryPage {

    DashboardPage dashboardPage;
    AddNewProductPage addNewProductPage;

    private String PAGE_URL = "https://cms.anhtester.com/admin/categories";
    private String PAGE_TEXT = "All categories";

    By tableCategory = By.xpath("//div[@class='card']");
    By headerPage = By.xpath("//h1");
    By AddNewButton = By.xpath("//div[@class = 'col-md-6 text-md-right']//a");
    By inputSearchCategory = By.xpath("//input[@id='search']");
    By tdRecord = By.xpath("//div[@class = 'card-body']//tbody//tr[1]//td[2]");

    private WebDriver driver;
    public CategoryPage(WebDriver _driver) {
        driver = _driver;
        dashboardPage= new DashboardPage(driver);
        new WebUI (driver);
    }

    //Veriy Category Page
    public void verifyCategoryPage (){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "URL is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "Header page is invalid");
        Assert.assertTrue(checkElementExist(tableCategory), "Category table is not exist");
        Assert.assertTrue(checkElementExist(AddNewButton), "Add New Category button is not exist");
    }

    //Xử lý tìm kiếm Category
    public void searchCategory(String CATEGORY_NAME){
        getElement(inputSearchCategory).sendKeys(CATEGORY_NAME, Keys.ENTER);
    }
    //Verify kết quả tìm kiếm Category
    public void checkCategoryName(String CATEGORY_NAME){
        Assert.assertEquals(getAttributeElement(tdRecord, "innerHTML"), CATEGORY_NAME, "FAILED. The category name is invalid");
    }
    //Verify Category đã thêm hiển thị ở dropdown Category trong Add New Product Page
    public void checkCategoryNameInProduct(String CATEGORY_NAME){
        addNewProductPage = dashboardPage.openAddNewProductPage();
        waitforPageLoaded();
        clickElement(addNewProductPage.dropdownCategory);
        getElement(addNewProductPage.searchCategory).sendKeys(CATEGORY_NAME, Keys.ENTER);
        By searchCategoryResult = By.xpath("(//div[@id = 'category']//button//following-sibling::div//ul//li//span)[1]");
        Assert.assertEquals(getAttributeElement(searchCategoryResult, "innerHTML"), "-- " + CATEGORY_NAME, "FAILED. The category name is invalid");
    }
    //Mở trang Add New Category
    public AddNewCategoryPage openAddNewCategoryPage (){
        clickElement(AddNewButton);
        waitforPageLoaded();

        return new AddNewCategoryPage(driver);
    }
}
