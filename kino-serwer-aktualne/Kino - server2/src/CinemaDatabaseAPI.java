import oracle.jdbc.OracleTypes;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;

/**
 * Created by Adrian on 14.01.2017.
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
            if(placeCoordinate.length() > 0){
                placeCoordinate = placeCoordinate.substring(0, placeCoordinate.length()-1);
            }
            else{
                placeCoordinate = "-1,";
            }

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

    public void addClient(String firstname, String lastname){
        try{

            PreparedStatement preparedStatement = conn.prepareStatement("{CALL addClient(?,?)}");
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.execute();

        } catch (SQLException e){
            System.err.println("addClient Error!");
            e.printStackTrace();
        }
    }


    public String showPrices(){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from PRICE");
            ResultSet resultSet = preparedStatement.executeQuery();

            String prices = "";

            while(resultSet.next()){
                prices += resultSet.getString(1)+","+String.valueOf(resultSet.getInt(2))+",";
            }
            return prices;
        }catch (SQLException e){
            System.out.println("showPrices Error!");
            e.printStackTrace();
        }
        return "";
    }

    public String showPurchaseID(){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT purchase.purchase_id from purchase where purchase_id = (select max(purchase_id) from purchase)");
            ResultSet resultSet = preparedStatement.executeQuery();

            String purchaseId = "";

            while(resultSet.next()){
                purchaseId += String.valueOf(resultSet.getInt(1));
            }
            return purchaseId;
        }catch (SQLException e){
            System.out.println("showPurchaseID Error!");
            e.printStackTrace();
        }
        return "";
    }

    public String showTicketNumber(){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT ticket.ticket_id from ticket where ticket_id = (select max(ticket_id) from ticket)");
            ResultSet resultSet = preparedStatement.executeQuery();

            String ticketId = "";

            while(resultSet.next()){
                ticketId += String.valueOf(resultSet.getInt(1));
            }
            return ticketId;
        }catch (SQLException e){
            System.out.println("showTicketNumber Error!");
            e.printStackTrace();
        }
        return "";
    }

    public void addTicket(String purchaseId){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("call addTicket(?)");
            preparedStatement.setString(1, purchaseId);
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("addTicket Error!");
            e.printStackTrace();
        }
    }
}
