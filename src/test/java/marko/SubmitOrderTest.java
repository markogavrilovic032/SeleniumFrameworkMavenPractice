package marko;

import io.github.bonigarcia.wdm.WebDriverManager;
import marko.TestComponents.BaseTest;
import marko.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.Duration;

public class SubmitOrderTest extends BaseTest {
    @Test
    public void submitOrder() throws IOException, InterruptedIOException {
        String productName = "ZARA COAT 3";
        String contryName = "Canada";

//        LandingPage landingPage = launchApplication();
        ProductCatalogPage productCatalogPage = landingPage.loginApplication("marko.gavrilovic88@yahoo.com", "3423Evepas.");

        productCatalogPage.addProducttoCart(productName);
        CartPage cartPage = productCatalogPage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry(contryName);
        ConfirmationPage confirmationPage =  checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//        driver.quit();
    }
}

