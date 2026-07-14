package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NotificationBanner {

    @FindBy(id = "flash")
    private WebElement flashMessage;

    public NotificationBanner(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public String getNotificationMessage() {
        return flashMessage.getText();
    }
}
