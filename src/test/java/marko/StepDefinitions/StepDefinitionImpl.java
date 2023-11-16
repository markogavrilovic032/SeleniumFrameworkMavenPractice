package marko.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import marko.TestComponents.BaseTest;
import marko.pageobjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogPage productCatalogPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on ecommerce page")
    public void I_landed_on_ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        productCatalogPage = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) throws InterruptedIOException {
        productCatalogPage.addProducttoCart(productName);
    }

    @When("^Checkout (.+) and submit the order and (.+)$")
    public void Checkout_productName_and_submit_the_order(String productName, String contryName){
        CartPage cartPage = productCatalogPage.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry(contryName);
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on confimation page")
    public void message_is_displayed_on_confimation_page(String string){
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
        driver.quit();
    }

    @Then("{string} message is displayed")
    public void error_message_is_displayed(String string){
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.quit();
    }
}
