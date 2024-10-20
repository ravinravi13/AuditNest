package Pages.Scenario_04;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SC_AN_004 extends BaseClass {

    String Audit_loc1 = "(//li[@title='Audit Maintenance']//a)[1]";
    String Audit_loc2 = "//a[@href='/auditList']";
    String Add_loc = "//span[@class='glyphicon glyphicon-plus center']";
    String AccountName_loc ="//select[@name='businessName']";
    String ProjectName_loc = "//select[@name='projectName']";
    String Unit_loc = "//select[@name='unit']";
    String Auditdate = "//input[@name='auditDate']";
    String save_loc = "//button[text()='Save']";
    String cancel_loc ="//button[text()='Cancel']";
    String Search_loc = "//input[@placeholder='Search Text Here']";
    String Auditby_loc = "//input[@name='auditBy']";
    String  AuditStatus = "//select[@name='overallStatus']";
    String Auditee_loc ="//div[@role='button']";
    String ProjectType_loc = "//select[@name='projectType']";
    String ProjectHandling_loc = "//select[@name='category']";
    String ProjectMatrix_loc = "//select[@name='projectmatrix']";
    String OMAapplication_loc ="//select[@name='OMApplicable']";
    String checklist_loc = "//select[@name='checkList']";
    String table_loc = "//table[@class='MuiTable-root-2442 table']";
    String Edit_btn = "(//a[@class='action cursor'])[2]";
    String Delete_loc = "//*[@id=\"react-app\"]/div/div[2]/div/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[15]/a[3]";

    int c=0;
    List arr;


    @Step("Click the AuditReport navigation bar")
    public void ClickAuditReport() {
        click(Findelement(Locators.xpath, Audit_loc1));
    }


    @Step("Click the sub category AuditReport")
    public void ClickAuditReport1() {
        click(Findelement(Locators.xpath, Audit_loc2));
    }

    @Step("Click Add new Audit report icon from right corner")
    public void Click_AddIcon() {
        click(Findelement(Locators.xpath, Add_loc));
    }

    @Step("Select Account as : {0}")
    public void SelectAccountName(String value){
        SelectVisibleText(Findelement(Locators.xpath,AccountName_loc),value);
    }

    @Step("Select ProjectName as : {0}")
    public void SelectProjectName(String value){
        SelectVisibleText(Findelement(Locators.xpath,ProjectName_loc),value);
    }

    @Step("Enter the Auditor mail id as : {0}")
    public void EnterAuditor(String value){
        Send(Findelement(Locators.xpath,Auditby_loc),value);
    }

    @Step("Select Date as : {0}")
    public void SelectDate(String value){
        Send(Findelement(Locators.xpath,Auditdate),value);
    }
    @Step("Select Auditee as : {0}")
    public void SelectAuitee(String value){
        click(Findelement(Locators.xpath,Auditee_loc));
        click(Findelement(Locators.xpath,""));
    }

    @Step("Select Audit Status as : {0}")
    public void SelectAuditStatus(String value){
        SelectVisibleText(Findelement(Locators.xpath,AuditStatus),value);
    }

    @Step("Select projectType as : {0}")
    public void SelectProjectType(String value){
        SelectVisibleText(Findelement(Locators.xpath,ProjectType_loc),value);
    }

    @Step("Select Project Handling as : {0}")
    public void Select_PojectHanding(String value){
        SelectVisibleText(Findelement(Locators.xpath,ProjectHandling_loc),value);
    }

    @Step("Select Unit as : {0}")
    public void Select_Unit(String Unit) {

        SelectVisibleText(Findelement(Locators.xpath,Unit_loc),Unit);
    }
    @Step("Select Project Matrix as : {0}")
    public void Select_ProjecMatrix(String value) {

        SelectVisibleText(Findelement(Locators.xpath,ProjectMatrix_loc),value);
    }

    @Step("Select OMA appliaction as : {0}")
    public void Select_OMAapplication(String value) {

        SelectVisibleText(Findelement(Locators.xpath,OMAapplication_loc),value);
    }
    @Step("Select Checklist  as : {0}")
    public void Select_Checklist(String value) {
        SelectVisibleText(Findelement(Locators.xpath,checklist_loc),value);
    }

    @Step("Click Save button")
    public void Click_Save()
    {
        click(Findelement(Locators.xpath,save_loc));

    }
    @Step("Pass the value of text as : {0}")
    public void SeacrText(String value)
    {
        Send(Findelement(Locators.xpath,Search_loc),value);
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

                if(c<=5) {
                    String value = eachrow.getText();
                    arr.add(value);
                    c++;
                }
                if(c==5)
                {
                    break;
                }

            }

        }
        String Arraylist = arr.toString();

        Allure.addAttachment("Value get from tables :", new ByteArrayInputStream(Arraylist.getBytes(StandardCharsets.UTF_8)));
        return arr;

    }

    @Step("Click Edit button")
    public void ClickEdit() {
        click(Findelement(Locators.xpath,Edit_btn));
    }

    @Step("Click Delete button")
    public void ClickDelete() {
        click(Findelement(Locators.xpath,Delete_loc));
    }






}
