package TestCase.Scenario_01;

import Base.BaseClass;
import Pages.Scenario_01.SC_AN_001;
import Utilities.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Scenario_01 extends BaseClass {
    SC_AN_001 obj_SC_AN_001 = new SC_AN_001();

    @Test(groups ={"Login"},priority = 1)
    @Description("This method represents verify the login of application verify by 'Welcome to AuditNest' text")
    @Step("After Launched the browser Get text from UI")
    public void AN_TC_001_VerifyLogin() {
        String Actual = "Welcome To Audit Nest";
        Allure.step("Actual text is : "+ Actual);
        System.out.println("Actual "+ Actual);
        String expected = obj_SC_AN_001.Verify_Text();
        Allure.step("Expected text is : "+ expected);
        System.out.println("Actual "+ expected);
        Assert.assertEquals(Actual, expected);
        Screenshot.captureStepScreenshot(BaseClass.driver);
        System.out.println();
    }


}
