package TestCase.Scenario_07;

import Base.BaseClass;
import Pages.Scenario_04.SC_AN_004;
import Pages.Scenario_07.SC_AN_007;
import Pages.Scenarrio_05.SC_AN_005;
import TestCase.Scenario_05.Scenario_05;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Scenario_07 extends BaseClass {

    SC_AN_007 obj_SC_AN_007 = new SC_AN_007();
    SC_AN_005 obj_SC_AN_005 = new SC_AN_005();
    SC_AN_004 obj_SC_AN_004 = new SC_AN_004();




   // @Test
    @Description("Verify the EWS value from UI and Database using formula as Compliance = WeightRating / Sum of Weightage")
    public void TC_AN_001_Verify_CompliancePercentage() throws SQLException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_005.Click_ProjectName();
        obj_SC_AN_007.clickEWS();
        obj_SC_AN_007.Click_getPercentage();
        String actual = obj_SC_AN_007.Click_getPercentage();
        Allure.step("Actual value From UI: " + actual);
        String expected = obj_SC_AN_007.find_Compliance();
        Allure.step("Expected value From Database using formula : " + expected);
        Assert.assertEquals(actual,expected,"Value are not matched");
    }

    //@Test
    @Description("Verify the EWS value from UI and Database using formula as Average of WeightRating")
    public void TC_AN_002_Verify_Averagerating() throws SQLException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_005.Click_ProjectName();
        obj_SC_AN_007.clickEWS();
        String actual = obj_SC_AN_007.Click_Averagevalue();
        Allure.step("Actual value From UI: " + actual);
        String expected = obj_SC_AN_007.findAveragerating();
        Allure.step("Expected value From Database using formula : " + expected);
        Assert.assertEquals(actual,expected,"Value are not matched");
    }

   // @Test
    @Description("")
    public void TC_AN_003_VerifyPagination() throws SQLException, InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_005.Click_ProjectName();
        obj_SC_AN_007.clickEWS();
        String actual = obj_SC_AN_007.GetCountChecklist();
        Allure.step("Actual value From UI: " + actual);
        String expected = obj_SC_AN_007.GetCheckListCount_DB();
        Allure.step("Expected value From Database using formula : " + expected);
        Assert.assertEquals(actual,expected,"Value are not matched");
    }






}
