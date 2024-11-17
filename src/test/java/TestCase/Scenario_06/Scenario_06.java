package TestCase.Scenario_06;

import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_04.SC_AN_004;
import Pages.Scenario_06.SC_AN_006;
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
import java.util.ArrayList;

public class Scenario_06 extends BaseClass {

    SC_AN_004 obj_SC_AN_004 = new SC_AN_004();
    SC_AN_006 obj_SC_AN_006 = new SC_AN_006();
    DBconnection obj_DBconnection = new DBconnection();





    @Test(priority = 1)
   @Description("Verify Add the sprint in OM sprint Section by selecting SprintName,StartDate,EndDate")
    public void TC_AN_001_Verify_AddSprint(){
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
         obj_SC_AN_006.Click_ProjectName();
         obj_SC_AN_006.ClickOMSprint();
         obj_SC_AN_006.ClikOMSprint1();
         obj_SC_AN_006.ClickAddSprint();
         obj_SC_AN_006.EnterSprintName("Sprint-01");
         obj_SC_AN_006.EnterStartDate("29-10-2024");
         obj_SC_AN_006.EnterEndDate("4-11-2024");
         Screenshot.captureStepScreenshot(BaseClass.driver);
         obj_SC_AN_006.Cick_Save();
    }

    @Test(priority = 2)
    @Description("Verify Add Sprint functionality of Invalid test of without enter the Sprint name")
    public void TC_AN_002_Verify_InvalidTest() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_006.Click_ProjectName();
        obj_SC_AN_006.ClickOMSprint();
        obj_SC_AN_006.ClikOMSprint1();
        obj_SC_AN_006.ClickAddSprint();
        obj_SC_AN_006.EnterStartDate("29-10-2024");
        obj_SC_AN_006.EnterEndDate("4-11-2024");
        obj_SC_AN_006.Cick_Save();
        Thread.sleep(3000);
        Alert alert = BaseClass.driver.switchTo().alert();
        alert.accept();
        Screenshot.captureStepScreenshot(BaseClass.driver);
        Assert.fail("It allows to save  the Sprint without type SprintName");
    }

   @Test(priority = 3)
    @Description("Verify the database of recently saved sprint to check saved or not")
    public void TC_AN_003_Verify_DB_ADDSprint() throws SQLException {
        ArrayList<String> actual = new ArrayList<String>();
        ArrayList<String> Expected = new ArrayList<String>();
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_006.Click_ProjectName();
        obj_SC_AN_006.ClickOMSprint();
        obj_SC_AN_006.ClikOMSprint1();
        actual = obj_SC_AN_006.Sprint01();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select SprintName,StartDate,EndDate,Unit from OmSprintMaintenance where SprintId = 35";
        Allure.step("Use this query to fetch the table : \n" + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            tableContent.append(rs.getMetaData().getColumnName(i)).append("\t");
        }
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                Expected.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(actual,Expected,"List is not matched");

    }


   @Test(priority = 4)
    @Description("Verify the Back button feature in OM sprint it helps to backward the previous page")
  public void TC_AN_004_InvalidTest() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_006.Click_ProjectName();
        obj_SC_AN_006.ClickOMSprint();
        obj_SC_AN_006.ClikOMSprint1();
        obj_SC_AN_006.ClickBack();
        Thread.sleep(3000);
        String Expectedurl = "https://auditnesttest.azurewebsites.net/OMSprint/1742";
        Allure.step("Expected Page URL as : " +Expectedurl);
        Allure.step("But Actual URL is : " + BaseClass.driver.getCurrentUrl());
        Assert.fail("Back not follows the previous it allows homepage ");
  }





}
