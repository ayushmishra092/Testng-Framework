package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtilsExcel {

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] getLoginData() {
        String filePath = "testdata/LoginData.xlsx"; // ✅ relative path
        String sheetName = "Sheet1";
        return ExcelUtils.getExcelData(filePath, sheetName);
    }
}