package KieuDiem.pages;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CategoryPage {

    private String PAGE_URL = "https://cms.anhtester.com/admin/categories";
    private String PAGE_TEXT = "All categories";

    By tableCategory = By.xpath("//div[@class='card']");
    By headerPage = By.xpath("//h1");
    By AddNewButton = By.xpath("//div[@class = 'col-md-6 text-md-right']//a");

    private WebDriver driver;
    public CategoryPage(WebDriver _driver) {
        driver = _driver;
        new WebUI (driver);
    }

    public void verifyCategoryPage (){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "URL is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "Header page is invalid");
        Assert.assertTrue(checkElementExist(tableCategory), "Category table is not exist");
        Assert.assertTrue(checkElementExist(AddNewButton), "Add New Category button is not exist");
    }
    public AddNewCategoryPage openAddCategoryPage (){
        clickElement(AddNewButton);

        return new AddNewCategoryPage(driver);
    }

    public static class AddNewCategoryPage {
        CategoryPage categoryPage;

        private WebDriver driver;
        public AddNewCategoryPage (WebDriver _driver){
            driver = _driver;
        }

        public void addNewCategorySuccess(){
            categoryPage.openAddCategoryPage();
        }
    }
}
