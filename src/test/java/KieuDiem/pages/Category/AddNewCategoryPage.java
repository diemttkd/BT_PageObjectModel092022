package KieuDiem.pages.Category;

import KieuDiem.keyword.WebUI;
import static KieuDiem.keyword.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AddNewCategoryPage extends CategoryPage{

    private String PAGE_TEXT = "Category Information";
    private String PAGE_URL = "https://cms.anhtester.com/admin/categories/create";

    By inputForm = By.xpath("//div[@class='card']");
    By headerPage = By.xpath("//h5");
    By inputCategoryName = By.xpath("//input[@id='name']");
    By dropdownCategoryParent = By.xpath("//label[normalize-space()='Parent Category']//following-sibling::div");
    By searchCategoryParent = By.xpath("//label[normalize-space()='Parent Category']//following-sibling::div//input");
    By inputOrderingNumber = By.xpath("//input[@id='order_level']");
    By dropdownFilteringAttributes = By.xpath("//label[normalize-space()='Filtering Attributes']//following-sibling::div");
    By searchFilteringAttributes = By.xpath("//label[normalize-space()='Filtering Attributes']//following-sibling::div//input");
    By buttonSave = By.xpath("//button[normalize-space()='Save']");

    private WebDriver driver;
    public AddNewCategoryPage(WebDriver _driver)
    {
        super(_driver);
        driver = _driver;
        new WebUI(driver);
    }

    //Xử lý chọn Parent Category field
    public void selectParentCategory(String CATEGORY){
        clickElement(dropdownCategoryParent);
        getElement(searchCategoryParent).sendKeys(CATEGORY, Keys.ENTER);
    }
    //Xử lý chọn Filtering Attributes field
    public void selectFilteringAttributes (String ATTRIBUTE){
        clickElement(dropdownFilteringAttributes);
        getElement(searchFilteringAttributes).sendKeys(ATTRIBUTE, Keys.ENTER);
    }

    //Veriy Add New Category Page
    public void verifyAddNewCategoryPage (){
        Assert.assertEquals(getCurrentURL(), PAGE_URL, "The URL page is invalid");
        Assert.assertEquals(getTextElement(headerPage), PAGE_TEXT, "The title page is invalid");
        Assert.assertTrue(verifyElementVisible(inputForm, 5), "FAILED. The form is not displayed ");
    }
    //Hàm thêm mới Category
    public void addCategorySuccess(String CATEGORY_NAME){
        setText(inputCategoryName, CATEGORY_NAME);
        selectParentCategory("Demo Category 3");
        setText(inputOrderingNumber, "10");
        selectFilteringAttributes("Size");
        clickElement(buttonSave);
    }
    public void AddCategoryWithName(String CATEGORY_NAME){
        setText(inputCategoryName, CATEGORY_NAME);
        clickElement(buttonSave);
    }
    //Hàm thêm mới Category thất bại
    public void addCategoryWithoutName(String CATEGORY_NAME){
        setText(inputCategoryName, CATEGORY_NAME);
        selectParentCategory("Demo Category 3");
        setText(inputOrderingNumber, "10");
        selectFilteringAttributes("Size");
        clickElement(buttonSave);
        System.out.println(getErrorInRequiredField(inputCategoryName));
        Assert.assertEquals(getErrorInRequiredField(inputCategoryName), "Please fill out this field." , "FAILED, The error validation is not displayed");
    }
}
