package TestCase.Scenario_02;
import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_02.SC_AN_002;
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
import java.util.Collections;
import java.util.List;

public class Scenario_02 extends BaseClass {

    SC_AN_002 obj_SC_AN_002 = new SC_AN_002();
    DBconnection obj_DBconnection = new DBconnection();

    ArrayList expected = new ArrayList();


   @Test(groups = {"Relevant_error"},priority = 1)
    @Description("This test used for relevant error message pop-up on respective filed." +
            "By select only AccountName then click save button error message pop-up on Unit filed")

    public void TC_AN_001_SelectAccount() {
        obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_AccoutName("Yum Food");
        obj_SC_AN_002.Click_Save();
        String value = obj_SC_AN_002.Required_unit();
        Allure.addAttachment("Expected error message from Unit", new ByteArrayInputStream(value.getBytes()));

        obj_SC_AN_002.Cancel_btn();
    }


   @Test(groups = {"Relevant_error"},priority = 2)
    @Description("This test used for relevant error message pop-up on respective filed." +
            "By select only AccountName and ProjectName then click save button error message pop-up on Unit filed")
    public void TC_AN_002_SelectProjectName() {

        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_AccoutName("Yum Food");
        obj_SC_AN_002.Select_ProjectName("Yum Foods_POC");
        obj_SC_AN_002.Click_Save();
        String value = obj_SC_AN_002.Required_unit();
        Allure.addAttachment("Expected error message from Unit", new ByteArrayInputStream(value.getBytes()));
        obj_SC_AN_002.Cancel_btn();

    }

    @Test(groups = {"Relevant_error"},priority = 3)
    @Description("This test used for relevant error message pop-up on respective filed." +
            "By select only Unit then click save button error message pop-up on AccountName filed")
    public void TC_AN_003_SelectUnit() {
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_Unit("DEX");
        obj_SC_AN_002.Click_Save();
        String value = obj_SC_AN_002.required_accountname();
        Allure.addAttachment("Expected error message from AccountName", new ByteArrayInputStream(value.getBytes()));
        obj_SC_AN_002.Cancel_btn();
    }

    @Test(groups = {"Relevant_error"},priority = 4)
    @Description("This test used for relevant error message pop-up on respective filed." +
            "By select only Unit then click save button error message pop-up on AccountName filed")
    public void TC_AN_004_SelectDate() {
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Selectdate("16-10-2024");
        obj_SC_AN_002.Click_Save();
        String value = obj_SC_AN_002.required_accountname();
        Allure.addAttachment("Expected error message from AccountName", new ByteArrayInputStream(value.getBytes()));
        obj_SC_AN_002.Cancel_btn();

    }
    @Test(groups = {"Relevant_error"},priority = 5)
    @Description("This test used for relevant error message pop-up on respective filed." +
            "By select AccountName,Unit,date then click save button error message pop-up on projectName filed")
    public void TC_AN_005_Verify_ProjectFiled() {
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_AccoutName("Yum Food");
        obj_SC_AN_002.Select_Unit("DEX");
        obj_SC_AN_002.Selectdate("16-10-2024");
        String value = obj_SC_AN_002.required_projetcname();
        Allure.addAttachment("Expected error message from projectName", new ByteArrayInputStream(value.getBytes()));
        obj_SC_AN_002.Cancel_btn();
    }

   @Test(groups = "PreAudit")
    @Description("Verify to create a New Pre-Audit report by select AccountName,ProjectName,Unit,Audit Date and verify the recent report saved or not")
    public void TC_AN_006_Verify_Create_NewPreaudit() throws InterruptedException {
       ArrayList expected = new ArrayList();
       expected.add("Yum Foods_POC");
       expected.add("Yum Food");
       expected.add("DEX");
       expected.add("13-11-2024");
         obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_AccoutName("Yum Food");
        obj_SC_AN_002.Select_ProjectName("Yum Foods_POC");
        obj_SC_AN_002.Select_Unit("DEX");
        Thread.sleep(4000);
        obj_SC_AN_002.Selectdate("13112024");
       Thread.sleep(4000);
        obj_SC_AN_002.Click_Save();
        List<String> Actual = obj_SC_AN_002.tableContent();
        Assert.assertEquals(Actual,expected,"Values are not Matched");


//        List<String> actual = obj_SC_AN_002.tableContent();
//        System.out.println("Actual list = "+actual);
//        String expected_Str = expected.toString();
//        System.out.println("Expected list = "+expected_Str);
//        Allure.addAttachment("Actual value of input :", new ByteArrayInputStream(expected_Str.getBytes(StandardCharsets.UTF_8)));
////        Boolean result = expected.contains(actual);
//        String Actual_str = actual.toString();
//        Assert.assertEquals(Actual_str, expected_Str, "Table Value is not matched");


    }


  @Test(groups = "PreAudit")
    @Description("Verify the recently registered pre audit record successfully stored in database")
    public void TC_AN_007_Verify_DB_PreAuditrecord() throws SQLException {
      ArrayList expected = new ArrayList();
        expected.add("Yum Food");
        expected.add("Yum Foods_POC");
        expected.add("DEX");
        expected.add("13-11-2024");
        List<String> arraylist =  new ArrayList<>();
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();

        String qu = "select top 5 * from PreAuditMaintenance where id=167";
        Allure.step("Use this query fetch value from database : " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int columnCount = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 2; i <= columnCount; i++) {
                System.out.println(rs.getString(i));
                arraylist.add(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));
      System.out.println("Actual list : "+ arraylist);
      System.out.println("Expected list :" + expected);


    }

    @Test(groups = "Invalid")
    @Description("Verify to Invalid date can registered in pre-Audit reports")
    public void TC_AN_008_Verify_Invaliddate_Preaudit() {
        obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
        obj_SC_AN_002.Click_AddPreAudit();
        obj_SC_AN_002.Select_AccoutName("Yum Food");
        obj_SC_AN_002.Select_ProjectName("Yum Foods_POC");
        obj_SC_AN_002.Select_Unit("DEX");
        obj_SC_AN_002.Selectdate("16-10-0001");
        obj_SC_AN_002.Click_Save();
        expected.add("MADI_AbbVie");
        expected.add("AbbVie");
        expected.add("DATA");
        expected.add("22-02-0001");
        List actual = obj_SC_AN_002.tableContent();
        String actual_Str = expected.toString();
        Allure.addAttachment("Actual value of input :", new ByteArrayInputStream(actual_Str.getBytes(StandardCharsets.UTF_8)));
        Boolean result = expected.equals(actual);
         Assert.fail("Past date is allowed");
    }

   @Test(priority = 1,groups = "Search")
    @Description("Verify the Searching functionality By ProjectName and data are correctly display in table")
    public void TC_AN_009_verify_SearchingBY_ProjectName() throws InterruptedException {
        obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
       obj_SC_AN_002.SeacrText("MADI_AbbVie");
       Thread.sleep(3000);
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }
    @Test(priority = 2,groups = "Search")
    @Description("Verify the Searching functionality By AccountName and data are correctly display in table")
    public void TC_AN_009_Verify_SearchingBy_AccountName() throws InterruptedException {
        obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
        obj_SC_AN_002.SeacrText("Costco");
        Thread.sleep(3000);
        Screenshot.captureStepScreenshot(BaseClass.driver);
    }
    @Test(priority = 3,groups = "Search")
    @Description("Verify the Searching functionality By Unit and data are correctly display in table")
    public void TC_AN_0010_Verify_SearchingBy_Unit() throws InterruptedException {
        obj_SC_AN_002.Click_PreAuditreport();
        obj_SC_AN_002.Click_PreAuditreport_2();
        obj_SC_AN_002.SeacrText("DEX");
        Thread.sleep(3000);
        Screenshot.captureStepScreenshot(BaseClass.driver);

    }

   // @Test
    @Description("Edit the pre-Audit table data")
    public void TC_AN_0011_Verify_EditTable() throws SQLException {
        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select top 5 * from PreAuditMaintenance where id=158";
        Allure.step("Before edit data value fetch value from database : " + qu);
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
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));




        obj_SC_AN_002.Select_Unit("");
        obj_SC_AN_002.Click_Save();

        StringBuilder tableContent1 = new StringBuilder();
        Connection connection1 = obj_DBconnection.dbconnect();
        String qu1 = "select top 5 * from PreAuditMaintenance where id=149";
        Allure.step("After edit data value fetch value from database : " + qu1);
        String query1 = qu1;
        Statement stmt1 = connection1.createStatement();
        ResultSet rs1 = stmt1.executeQuery(query);
        int columnCount1 = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount1; i++) {
            tableContent.append(rs.getMetaData().getColumnName(i)).append("\t");
        }
        while (rs.next()) {
            for (int i = 2; i <= columnCount1; i++) {
                System.out.println(rs.getString(i));
                tableContent.append(rs.getString(i)).append("\t");
            }
            tableContent1.append("\n");
        }
        Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent1.toString().getBytes(StandardCharsets.UTF_8)));



    }





}

































