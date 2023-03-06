package KieuDiem.tests;

import KieuDiem.common.BaseTest;
import KieuDiem.keyword.WebUI;
import KieuDiem.pages.Category.AddNewCategoryPage;
import KieuDiem.pages.Category.CategoryPage;
import KieuDiem.pages.DashboardPage;
import KieuDiem.pages.LoginPage;
import KieuDiem.pages.Product.AddNewProductPage;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddNewCategoryPage addNewCategoryPage;
    CategoryPage categoryPage;
    AddNewProductPage addNewProductPage;

    @Test
    public void Test1_AddCategory(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        categoryPage = dashboardPage.openCategoryPage();
        addNewCategoryPage = categoryPage.openAddNewCategoryPage();
        String CATEGORY_NAME = "Testing Add Category 1";
        addNewCategoryPage.addCategorySuccess(CATEGORY_NAME);
        WebUI.waitforPageLoaded();
        categoryPage.searchCategory(CATEGORY_NAME);
        categoryPage.checkCategoryName(CATEGORY_NAME);
    }
    @Test
    public void Test2_CategoryIsDisplayedInProductPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        categoryPage = dashboardPage.openCategoryPage();
        addNewCategoryPage = categoryPage.openAddNewCategoryPage();
        String CATEGORY_NAME = "Testing Add Category 3";
        addNewCategoryPage.addCategorySuccess(CATEGORY_NAME);
        dashboardPage.openProductPage();
        dashboardPage.openAddNewProductPage();
        categoryPage.checkCategoryNameInProduct(CATEGORY_NAME);
    }
    @Test
    public void Test3_AddCategorywithName(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        categoryPage = dashboardPage.openCategoryPage();
        addNewCategoryPage = categoryPage.openAddNewCategoryPage();
        String CATEGORY_NAME = "Testing Add Category 1";
        addNewCategoryPage.AddCategoryWithName(CATEGORY_NAME);
        WebUI.waitforPageLoaded();
        categoryPage.searchCategory(CATEGORY_NAME);
        categoryPage.checkCategoryName(CATEGORY_NAME);
    }
    @Test
    public void Test4_AddCategoryWithoutName(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        categoryPage = dashboardPage.openCategoryPage();
        addNewCategoryPage = categoryPage.openAddNewCategoryPage();
        String CATEGORY_NAME = "";
        addNewCategoryPage.addCategoryWithoutName(CATEGORY_NAME);
    }
}
