package TestCase.Scenario_03;
import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenario_03.SC_AN_003;
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

public class Scenario_03 extends BaseClass {
    SC_AN_003 obj_SC_AN_003  = new SC_AN_003();
    DBconnection obj_DBconnection = new DBconnection();

    @Test
    @Description("Edit the Pre-Audit data by Unit and date")
    public void TC_AN_001_Verify_editDate_Unit() throws SQLException {
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.SelectUnit("DATA");
        obj_SC_AN_003.Selectdate("18-10-2024");
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_003.ClickSave();

        StringBuilder tableContent = new StringBuilder();
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select top 5 * from PreAuditMaintenance where id=149";
        Allure.step("Use this query to fetch the table : " + qu);
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

    }

    @Test
    @Description("Verify able to edit invalid date")
    public void TC_AN_002_Verify_Invalid_date(){
        obj_SC_AN_003.ClickEdit();
        obj_SC_AN_003.Selectdate("18-10-2024");
        Screenshot.captureStepScreenshot(BaseClass.driver);
        obj_SC_AN_003.ClickSave();
    }



   @Test
   @Description("Verify the ProjectName on Pre Audit Compliance")
   public void TC_AN_003_Verify_projectName()
   {
       String Actual = "";
       Allure.step("The Actual projectName : "+ Actual);
       String expected = obj_SC_AN_003.GetProjectNameText();
       Allure.step("The Expected projectName : "+ expected);
       Assert.assertEquals(Actual,expected,"ProjectName is not matched");
   }

    @Test
    @Description("Veriy the Response input that correctly reflect on respective filed")
    public void TC_AN_004_Verify_Edit_Pre_Audit_Compalince()
    {
        obj_SC_AN_003.ClickProjectName();
        obj_SC_AN_003.SendResponse("");
        obj_SC_AN_003.ClickSave();
        String actual ="";
        Allure.step("Actual Response : "+actual);
        String expected = obj_SC_AN_003.Validate_response();
        Allure.step("expected Response : "+expected);
        Assert.assertEquals(actual,expected,"Response did not matched");
    }







}
