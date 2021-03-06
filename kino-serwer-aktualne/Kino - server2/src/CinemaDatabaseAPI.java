import oracle.jdbc.OracleTypes;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("Duplicates")
    public String showRepertoireInHall(String date, String numOfHall){
        try {
            CallableStatement callableStatement = conn.prepareCall("{call WRITEMOVIEINHALL(?,?,?)}");
            callableStatement.setInt(1, Integer.parseInt(numOfHall));
            callableStatement.setString(2, date);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();

            Map<String, String> map = new HashMap<>();
            ResultSet resultSet = null;
            resultSet = (ResultSet)callableStatement.getObject(3);



            String result = "";

            while(resultSet.next()){
               result += resultSet.getString("title")+","+resultSet.getString("hours") + ";";
            }

            String [] tab = result.split(";");
            String [] tempHours;
            String resultFromMap = "";

            for(int i = 0; i < tab.length; i++ ){
                tempHours = tab[i].split(",");
                if(map.containsKey(tempHours[0])){
                    resultFromMap += map.get(tempHours[0]) + ", " +  tempHours[1].substring(0,2)+":"+tempHours[1].substring(2,4);
                    map.put(tempHours[0], resultFromMap);
                }
                else{
                    map.put(tempHours[0], tempHours[1].substring(0,2)+":"+tempHours[1].substring(2,4));
                    resultFromMap = "";
                }
            }
            result = "";
            for(String title : map.keySet()){
                result += title + ":  " + map.get(title) + ";";
            }

            return result;
        } catch (SQLException e) {
            System.err.println("showReservedPlaces Error!");
            e.printStackTrace();
        }
        return "blad";
    }


    public String removeFilmFromRepertoire(String title){
        int r=0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE MOVIE SET PLAY = 0 WHERE TITLE =\'" + title + "\'");
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("updatePriceCost Error!");
            e.printStackTrace();
        }

            return "Movie deleted";

    }


    public String updatePriceCost(String type, int newCost){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("{call UPDATEPRICE(?,?)}");
            preparedStatement.setString(1, type);
            preparedStatement.setInt(2, newCost);
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("updatePriceCost Error!");
            e.printStackTrace();
        }
        return "Price updated";
    }


    public void addMovie(String title, int duration, int yearOfProduction, String director, String typeOfMovie, int ageLimit, int isPlay){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("{CALL addMovie(?,?,?,?,?,?,?)}");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, duration);
            preparedStatement.setInt(3, yearOfProduction);
            preparedStatement.setString(4, director);
            preparedStatement.setString(5, typeOfMovie);
            preparedStatement.setInt(6, ageLimit);
            preparedStatement.setInt(7, isPlay);
            preparedStatement.execute();

        } catch (SQLException e){
            System.err.println("addMovie Error!");
            e.printStackTrace();
        }
    }


    public String showTypesOfPrice(){

        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT type_p from PRICE");
            ResultSet resultSet = preparedStatement.executeQuery();
            String prices = "";

            while(resultSet.next()){
                prices+=resultSet.getString("type_p") + ",";
            }

            return prices;

        }catch (SQLException e){
            System.out.println("showTypesOfPrice Error!");
            e.printStackTrace();
        }
        return "blad";
    }


    public String viewership(String title){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT count(*) from Purchase join SEANCE on PUrchase.seance_id = SEANCE.seance_id where title =\'"+title+ "\'and PURCHASE.is_paid = 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int audiece = resultSet.getInt(1);

            return String.valueOf(audiece);

        }catch (SQLException e){
            System.out.println("viewership Error!");
            e.printStackTrace();
        }
        return "blad";
    }


    public void addSeance(String title, int hallId, String dataSeance, String hours){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("{CALL addSeance(?,?,?,?)}");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, hallId);
            preparedStatement.setString(3, dataSeance);
            preparedStatement.setString(4, hours);

            preparedStatement.execute();

        } catch (SQLException e){
            System.err.println("addSeance Error!");
            e.printStackTrace();
        }
    }

    public String checkEmployee(String lastname){
        try{
            System.out.println(lastname);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from EMPLOYEE WHERE EMPLOYEE.SECOND_NAME=\'"+lastname+"\'");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            Long pesel = resultSet.getLong(1);
            String branchName = resultSet.getString(2);
            String firstname = resultSet.getString(3);
            String secondName = resultSet.getString(4);
            String position = resultSet.getString(5);
            String email = resultSet.getString(6);
            String address = resultSet.getString(7);
            int salary = resultSet.getInt(8);
            String start_date = resultSet.getString(9);

            return String.valueOf(pesel)+","+branchName+","+firstname+","+secondName+","+position+","+email+","+address+","+String.valueOf(salary)+","+start_date;

        }catch (SQLException e){
            System.out.println("checkEmployee Error!");
            e.printStackTrace();
        }
        return "blad";
    }

    public void removeEmployee(String firstName, String lastName, String position){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("{call removeEmployee(?,?,?)}");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, position);
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("removeEmployee Error!");
            e.printStackTrace();
        }
    }

    public void addEmployee(long pesel, String branchName, String firstName, String secondName, String position, String email, String address, int salary, String startDate){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("{CALL addEmployee(?,?,?,?,?,?,?,?,?)}");
            preparedStatement.setLong(1, pesel);
            preparedStatement.setString(2, branchName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, secondName);
            preparedStatement.setString(5, position);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, address);
            preparedStatement.setInt(8, salary);
            preparedStatement.setString(9, startDate);
            preparedStatement.execute();

        } catch (SQLException e){
            System.err.println("addEmployee Error!");
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

    public void removeTicket(String idT){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM TICKET WHERE TICKET_ID = " + idT);
            preparedStatement.executeQuery();

        }catch (SQLException e){
            System.out.println("removeTicket Error!");
            e.printStackTrace();
        }
    }

    public void removePurchase(String idP){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM PURCHASE WHERE PURCHASE_ID = " + idP);
            preparedStatement.executeQuery();

        }catch (SQLException e){
            System.out.println("removePurchase Error!");
            e.printStackTrace();
        }
    }

    public String takePurchaseIdFromTicketId(String idT){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT PURCHASE_ID FROM TICKET WHERE TICKET_ID = " + idT);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String temp =  String.valueOf(resultSet.getInt(1));

            return temp;
        }catch (SQLException e){
            System.out.println("takePurchaseIdFromTicketId Error!");
            e.printStackTrace();
        }
        return "blad";
    }



    public String showRepertoire(String date){
        try{
            String sqll = "select m.title, s.hours from seance s join movie m on s.title = m.title where date_seance =" + date + "and m.play = 1";

            PreparedStatement preparedStatement = conn.prepareStatement(sqll);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<String, String> repertouireMap = new HashMap<>();
            String tempHours = "";
            String repertoire = "";

            while(resultSet.next()){
                repertoire += resultSet.getString("title") + "," + resultSet.getString("hours") + ";";
            }

            String [] repertoireTab = repertoire.split(";");
            String [] temp;

            for(int i = 0; i < repertoireTab.length; i++ ){
                temp = repertoireTab[i].split(",");
                if(repertouireMap.containsKey(temp[0])){
                    tempHours += repertouireMap.get(temp[0]) + ", " +  temp[1].substring(0,2)+":"+temp[1].substring(2,4);
                    repertouireMap.put(temp[0], tempHours);
                }
                else{
                    repertouireMap.put(temp[0], temp[1].substring(0,2)+":"+temp[1].substring(2,4));
                    tempHours = "";
                }
            }
            repertoire = "";
            for(String title : repertouireMap.keySet()){
                repertoire += title + ":  " + repertouireMap.get(title) + ";";
            }

            return repertoire;
        }catch (SQLException e){
            System.out.println("takePurchaseIdFromTicketId Error!");
            e.printStackTrace();
        }
        return "blad";
    }

    public String checkISPAID(String idP){
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT PURCHASE.IS_PAID FROM PURCHASE WHERE PURCHASE.PURCHASE_ID = " + idP);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String temp =  String.valueOf(resultSet.getInt(1));

            return temp;
        }catch (SQLException e){
            System.out.println("checkISPAID Error!");
            e.printStackTrace();
        }
        return "blad";
    }
}
