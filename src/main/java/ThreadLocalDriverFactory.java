import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalDriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (tlDriver.get() == null) {
            tlDriver.set(createDriver());
        }
        return tlDriver.get();
    }

    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome");
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--headless", "--disable-notifications");
                chromeOpts.addArguments("--disable-features=PasswordLeakDetection");
                chromeOpts.setBinary("/usr/bin/chromium");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                chromeOpts.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(chromeOpts);
                break;
            case "firefox":
                FirefoxOptions ffOpts = new FirefoxOptions();
                ffOpts.addArguments("--headless");
                driver = new FirefoxDriver(ffOpts);
                break;
            default: throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
