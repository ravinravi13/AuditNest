package Pages.Scenario_01;

import Base.BaseClass;
import Utilities.Locators;
import io.qameta.allure.Step;

public class SC_AN_001 extends BaseClass {

    String Welcome_text_loc ="//h3[text()='Welcome To Audit Nest']";


    @Step("Get the text from Application ")
    public String Verify_Text()
    {
        String expected = getText(Locators.xpath,Welcome_text_loc);
        return expected;
    }



}
