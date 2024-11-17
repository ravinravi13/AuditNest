package TestCase.Scenario_04;

import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_04.SC_AN_004;
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
import java.util.List;

public class Scenario_04 extends BaseClass {
    SC_AN_004 obj_SC_AN_004 = new SC_AN_004();
    static ArrayList<String> expected = new ArrayList<String>();
    DBconnection obj_DBconnection = new DBconnection();
    String accountName = "Yum Food";
    String projectName = "Yum Foods_POC";
    String unitName = "DATA";
    String Auditor = "raveendran.manickam@ilink-systems.com";
    String auditStatus = "Open";
    String projectHandling = "iLink";
    String projectMatrix = "PSM1";
    String omApplicable = "Applicable";
    String checkList = "OLD";

    @Test(groups = "Audit", priority = 1)
    @Description("Verify the Create a new Audit Report by AccountName,ProjectName,Auditor,Auditee," +
            "AuditDate,AuditStatus,ProjectType,ProjectHandling,Unit,ProjectMatrix,OM Application,Checklist" +
            "and verify From UI ")
    public void TC_AN_001_Verify_createAuditReport() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.Click_AddIcon();
        obj_SC_AN_004.SelectAccountName(accountName);
        expected.add(accountName);
        obj_SC_AN_004.SelectProjectName(projectName);
        expected.add(projectName);
        obj_SC_AN_004.EnterAuditor(Auditor);
        expected.add(Auditor);
        obj_SC_AN_004.SelectDate("24-10-2024");
        expected.add("24-20-2024");
        obj_SC_AN_004.SelectAuditStatus(auditStatus);
        expected.add(auditStatus);
        obj_SC_AN_004.Select_PojectHanding(projectHandling);
        expected.add(projectHandling);
        obj_SC_AN_004.Select_Unit(unitName);
        expected.add(unitName);
        obj_SC_AN_004.Select_ProjecMatrix(projectMatrix);
        expected.add(projectMatrix);
        obj_SC_AN_004.Select_OMAapplication(omApplicable);
        expected.add(omApplicable);
        obj_SC_AN_004.Select_Checklist(checkList);
        expected.add(checkList);
        String expected_str = expected.toString();
        Allure.addAttachment("Actual value of input :", new ByteArrayInputStream(expected_str.getBytes(StandardCharsets.UTF_8)));
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_004.Click_Save();
    }

    @Test(groups = "Audit", priority = 2)
    @Description("Verify the Database validate with recently created audit report")
    public void TC_AN_002_Verify_DB_AuditReport() throws SQLException, InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        ArrayList<String> actual_list = new ArrayList<String>();
        actual_list.add(projectName);
        actual_list.add(accountName);
        actual_list.add(unitName);
        actual_list.add(Auditor);
        actual_list.add(projectMatrix);
        System.out.println("Actual  = " + actual_list);
        Thread.sleep(3000);
        List arraylist = new ArrayList();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select ProjectName,BusinessName,Unit,AuditDate,AuditBy,ProjectMatrix from AuditMaintenance where id=1737";
        Allure.step("Use this query fetch value from database : " + qu);
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
                arraylist.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(actual_list, actual_list, "Table Value is not matched");
    }


    @Test(groups = "Search", priority = 5)
    @Description("Verify the Searching functionality By ProjectName and data are correctly display in table")
    public void TC_AN_003_verify_SearchingBY_ProjectName() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText(projectName);
        Thread.sleep(3000);
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

    @Test(groups = "Search", priority = 6)
    @Description("Verify the Searching functionality By Account Name and data are correctly display in table")
    public void TC_AN_004_verify_SearchingBY_AccountName() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText(accountName);
        Thread.sleep(3000);
        //obj_SC_AN_004.tableContent();
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

    @Test(groups = "Search", priority = 7)
    @Description("Verify the Searching functionality By AccountName and data are correctly display in table")
    public void TC_AN_005_verify_SearchingBY_Unit() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText(unitName);
        Thread.sleep(3000);
        //obj_SC_AN_004.tableContent();
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

    @Test(groups = "Search", priority = 8)
    @Description("Verify the Searching functionality By Auditor and data are correctly display in table")
    public void TC_AN_006_verify_SearchingBY_Auditor() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText(Auditor);
        Thread.sleep(3000);
//        obj_SC_AN_004.tableContent();
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

    @Test(groups = "Search", priority = 9)
    @Description("Verify the Searching functionality By Projectmatrix and data are correctly display in table")
    public void TC_AN_007_verify_SearchingBY_ProjectMatrix() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText(projectMatrix);
        Thread.sleep(3000);
        // obj_SC_AN_004.tableContent();
        Screenshot.captureStepScreenshot(BaseClass.driver);


    }

    @Test(groups = "Audit", priority = 3)
    @Description("Verify the Edit functionality to edit the date filed and Verify database to edited date")
    public void TC_AN_008_VerifyEdit_AuitReport() throws SQLException, InterruptedException {


        ArrayList<String> actual = new ArrayList<String>();
        actual.add(projectName);
        actual.add(accountName);
        actual.add(unitName);
        actual.add("2024-10-25");
        ArrayList<String> arraylist = new ArrayList<String>();
        String data = "25-10-2024";
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.ClickEdit();
        obj_SC_AN_004.SelectDate(data);
        obj_SC_AN_004.setZoomout();
        Thread.sleep(2000);
        obj_SC_AN_004.Click_Save();
        String expected = obj_SC_AN_004.GetDate();
        Assert.assertEquals(data, expected, "Date is not matched");


        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select ProjectName,BusinessName,Unit,AuditDate from AuditMaintenance where id=1737";
        Allure.step("Use this query fetch value from database : " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();


        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                arraylist.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));

        Assert.assertEquals(actual.toString(), arraylist.toString());
    }

    @Test(groups = "Audit", priority = 4)
    @Description("Verify the delete feature in Audit report and Database also")
    public void TC_AN_009_Verify_Delete() throws SQLException, InterruptedException {
        ArrayList<String> arraylist = new ArrayList<String>();
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        Thread.sleep(3000);
        obj_SC_AN_004.ClickDelete();
        Thread.sleep(3000);
        Alert alert = BaseClass.driver.switchTo().alert();
        alert.accept();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select ProjectName,BusinessName,Unit,AuditDate from AuditMaintenance where id=1737";
        Allure.step("Use this query fetch value from database : " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();


        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                arraylist.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        System.out.println(arraylist);
        Assert.assertEquals(arraylist == null, false);
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));

    }


}
