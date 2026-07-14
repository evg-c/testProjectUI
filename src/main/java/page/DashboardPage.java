package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    public NotificationBanner banner;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.banner = new NotificationBanner(driver);
    }

    public void logout() {
        clickElement(logoutButton);
    }

    public boolean isButtonLogoutDisplayed() {
        return super.isElementDisplayed(logoutButton);
    }

}
