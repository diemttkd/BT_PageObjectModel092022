package KieuDiem.tests;

import KieuDiem.common.BaseTest;
import KieuDiem.pages.Product.AddNewProductPage;
import KieuDiem.pages.CategoryPage;
import KieuDiem.pages.DashboardPage;
import KieuDiem.pages.LoginPage;
import KieuDiem.pages.Product.ProductPage;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    AddNewProductPage addNewProductPage;
    CategoryPage categoryPage;
    ProductPage productPage;

    @Test
    public void Test1_openAddNewProductPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        dashboardPage.verifyDashboardPage();
        addNewProductPage = dashboardPage.openAddNewProductPage();
        addNewProductPage.verifyAddNewProductPage();
    }
    @Test
    public void Test2_openCategoryPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        dashboardPage.verifyDashboardPage();
        categoryPage = dashboardPage.openCategoryPage();
        categoryPage.verifyCategoryPage();
    }
    @Test
    public void Test3_openProductPage(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        dashboardPage.verifyDashboardPage();
        productPage = dashboardPage.openProductPage();
        productPage.verifyProductPage();
    }
}
