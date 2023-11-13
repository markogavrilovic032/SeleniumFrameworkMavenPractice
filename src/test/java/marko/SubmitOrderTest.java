package marko;

import marko.TestComponents.BaseTest;
import marko.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    //    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData", groups = {"SubmitOrder"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedIOException {

        String contryName = "Canada";

//        LandingPage landingPage = launchApplication();
        ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalogPage.addProducttoCart(input.get("productName"));
        CartPage cartPage = productCatalogPage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry(contryName);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        driver.quit();
    }

    // to verify ZARA COAT 3 is displaying in orders page
    @Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"}, groups = {"SubmitOrder"})
    public void orderHistoruyTest(HashMap<String, String> input) {
        ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalogPage.goToOrdrsPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\marko\\data\\order.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    // way 1
//    @DataProvider
//    public Object[][] getData()
//    {
//        return new Object[][] {{"email","marko.gavrilovic88@yahoo.com","password", "3423Evepas.","ZARA COAT 3"}, {"email","marko.gavrilovic88@yahoo.com","password", "3423Evepas.","ADIDAS ORIGINAL"} };
//    }
//    way 2
//@DataProvider
//public Object[][] getData() throws IOException {
//        HashMap<String,String> map = new HashMap<String ,String>();
//        map.put("email","marko.gavrilovic88@yahoo.com");
//        map.put("password", "3423Evepas.");
//        map.put("productName","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String ,String>();
//        map1.put("email","marko.gavrilovic88@yahoo.com");
//        map1.put("password", "3423Evepas.");
//        map1.put("productName","ADIDAS ORIGINAL");
//    return new Object[][] {{map}, {map1} };
//}
    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}

