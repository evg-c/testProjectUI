import helpers.CheckingDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.CurrencyPage;

import java.util.List;
import java.util.Map;

import static helpers.Properties.testsProperties;

@Listeners(TestListener.class)
public class CurrencyTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = ThreadLocalDriverFactory.getDriver();
        driver.get(testsProperties.defaultUrl());
    }

    @Test(dataProvider = "CheckingMoney", dataProviderClass = CheckingDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void testCurrencyByNameCurrency(String money1, String money2) {
        CurrencyPage currencyPage = new CurrencyPage(driver);
        List<Map<String, String>> rates = currencyPage.getMapCurrency();
        Double rate1 = currencyPage.getRateByName(money1);
        Double rate2 = currencyPage.getRateByName(money2);
        Assert.assertTrue(rate1 <  rate2,
                "Курс " + money1 + " ожидался меньше курса " + money2 + ", но оказалось не так");
    }

    @AfterTest
    public void tearDown2() {
        ThreadLocalDriverFactory.quitDriver();
    }
}
