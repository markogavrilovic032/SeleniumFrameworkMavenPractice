package marko;

import marko.TestComponents.BaseTest;
import marko.pageobjects.CartPage;
import marko.pageobjects.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InterruptedIOException;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() throws IOException, InterruptedIOException {

        ProductCatalogPage productCatalogPage = landingPage.loginApplication("marko.gavrilovic88@yahoo.com", "3423Evepas.12313");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void submitOrder() throws IOException, InterruptedIOException {
        String productName = "ZARA COAT 3";
        ProductCatalogPage productCatalogPage = landingPage.loginApplication("marko.gavrilovic88@yahoo.com", "3423Evepas.");
        productCatalogPage.addProducttoCart(productName);
        CartPage cartPage = productCatalogPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT error");
        Assert.assertFalse(match);
    }
}

