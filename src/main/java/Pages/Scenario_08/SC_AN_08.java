package Pages.Scenario_08;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;

public class SC_AN_08 extends BaseClass {

    String BEF_loc ="(//li[@title='FunctionMaintenance']//a)[1]";
    String BEF_loc1  = "BEF";
    String Add_loc = "//div[@class='control-right']//a[1]";
    String FunctionType_loc  ="//select[@name='functionType']";
    String AuditStatus_loc = "//select[@name='auditStatus']";
    String Auditdate_loc  = "//input[@name='auditDate']";
    String Checklist_loc = "//select[@name='checkList']";
    String Save_loc = "(//button[@type='submit'])[2]";
    String Back_loc ="Back";


    @Step("Click BEF from side nav bar")
    public void ClickBEF(){
        click(Findelement(Locators.xpath,BEF_loc));
    }

    @Step("Click BEF Sub-Category of Business Enabler function")
    public void ClickBEF1(){
        click(Findelement(Locators.link,BEF_loc1));
    }

    @Step("Click Add icon from right corner")
    public void ClickAdd(){
        click(Findelement(Locators.xpath,Add_loc));
    }

    @Step("Select the FunctionType  in Dropdown as : {0}")
    public void SelectFunctionType(String value){
       SelectVisibleText(Findelement(Locators.xpath,FunctionType_loc),value);
    }
    @Step("Select the AuditStatus description in Textbox as : {0}")
    public void SelectAuditStatus(String value){
     SelectVisibleText(Findelement(Locators.xpath,AuditStatus_loc),value);
    }

    @Step("Enter the Auditdate date in date picker as : {0}")
    public void Audittdate(String value){
        Send(Findelement(Locators.xpath,Auditdate_loc),value);
    }
    @Step("Select CheckList in dropdown as : {0}")
    public void CheckList(String value){
        SelectVisibleText(Findelement(Locators.xpath,Checklist_loc),value);
    }

    @Step("Click Save button")
    public void Click_Save(){
        click(Findelement(Locators.xpath,Save_loc));
    }
    @Step("Click back button")
    public void Click_back(){
        click(Findelement(Locators.link,Back_loc));
    }





}
