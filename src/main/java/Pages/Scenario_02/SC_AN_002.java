package Pages.Scenario_02;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class SC_AN_002 extends BaseClass {

    String Add_loc = "//span[@class='glyphicon glyphicon-plus center']";
    String preaudit_loc_1 = "(//li[@title='PreAudit']//a)[1]";
    String Preaudit_loc_2 = "//ul[@id='preauditreport']//a[1]";
    String AccountName_loc = "//select[@name='accountName']";
    String ProjectName_loc = "//select[@name='projectName']";
    String Unit_loc = "//select[@name='unit']";
    String Auditdate = "//input[@name='auditDate']";
    String save_loc = "//button[text()='Save']";
    String cancel_loc ="//button[text()='Cancel']";
    String Date_loc = "//input[@type='date']";
    String Search_loc = "//input[@placeholder='Search Text Here']";
    String Edit_btn = "(//a[@class='action cursor'])[2]";
     List arr;
     int c=0;

     String table_loc ="//table[@class='MuiTable-root-134 table']";


    @Step("Click Pre-Audite Report from side navigation bar")
    public void Click_PreAuditreport() {
        click(Findelement(Locators.xpath, preaudit_loc_1));
    }

    @Step("Click togglecase of PreAudit Report ")

    public void Click_PreAuditreport_2() {
        click(Findelement(Locators.xpath, Preaudit_loc_2));
    }
    @Step("Click Add new PreAudit iconn from right corner")
    public void Click_AddPreAudit()
    {
        click(Findelement(Locators.xpath,Add_loc));
    }

    @Step("Select AccountName as : {0}")
    public void Select_AccoutName(String accountName) {
        SelectVisibleText(Findelement(Locators.xpath,AccountName_loc),accountName);
    }

    @Step("Select ProjectName as : {0}")
    public void Select_ProjectName(String ProjectName) {

      SelectVisibleText(Findelement(Locators.xpath,ProjectName_loc),ProjectName);
    }

    @Step("Select Unit as : {0}")
    public void Select_Unit(String Unit) {

        SelectVisibleText(Findelement(Locators.xpath,Unit_loc),Unit);
    }

    @Step("Select Audit Date from date picker as : {0}")
    public void Send_Auditdate(String Date) {
        Send(Findelement(Locators.xpath,Auditdate),Date);
    }

    @Step("Click Save button")
    public void Click_Save()
    {
        click(Findelement(Locators.xpath,save_loc));

    }

    @Step("Click the Save and Required message pop-up Unit field")
    public String  Required_unit(){
        click(Findelement(Locators.xpath,save_loc));
        WebElement element = Findelement(Locators.xpath,Unit_loc);
        String value = element.getAttribute("validationMessage");
        return value;
    }

    @Step("Click the Save and Required message pop-up AccountName field")
    public String  required_accountname(){
        click(Findelement(Locators.xpath,save_loc));
        WebElement element = Findelement(Locators.xpath,AccountName_loc);
        String value = element.getAttribute("validationMessage");
        return value;
    }

    @Step("Click the Save and Required message pop-up ProjectName field")
    public String  required_projetcname(){
        click(Findelement(Locators.xpath,save_loc));
        WebElement element = Findelement(Locators.xpath,ProjectName_loc);
        String value = element.getAttribute("validationMessage");
        return value;
    }

    @Step("Click cancel button")
    public void Cancel_btn()
    {
        click(Findelement(Locators.xpath,cancel_loc));
    }
    @Step("Select date as : {0}")
    public void Selectdate(String value){
        Send(Findelement(Locators.xpath,Date_loc),value);
    }

    @Step("After Successfully registered the preAudit then verify it display table of Pre-Audit Section")
    public List tableContent()
    {
        arr = new ArrayList<String>();
        WebElement table = Findelement(Locators.xpath,table_loc);
        List<WebElement> allRows = table.findElements(By.cssSelector("tbody tr"));
        for(WebElement rowElement : allRows)
        {
            List<WebElement> row = rowElement.findElements(By.tagName("td"));
            for(WebElement eachrow : row)
            {
                c++;
                if(c>5 && c<=9) {
                    String value = eachrow.getText();
                    arr.add(value);

                }
                if(c==9)
                {
                    break;
                }

            }

        }
        String Arraylist = arr.toString();

        Allure.addAttachment("Value get from tables :", new ByteArrayInputStream(Arraylist.getBytes(StandardCharsets.UTF_8)));
        return arr;

    }

    @Step("Search the content fetch from table ")
    public List tableContents()
    {
        arr = new ArrayList<String>();
        WebElement table = Findelement(Locators.xpath,table_loc);
        List<WebElement> allRows = table.findElements(By.cssSelector("tbody tr"));
        for(WebElement rowElement : allRows)
        {
            List<WebElement> row = rowElement.findElements(By.tagName("td"));
            for(WebElement eachrow : row)
            {
                c++;
                if(c>5) {
                    String value = eachrow.getText();
                    arr.add(value);

                }

            }

        }
        String Arraylist = arr.toString();

        Allure.addAttachment("Value get from tables :", new ByteArrayInputStream(Arraylist.getBytes(StandardCharsets.UTF_8)));
        return arr;

    }








    @Step("Pass the value of text as : {0}")
    public void SeacrText(String value)
    {
        Send(Findelement(Locators.xpath,Search_loc),value);
    }

    @Step("Click Edit button")
    public void ClickEdit() {
        click(Findelement(Locators.xpath,Edit_btn));
    }



}
