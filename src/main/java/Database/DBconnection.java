package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {


    String connection = "jdbc:sqlserver://ilinktestdata.database.windows.net:1433;"
            + "database=iLinkEMS_Audit;"
            + "user=IATadmin;"
            + "password=Ntx67qD3*0K@;";

    Connection con;

    public Connection dbconnect() {
        {
            try {
                con = DriverManager.getConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }


}
