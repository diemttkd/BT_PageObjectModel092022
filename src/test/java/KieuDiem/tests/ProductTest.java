package KieuDiem.tests;

import KieuDiem.common.BaseTest;
import KieuDiem.keyword.WebUI;
import KieuDiem.pages.Product.AddNewProductPage;
import KieuDiem.pages.DashboardPage;
import KieuDiem.pages.LoginPage;
import KieuDiem.pages.Product.ProductPage;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddNewProductPage addNewProductPage;
    ProductPage productPage;

//    constructor() {
//
//    }

    @Test
    public void Test1_AddNewProduct(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        addNewProductPage = dashboardPage.openAddNewProductPage();
        String PRODUCT_NAME = "Selenium Java";
        addNewProductPage.verifyAddNewProductPage();
        addNewProductPage.AddProductSuccess(PRODUCT_NAME);
        productPage = dashboardPage.openProductPage();
        productPage.searchProduct(PRODUCT_NAME);
        productPage.checkProductName(PRODUCT_NAME);
    }
    @Test
    public void Test2_NoEnterDataInProductName(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        addNewProductPage = dashboardPage.openAddNewProductPage();
        addNewProductPage.verifyAddNewProductPage();
        addNewProductPage.AddProductFailWithEmptyProductName();
    }
}
