package marko.pageobjects.AbstractComponents;

import marko.pageobjects.CartPage;
import marko.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent{

    WebDriver driver;
    private marko.pageobjects.OrderPage OrderPage;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css= "[routerlink*= 'myorders']")
    WebElement orderHeader;

    public void waitForElementToApper(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToApper(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public CartPage goToCartPage(){
        cartHeader.click();;
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrdrsPage(){
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    public void  waitForElementDisappear(WebElement ele){
        // 4 secounds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

}
