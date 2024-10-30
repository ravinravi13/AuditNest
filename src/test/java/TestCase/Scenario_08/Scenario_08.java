package TestCase.Scenario_08;

import Base.BaseClass;
import Pages.Scenario_04.SC_AN_004;
import Pages.Scenario_08.SC_AN_08;
import Utilities.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Scenario_08 extends BaseClass {

    SC_AN_08 obj_SC_AN_08 = new SC_AN_08();






  //  @Test
    @Description("Verify BEF functionality add new BEF by FuntionType, AuditStatus,CheckList,Auditdate")
    public void TC_AN_001_VerifyADDBEF(){
        obj_SC_AN_08.ClickBEF();
        obj_SC_AN_08.ClickBEF1();
        obj_SC_AN_08.ClickAdd();
        obj_SC_AN_08.SelectFunctionType("LND");
        obj_SC_AN_08.SelectAuditStatus("Open");
        obj_SC_AN_08.Audittdate("30-10-2024");
        obj_SC_AN_08.CheckList("OLD");
        obj_SC_AN_08.Click_Save();
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

    @Test
    @Description("Verify Back button feature ")
    public void TC_AN_002_Invalid_Back() throws InterruptedException {
        obj_SC_AN_08.ClickBEF();
        obj_SC_AN_08.ClickBEF1();
        obj_SC_AN_08.ClickAdd();
        obj_SC_AN_08.Click_back();
        String expected ="https://auditnesttest.azurewebsites.net/functionmaintenanceList";
        Allure.step("Expected URL : = "+ expected);
        Thread.sleep(3000);
        Allure.step("Actual URL: = "+ BaseClass.driver.getCurrentUrl());
        Assert.fail("back button feature it not backward previous page it allows the home Page");
    }


}
