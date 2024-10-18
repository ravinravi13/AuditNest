package TestCase.Scenario_01;

import Base.BaseClass;
import Pages.Scenario_01.SC_AN_001;
import Utilities.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Scenario_01 extends BaseClass {
    SC_AN_001 obj_SC_AN_001 = new SC_AN_001();

    @Test
    @Description("This method reprsents verfiy the login of application verfiy by 'Welcome to AuditNest' text")
    @Step("Atfer Lanched the browser Get text from UI")
    public void AN_TC_001_VerifyLogin() {
        String Actual = "Welcome To Audit Nest";
        Allure.step("Actual text is"+ Actual);
        String expected = obj_SC_AN_001.Verify_Text();
        Allure.step("Expected text is"+ expected);
        Assert.assertEquals(Actual, expected, "text can't be equal");
        Screenshot.captureStepScreenshot(BaseClass.driver);
    }


}
