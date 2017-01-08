import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by SG0226566 on 2017-01-08.
 */
public class CinemaDatabaseAPI {

    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; //oracle.jdbc.OracleDriver
    public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";

    private Connection conn;
    private Statement stat;

    public void openDatabaseConnection(){
        try {
            Class.forName(DRIVER).newInstance();
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC0");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("Brak sterownika JDBC1");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.err.println("Brak sterownika JDBC2");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL,"", "");
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public void closeDatabaseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }

}
