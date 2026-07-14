package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Компонент страницы (баннер есть на обеих страницах, можно положить в BasePage)
    public NotificationBanner banner;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.banner = new NotificationBanner(driver);
    }

    public DashboardPage loginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        clickElement(loginButton);
        return new DashboardPage(driver);
    }

    public LoginPage loginExpectingError(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        clickElement(loginButton);
        return this;   // остаемся на странице
    }

    public String getErrorMessage() {
        return banner.getNotificationMessage();
    }

    public boolean isButtonLoginDisplayed() {
        return super.isElementDisplayed(loginButton);
    }
}
