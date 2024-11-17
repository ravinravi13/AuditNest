package TestCase.Scenario_05;

import Base.BaseClass;
import Pages.Scenario_04.SC_AN_004;
import Pages.Scenarrio_05.SC_AN_005;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Scenario_05 extends BaseClass {

    SC_AN_004 obj_SC_AN_004 = new SC_AN_004();
    SC_AN_005 obj_SC_AN_005 = new SC_AN_005();

    @Test(priority = 1)
    @Description("Verify the Compliance value from UI and Database using formula as Compliance = WeightRating / Sum of Weightage")
    public void TC_AN_001_VerifyComplianceValue() throws SQLException {

        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_005.Click_ProjectName();
        String actual = obj_SC_AN_005.Click_getPercentage();
        Allure.step("Actual value From UI: " + actual);
        String expected = obj_SC_AN_005.find_Compliance();
        Allure.step("Expected value From Database using formula : " + expected);
        Assert.assertEquals(actual,expected,"Value are not matched");
    }

    @Test(priority = 2)
    @Description("Verify the pagination count UI and Database compare the values")
    public void TC_AN_002_VerifyNo_Checklist() throws InterruptedException, SQLException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_005.Click_ProjectName();
        Thread.sleep(3000);
        String Actual =  obj_SC_AN_005.GetCountChecklist();
        Allure.step("Actual Value from UI : " + Actual);
        String Expected = obj_SC_AN_005.GetCheckListCount_DB();
        Allure.step("Expected value from Database : " + Expected);
       Assert.assertEquals(Actual,Expected,"Value not matched");

    }

//    @Test
//    @Description("Verify the test of Invalid to enter the PCI Compliance and click save without select and enter values from UI")
//   public void TC_AN_003_VerifyPCI_Edit() throws InterruptedException {
//       obj_SC_AN_004.ClickAuditReport();
//       obj_SC_AN_004.ClickAuditReport1();
//       obj_SC_AN_005.Click_ProjectName();
//       obj_SC_AN_005.Click_edit_PCI();
//        Thread.sleep(3000);
//       obj_SC_AN_004.Click_Save();
//       Thread.sleep(3000);
//        Alert alert = BaseClass.driver.switchTo().alert();
//        alert.accept();
//        String alerttxt = alert.getText();
//        Allure.step("Alert message from alertbox as : " + alerttxt);
//        Assert.fail("Alert is pop up without selecting any field");
//
//
//   }



}
