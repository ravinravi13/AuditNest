package TestCase.Scenario_04;

import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_04.SC_AN_004;
import Utilities.Screenshot;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
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
    ArrayList expected = new ArrayList();
    DBconnection obj_DBconnection = new DBconnection();

    @Test
    @Description("Verify the Create a new Audit Report by AccountName,ProjectName,Auditor,Auditee," +
            "AuditDate,AuditStatus,ProjectType,ProjectHandling,Unit,ProjectMatrix,OMAapplication,Checklist" +
            "and verify From UI ")
    public void TC_AN_001_Verify_createAuditReport() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.Click_AddIcon();
        obj_SC_AN_004.SelectAccountName("");
        expected.add("");
        obj_SC_AN_004.SelectProjectName("");
        expected.add("");
        obj_SC_AN_004.EnterAuditor("");
        expected.add("");
        obj_SC_AN_004.SelectDate("");
        expected.add("");
        obj_SC_AN_004.SelectAuitee("");
        expected.add("");
        obj_SC_AN_004.SelectAuditStatus("");
        expected.add("");
        obj_SC_AN_004.SelectProjectType("");
        expected.add("");
        obj_SC_AN_004.Select_PojectHanding("");
        expected.add("");
        obj_SC_AN_004.Select_Unit("");
        expected.add("");
        obj_SC_AN_004.Select_ProjecMatrix("");
        expected.add("");
        obj_SC_AN_004.Select_OMAapplication("");
        expected.add("");
        obj_SC_AN_004.Select_Checklist("");
        expected.add("");
        String expected_str = expected.toString();
        Allure.addAttachment("Actual value of input :", new ByteArrayInputStream(expected_str.getBytes(StandardCharsets.UTF_8)));
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_004.Click_Save();
        String actual = obj_SC_AN_004.tableContent().toString();
        boolean result = expected_str.contains(actual);
        System.out.println(result);
        Assert.assertEquals(result,true,"Table value is not matched");
    }

    @Test
    @Description("Verify the Database validate with recently created audit report")
    public void TC_AN_002_Verify_DB_AuditReport() throws SQLException {
        List arraylist = new ArrayList();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "";
        Allure.step("Use this query fetch value from database : " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            tableContent.append(rs.getMetaData().getColumnName(i)).append("\t");
        }
        while (rs.next()) {
            for (int i = 2; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                arraylist.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));
        Boolean result = expected.equals(arraylist);
        Assert.assertEquals(result, true, "Table Value is not matched");
    }


    @Test()
    @Description("Verify the Searching functionality By ProjectName and data are correctly display in table")
    public void TC_AN_003_verify_SearchingBY_ProjectName() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText("MADI_AbbVie");
        Thread.sleep(3000);
        obj_SC_AN_004.tableContent();

    }
    @Test()
    @Description("Verify the Searching functionality By AccounttName and data are correctly display in table")
    public void TC_AN_004_verify_SearchingBY_AccountName() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText("MADI_AbbVie");
        Thread.sleep(3000);
        obj_SC_AN_004.tableContent();
    }

    @Test()
    @Description("Verify the Searching functionality By AccounttName and data are correctly display in table")
    public void TC_AN_005_verify_SearchingBY_Unit() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText("MADI_AbbVie");
        Thread.sleep(3000);
        obj_SC_AN_004.tableContent();
    }

    @Test()
    @Description("Verify the Searching functionality By Auditor and data are correctly display in table")
    public void TC_AN_006_verify_SearchingBY_Auditor() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText("MADI_AbbVie");
        Thread.sleep(3000);
        obj_SC_AN_004.tableContent();
    }

    @Test()
    @Description("Verify the Searching functionality By Projectmatrix and data are correctly display in table")
    public void TC_AN_007_verify_SearchingBY_ProjectMatrix() throws InterruptedException {
        obj_SC_AN_004.ClickAuditReport();
        obj_SC_AN_004.ClickAuditReport1();
        obj_SC_AN_004.SeacrText("MADI_AbbVie");
        Thread.sleep(3000);
        obj_SC_AN_004.tableContent();
    }





}
