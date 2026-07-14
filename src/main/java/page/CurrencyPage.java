package page;

import data.Currency;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyPage extends BasePage {

    @FindBy(xpath = "//table[@class = 'data']")
    private WebElement tableCurrency;

    @FindBy(xpath = "//table[@class = 'data']//th")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//table[@class = 'data']//tbody/tr/td/..")
    private List<WebElement> tableRows;

    public CurrencyPage(WebDriver driver) {
        super(driver);
    }

    private List<Map<String, String>> collectCurrency = new ArrayList<>();

    public List<Map<String, String>> getMapCurrency() {
        collectCurrency.clear();

        // Ждём именно появления строк таблицы (наличие, а не "видимость")
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//table[@class='data']//tbody/tr")
        ));

        wait.until(d -> d.findElements(By.xpath("//table[@class='data']//tbody/tr")).size() > 0);;

        for (int i = 0; i < tableRows.size(); i++) {
            Map<String, String> oneRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); j++) {
                String key = tableHeaders.get(j).getText();
                String value = tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText();
                oneRow.put(key, value);
            }
            collectCurrency.add(oneRow);
        }
        System.out.println("В справочнике - " + collectCurrency.size() + " валют");
        return collectCurrency;
    }

    public List<Currency> getListCurrency() {
        List<Currency> result = new ArrayList<>();
        collectCurrency = getMapCurrency();
        for (Map<String, String> oneRate: collectCurrency) {
            String nCode = oneRate.getOrDefault("Цифр. код", "");
            String strCode = oneRate.getOrDefault("Букв. код", "");
            String count = oneRate.getOrDefault("Единиц", "1");
            String nameCurrency = oneRate.getOrDefault("Валюта", "");
            String valueRate = oneRate.getOrDefault("Курс", "0.00");
            Currency current = Currency.builder()
                    .numberCode(Integer.parseInt(nCode))
                    .stringCode(strCode)
                    .count(Integer.parseInt(count))
                    .currency(nameCurrency)
                    .rate(Double.parseDouble(valueRate))
                    .build();
            result.add(current);
        }
        return result;
    }

    public String getRowByNumberCode(int nCode) {
        collectCurrency = getMapCurrency();
        for (Map<String, String> oneRow: collectCurrency) {
            String numberCode = oneRow.getOrDefault("Цифр. код", "0");
            int valueCode = Integer.parseInt(numberCode);
            if (valueCode == nCode) {
                String strCode = oneRow.getOrDefault("Букв. код", "");
                String count = oneRow.getOrDefault("Единиц", "1");
                String nameCurrency = oneRow.getOrDefault("Валюта", "");
                String valueRate = oneRow.getOrDefault("Курс", "0.00");
                String result = "Цифр. код " + valueCode + ", Букв. код " + strCode + ", Единиц " + count
                        + ", Валюта " + nameCurrency + ", Курс " + valueRate;
                return result;
            }
        }
        System.out.println("Нет валюты с кодом " + nCode);
        return null;
    }

    public Double getRateByNumberCode(int nCode) {
        collectCurrency = getMapCurrency();
        for (Map<String, String> oneRow: collectCurrency) {
            String numberCode = oneRow.getOrDefault("Цифр. код", "0");
            int valueCode = Integer.parseInt(numberCode);
            if (valueCode == nCode) {
                String valueRate = oneRow.getOrDefault("Курс", "0.00");
                return Double.parseDouble(valueRate.replace(",", "."));
            }
        }
        System.out.println("Нет валюты с кодом " + nCode);
        return 0.00;
    }

    public Double getRateByStrCode(String strCode) {
        collectCurrency = getMapCurrency();
        for (Map<String, String> oneRow: collectCurrency) {
            String stringCode = oneRow.getOrDefault("Букв. код", "");
            if (strCode.equals(stringCode)) {
                String valueRate = oneRow.getOrDefault("Курс", "0.00");
                return Double.parseDouble(valueRate.replace(",", "."));
            }
        }
        System.out.println("Нет валюты с кодом " + strCode);
        return 0.00;
    }

    public Double getRateByName(String nameCurrency) {
        if (collectCurrency.isEmpty()) {
            collectCurrency = getMapCurrency();
        }
        for (Map<String, String> oneRow: collectCurrency) {
            String name = oneRow.getOrDefault("Валюта", "");
            if (nameCurrency.equals(name)) {
                String valueRate = oneRow.getOrDefault("Курс", "0.00");
                return Double.parseDouble(valueRate.replace(",", "."));
            }
        }
        System.out.println("Нет валюты " + nameCurrency);
        return 0.00;
    }
}
