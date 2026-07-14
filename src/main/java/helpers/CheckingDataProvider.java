package helpers;

import org.testng.annotations.DataProvider;

public class CheckingDataProvider {

    @DataProvider(name = "CheckingMoney")
    public static Object[][] providerCheckingMoney() {
        return new Object[][] {{"Лари", "Азербайджанский манат"}};
    }

}
