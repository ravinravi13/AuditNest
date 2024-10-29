package Pages.Scenarrio_05;
import Base.BaseClass;
import Database.DBconnection;
import Utilities.Locators;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

public class SC_AN_005 extends BaseClass {
    DBconnection obj_DBconnection = new DBconnection();

    String Audit_loc1 = "(//li[@title='Audit Maintenance']//a)[1]";
    String Audit_loc2 = "//a[@href='/auditList']";
    String ProjectNameLink_loc = "(//a[@class='action cursor'])[1]";
    String Action_loc = "(//a[@class='action cursor'])[1]";
    String Edit_loc = "(//a[@class='action cursor'])[1]";
    String complaince_status_loc = "//select[@name='complainceStatus']";
    String save_loc = "(//button[@type='submit'])[2]";
    String ChecklistCount_loc ="//div[@class='MuiInput-root-334 MuiTablePagination-input-370']/following-sibling::span[1]";
   Double weightRating =0.0;
   int weightage =0;

    public void Click_edit_PCI() {
        click(Findelement(Locators.xpath, "(//a[@class='action cursor'])[4]"));
    }

    @Step("Get the Percentage value from UI")
    public String Click_getPercentage() {

        int c = 0;
        String value = "";
        List<WebElement> elements = BaseClass.driver.findElements(By.xpath("//div[@class=' secondary-color'][1]"));
        for (WebElement ele : elements) {
              c++;
            if (c == 2) {
                value = ele.getText();
                if (value.contains("Compliance: ")) {
                    value = value.substring(value.indexOf("Compliance: ") + "Compliance: ".length()).trim();
                }
                value = value.replace("%", "");
//                System.out.println(value);
                break;

            }

        }
        return value;
    }

    @Step("Click the ProjectName it shows as a link")
     public void Click_ProjectName(){
         click(Findelement(Locators.xpath,ProjectNameLink_loc));
     }


     public Double findweightRating() throws SQLException {

         Connection connection = obj_DBconnection.dbconnect();
         String qu = "select WeightedRatingOrg from ProjectPCIComplaince where Id = 39419";
         Allure.step("Use this Query to fetch the data for Find weightRating: \n" + qu);
         String query = qu;
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         while(rs.next()){
             String result = rs.getString(1);
             weightRating += Double.parseDouble(result);
             Allure.step("First row value of WeightRatingorg : "+ weightRating);
         }


         String qu1 = "select WeightedRatingOrg from ProjectPCIComplaince where Id = 39420";
         Allure.step("Use this Query to fetch the data for Find weightRating : \n" + qu1);
         String query1 = qu1;
         Statement stmt1 = connection.createStatement();
         ResultSet rs1 = stmt.executeQuery(query1);
         while(rs1.next()){
             String result = rs1.getString(1);
             weightRating += Double.parseDouble(result);
             Allure.step("Second row value of WeightRatingorg : "+ weightRating);
         }

         String qu2 = "select WeightedRatingOrg from ProjectPCIComplaince where Id = 39421";
         Allure.step("Use this Query to fetch the data for Find weightRating : \n" + qu2);
         String query2 = qu2;
         Statement stmt2 = connection.createStatement();
         ResultSet rs2 = stmt.executeQuery(query2);
         while(rs2.next()){
             String result = rs2.getString(1);
             weightRating += Double.parseDouble(result);
             Allure.step("Third row value of WeightRatingorg : "+ weightRating);
         }
         Allure.step("Find weightage using formula as <b>Sum of weightRatingORG</b> \n" +
                 "Sum of WeightratingOrg first + second+ third  =: "+ weightRating);
         return weightRating;
     }



     public int findSumWightage() throws SQLException {
         Connection connection = obj_DBconnection.dbconnect();
         String qu = "select Weightage from ProjectPCIComplaince where Id = 39419";
         Allure.step("Use this  query to fetch data from database for find Sum of weightage : \n "+ qu);
         String query = qu;
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         while(rs.next()){
             String result = rs.getString(1);
             weightage += Double.parseDouble(result);
             Allure.step("first row value of Weightage : "+ weightage);

         }

         String qu1 = "select Weightage from ProjectPCIComplaince where Id = 39420";
         Allure.step("Use this  query to fetch data from database for find Sum of weightage : \n "+ qu1);
         String query1 = qu1;
         Statement stmt1 = connection.createStatement();
         ResultSet rs1 = stmt.executeQuery(query1);
         while(rs1.next()){
             String result = rs1.getString(1);
             weightage += Double.parseDouble(result);
             Allure.step("Second row value of Weightage : "+ weightage);



         }

         String qu2 = "select Weightage from ProjectPCIComplaince where Id = 39421";
         Allure.step("Use this  query to fetch data from database for find Sum of weightage : \n "+ qu2);

         String query2 = qu2;
         Statement stmt2 = connection.createStatement();
         ResultSet rs2 = stmt.executeQuery(query2);
         while(rs2.next()){
             String result = rs2.getString(1);
             weightage += Double.parseDouble(result);
             Allure.step("Third row value of Weightage : "+ weightage);

         }
         Allure.step("Find Weightage using formula as <b>Sum of weightage</b> \n" +
                 "Sum of weightage of first + second + third = " + weightage);
        return weightage;

     }

     @Step("Get the value and Database and perform action using formula ")
     public String  find_Compliance() throws SQLException {
         SC_AN_005 obj_SC_AN_005 = new SC_AN_005();
         int divisor = obj_SC_AN_005.findSumWightage();
         Double divident = obj_SC_AN_005.findweightRating();
         Allure.step("Formula : <b> Compliance = WeightRating / Sum of Weight </b>");
         Double res = divident/divisor;
         String formattedNumber = String.format("%.2f", res);
         return formattedNumber;
     }









     public void DB_Percentagevalue() throws SQLException {
         Connection connection = obj_DBconnection.dbconnect();
         StringBuilder tableContent = new StringBuilder();
         String qu = "select * from ProjectPCIComplaince where AuditId = 1742";
         Allure.step("Use this query1 fetch value from database : " + qu);
         String query = qu;
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         int columnCount = rs.getMetaData().getColumnCount();
         for (int i = 1; i <= columnCount; i++) {
             System.out.println(rs.getString(i));
             tableContent.append(rs.getString(i)).append("\t");
         }
         tableContent.append("\n");
     Allure.addAttachment("Database Table", new ByteArrayInputStream(tableContent.toString().getBytes(StandardCharsets.UTF_8)));

         String qu1 = "select * from ProjectPCIComplaince where AuditId = 1742";
         String query1 = qu;
         Statement stmt1 = connection.createStatement();
         int columnCount1 = rs.getMetaData().getColumnCount();
         ResultSet rs1 = stmt.executeQuery(query1);
         while(rs1.next()){
             String result = rs.getString(5
             );
         }



     }


     @Step("Get the Pagination of total count from UI ")
     public String  GetCountChecklist(){
        String [] splitted = new String[10];
        String split_txt = getText(Locators.xpath,ChecklistCount_loc);
         splitted = split_txt.split("of");
         String result = splitted[splitted.length-1].trim();
         System.out.println(result);
         Allure.step("Total count of checklist as : "+ result);
         return result;

     }

     @Step("Get the Pagination value from database")
     public String GetCheckListCount_DB() throws SQLException {
         String value="";
         Connection connection = obj_DBconnection.dbconnect();
         StringBuilder tableContent = new StringBuilder();
         String qu = "select count(*) as count_of_rows from ProjectPCIComplaince where AuditId = 1742";
         Allure.step("Use this query fetch value from database : " + qu);
         String query = qu;
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         int columnCount = rs.getMetaData().getColumnCount();
         while(rs.next()){
             value = rs.getString(1);
         }
         return value;
     }








}








