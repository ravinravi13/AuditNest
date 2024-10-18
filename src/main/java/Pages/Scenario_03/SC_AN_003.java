package Pages.Scenario_03;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SC_AN_003 extends BaseClass {

    String Search_loc = "//input[@placeholder='Search Text Here']";
    String projectName_loc ="//div[@class=' secondary-color']//b";
    String Response_loc = "//textarea[@name='Response']";
    String save_loc = "//button[text()='Save']";
    String Edit_loc = "(//a[@class='action cursor'])[2]";
    String Unit_loc = "//select[@name='unit']";
    String date_loc ="//input[@type='date']";
    String table_loc  = "//table[@class='MuiTable-root-2051 table']";
    String ProjectNameLink_loc = "(//a[@class='action cursor'])[1]";
    int c=0;
    String response;




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

    public void ClickSave(){
        click(Findelement(Locators.xpath,save_loc));
    }

    public void SelectUnit(String value){
        SelectVisibleText(Findelement(Locators.xpath,Unit_loc),value);
    }

    public void Selectdate(String value)
    {
        Send(Findelement(Locators.xpath,date_loc),value);
    }

    public String Validate_response(){

        WebElement table = Findelement(Locators.xpath,table_loc);
        List<WebElement> allRows = table.findElements(By.cssSelector("tbody tr "));

        for(WebElement rowElement : allRows){
            List<WebElement> row = rowElement.findElements(By.tagName("td"));
            for(WebElement eachrow : row)
            {
                c++;
                 response = eachrow.getText();
                if(c==1){
                    break;
                }
            }

            }
        return response;
    }

    public void ClickProjectName(){
        click(Findelement(Locators.xpath,ProjectNameLink_loc));
    }


    public void SendResponse(String value){
        Send(Findelement(Locators.xpath,Response_loc),value);
    }



}
