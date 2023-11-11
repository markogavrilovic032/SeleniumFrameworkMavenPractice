package marko.pageobjects;

import marko.pageobjects.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement password;
    @FindBy(id="login")
    WebElement submit;

    public ProductCatalogPage loginApplication(String email, String pass){
        userEmail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        return productCatalogPage;
    }

    public void  goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
}
