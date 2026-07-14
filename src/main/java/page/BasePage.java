package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// ========= BasePage - общие методы  ==========
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    protected void clickElement(WebElement el) {
        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
    }

    protected void type(WebElement el, String text) {
        wait.until(ExpectedConditions.visibilityOf(el));
        el.clear();
        el.sendKeys(text);
    }

    protected boolean isElementDisplayed(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
