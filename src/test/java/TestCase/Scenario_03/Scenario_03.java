package TestCase.Scenario_03;
import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_03.SC_AN_003;
import TestCase.Scenario_02.Scenario_02;
import Utilities.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scenario_03 extends BaseClass {
    SC_AN_003 obj_SC_AN_003  = new SC_AN_003();
    DBconnection obj_DBconnection = new DBconnection();
    String accountName ="Yum Food";
    String projectName = "Yum Foods_POC";
    String unitName = "DEX";

  //  @Test(groups = "preAuditEdit",priority = 1)
    @Description("Edit the Pre-Audit data by Unit and date and Validate with database")
    public void TC_AN_001_Verify_editDate_Unit() throws SQLException {
        Scenario_02 obj_Scenario_02 = new Scenario_02();
        obj_SC_AN_003.Click_PreAuditreport();
        obj_SC_AN_003.Click_PreAuditreport_2();
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.SelectUnit(unitName);
        obj_SC_AN_003.Selectdate("24-10-2024");
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_003.ClickSave();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        System.out.println("table id :" +Scenario_02.preMainntanceTableId);
        String qu = "select top 5 * from PreAuditMaintenance where id="+ Scenario_02.preMainntanceTableId;
        Allure.step("Use this query to fetch the table : " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 2; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));

    }

    @Test(groups = "preAuditEdit",priority = 2)
    @Description("Verify the able to edit invalid date")
    public void TC_AN_002_Verify_Invalid_date(){
        obj_SC_AN_003.Click_PreAuditreport();
        obj_SC_AN_003.Click_PreAuditreport_2();
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.Selectdate("18-10-0001");
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_003.ClickSave();
        Assert.fail("Invalidate is allowed ");
    }



   @Test(groups = "preAuditEdit",priority = 3)
   @Description("Verify the ProjectName on Pre Audit Compliance")
   public void TC_AN_003_Verify_projectName()
   {
       obj_SC_AN_003.Click_PreAuditreport();
       obj_SC_AN_003.Click_PreAuditreport_2();
       obj_SC_AN_003.ClickProjectName();
       String Actual = " Yum Foods_POC";
       Allure.step("The Actual projectName : "+ Actual);
       String expected = obj_SC_AN_003.GetProjectNameText();
       Allure.step("The Expected projectName : "+ expected);
       Assert.assertEquals(Actual,expected,"ProjectName is not matched");
   }

    @Test(groups = "preAuditCompliance",priority = 1)
    @Description("Verify Edit the PreAudit Compliance provide input of response filed ")
    public void TC_AN_004_Verify_Edit_Pre_Audit_Compliance() throws InterruptedException {
        obj_SC_AN_003.Click_PreAuditreport();
        obj_SC_AN_003.Click_PreAuditreport_2();
        obj_SC_AN_003.ClickProjectName();
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.ClickSave();
        Thread.sleep(3000);
        Allure.step("Accpect the ");
        Alert alert = BaseClass.driver.switchTo().alert();
        Allure.step("Accpect the Alert of : "+ alert.getText());
        alert.accept();
        Assert.fail("Not showing any error instead of showing alert displays like changes is saved");

    }

    @Test(groups = "preAuditCompliance",priority = 2)
    @Description("Verify Edit the PreAudit Compliance provide input of response filed")
    public void TC_AN_005_Verify_Edit_Pre_Audit_Compliance() throws InterruptedException {
        String value ="SMAART";
        obj_SC_AN_003.Click_PreAuditreport();
        obj_SC_AN_003.Click_PreAuditreport_2();
        obj_SC_AN_003.ClickProjectName();
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.SendResponse(value);
        obj_SC_AN_003.ClickSave();
        Thread.sleep(3000);
        Alert alert = BaseClass.driver.switchTo().alert();
        alert.accept();
        obj_SC_AN_003.Click_Back();
        String expected = obj_SC_AN_003.Validate_response();
        Assert.assertEquals(value,expected,"Value is not matched");
    }








}
