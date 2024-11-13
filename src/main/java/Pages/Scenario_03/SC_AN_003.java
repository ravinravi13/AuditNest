package Pages.Scenario_03;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.print.attribute.standard.Fidelity;
import java.util.List;

public class SC_AN_003 extends BaseClass {

    String Search_loc = "//input[@placeholder='Search Text Here']";
    String projectName_loc ="//div[@class=' secondary-color']//b";
    String preaudit_loc_1 = "(//li[@title='PreAudit']//a)[1]";
    String Preaudit_loc_2 = "//ul[@id='preauditreport']//a[1]";
    String Response_loc = "//textarea[@name='Response']";
    String save_loc = "//button[text()='Save']";
    String Edit_loc = "(//a[@class='action cursor'])[1]";
    String Unit_loc = "//select[@name='unit']";
    String date_loc ="//input[@type='date']";
    String table_loc  = "//table[@class='MuiTable-root-2051 table']";
    String ProjectNameLink_loc = "(//a[@class='action cursor'])[1]";
    String back_loc ="//button[text()='Back']";
    int c=0;
    String response;


    public void Click_PreAuditreport() {
        click(Findelement(Locators.xpath, preaudit_loc_1));
    }

    @Step("Click togglecase of PreAudit Report ")

    public void Click_PreAuditreport_2() {

        click(Findelement(Locators.xpath, Preaudit_loc_2));
    }



    @Step("Get Project from UI")
    public String GetProjectNameText(){
        String text = getText(Locators.xpath,projectName_loc);
        String arr[] = text.split(":");
         return arr[arr.length-1];
    }
    @Step("Search the text as : {0}")
    public void SearchText(String value){
        Send(Findelement(Locators.xpath,Search_loc),value);
    }

    public void ClickEdit(){
        click(Findelement(Locators.xpath,Edit_loc));
    }

    @Step("Click Save button")
    public void ClickSave(){
        click(Findelement(Locators.xpath,save_loc));
    }

    @Step("Select Unit as : {0} ")
    public void SelectUnit(String value){
        SelectVisibleText(Findelement(Locators.xpath,Unit_loc),value);
    }

    @Step("Select Date as : {0} ")
    public void Selectdate(String value)
    {
        Send(Findelement(Locators.xpath,date_loc),value);
    }


    public String Validate_response(){

      String value = getText(Locators.xpath,"//td[text()='Project name']/following-sibling::td");

        return value;
    }

    @Step("Click Project Name of first column of as SMAART ")
    public void ClickProjectName(){
        click(Findelement(Locators.xpath,ProjectNameLink_loc));
    }


    @Step("Enter Text as : {0}")
    public void SendResponse(String value){
        Send(Findelement(Locators.xpath,Response_loc),value);
    }

    @Step("Click BAck button")
    public void Click_Back()
    {
        click(Findelement(Locators.xpath,back_loc));
    }




}
