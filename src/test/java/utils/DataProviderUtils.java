package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"user1", "pass1"},
//                {"user2", "pass2"},
//                {"user3", "pass3"}
        };
    }
}