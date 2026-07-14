import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.DashboardPage;
import page.LoginPage;

@Listeners(TestListener.class)
public class LoginTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = ThreadLocalDriverFactory.getDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }


    @Test
    public void testSuccessfulLogin() {
        DashboardPage dashboard = new LoginPage(driver)
                .loginAs("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(dashboard.isButtonLogoutDisplayed());
        Assert.assertTrue(dashboard.banner.getNotificationMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testLoginWithInvalidData() {
        LoginPage page = new LoginPage(driver)
                .loginExpectingError("bad", "creds");
        Assert.assertTrue(page.isButtonLoginDisplayed());
        Assert.assertTrue(page.getErrorMessage().contains("Your username is invalid!"));
    }

    @AfterTest
    public void tearDown() {
        System.out.println("QUIT driver for LoginTest");
        ThreadLocalDriverFactory.quitDriver();
    }
}
