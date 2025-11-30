package tests;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import pages.LoginPage;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {

//    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
  //  @Test(dataProvider = "loginData", dataProviderClass = utils.DataProviderUtils.class)
    @Test(dataProvider = "loginData", dataProviderClass = utils.DataProviderUtilsExcel.class)
    public void testValidLogin(String username, String password) {
     //   test = extent.createTest("Valid Login Test");
         test = BaseTest.getTest();

        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLogin();

            // Example assertion
            String expectedTitle = "Dashboard";
            if (driver.getTitle().contains(expectedTitle)) {
               // test.log(Status.PASS, "Login successful");
                test.pass("Login successful", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("testValidLogin")).build());      } else {
               // test.log(Status.FAIL, "Login failed");
                test.fail("Login failed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot("testValidLogin")).build());
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "Exception occurred: " + e.getMessage());
        }
    }
}