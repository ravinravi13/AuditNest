package TestCase.Scenario_05;

import Base.BaseClass;
import Pages.Scenario_04.SC_AN_004;
import Pages.Scenarrio_05.SC_AN_005;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class Scenario_05 extends BaseClass {

    SC_AN_004 obj_SC_AN_004 = new SC_AN_004();
    SC_AN_005 obj_SC_AN_005 = new SC_AN_005();




    @Test
    public void TC_AN_001() throws SQLException {

//        obj_SC_AN_004.ClickAuditReport();
//        obj_SC_AN_004.ClickAuditReport1();
//        obj_SC_AN_005.Click_ProjectName();
//        String actucal = obj_SC_AN_005.Click_getPercentage();
        obj_SC_AN_005.find_Compliance();


    }



}
