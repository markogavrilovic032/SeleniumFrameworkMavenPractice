package marko.pageobjects;

import marko.pageobjects.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".action__submit")
    WebElement submit;
    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement selectCounty;
    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        waitForElementToApper(results);
//        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        selectCounty.click();
    }

    public ConfirmationPage submitOrder(){
        //        driver.findElement(By.cssSelector(".action__submit ")).click();
        submit.click();
        return new ConfirmationPage(driver);
    }

}
