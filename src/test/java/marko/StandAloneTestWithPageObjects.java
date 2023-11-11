package marko;

import io.github.bonigarcia.wdm.WebDriverManager;
import marko.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class StandAloneTestWithPageObjects {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        String contryName = "India";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
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
        driver.quit();
    }
}

