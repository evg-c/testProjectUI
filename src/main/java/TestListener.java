import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ThreadLocalDriverFactory.getDriver();
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(
                    "Screenshot on failure", "image/png", "png",
                    new ByteArrayInputStream(screenshot)
            );
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("Тест " + result.getMethod().getMethodName() + " стартовал");
    }
}
