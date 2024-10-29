package Pages.Scenario_07;

import Base.BaseClass;
import Database.DBconnection;
import Pages.Scenarrio_05.SC_AN_005;
import Utilities.Locators;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.List;

public class SC_AN_007 extends BaseClass {
    DBconnection obj_DBconnection = new DBconnection();

    String EWS_loc  = "EWS";
    String ChecklistCount_loc = "(//span[contains(@class,'MuiTypography-root-5220 MuiTypography-caption-5230')])[2]";



    Double weightRating1 =0.0;
    int weightage1 =0;


    public void clickEWS(){
       click(Findelement(Locators.link,EWS_loc));
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
                System.out.println(value);
                break;

            }

        }
        return value;
    }

    public Double findweightRating() throws SQLException {

        Connection connection = obj_DBconnection.dbconnect();
        String qu = "select WeightedRatingOrg from ProjectEWSComplaince where AuditId = 1742";
        Allure.step("Use this Query to fetch the data for Find weightRating: \n" + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            String result = rs.getString(1);
            weightRating1 += Double.parseDouble(result);
            Allure.step("First row value of WeightRatingorg : "+ weightRating1);
        }

        Allure.step("Find weightage using formula as <b>Sum of weightRatingORG</b> \n" +
                "Sum of WeightratingOrg first + second+ third  =: "+ weightRating1);
        return weightRating1;
    }


    public int findSumWightage() throws SQLException {
        Connection connection = obj_DBconnection.dbconnect();
        String qu = "  select Weightage from ProjectEWSComplaince where AuditId = 1742";
        Allure.step("Use this  query to fetch data from database for find Sum of weightage : \n " + qu);
        String query = qu;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String result = rs.getString(1);
            weightage1 += Double.parseDouble(result);
            Allure.step("values of Weightage : " + weightage1);

        }
            return weightage1;
    }

    @Step("Get the value and Database and perform action using formula ")
    public String  find_Compliance() throws SQLException {
        SC_AN_007 obj_SC_AN_007 = new SC_AN_007();
        int divisor = obj_SC_AN_007.findSumWightage();
        Double divident = obj_SC_AN_007.findweightRating();
        Allure.step("Formula : <b> Compliance = WeightRating / Sum of Weight </b>");
        Double res = divident/divisor;
        String formattedNumber = String.format("%.2f", res);
        return formattedNumber;
    }



    public String rowCount() throws SQLException {
        String value="";
        Connection connection = obj_DBconnection.dbconnect();
        StringBuilder tableContent = new StringBuilder();
        String qu = "select count(WeightedRating) from ProjectEWSComplaince where AuditId = 1742";
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


    public String Sum_Weighting() throws SQLException {
        String value="";
        Connection connection = obj_DBconnection.dbconnect();
        StringBuilder tableContent = new StringBuilder();
        String qu = " select sum(WeightedRating) from ProjectEWSComplaince where AuditId = 1742";
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

    @Step("Get the Average rating value from Database")
     public String findAveragerating() throws SQLException {
         SC_AN_007 obj_SC_AN_007 = new SC_AN_007();
          int divisor = Integer.parseInt(obj_SC_AN_007.rowCount());
          Double divident = Double.parseDouble(obj_SC_AN_007.Sum_Weighting());
          Double result = divident/divisor;
          DecimalFormat df = new DecimalFormat("0.00");
         String formattedNumber = df.format(result);
         System.out.println(formattedNumber);
         return formattedNumber;
    }


    @Step("Get the Average value from UI")
    public String Click_Averagevalue() {

        int c = 0;
        String value = "";
        List<WebElement> elements = BaseClass.driver.findElements(By.xpath("//div[@class=' secondary-color'][1]"));
        for (WebElement ele : elements) {
            c++;
            if (c == 1) {
                value = ele.getText();
                if (value.contains("Average Rating: ")) {
                    value = value.substring(value.indexOf("Average Rating: ") + "Average Rating: ".length()).trim();
                }
//                value = value.replace("%", "");
                System.out.println(value);
                break;

            }

        }
        return value;
    }


    @Step("Get the Pagination of total count from UI ")
    public String GetCountChecklist() throws InterruptedException {
        String [] splitted = new String[10];
        Thread.sleep(3000);
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
        String qu = "select count(*) from ProjectEWSComplaince where AuditId = 1742";
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
