package marko;

import marko.TestComponents.BaseTest;
import marko.pageobjects.CartPage;
import marko.pageobjects.CheckoutPage;
import marko.pageobjects.ConfirmationPage;
import marko.pageobjects.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InterruptedIOException;

public class ErrorValidations extends BaseTest {
    @Test
    public void submitOrder() throws IOException, InterruptedIOException {

        ProductCatalogPage productCatalogPage = landingPage.loginApplication("marko.gavrilovic88@yahoo.com", "3423Evepas.12313");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }
}

