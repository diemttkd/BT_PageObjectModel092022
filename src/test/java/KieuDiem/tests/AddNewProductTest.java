package KieuDiem.tests;

import KieuDiem.common.BaseTest;
import KieuDiem.keyword.WebUI;
import KieuDiem.pages.Product.AddNewProductPage;
import KieuDiem.pages.DashboardPage;
import KieuDiem.pages.LoginPage;
import KieuDiem.pages.Product.ProductPage;
import org.testng.annotations.Test;

public class AddNewProductTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddNewProductPage addNewProductPage;
    ProductPage productPage;

    @Test
    public void Test1_AddNewProduct(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMSsuccess("admin@example.com", "123456");
        WebUI.waitforPageLoaded();
        addNewProductPage = dashboardPage.openAddNewProductPage();
        String PRODUCT_NAME = "Selenium Java";
        addNewProductPage.verifyAddNewProductPage();
        addNewProductPage.AddProduct(PRODUCT_NAME);
        productPage.searchProduct(PRODUCT_NAME);
        productPage.checkProductName(PRODUCT_NAME);
    }

}
