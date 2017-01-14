import oracle.jdbc.OracleTypes;

import java.sql.*;

/**
 * Created by Adrian on 14.01.2017.
 */
public class CinemaDatabaseAPI {

    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; //oracle.jdbc.OracleDriver
    public static final String DB_URL = "jdbc:oracle:thin:@192.168.0.103:1521:orcl";

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
            conn = DriverManager.getConnection(DB_URL,"c##miczi", "8196897");
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

    public String showReservedPlaces(String movieTitle, String date, String hours){
        try {
            CallableStatement callableStatement = conn.prepareCall("{call SHOWRESERVEDPLACES(?,?,?,?)}");
            callableStatement.setString(1, movieTitle);
            callableStatement.setString(2, date);
            callableStatement.setString(3, hours);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet result = null;
            result = (ResultSet)callableStatement.getObject(4);


            String placeCoordinate = "";

            while(result.next()){
                placeCoordinate+=result.getString("ROW_P") + ",";
                placeCoordinate+=result.getString("NUMBER_P") + ",";
            }
            placeCoordinate = placeCoordinate.substring(0, placeCoordinate.length()-1);
            return placeCoordinate;
        } catch (SQLException e) {
            System.err.println("showReservedPlaces Error!");
            e.printStackTrace();
        }
        return "";

    }

  public void addPurchase(String filmTitle, String dataFilm, String hoursFilm, int rowNumber, int placeNumber, int isPaid, String ticketType, Integer clientID) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "{call ADDPURCHASE(?, ?, ?, ?, ?, ? ,? ,?)}");
            prepStmt.setString(1, filmTitle);
            prepStmt.setString(2, dataFilm);
            prepStmt.setString(3, hoursFilm);
            prepStmt.setInt(4, rowNumber);
            prepStmt.setInt(5, placeNumber);
            prepStmt.setInt(6, isPaid);
            prepStmt.setString(7, ticketType);
            prepStmt.setInt(8, clientID);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("addPurchase Error!");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Dublicates")
    public void addPurchase(String filmTitle, String dataFilm, String hoursFilm, int rowNumber, int placeNumber, int isPaid, String ticketType) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "{call ADDPURCHASE(?, ?, ?, ?, ?, ? ,? ,NULL)}");
            prepStmt.setString(1, filmTitle);
            prepStmt.setString(2, dataFilm);
            prepStmt.setString(3, hoursFilm);
            prepStmt.setInt(4, rowNumber);
            prepStmt.setInt(5, placeNumber);
            prepStmt.setInt(6, isPaid);
            prepStmt.setString(7, ticketType);
           // prepStmt.setObject(8, NULL);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("addPurchase Error!");
            e.printStackTrace();
        }
    }

    public String getFilms() {
        try {
            CallableStatement callableStatement = conn.prepareCall("{call SHOWMOVIE(?)}");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet result = null;
            result = (ResultSet)callableStatement.getObject(1);

            String filmy = "";

            while(result.next()){
                filmy+=result.getString("title") + ",";
            }

            return filmy;

        } catch (SQLException e) {
            System.err.println("showMovie Error!");
            e.printStackTrace();
        }
        return "";
    }

    public String getDates(String movieTitle){
        try {
            CallableStatement callableStatement = conn.prepareCall("{call SHOWDATEMOVIE(?,?)}");
            callableStatement.setString(1, movieTitle.substring(1,movieTitle.length()));
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet result = null;
            result = (ResultSet)callableStatement.getObject(2);

            String dates = "";

            while(result.next()){
                dates+=result.getString("DATE_SEANCE") + ",";
                System.out.println(dates);
            }
            return dates;
        } catch (SQLException e) {
            System.err.println("getDates Error!");
            e.printStackTrace();
        }
        return "";
    }

    public String getHours(String movieTitle, String date){
        try {
            CallableStatement callableStatement = conn.prepareCall("{call SHOWHOURSMOVIE(?,?,?)}");
            callableStatement.setString(1, movieTitle);
            callableStatement.setString(2, date);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet result = null;
            result = (ResultSet)callableStatement.getObject(3);

            String hours = "";

            while(result.next()){
                hours+=result.getString("hours") + ",";
            }
            return hours;
        } catch (SQLException e) {
            System.err.println("getHours Error!");
            e.printStackTrace();
        }
        return "";
    }




}
