package Pages.Scenario_06;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;

import java.util.ArrayList;

public class SC_AN_006 extends BaseClass {


    String OM_sprint_loc = "//a[contains(.,'OM Metrics')]";
    String OM_sprint_loc1 ="(//ul[@id='omauditstoggle']//a)[1]";
    String Add_loc ="//div[@class='control-right']//a[1]";
    String Sprintname_loc ="//input[@name ='sprintName']";
    String startdate_loc ="//input[@name ='startDate']";
    String enddate_loc ="//input[@name ='endDate']";
    String save_loc = "(//button[@type='submit'])[2]";
    String ProjectNameLink_loc = "(//a[@class='action cursor'])[1]";
    String Back_btn_loc ="//div[@class='control-right']//a[1]";


    @Step("Click OM Sprint from side nav bar")
    public void ClickOMSprint(){
        click(Findelement(Locators.xpath,OM_sprint_loc));
    }
    @Step("Click the first sub-category of OM sprint")
    public void ClikOMSprint1(){
        click(Findelement(Locators.xpath,OM_sprint_loc1));
    }

    @Step("Click the Add sprint from right corner")
    public void ClickAddSprint(){
        click(Findelement(Locators.xpath,Add_loc));
    }

    @Step("Enter the SprintName in text field as : {0}")
    public void EnterSprintName(String value){
        Send(Findelement(Locators.xpath,Sprintname_loc),value);
    }

    @Step("Enter the Start date in Date picker as : {0}")
    public void EnterStartDate(String value){
        Send(Findelement(Locators.xpath,startdate_loc),value);
    }
    @Step("Enter the End date in Date picker as : {0}")
    public void EnterEndDate(String value){
        Send(Findelement(Locators.xpath,enddate_loc),value);
    }

    @Step("Click save button")
    public void Cick_Save(){
        click(Findelement(Locators.xpath,save_loc));
    }

    @Step("Click the ProjectName it shows as a link")
    public void Click_ProjectName(){
        click(Findelement(Locators.xpath,ProjectNameLink_loc));
    }

    @Step("Get the Value table UI")
    public ArrayList<String> Sprint01(){
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("Sprint-01");
        arr.add("2024-10-29");
        arr.add("2024-11-04");
        arr.add("DATA");
        return arr;
    }

    @Step("Click Back button right corner")
    public void ClickBack(){
        click(Findelement(Locators.xpath,Back_btn_loc));
    }



}





