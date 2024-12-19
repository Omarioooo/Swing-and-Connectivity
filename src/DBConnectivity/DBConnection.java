package DBConnectivity;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;


public class DBConnection {
    private Connection connection;


    SQLServerDataSource myDriver(){
        SQLServerDataSource driver = new SQLServerDataSource();
        driver.setURL("jdbc:sqlserver://localhost:1433;databaseName=spongebob;encrypt=true;trustServerCertificate=true");
        driver.setUser("sa");
        driver.setPassword("1234");

        return driver;

    }

    void Connect() throws SQLServerException {
        connection = (Connection) myDriver().getConnection();
    }

    void disConnect(){
        connection = null;
    }

    Connection getConnection(){
        return connection;
    }

    boolean isConnected(){
        return connection != null;
    }




    public static void main(String[] args) throws SQLServerException {
        DBConnection connection1 = new DBConnection();
        connection1.Connect();
        System.out.println(connection1.isConnected());
    }
}
