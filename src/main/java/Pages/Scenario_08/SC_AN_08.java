package Pages.Scenario_08;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;

public class SC_AN_08 extends BaseClass {

    String KPI_loc  = "//a[@class='active']";
    String Add_loc = "//div[@class='control-right']//a[1]";
    String sprintName_loc  ="//input[@name='sprintName']";
    String SprintDesc_loc = "//input[@name='sprintDesc']";
    String startdate_loc  = "//input[@name='startDate']";
    String Enddate_loc = "//input[@name='endDate']";
    String Save_loc = "(//button[@type='submit'])[2]";


    @Step("Click KPI from side nav bar")
    public void ClickKPI(){
        click(Findelement(Locators.xpath,KPI_loc));
    }

    @Step("Click Add icon from right corner")
    public void ClickAdd(){
        click(Findelement(Locators.xpath,Add_loc));
    }

    @Step("Enter the Sprintname in Textbox as : {0}")
    public void EnterSprintName(String value){
        Send(Findelement(Locators.xpath,sprintName_loc),value);
    }
    @Step("Enter the Sprintname description in Textbox as : {0}")
    public void EnterSprintNamedesc(String value){
        Send(Findelement(Locators.xpath,SprintDesc_loc),value);
    }

    @Step("Enter the Start date in date picker as : {0}")
    public void startdate(String value){
        Send(Findelement(Locators.xpath,startdate_loc),value);
    }
    @Step("Enter the End date in date picker as : {0}")
    public void Enddate(String value){
        Send(Findelement(Locators.xpath,Enddate_loc),value);
    }

    @Step("Click Save button")
    public void Click_Save(){
        click(Findelement(Locators.xpath,Save_loc));
    }





}
